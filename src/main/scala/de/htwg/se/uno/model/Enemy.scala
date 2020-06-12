package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer

case class Enemy() {
  var enemyCards = new ListBuffer[Card]()

  def pushCardEnemy(card: Card, game: Game) : Enemy = {
    var c = 0
    for (i <- 2 to enemyCards.length) {
      if (enemyCards(i - 2).color == card.color && enemyCards(i - 2).value == card.value && c == 0) {
        game.cardsRevealed = enemyCards(i - 2) +: game.cardsRevealed
        enemyCards = enemyCards.take(i - 2) ++ enemyCards.drop(i-1)
        c += 1
      }
    }
    if (c == 0) {
      game.cardsRevealed = enemyCards(enemyCards.length - 1) +: game.cardsRevealed
      enemyCards = enemyCards.take(enemyCards.length - 1)
    }
    this
  }

  def pullEnemy(game: Game) : Enemy = {
    enemyCards += Card(game.cardsCovered.head.color, game.cardsCovered.head.value)
    game.cardsCovered = game.cardsCovered.drop(1)
    this
  }

  def enemy(game: Game) : Enemy = {
    for (i <- 1 to enemyCards.length) {
      if(pushableEnemy(enemyCards(i-1), game)) {
        return pushCardEnemy(enemyCards(i-1), game)
      }
    }
    pullEnemy(game)
  }

  def pushableEnemy(card: Card, game: Game) : Boolean = {
    if (card.value == Value.PlusFour) {
      for (i <- 1 to enemyCards.length) {
        if (enemyCards(i - 1).color == game.cardsRevealed.head.color) {
          return false
        }
      }
      return true
    } else if (card.color == game.cardsRevealed.head.color || card.value == game.cardsRevealed.head.value
      || card.value == Value.ColorChange || game.cardsRevealed.head.color == Color.Schwarz) {
      return true
    }
    false
  }
}

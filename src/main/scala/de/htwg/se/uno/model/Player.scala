package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer

class Player() {
  var handCards = new ListBuffer[Card]()

  def pushCard(card: Card, game: Game) : Player = {
    var c = 0
    for (i <- 2 to handCards.length) {
      if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
        game.cardsRevealed = handCards(i - 2) +: game.cardsRevealed
        handCards = handCards.take(i - 2) ++ handCards.drop(i-1)
        c += 1
      }
    }
    if (c == 0) {
      game.cardsRevealed = handCards(handCards.length-1) +: game.cardsRevealed
      handCards = handCards.take(handCards.length - 1)
    }
    this
  }

  def pushable(card: Card, game: Game) : Boolean = {
    if (card.value == Value.PlusFour) {
      for (i <- 1 to handCards.length) {
        if (handCards(i - 1).color == game.cardsRevealed.head.color) {
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

  def pullable(game: Game) : Boolean = {
    for (i <- 1 to handCards.length) {
      if(pushable(handCards(i-1), game)) {
        return false
      }
    }
    true
  }

  def pull(game: Game) : Player = {
    handCards += Card(game.cardsCovered.head.color, game.cardsCovered.head.value)
    game.cardsCovered = game.cardsCovered.drop(1)
    this
  }

  def equalsCard(s: String): Boolean = {
    for (i <- 1 to handCards.length) {
      if (s.equals(handCards(i - 1).toString)) {
        return true
      }
    }
    false
  }

  def getCard(s: String): Card = {
    var c = 0
    for (i <- 1 to handCards.length) {
      if (s.equals(handCards(i - 1).toString)) {
        c = i - 1
      }
    }
    handCards(c)
  }
}

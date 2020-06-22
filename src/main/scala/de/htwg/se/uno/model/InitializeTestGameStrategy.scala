package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer

class InitializeTestGameStrategy extends InitializeGameStrategy {
  override def initializeGame(numOfPlayerCards: Int = 7): InitializeTestGameStrategy = {
    cardsCovered = new ListBuffer[Card]()
    cardsRevealed = new ListBuffer[Card]()
    enemy.enemyCards = new ListBuffer[Card]()
    player.handCards = new ListBuffer[Card]()
    var cards = new ListBuffer[Card]()
    for (color <- Color.values) {
      for (value <- Value.values) {
        if (value == Value.Zero && color != Color.Schwarz) {
          cards += Card(color, value)
        } else if (color == Color.Schwarz && (value == Value.ColorChange || value == Value.PlusFour)) {
          for (_ <- 0 to 3)
            cards += Card(color, value)
        } else if (color != Color.Schwarz && (value != Value.PlusFour && value != Value.ColorChange)) {
          for (_ <- 0 to 1)
            cards += Card(color, value)
        }
      }
    }
    for (_ <- 1 to 108) {
      cardsCovered = cardsCovered :+ cards(0)
      cards = cards.take(0) ++ cards.drop(1)
    }

    cardsRevealed = cardsRevealed :+ cardsCovered(0)
    cardsCovered = cardsCovered.drop(1)
    player.handCards = player.handCards :+ cardsCovered(0)
    cardsCovered = cardsCovered.drop(1)
    enemy.enemyCards = enemy.enemyCards :+ cardsCovered(0)
    cardsCovered = cardsCovered.drop(1)
    player.handCards = player.handCards :+ cardsCovered(23)
    cardsCovered = cardsCovered.take(23) ++ cardsCovered.drop(24)
    enemy.enemyCards = enemy.enemyCards :+ cardsCovered(23)
    cardsCovered = cardsCovered.take(23) ++ cardsCovered.drop(24)
    player.handCards = player.handCards :+ cardsCovered(23)
    cardsCovered = cardsCovered.take(23) ++ cardsCovered.drop(24)
    enemy.enemyCards = enemy.enemyCards :+ cardsCovered(23)
    cardsCovered = cardsCovered.take(23) ++ cardsCovered.drop(24)
    player.handCards = player.handCards :+ cardsCovered(99)
    cardsCovered = cardsCovered.take(99) ++ cardsCovered.drop(100)
    enemy.enemyCards = enemy.enemyCards :+ cardsCovered(99)
    cardsCovered = cardsCovered.take(99)
    player.handCards = player.handCards :+ cardsCovered(95)
    cardsCovered = cardsCovered.take(95) ++ cardsCovered.drop(96)
    enemy.enemyCards = enemy.enemyCards :+ cardsCovered(95)
    cardsCovered = cardsCovered.take(95) ++ cardsCovered.drop(96)

    this
  }
}

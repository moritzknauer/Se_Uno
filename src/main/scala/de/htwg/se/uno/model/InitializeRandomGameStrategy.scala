package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer

class InitializeRandomGameStrategy extends InitializeGameStrategy {
  override def initializeGame(numOfPlayerCards: Int): InitializeRandomGameStrategy = {
      var cards = new ListBuffer[Card]()
    /*
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
      }*/

      for (i <- 0 to 53) {
        if (i == 0 || i == 13 || i == 26 || i == 39) {
          cards += Card(i)
        } else if (i == 52 || i == 53) {
          for (_ <- 0 to 3) cards += Card(i)
        } else {
          for (_ <- 0 to 1) cards += Card(i)
        }
      }

      var n = 108
      for (_ <- 0 to 107) {
        val r = new scala.util.Random
        val p = 1 + r.nextInt(n)
        cardsCovered = cards(p - 1) +: cardsCovered
        cards = cards.take(p - 1) ++ cards.drop(p)
        n -= 1
      }
      for (i <- 1 to numOfPlayerCards) {
        player.handCards = cardsCovered(0) +: player.handCards
        cardsCovered = cardsCovered.drop(1)
        enemy.enemyCards = cardsCovered(0) +: enemy.enemyCards
        cardsCovered = cardsCovered.drop(1)
      }
      cardsRevealed = cardsCovered(0) +: cardsRevealed
      cardsCovered = cardsCovered.drop(1)
      this
  }
}

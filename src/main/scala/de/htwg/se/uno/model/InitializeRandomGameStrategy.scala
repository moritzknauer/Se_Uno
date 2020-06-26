package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer

class InitializeRandomGameStrategy extends InitializeGameStrategy {
  override def initializeGame(numOfPlayerCards: Int = 7): InitializeRandomGameStrategy = {
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

    player.stack1.popAll()
    player.stack1.push("Start")
    player.stack2.popAll()
    player.stack2.push(-1)
    enemy.stack1.popAll()
    enemy.stack1.push(" ")
    enemy.stack2.popAll()
    enemy.stack2.push(-1)

    this
  }
}

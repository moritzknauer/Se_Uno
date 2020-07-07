package de.htwg.se.uno.model.gameComponent.gameBaseImpl

import scala.collection.mutable.ListBuffer

class InitializeRandomGameStrategy extends InitializeGameStrategy {
  override def initializeGame(numOfPlayers: Int): InitializeRandomGameStrategy = {
    cardsCovered = new ListBuffer[Card]()
    cardsRevealed = new ListBuffer[Card]()
    enemy.enemyCards = new ListBuffer[Card]()
    player.handCards = new ListBuffer[Card]()
    enemy2.enemyCards = new ListBuffer[Card]()
    enemy3.enemyCards = new ListBuffer[Card]()
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
    for (i <- 1 to 7) {
      player.handCards = cardsCovered.head +: player.handCards
      cardsCovered = cardsCovered.drop(1)
      enemy.enemyCards = cardsCovered.head +: enemy.enemyCards
      cardsCovered = cardsCovered.drop(1)
      if(numOfPlayers >= 3) {
        enemy2.enemyCards = cardsCovered.head +: enemy2.enemyCards
        cardsCovered = cardsCovered.drop(1)
        if (numOfPlayers == 4) {
          enemy3.enemyCards = cardsCovered.head +: enemy3.enemyCards
          cardsCovered = cardsCovered.drop(1)
        }
      }
    }
    cardsRevealed = cardsCovered(0) +: cardsRevealed
    cardsCovered = cardsCovered.drop(1)

    player.stack1.popAll()
    player.stack1.push("Start")
    player.stack2.popAll()
    player.stack2.push(-1)
    player.stack3.popAll()
    player.stack4.popAll()
    enemy.stack1.popAll()
    enemy.stack1.push("Start")
    enemy.stack2.popAll()
    enemy.stack2.push(-1)
    enemy.stack3.popAll()
    enemy.stack4.popAll()
    enemy2.stack1.popAll()
    enemy2.stack1.push("Start")
    enemy2.stack2.popAll()
    enemy2.stack2.push(-1)
    enemy2.stack3.popAll()
    enemy2.stack4.popAll()
    enemy3.stack1.popAll()
    enemy3.stack1.push("Start")
    enemy3.stack2.popAll()
    enemy3.stack2.push(-1)
    enemy3.stack3.popAll()
    enemy3.stack4.popAll()

    this
  }
}

package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer

trait InitializeGameStrategy {
  var cardsCovered = new ListBuffer[Card]()
  var cardsRevealed = new ListBuffer[Card]()
  var player = new Player
  var enemy = new Enemy

  def initializeGame(numOfPlayerCards: Int): InitializeGameStrategy
}

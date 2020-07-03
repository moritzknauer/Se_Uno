package de.htwg.se.uno.model.gameComponent

import de.htwg.se.uno.model.gameComponent.gameBaseImpl.{Game, InitializeGameStrategy}

trait GameInterface {
  def toString: String
  def createTestGame() : Game
  def createRandomGame(numOfCards : Int = 7) : Game
}

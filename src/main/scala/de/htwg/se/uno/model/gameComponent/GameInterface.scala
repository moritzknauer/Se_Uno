package de.htwg.se.uno.model.gameComponent

import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game

import scala.swing.Color

trait GameInterface {
  def toString: String
  def createTestGame() : Game
  def enemy() : Game
  def enemyUndo() : Game
  def pullMove() : Game
  def playerUndo() : Game
  def pushMove(string : String, color : Int) : Game
  def getLength(list:Integer) : Int
  def getCardText(list : Int, index : Int) : String
  def getGuiCardText(list : Int, index : Int) : String
  def getNumOfPlayers() : Int
  def createGame() : Game
  def enemyUndo2() : Game
  def enemyUndo3() : Game
  def enemy2() : Game
  def enemy3() : Game
  def nextTurn() : Boolean
  def nextEnemy() : Int
  def setActivePlayer() : Game
  def setDirection() : Game
  def getActivePlayer() : Int
  def getDirection() : Boolean
  def getColor() : Color
  def getAnotherPull() : Boolean
  def setAnotherPull(b : Boolean = false) : Game
  def getHv() : Boolean
  def setHv(b : Boolean = true) : Game
  def getHv2() : Boolean
  def getHv3() : Boolean
}

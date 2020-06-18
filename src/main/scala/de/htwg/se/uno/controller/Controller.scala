package de.htwg.se.uno.controller

import de.htwg.se.uno.model.Game
import de.htwg.se.uno.util.{Observable, UndoManager}


class Controller(var game:Game) extends Observable {
  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager

  def createGame(size: Int = 7):Unit = {
    game = Game(size)
    notifyObservers
  }

  def gameToString: String = game.toString

  def set(string:String): Unit = {
    val s = string.substring(2)
    undoManager.doStep(new SetCommand(s, this))
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }
}

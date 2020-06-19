package de.htwg.se.uno.controller

import de.htwg.se.uno.controller.GameStatus.GameStatus
import de.htwg.se.uno.model.Game
import de.htwg.se.uno.util.{Observable, State, UndoManager, enemyTurnEvent, pullCardNotAllowedEvent, pushCardNotAllowedEvent}


class Controller(var game:Game) extends Observable {
  var gameStatus: GameStatus = GameStatus.IDLE
  private val undoManager = new UndoManager

  def createGame(size: Int = 7):Unit = {
    game = Game(size)
    notifyObservers
  }

  def gameToString: String = game.toString

  def set(string:String): Unit = {
    val s = gameToString
    undoManager.doStep(new PushCommand(string, this))
    if(!s.equals(gameToString)) {
      notifyObservers
      State.handle(enemyTurnEvent())
      enemy()
    } else {
      State.handle(pushCardNotAllowedEvent())
    }
  }

  def get(): Unit = {
    val s = gameToString
    undoManager.doStep(new PullCommand(this))
    if(!s.equals(gameToString)) {
      notifyObservers
      State.handle(enemyTurnEvent())
      enemy()
    } else {
      State.handle(pullCardNotAllowedEvent())
    }
  }

  def enemy(): Unit = {
    undoManager.doStep(new EnemyCommand(this))
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

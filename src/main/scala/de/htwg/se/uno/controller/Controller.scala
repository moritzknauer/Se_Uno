package de.htwg.se.uno.controller

import de.htwg.se.uno.model.Game
import de.htwg.se.uno.util.{Observable, UndoManager}


class Controller(var game:Game) extends Observable {
  private val undoManager = new UndoManager

  def createGame(size: Int = 7):Unit = {
    game = Game(size)
    notifyObservers
  }

  def gameToString: String = game.toString

  def pushable(s: String):Boolean = {
    if (game.player.equalsCard(s)) {
      val card = game.player.getCard(s)
      game.player.pushable(card, game)
    } else {
      false
    }
  }

  def pushCard(s: String):Unit = {
    val card = game.player.getCard(s)
    undoManager.doStep(new PushCardCommand(card, game))
    notifyObservers
  }

  def pullable():Boolean = {
    game.player.pullable(game)
  }

  def pull():Unit = {
    undoManager.doStep(new PullCommand(game))
    notifyObservers
  }

  def enemy():Unit = {
    undoManager.doStep(new EnemyCommand(game))
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

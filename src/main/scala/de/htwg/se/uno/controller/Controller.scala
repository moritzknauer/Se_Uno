package de.htwg.se.uno.controller

import scala.swing.Publisher
import de.htwg.se.uno.model.{Game, InitializeTestGameStrategy}
import de.htwg.se.uno.util.{State, UndoManager, enemyTurnEvent, idleEvent, lostEvent, notPushableEvent, pullCardNotAllowedEvent, pushCardNotAllowedEvent, pushableEvent, wonEvent}


class Controller(var game:Game) extends Publisher {
  private val undoManager = new UndoManager

  def createGame(size: Int = 7):Unit = {
    game = Game(size)
    publish(new GameSizeChanged(size))
  }

  def createTestGame():Unit = {
    game = Game()
    game.init = new InitializeTestGameStrategy
    game.init = game.init.initializeGame()
    publish(new GameSizeChanged())
  }

  def gameToString: String = game.toString

  def set(string:String): Unit = {
    val s = gameToString
    undoManager.doStep(new PushCommand(string, this))
    if(!s.equals(gameToString)) {
      publish(new GameChanged)
      won
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
      publish(new GameChanged)
      State.handle(enemyTurnEvent())
      enemy()
    } else {
      State.handle(pullCardNotAllowedEvent())
    }
  }

  def enemy(): Unit = {
    undoManager.doStep(new EnemyCommand(this))
    publish(new GameChanged)
    won
  }

  def undo: Unit = {
    undoManager.undoStep
    publish(new GameChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    publish(new GameChanged)
  }

  def maxSize: Int = {
    if(game.init.player.handCards.length >= game.init.enemy.enemyCards.length && game.init.player.handCards.length >= 2)
      game.init.player.handCards.length
    else if (game.init.player.handCards.length < game.init.enemy.enemyCards.length && game.init.enemy.enemyCards.length >= 2)
      game.init.enemy.enemyCards.length
    else
      2
  }

  def won: Unit = {
    if(game.init.player.handCards.length == 0) {
      State.handle(wonEvent())
    } else if(game.init.enemy.enemyCards.length == 0) {
      State.handle(lostEvent())
    } else {
      State.handle(idleEvent())
    }
  }

  def showPushable(list: Int, index: Int) : Unit = {
    if(list != 2) {
      State.handle(notPushableEvent())
    } else {
      if (game.init.player.pushable(game.init.player.handCards(index), game)) {
        State.handle(pushableEvent())
      } else {
        State.handle(notPushableEvent())
      }
    }
  }

  def statusText:String = GameStatus.message((gameStatus))
}

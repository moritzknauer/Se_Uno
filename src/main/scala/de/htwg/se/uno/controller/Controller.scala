package de.htwg.se.uno.controller

import scala.swing.Publisher
import de.htwg.se.uno.model.{Game, InitializeTestGameStrategy}
import de.htwg.se.uno.util.UndoManager


class Controller(var game:Game) extends Publisher {
  private val undoManager = new UndoManager
  var s = ""

  def createGame(size: Int = 7):Unit = {
    game = Game(size)
    publish(new GameSizeChanged)
  }

  def createTestGame():Unit = {
    game = Game()
    game.init = new InitializeTestGameStrategy
    game.init = game.init.initializeGame()
    publish(new GameSizeChanged)
  }

  def gameToString: String = game.toString

  def set(string:String): Unit = {
    val s = gameToString
    undoManager.doStep(new PushCommand(string, this))
    if(!s.equals(gameToString)) {
      State.handle(enemyTurnEvent())
      publish(new GameChanged)
      enemy()
      won
    } else {
      State.handle(pushCardNotAllowedEvent())
      publish(new GameNotChanged)
    }
  }

  def get(): Unit = {
    val s = gameToString
    undoManager.doStep(new PullCommand(this))
    if(!s.equals(gameToString)) {
      State.handle(enemyTurnEvent())
      publish(new GameChanged)
      enemy()
      won
    } else {
      State.handle(pullCardNotAllowedEvent())
      publish(new GameNotChanged)
    }
  }

  def enemy(): Unit = {
    undoManager.doStep(new EnemyCommand(this))
    State.handle(yourTurnEvent())
    publish(new GameChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    State.handle(undoEvent())
    publish(new GameChanged)
    won
  }

  def redo: Unit = {
    undoManager.redoStep
    State.handle(redoEvent())
    publish(new GameChanged)
    won
  }

  def won: Unit = {
    if(game.init.player.handCards.length == 0) {
      State.handle(wonEvent())
      publish(new GameNotChanged)
    } else if(game.init.enemy.enemyCards.length == 0) {
      State.handle(lostEvent())
      publish(new GameNotChanged)
    } else {
      State.state = State.state
    }
  }

  def showPushable(list: Int, index: Int) : Unit = {
    if(list != 2) {
      State.handle(notPushableEvent())
      publish(new GameNotChanged)
    } else {
      if (game.init.player.pushable(game.init.player.handCards(index), game)) {
        State.handle(pushableEvent())
        publish(new GameNotChanged)
      } else {
        State.handle(notPushableEvent())
        publish(new GameNotChanged)
      }
    }
  }

  def getIndex(string: String): Int = {
    var c = -1
    for(i <- 0 to game.init.player.handCards.length-1) {
      if(game.init.player.handCards(i).toString.equals(string)) {
        c = i
      }
    }
    c
  }

  def pushNext(string:String): Unit = {
    s = string
  }
}

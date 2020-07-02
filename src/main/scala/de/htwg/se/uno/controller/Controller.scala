package de.htwg.se.uno.controller

import scala.swing.Publisher
import de.htwg.se.uno.model.{Game, InitializeTestGameStrategy}
import de.htwg.se.uno.util.UndoManager


class Controller(var game:Game) extends Publisher {
  private var undoManager = new UndoManager
  var s = ""

  def createGame(size: Int = 7):Unit = {
    game = Game(size)
    undoManager = new UndoManager
    publish(new GameSizeChanged)
  }

  def createTestGame():Unit = {
    game = Game()
    game.init = new InitializeTestGameStrategy
    game.init = game.init.initializeGame()
    undoManager = new UndoManager
    publish(new GameSizeChanged)
  }

  def gameToString: String = game.toString

  def set(string:String): Unit = {
    val s = gameToString
    undoManager.doStep(new PushCommand(string, this))
    if(!s.equals(gameToString)) {
      GameEvent.handle(enemyTurnEvent())
      publish(new GameChanged)
      enemy()
      won
    } else {
      GameEvent.handle(pushCardNotAllowedEvent())
      publish(new GameNotChanged)
    }
  }

  def get(): Unit = {
    val s = gameToString
    undoManager.doStep(new PullCommand(this))
    if(!s.equals(gameToString)) {
      GameEvent.handle(enemyTurnEvent())
      publish(new GameChanged)
      enemy()
      won
    } else {
      GameEvent.handle(pullCardNotAllowedEvent())
      publish(new GameNotChanged)
    }
  }

  def enemy(): Unit = {
    undoManager.doStep(new EnemyCommand(this))
    GameEvent.handle(yourTurnEvent())
    publish(new GameChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    GameEvent.handle(undoEvent())
    publish(new GameChanged)
    won
  }

  def redo: Unit = {
    undoManager.redoStep
    GameEvent.handle(redoEvent())
    publish(new GameChanged)
    won
  }

  def won: Unit = {
    if(game.init.player.handCards.length == 0) {
      GameEvent.handle(wonEvent())
      publish(new GameEnded)
    } else if(game.init.enemy.enemyCards.length == 0) {
      GameEvent.handle(lostEvent())
      publish(new GameEnded)
    } else {
      GameEvent.state = GameEvent.state
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

  def notPush : Unit = {
    GameEvent.handle(pushCardNotAllowedEvent())
    publish(new GameNotChanged)
  }
}

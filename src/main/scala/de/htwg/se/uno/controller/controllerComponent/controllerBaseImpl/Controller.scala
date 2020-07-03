package de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.uno.controller.controllerComponent._
import de.htwg.se.uno.model.cardComponent.cardBaseImpl.Card
import de.htwg.se.uno.model.gameComponent.GameInterface
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import de.htwg.se.uno.util.UndoManager

import scala.swing.Publisher


class Controller(var game: GameInterface) extends ControllerInterface with Publisher {
  private var undoManager = new UndoManager
  private var hs = "Du bist dran. Mögliche Befehle: q, n, t, s [Karte], g, u, r"

  def createGame(size: Int = 7):Unit = {
    game = new Game(size)
    undoManager = new UndoManager
    publish(new GameSizeChanged)
  }

  def createTestGame():Unit = {
    game = game.createTestGame()
    undoManager = new UndoManager
    publish(new GameSizeChanged)
  }

  def gameToString: String = game.toString

  def set(string:String): Unit = {
    val s = gameToString
    undoManager.doStep(new PushCommand(string, this))
    if(!s.equals(gameToString)) {
      gameStatus("enemyTurn")
      publish(new GameChanged)
      enemy()
      won
    } else {
      gameStatus("pushCardNotAllowed")
      publish(new GameNotChanged)
    }
  }

  def get(): Unit = {
    val s = gameToString
    undoManager.doStep(new PullCommand(this))
    if(!s.equals(gameToString)) {
      gameStatus("enemyTurn")
      publish(new GameChanged)
      enemy()
      won
    } else {
      gameStatus("pullCardNotAllowed")
      publish(new GameNotChanged)
    }
  }

  def enemy(): Unit = {
    undoManager.doStep(new EnemyCommand(this))
    gameStatus("yourTurn")
    publish(new GameChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    gameStatus("undo")
    publish(new GameChanged)
    won
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus("redo")
    publish(new GameChanged)
    won
  }

  def won: Unit = {
    if(game.getLength(1) == 0) {
      gameStatus("won")
      publish(new GameEnded)
    } else if(game.getLength(0) == 0) {
      gameStatus("lost")
      publish(new GameEnded)
    } else {
      gameStatus("idle")
    }
  }

  def notPush : Unit = {
    gameStatus("pushCardNotAllowed")
    publish(new GameNotChanged)
  }

  def getCardText(list : Int, index : Int) : String = {
    game.getCardText(list, index)
  }

  def getGuiCardText(list : Int, index : Int) : String = {
    getGuiCardText(list, index)
  }

  def getLength(list : Int) : Int = {
      game.getLength(list)
  }

  def gameStatus(string : String) : String = {
    string match {
      case "pushCardNotAllowed" => {
        hs = "Du kannst diese Karte nicht legen"
        hs
      }
      case "enemyTurn" => {
        hs = "Gegner ist an der Reihe"
        hs
      }
      case "pullCardNotAllowed" => {
        hs = "Du kannst keine Karte ziehen, da du eine Karte legen kannst"
        hs
      }
      case "unknownCommand" => {
        hs = "Befehl nicht bekannt"
        hs
      }
      case "yourTurn" => {
        hs = "Du bist dran. Mögliche Befehle: q, n, t, s [Karte], g, u, r"
        hs
      }
      case "won" => {
        hs = "Glückwunsch du hast gewonnen"
        hs
      }
      case "lost" => {
        hs = "Du hast leider verloren"
        hs
      }
      case "undo" => {
        hs = "Zug rückgängig gemacht"
        hs
      }
      case "redo" => {
        hs = "Zug wiederhergestellt"
        hs
      }
      case "idle" => hs
    }
  }
}

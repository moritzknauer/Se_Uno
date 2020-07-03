package de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.uno.controller.controllerComponent._
import de.htwg.se.uno.model.gameComponent.GameInterface
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import de.htwg.se.uno.util.UndoManager

import scala.swing.Publisher


class Controller(var game: GameInterface) extends ControllerInterface with Publisher {
  private var undoManager = new UndoManager
  private var hs = "Du bist dran. Mögliche Befehle: q, n, t, s [Karte], g, u, r"

  def createGame(size: Int = 7):Unit = {
    game = game.createRandomGame(size)
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
    if(game.init.player.handCards.length == 0) {
      gameStatus("won")
      publish(new GameEnded)
    } else if(game.init.enemy.enemyCards.length == 0) {
      gameStatus("lost")
      publish(new GameEnded)
    } else {
      gameStatus("idle")
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

  def notPush : Unit = {
    gameStatus("pushCardNotAllowed")
    publish(new GameNotChanged)
  }

  def getCard(list : Int, index : Int) : Card = {
    list match{
      case 0 => game.init.enemy.enemyCards(index)
      case 1 => {
        index match {
          case 0 => game.init.cardsCovered.head
          case 1 => game.init.cardsRevealed.head
        }
      }
      case 2 => game.init.player.handCards(index)
    }
  }

  def getCardText(list : Int, index : Int) : String = {
    if (list == 1 && index == 1) {
      game.init.cardsRevealed.head.toString
    } else if (list == 2) {
      game.init.player.handCards(index).toString
    } else {
      "Uno"
    }
  }

  def getGuiCardText(list : Int, index : Int) : String = {
    if (list == 0 || (list == 1 && index == 0)) {
      "Uno"
    } else if (list == 1 && index == 1) {
      game.init.cardsRevealed.head.toGuiString
    } else {
      game.init.player.handCards(index).toGuiString
    }
  }

  def getLength(list : Int) : Int = {
    if(list == 0) {
      game.init.enemy.enemyCards.length
    } else {
      game.init.player.handCards.length
    }
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

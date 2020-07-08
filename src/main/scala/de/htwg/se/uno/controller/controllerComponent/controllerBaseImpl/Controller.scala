package de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject, Injector}
import com.google.inject.name.Names
import de.htwg.se.uno.UnoModule
import de.htwg.se.uno.controller.controllerComponent._
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.uno.model.gameComponent.GameInterface
import de.htwg.se.uno.model.gameComponent.fileIoComponent.FileIOInterface
import de.htwg.se.uno.util.UndoManager

import scala.swing.{Color, Publisher}

class Controller @Inject() (var game: GameInterface) extends ControllerInterface with Publisher {
  private var undoManager = new UndoManager
  val injector: Injector = Guice.createInjector(new UnoModule)
  val fileIo = injector.instance[FileIOInterface]
  private var hs = "Du bist dran. Mögliche Befehle: q, n, t, s [Karte], g, u, r"
  private var hs2 = ""
  private var color = 0
  private var hv = false

  def createGame(size: Int):Unit = {
    size match {
      case 2 => game = injector.instance[GameInterface](Names.named("2 Players"))
      case 3 => game = injector.instance[GameInterface](Names.named("3 Players"))
      case 4 => game = injector.instance[GameInterface](Names.named("4 Players"))
      case _ =>
    }
    game = game.createGame()
    hs2 = ""
    color = 0
    undoManager = new UndoManager
    controllerEvent("yourTurn")
    publish(new GameSizeChanged)
  }
  def createTestGame():Unit = {
    game = injector.instance[GameInterface](Names.named("4 Players"))
    game = game.createTestGame()
    hs2 = ""
    undoManager = new UndoManager
    controllerEvent("yourTurn")
    publish(new GameSizeChanged)
  }

  def set(string:String, color : Int = 0): Unit = {
    if (string.charAt(0) != 'S' || color != 0) {
      if (game.nextTurn()) {
        val s = gameToString
        game = game.setActivePlayer()
        undoManager.doStep(new PushCommand(string, color, this))
        if (!s.equals(gameToString)) {
          controllerEvent("enemyTurn")
          game = game.setAnotherPull()
          publish(new GameChanged)
          won
        } else {
          game = game.setDirection()
          game = game.setActivePlayer()
          game = game.setDirection()
          controllerEvent("pushCardNotAllowed")
          publish(new GameNotChanged)
        }
      } else {
        controllerEvent("enemyTurn")
        publish(new GameNotChanged)
      }
    } else {
      hs2 = string
      controllerEvent("chooseColor")
      publish(new ChooseColor)
    }
  }
  def get(): Unit = {
    if (game.nextTurn()) {
      val b = game.getAnotherPull()
      game = game.setActivePlayer()
      val activePlayer = game.getActivePlayer()
      undoManager.doStep(new PullCommand(this))
      val activePlayer2 = game.getActivePlayer()
      controllerEvent("enemyTurn")
      if (b) {
        game = game.setAnotherPull()
      }
      if (game.getAnotherPull()) {
        game = game.setDirection()
        game = game.setActivePlayer()
        game = game.setDirection()
        controllerEvent("yourTurn")
      }
      if (activePlayer != activePlayer2) {
        game = game.setDirection()
        game = game.setActivePlayer()
        game = game.setDirection()
        controllerEvent("pullCardNotAllowed")
      }
      publish(new GameChanged)
      shuffle
    } else {
      controllerEvent("enemyTurn")
      publish(new GameNotChanged)
    }
  }
  def enemy(): Unit = {
    if (game.nextEnemy() == 1) {
      game = game.setActivePlayer()
      undoManager.doStep(new EnemyCommand(this))
    } else if (game.nextEnemy() == 2) {
      game = game.setActivePlayer()
      undoManager.doStep(new EnemyCommand2(this))
    } else {
      game = game.setActivePlayer()
      undoManager.doStep(new EnemyCommand3(this))

    }
    if (game.nextTurn()) {
      controllerEvent("yourTurn")
    } else {
      controllerEvent("enemyTurn")
    }
    if (game.getAnotherPull()) {
      game.setDirection()
      game.setActivePlayer()
      game.setDirection()
      controllerEvent("enemyTurn")
    }
    publish(new GameChanged)
    shuffle
    won
  }

  def undo: Unit = {
    do {
      undoManager.undoStep
      if (game.getHv2()) {
        game.setDirection()
        game.setActivePlayer()
        game.setDirection()
      }
    }
    while(!game.nextTurn())
    controllerEvent("undo")
    publish(new GameChanged)
    won
  }
  def redo: Unit = {
    game = game.setHv()
    game.setActivePlayer()
    undoManager.redoStep
    if (game.getHv()) {
      game = game.setDirection()
      game = game.setActivePlayer()
      game = game.setDirection()
    }
    controllerEvent("redo")
    publish(new GameChanged)
    shuffle
    won
  }

  def save: Unit = {
    fileIo.save(game)
    gameStatus("save")
    publish(new GameChanged)
  }

  def load: Unit = {
    game = fileIo.load
    gameStatus("load")
    publish(new GameChanged)
  }

  def won: Unit = {
    if(game.getLength(4) == 0) {
      controllerEvent("won")
      publish(new GameEnded)
    } else if(game.getLength(0) == 0) {
      controllerEvent("lost")
      publish(new GameEnded)
    } else if (game.getNumOfPlayers() >= 3 &&game.getLength(1) == 0) {
      controllerEvent("lost")
      publish(new GameEnded)
    } else if (game.getNumOfPlayers() >= 4 &&game.getLength(2) == 0) {
      controllerEvent("lost")
      publish(new GameEnded)
    } else {
      controllerEvent("idle")
    }
  }

  def shuffle: Unit = {
    if(game.getLength(5) <= 16) {
      undoManager.doStep(new ShuffleCommand(this))
      controllerEvent("shuffled")
      publish(new GameChanged)
    } else {
      controllerEvent("idle")
    }
  }

  def gameToString: String = game.toString
  def getCardText(list : Int, index : Int) : String = game.getCardText(list, index)
  def getGuiCardText(list : Int, index : Int) : String = game.getGuiCardText(list, index)
  def getLength(list : Int) : Int = game.getLength(list)
  def getNumOfPlayers() : Int = game.getNumOfPlayers()
  def nextTurn() : Boolean = game.nextTurn()
  def getHs2() : String = hs2
  def nextEnemy() : Int = game.nextEnemy()

  def controllerEvent(string : String) : String = {
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
        hs = "Du kannst keine Karte ziehen"
        hs
      }
      case "unknownCommand" => {
        hs = "Befehl nicht bekannt"
        hs
      }
      case "yourTurn" => {
        hs = "Du bist dran. Mögliche Befehle: q, n [2 | 3 | 4], t, s Karte [Farbe], g, u, r, d"
        hs
      }
      case "won" => {
        hs = "Glückwunsch, du hast gewonnen!"
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
      case "save" => {
        hs = "Spiel gespeichert"
        hs
      }
      case "load" => {
        hs = "Spiel geladen"
        hs
      }
      case "chooseColor" => {
        hs = "Wähle eine Farbe"
        hs
      }
      case "shuffled" => {
        hs = "Verdeckter Kartenstapel wurde neu gemischt"
        hs
      }
      case "idle" => hs
    }
  }
}

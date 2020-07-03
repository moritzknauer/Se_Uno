package de.htwg.se.uno.controller

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.uno.model.InitializeGameStrategy
import org.scalatest.{Matchers, WordSpec}

import scala.language.reflectiveCalls

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val game = new Game(1)
      val controller = new Controller(game)
      "Should notify its Observer after creation" in {
        controller.createGame(1)
        controller.game.numOfCards should be(1)
      }
      "Should be able to create a new Game" in{
        controller.createGame()
        controller.game.numOfCards should be(7)
      }
      "Should be able to create a new Test Game" in{
        controller.createTestGame()
        controller.game.init.player.handCards.length should be(5)
      }
      "Should Have a String Representation of the game" in {
        controller.gameToString should be (controller.game.toString)
      }
      "Should be able to initialize a Test Game" in {
        controller.game.init = InitializeGameStrategy(1)
        controller.game.init = controller.game.init.initializeGame()
      }
      "Should be able to push a Card" in {
        controller.set(controller.game.init.player.handCards.head.toString)
      }
      "Should be able to push Cards" in {
        controller.set(controller.game.init.player.handCards.head.toString)
        controller.set(controller.game.init.player.handCards(1).toString)
        controller.set(controller.game.init.player.handCards.head.toString)
        controller.set(controller.game.init.player.handCards.head.toString)
        controller.get()
      }
      /*
      "Should be able to test if the game is won" in{
        controller.won
        State.state should be(wonEvent().won)
      }

       */



      "Should be able to let the enemy run" in{
        controller.enemy()
      }
      "Should be able to undo a Step" in {
        controller.undo
      }
      "Should be able to redo a Step" in{
        controller.redo
      }




      "Should be able to update the state to your turn" in {
        controller.gameStatus("yourTurn")
        controller.gameStatus("idle") should be("Du bist dran. Mögliche Befehle: q, n, t, s [Karte], g, u, r")
      }
      "Should be able to update the state to enemys turn" in {
        controller.gameStatus("enemyTurn")
        controller.gameStatus("idle") should be("Gegner ist an der Reihe")
      }
      "Should be able to update the state to pushCardNotAllowed Event" in {
        controller.gameStatus("pushCardNotAllowed")
        controller.gameStatus("idle") should be("Du kannst diese Karte nicht legen")
      }
      "Should be able to update the state to pullCardNotAllowed Event" in {
        controller.gameStatus("pullCardNotAllowed")
        controller.gameStatus("idle") should be("Du kannst keine Karte ziehen, da du eine Karte legen kannst")
      }
      "Should be able to update the state to unknownCommand Event" in {
        controller.gameStatus("unknownCommand")
        controller.gameStatus("idle") should be("Befehl nicht bekannt")
      }
      "Should be able to update the state to won Event" in {
        controller.gameStatus("won")
        controller.gameStatus("idle") should be("Glückwunsch du hast gewonnen")
      }
      "Should be able to update the state to lost Event" in {
        controller.gameStatus("lost")
        controller.gameStatus("idle") should be("Du hast leider verloren")
      }
      "Should be able to update the state to undo Event" in {
        controller.gameStatus("undo")
        controller.gameStatus("idle") should be("Zug rückgängig gemacht")
      }
      "Should be able to update the state to redo Event" in {
        controller.gameStatus("redo")
        controller.gameStatus("idle") should be("Zug wiederhergestellt")
      }
    }
  }
}
package de.htwg.se.uno.controller

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.{Matchers, WordSpec}

import scala.language.reflectiveCalls
import scala.swing.Color

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "it's a Publisher" should {
      val game = new Game(1)
      val controller = new Controller(game)
      "Be able to create a game with 2 Players" in {
        controller.createGame(2)
        controller.getNumOfPlayers() should be(2)
      }
      "Be able to create a new Game with 4 Players" in{
        controller.createGame(4)
        controller.getNumOfPlayers() should be(4)
      }
      "Be able to create a new Game with 3 Players" in{
        controller.createGame(3)
        controller.getNumOfPlayers() should be(3)
      }
      "Be able to create a new Test Game" in{
        controller.createTestGame()
        controller.getNumOfPlayers() should be(4)
      }

      "Not Push a Special card without a color" in {
        controller.set(controller.getCardText(4, 1))
        controller.getHs2() should be(controller.getCardText(4, 1))
      }
      "not push a Card if the Card is not pushable" in {
        val old = controller.gameToString
        controller.set(controller.getCardText(4, 4))
        controller.gameToString should be(old)
      }
      "be able to push a Special Card with a color" in {
        controller.set(controller.getCardText(4,1), 4)
        controller.gameStatus("idle") should be(controller.gameStatus("enemyTurn"))
      }
      "not push a Card if it's not the players turn" in {
        val old = controller.gameToString
        controller.set(controller.getCardText(4, 1))
        controller.gameToString should be(old)
      }
      "Not pull a Card if it's not the players turn" in {
        val old = controller.gameToString
        controller.get()
        controller.gameToString should be(old)
      }


      "Should be able to undo a Step" in {
        controller.undo
        controller.gameStatus("idle") should be(controller.gameStatus("undo"))
      }


      "Pull a Card if it is allowed" in {
        controller.get()
        controller.gameStatus("idle") should be(controller.gameStatus("yourTurn"))
      }
      "Not Pull another Card if a Card was already Pulled" in {
        controller.get()
        controller.gameStatus("idle") should be(controller.gameStatus(("enemyTurn")))
      }

      "Do the enemy's runs if it's the enemys turn" in {
        controller.enemy()
        controller.enemy()
        controller.enemy()
        controller.gameStatus("idle") should be(controller.gameStatus("yourTurn"))
      }
      "Should be able to undo another Step" in {
        controller.undo
        controller.gameStatus("idle") should be(controller.gameStatus("undo"))
      }
      "Do the enemy's run so that the enemy pulls and then can't do anything" in {
        controller.set("S C", 1)
        controller.enemy()
        controller.enemy()
        controller.enemy()
        controller.set("R+2")
        controller.enemy()
        controller.enemy()
        controller.enemy()
        controller.get()
        controller.enemy()
        controller.enemy()
        controller.enemy()
        controller.set("Y 4")
        controller.enemy()
        controller.enemy()
      }

      "Should be able to redo a Step" in{
        controller.redo
        controller.gameStatus("idle") should be(controller.gameStatus("redo"))
      }
      "Should be able to check if nobody has won" in {
        controller.won
        controller.gameStatus("idle") should be(controller.gameStatus("redo"))
      }

      "Should be able to check if the enemy 1 has won" in {
        controller.game.setLength(1)
        controller.won
        controller.gameStatus("idle") should be(controller.gameStatus("lost"))
      }
      "Should be able to check if the player has won" in {
        controller.game.setLength(4)
        controller.won
        controller.gameStatus("idle") should be(controller.gameStatus("won"))
      }
      "Should be able to check if the enemy 2 has won" in {
        controller.game.setLength(2)
        controller.won
        controller.gameStatus("idle") should be(controller.gameStatus("lost"))
      }
      "Should be able to check if the enemy 3 has won" in {
        controller.game.setLength(3)
        controller.gameStatus("yourTurn")
        controller.won
        controller.gameStatus("idle") should be(controller.gameStatus("lost"))
      }


      "Should Have a String Representation of the game" in {
        controller.gameToString should be (controller.game.toString)
      }
      "Should be able to get the cardText of a Card" in {
        controller.getCardText(0,0) should be("Uno")
      }
      "Should be able to get the guiCardText of a Card" in {
        controller.getGuiCardText(0,0) should be("Uno")
      }
      "Should be able to get the Length of a List" in {
        controller.getLength(1) should be(6)
      }
      "Should be able to get the number of Players" in {
        controller.getNumOfPlayers() should be(4)
      }
      "Should be able to check if it's the players turn" in {
        controller.nextTurn() should be(false)
      }
      "Should be able to return the Help String 2 of the controller" in {
        controller.getHs2() should be("S C")
      }
      "Should be able to get the Color of a card" in {
        controller.getColor() should be(new Color(128,128,128))
      }
      "Should be able to get the next Enemy" in {
        controller.nextEnemy() should be(2)
      }




      "Should be able to update the state to pushCardNotAllowed Event" in {
        controller.gameStatus("pushCardNotAllowed")
        controller.gameStatus("idle") should be("Du kannst diese Karte nicht legen")
      }
      "Should be able to update the state to enemys turn" in {
        controller.gameStatus("enemyTurn")
        controller.gameStatus("idle") should be("Gegner ist an der Reihe")
      }
      "Should be able to update the state to pullCardNotAllowed Event" in {
        controller.gameStatus("pullCardNotAllowed")
        controller.gameStatus("idle") should be("Du kannst keine Karte ziehen")
      }
      "Should be able to update the state to unknownCommand Event" in {
        controller.gameStatus("unknownCommand")
        controller.gameStatus("idle") should be("Befehl nicht bekannt")
      }
      "Should be able to update the state to your turn" in {
        controller.gameStatus("yourTurn")
        controller.gameStatus("idle") should be("Du bist dran. Mögliche Befehle: q, n [2 | 3 | 4], t, s Karte [Farbe], g, u, r, d")
      }
      "Should be able to update the state to won Event" in {
        controller.gameStatus("won")
        controller.gameStatus("idle") should be("Glückwunsch, du hast gewonnen!")
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
      "Schould be able to update the state to chooseColor Event" in {
        controller.gameStatus("chooseColor")
        controller.gameStatus("idle") should be(controller.gameStatus("chooseColor"))
      }
      "Should not chamge the state on input idle" in {
        controller.gameStatus("idle") should be(controller.gameStatus("chooseColor"))
      }
    }
  }
}
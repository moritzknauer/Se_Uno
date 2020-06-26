package de.htwg.se.uno.controller

import de.htwg.se.uno.model.{Game, InitializeGameStrategy}
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
        State.handle(yourTurnEvent())
        State.state should be(yourTurnEvent().yourTurn)
      }
      "Should be able to update the state to enemys turn" in {
        State.handle(enemyTurnEvent())
        State.state should be(enemyTurnEvent().enemyTurn)
      }
      "Should be able to update the state to pushCardNotAllowed Event" in {
        State.handle(pushCardNotAllowedEvent())
        State.state should be(pushCardNotAllowedEvent().pushCardNotAllowed)
      }
      "Should be able to update the state to pullCardNotAllowed Event" in {
        State.handle(pullCardNotAllowedEvent())
        State.state should be(pullCardNotAllowedEvent().pullCardNotAllowed)
      }
      "Should be able to update the state to unknownCommand Event" in {
        State.handle(unknownCommandEvent())
        State.state should be(unknownCommandEvent().unknownCommand)
      }
      "Should be able to update the state to pushable Event" in {
        State.handle(pushableEvent())
        State.state should be(pushableEvent().pushable)
      }
      "Should be able to update the state to notPushable Event" in {
        State.handle(notPushableEvent())
        State.state should be(notPushableEvent().notPushable)
      }
      "Should be able to update the state to won Event" in {
        State.handle(wonEvent())
        State.state should be(wonEvent().won)
      }
      "Should be able to update the state to lost Event" in {
        State.handle(lostEvent())
        State.state should be(lostEvent().lost)
      }
    }
  }
}
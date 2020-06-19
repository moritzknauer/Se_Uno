package de.htwg.se.uno.controller

import de.htwg.se.uno.model.Game
import de.htwg.se.uno.util.{Observer, State, enemyTurnEvent, yourTurnEvent}
import org.scalatest.{Matchers, WordSpec}

import scala.language.reflectiveCalls

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val game = new Game(1)
      val controller = new Controller(game)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {updated = true; updated}
      }
      controller.add(observer)
      "Should notify its Observer after creation" in {
        controller.createGame(1)
        observer.updated should be(true)
        controller.game.numOfCards should be(1)
      }
      "Should be able to create a new Game" in{
        controller.createGame()
        observer.updated should be(true)
        controller.game.numOfCards should be(7)
      }
      "Should Have a String Representation of the game" in {
        controller.gameToString should be (controller.game.toString)
      }
      "Should be able to initialize a Test Game" in {
        controller.game = controller.game.initializeTestGame()
      }
      "Should be able to push a Card" in {
        controller.set(controller.game.player.handCards.head.toString)
        observer.updated should be(true)
      }
      "Should be able to pull a Card" in {
        controller.get()
        observer.updated should be(true)
      }
      "Should be able to let the enemy run" in{
        controller.enemy()
        observer.updated should be(true)
      }
      "Should be able to undo a Step" in {
        controller.undo
        observer.updated should be(true)
      }
      "Should be able to redo a Step" in{
        observer.updated should be (true)
      }
      "Should be able to update the state to your turn" in {
        State.handle(yourTurnEvent())
        State.state should be(State.yourTurn)
      }
      "Should be able to update the state to enemys turn" in {
        State.handle(enemyTurnEvent())
        State.state should be(State.enemyTurn)
      }
    }
  }
}
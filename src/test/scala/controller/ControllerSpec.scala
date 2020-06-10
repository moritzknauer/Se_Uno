package de.htwg.se.sudoku.controller

import scala.language.reflectiveCalls
import org.scalatest.{Matchers, WordSpec}

import scala.controller.Controller
import scala.model.Game
import scala.util.Observer

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
      "Should be able to check if a String is Pushable" in {
        controller.pushable(controller.game.handCards.head.toString) should be (controller.game.pushable(controller.game.handCards.head))
      }
      "Should be able to check if a String is not Pushable" in {
        controller.pushable("Hallo") should be (false)
      }
      "Should be able to push a Card" in {
        controller.pushCard(controller.game.handCards.head.toString)
        observer.updated should be(true)
      }
      "Should be able to check if a Card can be pulled" in {
        controller.pullable() should be (controller.game.pullable())
      }
      "Should be able to pull a Card" in {
        controller.pull()
        observer.updated should be(true)
      }
      "Should be able to let the enemy run" in{
        controller.enemy()
        observer.updated should be(true)
      }
    }
  }
}
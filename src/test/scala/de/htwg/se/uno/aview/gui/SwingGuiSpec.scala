package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.controllerComponent.{ChooseColor, GameChanged, GameEnded}
import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.{Matchers, WordSpec}

import scala.swing.{BorderPanel, Color}

class SwingGuiSpec extends WordSpec with Matchers {
  "A SwingGui" when {
    var controller = new Controller(new Game(4))
    "created" should {
      controller.createTestGame()
      var swingGui = new SwingGui(controller)
      "Have a title" in {
        swingGui.title should be("HTWG Uno")
      }
      "Change if the activePlayer changes" in {
        controller.set("R 1")
        swingGui.title should be("HTWG Uno")
      }
      "Change if the activePlayer changes a second time" in {
        controller.enemy()
        swingGui.title should be("HTWG Uno")
      }
      "Change if the activePlayer changes a third time" in {
        controller.enemy()
        swingGui.title should be("HTWG Uno")
      }
      "Change if the activePlayer changes a fourth time" in {
        controller = new Controller(new Game(3))
        controller.createGame(3)
        swingGui = new SwingGui(controller)
        swingGui.title should be("HTWG Uno")
      }
      "Change if the activePlayer changes a fifth time" in {
        controller.game.setActivePlayer()
        controller.publish(new GameChanged)
        swingGui.title should be("HTWG Uno")
      }
      "Change if the activePlayer changes a sixth time" in {
        controller.game.setActivePlayer()
        controller.publish(new GameChanged)
        swingGui.title should be("HTWG Uno")
      }
      "Have a statusline" in {
        swingGui.statusline.text should be(controller.controllerEvent("idle"))
      }
      "Should change if the Event changes to chooseColor" in {
        controller.controllerEvent("chooseColor")
        controller.publish(new ChooseColor)
        swingGui.statusline.text should be(controller.controllerEvent("chooseColor"))
      }
      "Should change if the Event changes to GameEnded" in {
        controller.controllerEvent("won")
        controller.publish(new GameEnded)
        swingGui.statusline.text should be(controller.controllerEvent("won"))
      }
      "Should change if the Event changes to GameChanged" in {
        controller.controllerEvent("yourTurn")
        controller.publish(new GameChanged)
        swingGui.statusline.text should be(controller.controllerEvent("yourTurn"))
      }
    }
  }
}

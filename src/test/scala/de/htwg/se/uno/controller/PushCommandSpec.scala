package de.htwg.se.uno.controller

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.{Controller, PushCommand}
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.{Matchers, WordSpec}

class PushCommandSpec extends WordSpec with Matchers {
  "A PushCommand" when {
    "new" should {
      val game = Game(7)
      val controller = new Controller(game)
      val command = new PushCommand("Hallo", 0, controller)
      "Be able to do a Step" in {
        command.doStep
      }
      "Be able to undo a Step" in {
        command.undoStep
      }
      "Be able to redo a Step" in {
        command.redoStep
      }
    }
  }
}

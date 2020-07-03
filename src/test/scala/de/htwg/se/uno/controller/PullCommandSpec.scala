package de.htwg.se.uno.controller

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.{Controller, PullCommand}
import de.htwg.se.uno.model.Game
import org.scalatest.{Matchers, WordSpec}

class PullCommandSpec extends WordSpec with Matchers {
  "A PullCommand" when {
    "new" should {
      val game = Game()
      val controller = new Controller(game)
      val command = new PullCommand(controller)
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

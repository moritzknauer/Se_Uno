package de.htwg.se.uno.controller

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.{Controller, EnemyCommand}
import org.scalatest.{Matchers, WordSpec}

class EnemyCommandSpec extends WordSpec with Matchers {
  "A EnemyCommand" when {
    "new" should {
      val game = Game()
      val controller = new Controller(game)
      val command = new EnemyCommand(controller)
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

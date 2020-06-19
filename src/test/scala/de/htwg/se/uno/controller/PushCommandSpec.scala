package de.htwg.se.uno.controller

import de.htwg.se.uno.model.Game
import org.scalatest.{Matchers, WordSpec}

class PushCommandSpec extends WordSpec with Matchers {
  "A PushCommand" when {
    "new" should {
      val game = Game()
      val controller = new Controller(game)
      val command = new PushCommand("Hallo", controller)
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

package de.htwg.se.uno.util

import de.htwg.se.uno.controller.{Controller, PullCommand}
import de.htwg.se.uno.model.Game
import org.scalatest.{Matchers, WordSpec}

class UndoManagerSpec extends WordSpec with Matchers { {
  "A UndoManager" when {
    "new" should {
      val undoManager = new UndoManager()
      val game = new Game()
      val controller = new Controller(game)
      val command = new PullCommand(controller)
      "be able to do a Step" in {
        undoManager.doStep(command)
      }
      "be able to undo a Step" in {
        undoManager.undoStep
      }
      "be able to redo a Step" in {
        undoManager.redoStep
      }
    }
  }
}
}

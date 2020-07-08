package de.htwg.se.uno.controller

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.{Controller, EnemyCommand3}
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.{Matchers, WordSpec}

class EnemyCommand3Spec extends WordSpec with Matchers {
  "A EnemyCommand" when {
    "new" should {
      val game = Game(4)
      val controller = new Controller(game)
      val command = new EnemyCommand3(controller)
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
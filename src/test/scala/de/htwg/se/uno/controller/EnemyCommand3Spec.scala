package de.htwg.se.uno.controller

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.{Controller, EnemyCommand3}
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.{Matchers, WordSpec}

class EnemyCommand3Spec extends WordSpec with Matchers {
  "A EnemyCommand" when {
    "new" should {
      val controller = new Controller(new Game(4))
      controller.createTestGame()
      val command = new EnemyCommand3(controller)
      "Be able to do a Step" in {
        controller.game.setSpecialTop(-1)
        command.doStep
        controller.game.getHv() should be(false)
      }
      "Be able to undo a Step" in {
        command.undoStep
        controller.game.getHv2() should be(true)
      }
      "Be able to redo a Step" in {
        command.redoStep
        controller.game.getHv() should be(false)
      }
    }
  }
}
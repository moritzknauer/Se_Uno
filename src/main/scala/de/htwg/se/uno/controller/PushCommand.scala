package de.htwg.se.uno.controller

import de.htwg.se.uno.util.Command

class PushCommand(string: String, controller: Controller) extends Command  {
  override def doStep: Unit = {
    controller.game.player = controller.game.player.pushMove(string, controller.game)
  }

  override def undoStep: Unit = {
    controller.game.player = controller.game.player.undo(controller.game)
  }

  override def redoStep: Unit = {
    controller.game.player = controller.game.player.pushMove(string, controller.game)
  }
}

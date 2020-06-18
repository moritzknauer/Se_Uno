package de.htwg.se.uno.controller

import de.htwg.se.uno.util.Command

class PullCommand(controller: Controller) extends Command  {
  override def doStep: Unit = {
    controller.game.player = controller.game.player.pullMove(controller.game)
  }

  override def undoStep: Unit = {
    controller.game.player = controller.game.player.undo(controller.game)
  }

  override def redoStep: Unit = {
    controller.game.player = controller.game.player.pullMove(controller.game)
  }
}
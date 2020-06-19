package de.htwg.se.uno.controller

import de.htwg.se.uno.util.Command

class PullCommand(controller: Controller) extends Command  {
  override def doStep: Unit = {
    controller.game.init.player = controller.game.init.player.pullMove(controller.game)
  }

  override def undoStep: Unit = {
    controller.game.init.player = controller.game.init.player.undo(controller.game)
  }

  override def redoStep: Unit = {
    controller.game.init.player = controller.game.init.player.pullMove(controller.game)
  }
}
package de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.uno.util.Command

class PushCommand(string: String, controller: Controller) extends Command  {
  override def doStep: Unit = {
    controller.game = controller.game.pushMove(string)
  }

  override def undoStep: Unit = {
    controller.game = controller.game.playerUndo()
  }

  override def redoStep: Unit = {
    controller.game = controller.game.pushMove(string)
  }
}
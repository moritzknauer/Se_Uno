package de.htwg.se.uno.controller

import de.htwg.se.uno.util.Command

class EnemyCommand(controller: Controller) extends Command {
  override def doStep: Unit = {
    controller.game.enemy = controller.game.enemy.enemy(controller.game)
  }

  override def undoStep: Unit = {
    controller.game.enemy = controller.game.enemy.undo(controller.game)
  }

  override def redoStep: Unit = {
    controller.game.enemy = controller.game.enemy.enemy(controller.game)
  }
}

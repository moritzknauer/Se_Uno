package de.htwg.se.uno.controller

import de.htwg.se.uno.model.Card
import de.htwg.se.uno.util.Command

class SetCommand(string: String, controller: Controller) extends Command {
  override def doStep: Unit = {
    val s = controller.gameToString
    controller.game.player = controller.game.player.doS(string, controller.game)
    if(s.equals(controller.gameToString)) {
      controller.game.enemy = controller.game.enemy.enemy(controller.game)
    }
  }

  override def undoStep: Unit = {
    controller.game.enemy = controller.game.enemy.undo(controller.game)
    controller.game.player = controller.game.player.undo(controller.game)
  };

  override def redoStep: Unit = {
    controller.game.player = controller.game.player.doS(string, controller.game)
    controller.game.enemy = controller.game.enemy.enemy(controller.game)
  }

}

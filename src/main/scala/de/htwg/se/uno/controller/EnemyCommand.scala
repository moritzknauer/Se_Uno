package de.htwg.se.uno.controller

import de.htwg.se.uno.model.{Card, Game}
import de.htwg.se.uno.util.Command

class EnemyCommand (game: Game) extends  Command{
  override def doStep: Unit = game.enemy = game.enemy.enemy(game)

  override def undoStep: Unit = ???

  override def redoStep: Unit = ???
}

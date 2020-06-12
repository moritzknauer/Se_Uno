package de.htwg.se.uno.controller

import de.htwg.se.uno.model.{Card, Game}
import de.htwg.se.uno.util.Command

class PullCommand (game: Game) extends  Command{
  override def doStep: Unit = game.player = game.player.pull(game)

  override def undoStep: Unit = ???

  override def redoStep: Unit = ???
}

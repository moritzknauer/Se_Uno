package de.htwg.se.uno.controller

import de.htwg.se.uno.model.{Card, Game}
import de.htwg.se.uno.util.Command

class PushCardCommand (card: Card, game: Game) extends  Command{
  override def doStep: Unit = game.player = game.player.pushCard(card, game)

  override def undoStep: Unit = ???

  override def redoStep: Unit = ???
}

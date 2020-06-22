package de.htwg.se.uno.util

object State {
  var state = gameStartEvent().gameStart
  def handle(e: Event) = {
    e match {
      case a: pushCardNotAllowedEvent => state = pushCardNotAllowedEvent().pushCardNotAllowed
      case b: enemyTurnEvent => state = enemyTurnEvent().enemyTurn
      case c: pullCardNotAllowedEvent => state = pullCardNotAllowedEvent().pullCardNotAllowed
      case d: unknownCommandEvent => state = unknownCommandEvent().unknownCommand
      case f: yourTurnEvent => state = yourTurnEvent().yourTurn
      case g: gameStartEvent => state = gameStartEvent().gameStart
    }
    state
  }
}

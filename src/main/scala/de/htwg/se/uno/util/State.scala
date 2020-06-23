package de.htwg.se.uno.util

object State {
  var state = String
  def handle(e: Status) = {
    e match {
      case a: pushCardNotAllowedEvent => state = pushCardNotAllowedEvent().pushCardNotAllowed
      case b: enemyTurnEvent => state = enemyTurnEvent().enemyTurn
      case c: pullCardNotAllowedEvent => state = pullCardNotAllowedEvent().pullCardNotAllowed
      case d: unknownCommandEvent => state = unknownCommandEvent().unknownCommand
      case f: yourTurnEvent => state = yourTurnEvent().yourTurn
    }
    state
  }
}

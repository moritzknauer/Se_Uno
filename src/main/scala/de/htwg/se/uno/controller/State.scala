package de.htwg.se.uno.controller

object State {
  var state = yourTurnEvent().yourTurn
  def handle(e: Status) = {
    e match {
      case a: pushCardNotAllowedEvent => state = pushCardNotAllowedEvent().pushCardNotAllowed
      case b: enemyTurnEvent => state = enemyTurnEvent().enemyTurn
      case c: pullCardNotAllowedEvent => state = pullCardNotAllowedEvent().pullCardNotAllowed
      case d: unknownCommandEvent => state = unknownCommandEvent().unknownCommand
      case f: yourTurnEvent => state = yourTurnEvent().yourTurn
      case h: pushableEvent => state = pushableEvent().pushable
      case i: notPushableEvent => state = notPushableEvent().notPushable
      case j: wonEvent => state = wonEvent().won
      case k: lostEvent => state = lostEvent().lost
      case l: undoEvent => state = undoEvent().undo
      case m: redoEvent => state = redoEvent().redo
    }
    state
  }
}

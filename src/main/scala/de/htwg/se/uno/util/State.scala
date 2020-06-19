package de.htwg.se.uno.util

object State {
  var state = yourTurn
  def handle(e: Event) = {
    e match {
      case a: pushCardNotAllowedEvent => state = pushCardNotAllowed
      case b: enemyTurnEvent => state = enemyTurn
      case c: pullCardNotAllowedEvent => state = pullCardNotAllowed
      case d: unknownCommandEvent => state = unknownCommand
      case f: yourTurnEvent => state = yourTurn
    }
    state
  }

  def yourTurn: Unit = println("Du bist dran. Mögliche Befehle: q, n, s [Karte], g, u, r")
  def pushCardNotAllowed = println("Du kannst diese Karte nicht legen")
  def enemyTurn = println("Gegner ist an der Reihe")
  def pullCardNotAllowed = println("Du kannst keine Karte ziehen, da du eine Karte legen kannst")
  def unknownCommand = println("Befehl nicht bekannt")

}

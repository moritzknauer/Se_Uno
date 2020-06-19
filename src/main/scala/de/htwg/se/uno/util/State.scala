package de.htwg.se.uno.util

object State {
  var state = enemyTurn
  def handle(e: Event) = {
    e match {
      case a: pushCardNotAllowedEvent => state = pushCardNotAllowed
      case b: enemyTurnEvent => state = enemyTurn
      case c: pullCardNotAllowedEvent => state = pullCardNotAllowed
      case d: unknownCommandEvent => state = unknownCommand
    }
    state
  }

  def pushCardNotAllowed = println("Du kannst diese Karte nicht legen")
  def enemyTurn = println("Gegner ist an der Reihe")
  def pullCardNotAllowed = println("Du kannst keine Karte ziehen, da du eine Karte legen kannst")
  def unknownCommand = println("Befehl nicht bekannt")

}

package de.htwg.se.uno.util

trait Event

case class pushCardNotAllowedEvent() extends Event {
  def pushCardNotAllowed = println("Du kannst diese Karte nicht legen")
}

case class enemyTurnEvent() extends Event {
  def enemyTurn = println("Gegner ist an der Reihe")
}

case class pullCardNotAllowedEvent() extends Event {
  def pullCardNotAllowed = println("Du kannst keine Karte ziehen, da du eine Karte legen kannst")
}

case class unknownCommandEvent() extends Event {
  def unknownCommand = println("Befehl nicht bekannt")
}

case class yourTurnEvent() extends Event {
  def yourTurn: Unit = println("Du bist dran. Mögliche Befehle: q, n, s [Karte], g, u, r")
}

case class gameStartEvent() extends Event {
  def gameStart: Unit = println("Willkommen zu unserem Uno Spiel")
}
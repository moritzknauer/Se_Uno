package de.htwg.se.uno.util

trait Status

case class pushCardNotAllowedEvent() extends Status {
  def pushCardNotAllowed = println("Du kannst diese Karte nicht legen")
}

case class enemyTurnEvent() extends Status {
  def enemyTurn = println("Gegner ist an der Reihe")
}

case class pullCardNotAllowedEvent() extends Status {
  def pullCardNotAllowed = println("Du kannst keine Karte ziehen, da du eine Karte legen kannst")
}

case class unknownCommandEvent() extends Status {
  def unknownCommand = println("Befehl nicht bekannt")
}

case class yourTurnEvent() extends Status {
  def yourTurn: Unit = println("Du bist dran. Mögliche Befehle: q, n, s [Karte], g, u, r")
}

case class gameStartEvent() extends Status {
  def gameStart: Unit = println("Willkommen zu unserem Uno Spiel")
}
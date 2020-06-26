package de.htwg.se.uno.controller

trait Status

case class pushCardNotAllowedEvent() extends Status {
  def pushCardNotAllowed = "Du kannst diese Karte nicht legen"
}

case class enemyTurnEvent() extends Status {
  def enemyTurn = "Gegner ist an der Reihe"
}

case class pullCardNotAllowedEvent() extends Status {
  def pullCardNotAllowed = "Du kannst keine Karte ziehen, da du eine Karte legen kannst"
}

case class unknownCommandEvent() extends Status {
  def unknownCommand = "Befehl nicht bekannt"
}

case class yourTurnEvent() extends Status {
  def yourTurn = "Du bist dran. Mögliche Befehle: q, n, t, s [Karte], p [Karte], g, u, r"
}

case class pushableEvent() extends Status {
  def pushable = "Diese Karte kannst du legen"
}

case class notPushableEvent() extends Status {
  def notPushable = "Diese Karte kannst du nicht legen"
}

case class wonEvent() extends Status {
  def won = "Glückwunsch du hast gewonnen"
}

case class lostEvent() extends Status {
  def lost = "Du hast leider verloren"
}
package de.htwg.se.uno.util

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
  def yourTurn = "Du bist dran. Mögliche Befehle: q, n, s [Karte], g, u, r"
}

case class gameStartEvent() extends Status {
  def gameStart = "Willkommen zu unserem Uno Spiel"
}

case class idleEvent() extends Status {
  def idle = ""
}

case class pushableEvent() extends Status {
  def pushable = "Card is pushable"
}

case class notPushableEvent() extends Status {
  def notPushable = "Card is not pushable"
}

case class wonEvent() extends Status {
  def won = "Game won"
}

case class lostEvent() extends Status {
  def lost = "Game lost"
}
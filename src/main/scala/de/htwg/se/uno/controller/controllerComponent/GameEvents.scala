package de.htwg.se.uno.controller.controllerComponent

trait GameEvents

case class pushCardNotAllowedEvent() extends GameEvents {
  def pushCardNotAllowed = "Du kannst diese Karte nicht legen"
}

case class enemyTurnEvent() extends GameEvents {
  def enemyTurn = "Gegner ist an der Reihe"
}

case class pullCardNotAllowedEvent() extends GameEvents {
  def pullCardNotAllowed = "Du kannst keine Karte ziehen, da du eine Karte legen kannst"
}

case class unknownCommandEvent() extends GameEvents {
  def unknownCommand = "Befehl nicht bekannt"
}

case class yourTurnEvent() extends GameEvents {
  def yourTurn = "Du bist dran. Mögliche Befehle: q, n, t, s [Karte], g, u, r"
}

case class wonEvent() extends GameEvents {
  def won = "Glückwunsch du hast gewonnen"
}

case class lostEvent() extends GameEvents {
  def lost = "Du hast leider verloren"
}

case class undoEvent() extends GameEvents {
  def undo = "Zug rückgängig gemacht"
}

case class redoEvent() extends GameEvents {
  def redo = "Zug wiederhergestellt"
}
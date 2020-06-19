package de.htwg.se.uno.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, WON, LOST = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    WON ->"Game won",
    LOST ->"Game lost")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }
}

package de.htwg.se.uno.model.gameComponent.gameBaseImpl

object Value extends Enumeration {
  type Values = Value
  val Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DirectionChange,
  Suspend, PlusTwo, PlusFour, ColorChange = Value
}

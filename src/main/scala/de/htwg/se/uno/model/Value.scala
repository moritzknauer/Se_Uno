package de.htwg.se.uno.model

object Value extends Enumeration {
  type Values = Value
  val Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DirectionChange, Suspend, PlusTwo = Value
}
object Special extends Enumeration {
  type Special = Value
  val PlusFour, ColorChange = Value
}

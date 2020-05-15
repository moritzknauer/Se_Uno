package scala



object CardOptions {
  object Color extends Enumeration {
    type Color = Value
    val Red, Yellow, Green, Blue = Value
  }

  object Value extends Enumeration {
    type Values = Value
    val Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DirectionChange,
    Suspend, PlusTwo = Value
  }

  object Special extends Enumeration {
    type Special = Value
    val PlusFour, ColorChange , Uno = Value
  }

  object Black extends Enumeration {
    type Black = Value
    val Black = Value
  }
}

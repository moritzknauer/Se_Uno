package scala

object CardOptions {
  object Color extends Enumeration {
    type Color = Value
    val Red, Yellow, Green, Blue, Black = Value
  }

  object Value extends Enumeration {
    type Values = Value
    val Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DirectionChange,
    Suspend, PlusTwo, PlusFour, ColorChange , Uno = Value
  }
}

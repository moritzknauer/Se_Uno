package de.htwg.se.uno.model

import de.htwg.se.uno.model.Black.Black
import de.htwg.se.uno.model.Value.Values
import de.htwg.se.uno.model.Color.Color
import de.htwg.se.uno.model.Special.Special
/*
case class Card(color:Color, value:Values){
  override def toString:String = {
    value match {
      case Value.Zero => color.toString.charAt(0) + " 0"
      case Value.One => color.toString.charAt(0) + " 1"
      case Value.Two => color.toString.charAt(0) + " 2"
      case Value.Three => color.toString.charAt(0) + " 3"
      case Value.Four => color.toString.charAt(0) + " 4"
      case Value.Five => color.toString.charAt(0) + " 5"
      case Value.Six => color.toString.charAt(0) + " 6"
      case Value.Seven => color.toString.charAt(0) + " 7"
      case Value.Eight => color.toString.charAt(0) + " 8"
      case Value.Nine => color.toString.charAt(0) + " 9"
      case Value.Suspend => color.toString.charAt(0) + " S"
      case Value.DirectionChange => color.toString.charAt(0) + " D"
      case Value.ColorChange => color.toString.charAt(0) + " C"
      case Value.PlusTwo => color.toString.charAt(0) + "+2"
      case Value.PlusFour => color.toString.charAt(0) + "+4"
    }
  }
}
*/

trait Card {
  override def toString: String = super.toString
}

class ColorCard(color: Color, value: Values) extends Card {
  override def toString: String = {
    value match {
      case Value.Zero => color.toString.charAt(0) + " 0"
      case Value.One => color.toString.charAt(0) + " 1"
      case Value.Two => color.toString.charAt(0) + " 2"
      case Value.Three => color.toString.charAt(0) + " 3"
      case Value.Four => color.toString.charAt(0) + " 4"
      case Value.Five => color.toString.charAt(0) + " 5"
      case Value.Six => color.toString.charAt(0) + " 6"
      case Value.Seven => color.toString.charAt(0) + " 7"
      case Value.Eight => color.toString.charAt(0) + " 8"
      case Value.Nine => color.toString.charAt(0) + " 9"
      case Value.Suspend => color.toString.charAt(0) + " S"
      case Value.DirectionChange => color.toString.charAt(0) + " D"
      case Value.PlusTwo => color.toString.charAt(0) + "+2"
    }
  }
}

class SpecialCard(color: Black, value: Special) extends Card {
  override def toString: String = {
    value match {
      case Special.ColorChange => color.toString.charAt(0) + " C"
      case Special.PlusFour => color.toString.charAt(0) + "+4"
    }
  }
}

object Card {
  def apply(kind: Int): Card = kind match{
    case 0 => new ColorCard(Color.Blue, Value.Zero)
    case 1 => new ColorCard(Color.Blue, Value.One)
    case 2 => new ColorCard(Color.Blue, Value.Two)
    case 3 => new ColorCard(Color.Blue, Value.Three)
    case 4 => new ColorCard(Color.Blue, Value.Four)
    case 5 => new ColorCard(Color.Blue, Value.Five)
    case 6 => new ColorCard(Color.Blue, Value.Six)
    case 7 => new ColorCard(Color.Blue, Value.Seven)
    case 8 => new ColorCard(Color.Blue, Value.Eight)
    case 9 => new ColorCard(Color.Blue, Value.Nine)
    case 10 => new ColorCard(Color.Blue, Value.Suspend)
    case 11 => new ColorCard(Color.Blue, Value.DirectionChange)
    case 12 => new ColorCard(Color.Blue, Value.PlusTwo)

    case 13 => new ColorCard(Color.Red, Value.Zero)
    case 14 => new ColorCard(Color.Red, Value.One)
    case 15 => new ColorCard(Color.Red, Value.Two)
    case 16 => new ColorCard(Color.Red, Value.Three)
    case 17 => new ColorCard(Color.Red, Value.Four)
    case 18 => new ColorCard(Color.Red, Value.Five)
    case 19 => new ColorCard(Color.Red, Value.Six)
    case 20 => new ColorCard(Color.Red, Value.Seven)
    case 21 => new ColorCard(Color.Red, Value.Eight)
    case 22 => new ColorCard(Color.Red, Value.Nine)
    case 23 => new ColorCard(Color.Red, Value.Suspend)
    case 24 => new ColorCard(Color.Red, Value.DirectionChange)
    case 25 => new ColorCard(Color.Red, Value.PlusTwo)

    case 26 => new ColorCard(Color.Yellow, Value.Zero)
    case 27 => new ColorCard(Color.Yellow, Value.One)
    case 28 => new ColorCard(Color.Yellow, Value.Two)
    case 29 => new ColorCard(Color.Yellow, Value.Three)
    case 30 => new ColorCard(Color.Yellow, Value.Four)
    case 31 => new ColorCard(Color.Yellow, Value.Five)
    case 32 => new ColorCard(Color.Yellow, Value.Six)
    case 33 => new ColorCard(Color.Yellow, Value.Seven)
    case 34 => new ColorCard(Color.Yellow, Value.Eight)
    case 35 => new ColorCard(Color.Yellow, Value.Nine)
    case 36 => new ColorCard(Color.Yellow, Value.Suspend)
    case 37 => new ColorCard(Color.Yellow, Value.DirectionChange)
    case 38 => new ColorCard(Color.Yellow, Value.PlusTwo)

    case 39 => new ColorCard(Color.Green, Value.Zero)
    case 40 => new ColorCard(Color.Green, Value.One)
    case 41 => new ColorCard(Color.Green, Value.Two)
    case 42 => new ColorCard(Color.Green, Value.Three)
    case 43 => new ColorCard(Color.Green, Value.Four)
    case 44 => new ColorCard(Color.Green, Value.Five)
    case 45 => new ColorCard(Color.Green, Value.Six)
    case 46 => new ColorCard(Color.Green, Value.Seven)
    case 47 => new ColorCard(Color.Green, Value.Eight)
    case 48 => new ColorCard(Color.Green, Value.Nine)
    case 49 => new ColorCard(Color.Green, Value.Suspend)
    case 50 => new ColorCard(Color.Green, Value.DirectionChange)
    case 51 => new ColorCard(Color.Green, Value.PlusTwo)

    case 52 => new SpecialCard(Black.Schwarz, Special.ColorChange)
    case 53 => new SpecialCard(Black.Schwarz, Special.PlusFour)
  }
}



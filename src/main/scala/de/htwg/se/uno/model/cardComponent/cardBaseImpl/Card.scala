package de.htwg.se.uno.model.cardComponent.cardBaseImpl

import de.htwg.se.uno.model.cardComponent.cardBaseImpl
import de.htwg.se.uno.model.cardComponent.cardBaseImpl.Color.Color
import de.htwg.se.uno.model.cardComponent.cardBaseImpl.Value.Values

case class Card(color:Color, value:Values){
  override def toString:String = {
    value match {
      case cardBaseImpl.Value.Zero => color.toString.charAt(0) + " 0"
      case cardBaseImpl.Value.One => color.toString.charAt(0) + " 1"
      case cardBaseImpl.Value.Two => color.toString.charAt(0) + " 2"
      case cardBaseImpl.Value.Three => color.toString.charAt(0) + " 3"
      case cardBaseImpl.Value.Four => color.toString.charAt(0) + " 4"
      case cardBaseImpl.Value.Five => color.toString.charAt(0) + " 5"
      case cardBaseImpl.Value.Six => color.toString.charAt(0) + " 6"
      case cardBaseImpl.Value.Seven => color.toString.charAt(0) + " 7"
      case cardBaseImpl.Value.Eight => color.toString.charAt(0) + " 8"
      case cardBaseImpl.Value.Nine => color.toString.charAt(0) + " 9"
      case cardBaseImpl.Value.Suspend => color.toString.charAt(0) + " S"
      case cardBaseImpl.Value.DirectionChange => color.toString.charAt(0) + " D"
      case cardBaseImpl.Value.ColorChange => color.toString.charAt(0) + " C"
      case cardBaseImpl.Value.PlusTwo => color.toString.charAt(0) + "+2"
      case cardBaseImpl.Value.PlusFour => color.toString.charAt(0) + "+4"
    }
  }

  def toGuiString:String = {
    value match {
      case cardBaseImpl.Value.Zero => " 0 "
      case cardBaseImpl.Value.One => " 1 "
      case cardBaseImpl.Value.Two => " 2 "
      case cardBaseImpl.Value.Three => " 3 "
      case cardBaseImpl.Value.Four => " 4 "
      case cardBaseImpl.Value.Five => " 5 "
      case cardBaseImpl.Value.Six => " 6 "
      case cardBaseImpl.Value.Seven => " 7 "
      case cardBaseImpl.Value.Eight => " 8 "
      case cardBaseImpl.Value.Nine => " 9 "
      case cardBaseImpl.Value.Suspend => " S "//⊘
      case cardBaseImpl.Value.DirectionChange => " D "//⇆
      case cardBaseImpl.Value.ColorChange => " C "
      case cardBaseImpl.Value.PlusTwo => "+ 2"
      case cardBaseImpl.Value.PlusFour => "+ 4"
    }
  }
}

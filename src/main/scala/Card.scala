package scala

import CardOptions.Color.Color
import CardOptions.Value.Values
import scala.CardOptions.{Color, Value}


case class Card(color:Color, value:Values){
  override def toString:String = {
    color match {
      case Color.Blue =>
        value match {
          case Value.Zero => "B 0"
          case Value.One => "B 1"
          case Value.Two => "B 2"
          case Value.Three => "B 3"
          case Value.Four => "B 4"
          case Value.Five => "B 5"
          case Value.Six => "B 6"
          case Value.Seven => "B 7"
          case Value.Eight => "B 8"
          case Value.Nine => "B 9"
          case Value.Suspend => "B S"
          case Value.DirectionChange => "B D"
          case Value.PlusTwo => "B+2"
        }
      case Color.Red =>
        value match {
          case Value.Zero => "R 0"
          case Value.One => "R 1"
          case Value.Two => "R 2"
          case Value.Three => "R 3"
          case Value.Four => "R 4"
          case Value.Five => "R 5"
          case Value.Six => "R 6"
          case Value.Seven => "R 7"
          case Value.Eight => "R 8"
          case Value.Nine => "R 9"
          case Value.Suspend => "R S"
          case Value.DirectionChange => "R D"
          case Value.PlusTwo => "R+2"
        }
      case Color.Yellow =>
        value match {
          case Value.Zero => "Y 0"
          case Value.One => "Y 1"
          case Value.Two => "Y 2"
          case Value.Three => "Y 3"
          case Value.Four => "Y 4"
          case Value.Five => "Y 5"
          case Value.Six => "Y 6"
          case Value.Seven => "Y 7"
          case Value.Eight => "Y 8"
          case Value.Nine => "Y 9"
          case Value.Suspend => "Y S"
          case Value.DirectionChange => "Y D"
          case Value.PlusTwo => "Y+2"
        }
      case Color.Green =>
        value match {
          case Value.Zero => "G 0"
          case Value.One => "G 1"
          case Value.Two => "G 2"
          case Value.Three => "G 3"
          case Value.Four => "G 4"
          case Value.Five => "G 5"
          case Value.Six => "G 6"
          case Value.Seven => "G 7"
          case Value.Eight => "G 8"
          case Value.Nine => "G 9"
          case Value.Suspend => "G S"
          case Value.DirectionChange => "G D"
          case Value.PlusTwo => "G+2"
        }
      case Color.Black =>
        value match {
          case Value.PlusFour => "S+4"
          case Value.ColorChange => "S C"
        }
    }
  }

}


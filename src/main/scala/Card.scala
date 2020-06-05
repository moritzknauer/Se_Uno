package scala

import CardOptions.Color.Color
import CardOptions.Value.Values
import scala.CardOptions.{Color, Value}


case class Card(color:Color, value:Values){
  override def toString:String = {
    color match {
      case Color.Blue =>
        value match {
          case Value.Zero => "B0"
          case Value.One => "B1"
          case Value.Two => "B2"
          case Value.Three => "B3"
          case Value.Four => "B4"
          case Value.Five => "B5"
          case Value.Six => "B6"
          case Value.Seven => "B7"
          case Value.Eight => "B8"
          case Value.Nine => "B9"
          case Value.Suspend => "BS"
          case Value.DirectionChange => "BD"
          case Value.PlusTwo => "B+2"
        }
      case Color.Red =>
        value match {
          case Value.Zero => "R0"
          case Value.One => "R1"
          case Value.Two => "R2"
          case Value.Three => "R3"
          case Value.Four => "R4"
          case Value.Five => "R5"
          case Value.Six => "R6"
          case Value.Seven => "R7"
          case Value.Eight => "R8"
          case Value.Nine => "R9"
          case Value.Suspend => "RS"
          case Value.DirectionChange => "RD"
          case Value.PlusTwo => "R+2"
        }
      case Color.Yellow =>
        value match {
          case Value.Zero => "Y0"
          case Value.One => "Y1"
          case Value.Two => "Y2"
          case Value.Three => "Y3"
          case Value.Four => "Y4"
          case Value.Five => "Y5"
          case Value.Six => "Y6"
          case Value.Seven => "Y7"
          case Value.Eight => "Y8"
          case Value.Nine => "Y9"
          case Value.Suspend => "YS"
          case Value.DirectionChange => "YD"
          case Value.PlusTwo => "Y+2"
        }
      case Color.Green =>
        value match {
          case Value.Zero => "G0"
          case Value.One => "G1"
          case Value.Two => "G2"
          case Value.Three => "G3"
          case Value.Four => "G4"
          case Value.Five => "G5"
          case Value.Six => "G6"
          case Value.Seven => "G7"
          case Value.Eight => "G8"
          case Value.Nine => "G9"
          case Value.Suspend => "GS"
          case Value.DirectionChange => "GD"
          case Value.PlusTwo => "G+2"
        }
      case Color.Black =>
        value match {
          case Value.PlusFour => "S+4"
          case Value.ColorChange => "SC"
        }
    }
  }

}


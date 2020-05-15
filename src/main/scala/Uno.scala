package scala

import scala.CardOptions._


object Uno {
  def main(args:Array[String]) = {
    val card1 = Card(Color.Blue, Value.Seven)

    val card2 = Card(Color.Red, Value.Nine)

    val card3 = Card(Color.Yellow, Value.DirectionChange)

    val card4 = Card(Color.Red, Value.Suspend)

    val card5 = Card(Color.Green, Value.PlusTwo)

    val cardsCovered: List[Card] = List()
    val cardsRevealed: List[Card] = List()
    val handCards: List[Card] = List(card4, card5)

    val cardsCovered: List[Card] = List(card1, card2)
    val cardsRevealed: List[Card] = List(card3)
    val cardsRevealed: List[Card] = List(card3, card2)
    val cardsCovered: List[Card] = List(card1)
  }
}

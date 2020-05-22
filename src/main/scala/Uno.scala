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

    //val cardsCovered: List[Card] = List(card1, card2)
    //val cardsRevealed: List[Card] = List(card3)
    //val cardsRevealed: List[Card] = List(card3, card2)
    //val cardsCovered: List[Card] = List(card1)

    print(handCards)

    printf("\n")
    printf("\n")
    printf("⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝\n")
    printf("⎸       ⎹  ⎸      ⎹  ⎸       ⎹  ⎸      ⎹  ⎸       ⎹  ⎸       ⎹  ⎸      ⎹  ⎸       ⎹\n")
    printf("⎸  Uno  ⎹  ⎸  Uno ⎹  ⎸  Uno  ⎹  ⎸  Uno ⎹  ⎸  Uno  ⎹  ⎸  Uno  ⎹  ⎸  Uno ⎹  ⎸  Uno  ⎹\n")
    printf("⎸       ⎹  ⎸      ⎹  ⎸       ⎹  ⎸      ⎹  ⎸       ⎹  ⎸       ⎹  ⎸      ⎹  ⎸       ⎹\n")
    printf("⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟\n")
    printf("\n")
    printf("\n")
    printf("\n")
    printf("                     ⌜ ‾‾‾‾ ⌝                       ⌜ ‾‾‾‾ ⌝\n")
    printf("                     ⎸       ⎹                       ⎸      ⎹\n")
    printf("                     ⎸  UNO  ⎹                       ⎸  R 8 ⎹\n")
    printf("                     ⎸       ⎹                       ⎸      ⎹\n")
    printf("                     ⌞ ____ ⌟                       ⌞ ____ ⌟\n")
    printf("\n")
    printf("\n")
    printf("\n")
    printf("⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝  ⌜ ‾‾‾‾ ⌝\n")
    printf("⎸       ⎹  ⎸      ⎹  ⎸       ⎹  ⎸      ⎹  ⎸       ⎹  ⎸       ⎹  ⎸      ⎹  ⎸       ⎹\n")
    printf("⎸  G 6  ⎹  ⎸  B 2 ⎹  ⎸  Y 9  ⎹  ⎸  S+4 ⎹  ⎸  Y+2  ⎹  ⎸  G S  ⎹  ⎸  R D ⎹  ⎸  SCC  ⎹\n")
    printf("⎸       ⎹  ⎸      ⎹  ⎸       ⎹  ⎸      ⎹  ⎸       ⎹  ⎸       ⎹  ⎸      ⎹  ⎸       ⎹\n")
    printf("⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟  ⌞ ____ ⌟\n")

    printf("test\n")
  }
}

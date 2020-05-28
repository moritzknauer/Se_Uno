package scala

import scala.CardOptions.Color.Color
import scala.CardOptions.Value.Values
import scala.CardOptions._
import scala.Player
import scala.collection.mutable.ListBuffer


object Uno {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)

    val cardsCovered: List[Card] = List()
    val cardsRevealed: List[Card] = List()
    val enemyCarsd: List[Card] = List()
    val handCards: List[Card] = List()

    var cards = new ListBuffer[Card]()

    for (color <- Color.values) {
      for (value <- Value.values) {
        if (value == Value.Zero) {
          cards += Card(color, value)
        } else {
          cards += Card(color, value)
          cards += Card(color, value)
        }
      }
    }

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

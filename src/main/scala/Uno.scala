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

    var cardsCovered: List[Card] = List()
    var cardsRevealed: List[Card] = List()
    var enemyCards: List[Card] = List()
    var handCards: List[Card] = List()

    var cards = new ListBuffer[Card]()

    for (color <- Color.values) {
      for (value <- Value.values) {
        if (value == Value.Zero && color != Color.Black) {
          cards += Card(color, value)
        } else if (color == Color.Black && (value == Value.ColorChange || value == Value.PlusFour)) {
          for (i <- 0 to 3)
            cards += Card(color, value)
        } else if (color != Color.Black) {
          for (i <- 0 to 1)
            cards += Card(color, value)
        }
      }
    }

    println(cards)

    var playingField =
      """
        | ┌-------┐  ┌-------┐  ┌-------┐  ┌-------┐  ┌-------┐  ┌-------┐  ┌-------┐
        | |       |  |       |  |       |  |       |  |       |  |       |  |       |
        | |  Uno  |  |  Uno  |  |  Uno  |  |  Uno  |  |  Uno  |  |  Uno  |  |  Uno  |
        | |       |  |       |  |       |  |       |  |       |  |       |  |       |
        | └-------┘  └-------┘  └-------┘  └-------┘  └-------┘  └-------┘  └-------┘
        |
        |                       ┌-------┐            ┌-------┐
        |                       |       |            |       |
        |                       |  Uno  |            |  R 8  |
        |                       |       |            |       |
        |                       └-------┘            └-------┘
        |
        | ┌-------┐  ┌-------┐  ┌-------┐  ┌-------┐  ┌-------┐  ┌-------┐  ┌-------┐
        | |       |  |       |  |       |  |       |  |       |  |       |  |       |
        | |  G 6  |  |  B 2  |  |  Y 9  |  |  B+4  |  |  Y+2  |  |  G S  |  |  R D  |
        | |       |  |       |  |       |  |       |  |       |  |       |  |       |
        | └-------┘  └-------┘  └-------┘  └-------┘  └-------┘  └-------┘  └-------┘
        |
        |""".stripMargin

    printf(playingField)

    playingField = playingField.replace("R 8", "R 2")

    printf(playingField)

  }
}

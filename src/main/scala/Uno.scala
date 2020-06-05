package scala

import scala.CardOptions.Color.Color
import scala.CardOptions.Value.Values
import scala.CardOptions._
import scala.Player
import scala.Game
import scala.collection.mutable.ListBuffer

object Uno {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)

    var cardsCovered = new ListBuffer[Card]()
    var cardsRevealed = new ListBuffer[Card]()
    var enemyCards = new ListBuffer[Card]()
    var handCards = new ListBuffer[Card]()

    val game = Game()
    game.initializeGame(7, cardsCovered, cardsRevealed, enemyCards, handCards)

    println(cardsCovered)
    println(handCards)
    println(enemyCards)
    println(cardsRevealed)

    val playingField =
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

  }
}

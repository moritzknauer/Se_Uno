package scala

import scala.CardOptions.Color.Color
import scala.CardOptions.Value.Values
import scala.CardOptions._
import scala.Player
import scala.collection.mutable.ListBuffer

object Uno {
  var cardsCovered = new ListBuffer[Card]()
  var cardsRevealed = new ListBuffer[Card]()
  var enemyCards = new ListBuffer[Card]()
  var handCards = new ListBuffer[Card]()

  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)

    initializeGame(7)

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

  def initializeGame(numOfPlayerCards: Int): Boolean = {
    var cards = new ListBuffer[Card]()

    for (color <- Color.values) {
      for (value <- Value.values) {
        if (value == Value.Zero && color != Color.Black) {
          cards += Card(color, value)
        } else if (color == Color.Black && (value == Value.ColorChange || value == Value.PlusFour)) {
          for (i <- 0 to 3)
            cards += Card(color, value)
        } else if (color != Color.Black && (value != Value.PlusFour && value != Value.ColorChange)) {
          for (i <- 0 to 1)
            cards += Card(color, value)
        }
      }
    }

    println(cards)

    var cardsMixed = cards

    var n = 108
    for (i <- 0 to 107) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsCovered = cardsMixed.slice(p - 1, p) ++ cardsCovered
      cardsMixed = cardsMixed.take(p - 1) ++ cardsMixed.drop(p)
      n -= 1
    }

    n = numOfPlayerCards
    for (i <- 1 to numOfPlayerCards) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      handCards = cardsCovered.slice(p - 1, p) ++ handCards
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }

    n = numOfPlayerCards
    for (i <- 1 to numOfPlayerCards) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      enemyCards = cardsCovered.slice(p - 1, p) ++ enemyCards
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }

    n = 1
    for (i <- 0 to 0) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsRevealed = cardsCovered.slice(p - 1, p) ++ cardsRevealed
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }
    true
  }
}

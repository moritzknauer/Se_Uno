package scala

import scala.CardOptions.Color.Color
import scala.CardOptions.Value.Values
import scala.CardOptions._
import scala.Player
import scala.Game
import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine

object Uno {
  var cardsCovered = new ListBuffer[Card]()
  var cardsRevealed = new ListBuffer[Card]()
  var enemyCards = new ListBuffer[Card]()
  var handCards = new ListBuffer[Card]()
  val tui = new Tui

  def main(args: Array[String]): Unit = {
    var input: String = ""

    println("Vornamen eingeben:")
    input = readLine()
    val student = Player(input)
    println("Hallo " + student.name)
    println("Gewünschte Anzahl Karten pro Spieler eingeben:")
    input = readLine()
    var game = Game(input.toInt)


    do {
      println("Spielfeld: \n" + game.toString)
      input = readLine()
      game = tui processInputLine(input, game)
    } while (input != "q")
  }
}

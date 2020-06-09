package scala

import controller.Controller

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
  val controller = new Controller(Game())
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""

    println("Vornamen eingeben:")
    input = readLine()
    val student = Player(input)
    println("Hallo " + student.name)

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}

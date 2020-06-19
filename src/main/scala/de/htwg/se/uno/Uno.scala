package de.htwg.se.uno

import de.htwg.se.uno.aview.Tui
import de.htwg.se.uno.controller.Controller
import de.htwg.se.uno.model.Game

import scala.io.StdIn.readLine

object Uno {
  val controller = new Controller(Game())
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      println("Mögliche Befehle: q, n, s [Karte], g, u, r")
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}

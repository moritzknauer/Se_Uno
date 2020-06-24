package de.htwg.se.uno

import de.htwg.se.uno.aview.Tui
import de.htwg.se.uno.aview.gui.SwingGui
import de.htwg.se.uno.controller.{Controller, GameSizeChanged}
import de.htwg.se.uno.model.Game
import de.htwg.se.uno.util.{State, yourTurnEvent}

import scala.io.StdIn.readLine

object Uno {
  val controller = new Controller(Game())
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.publish(new GameSizeChanged())

  def main(args: Array[String]): Unit = {
    var input: String = ""

    gui.open()
    println(State.state)

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}

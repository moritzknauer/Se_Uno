package de.htwg.se.uno

import de.htwg.se.uno.aview.Tui
import de.htwg.se.uno.aview.gui.SwingGui
import de.htwg.se.uno.controller.controllerComponent.GameSizeChanged
import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.uno.model.Game

import scala.io.StdIn.readLine

object Uno {
  val controller = new Controller(new Game())
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.publish(new GameSizeChanged())

  def main(args: Array[String]): Unit = {
    var input: String = ""

    println(controller.gameToString)
    println(controller.gameStatus("idle"))

    gui.open()

    do {
      input = readLine()
      val s = tui.processInputLine(input).get
    } while (input != "q")
  }
}

package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.Controller
import de.htwg.se.uno.util.Observer


class Tui(controller: Controller) extends Observer {
  controller.add(this)


  def processInputLine(input: String): Unit = {
    val wf:Array[String] = input.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü^0-9/+]+")
    wf(0) match {
      case "n" => {
        if (wf.length == 2) {
          controller.createGame(wf(1).toInt)
        } else {
          controller.createGame()
        }
      }
      case "q" =>
      case "s" => {
        var s = ""
        if (wf.length == 3) {
          s = wf(1) + " " + wf(2)
        } else {
          s = wf(1)
        }
        controller.set(s)
      }
      case "g" => controller.set("")
      case "r" => controller.redo
      case "u" => controller.undo
      case _ => println("Befehl nicht bekannt")
    }
  }

  override def update: Boolean = {
    println(controller.gameToString)
    println(GameStatus.message(controller.gameStatus))
    controller.gameStatus=GameStatus.IDLE
    true}
}

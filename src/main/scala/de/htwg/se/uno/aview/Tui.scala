package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.Controller
import de.htwg.se.uno.util.Observer


class Tui(controller: Controller) extends Observer {
  controller.add(this)


  def processInputLine(input: String): Unit = {
    val wf:Array[String] = input.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü^0-9/+]+")
    wf(0) match {
      case "n" => controller.createGame()
      case "q" =>
      case "s" => {
        var s = ""
        if (wf.length == 3) {
          s = wf(1) + " " + wf(2)
        } else {
          s = wf(1)
        }
        if (controller.pushable(s)) {
          controller.pushCard(s)
          println("Gegner ist an der Reihe")
          controller.enemy()
        } else {
          println("Du kannst diese Karte nicht legen")
        }
      }
      case "g" => {
        if(controller.pullable()) {
          controller.pull()
          println("Gegner ist an der Reihe")
          return controller.enemy()
        }
        println("Du kannst keine Karte ziehen, da du eine Karte legen kannst")
      }
      case _ => println("Befehl nicht bekannt")
    }
  }

  override def update: Boolean = {println(controller.gameToString);true}
}

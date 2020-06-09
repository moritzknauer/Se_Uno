package scala.aview

import scala.controller.Controller
import scala.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)


  def processInputLine(input: String): Unit = {
    val wf:Array[String] = input.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü^0-9/+]+")
    wf(0) match {
      case "n" => controller.createGame()
      case "q" =>
      case "s" => {
        if (wf.length == 3 ) {
          val s = (wf(1) + " " + wf(2))
          if (controller.equalsCard(s)) {
            val card = controller.getCard(s)
            if(controller.pushable(card)) {
              controller.pushCard(card)
              println("Gegner ist an der Reihe")
              controller.enemy()
            } else {
              println("Du kannst diese Karte nicht legen")
            }
          } else {
            println("Die zu legende Karte ist nicht auf deiner Hand :(")
          }
        } else {
          if (controller.equalsCard(wf(1))) {
            val card = controller.getCard(wf(1))
            if(controller.pushable(card)) {
              controller.pushCard(card)
              println("Gegner ist an der Reihe")
              return controller.enemy()
            } else {
              println("Du kannst diese Karte nicht legen")
            }
          }
          println("Die zu legende Karte ist nicht auf deiner Hand :(")
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
      case _ => {
        println("Eingegebener Befehl nicht bekannt. Mögliche Befehle: q, s [Karte], g \n")
      }
    }
  }

  override def update: Unit = println(controller.gameToString)
}

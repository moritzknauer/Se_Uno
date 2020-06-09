package scala


import controller.Controller

import scala.Uno.handCards

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
          for (i <- 1 to handCards.length) {
            if (s.equals(handCards(i-1).toString)) {
              if(controller.pushable(handCards(i-1))) {
                controller.pushCard(handCards(i - 1))
                println(controller.toString())
                println("Gegner ist an der Reihe")
                return controller.enemy()
              } else {
                println("Du kannst diese Karte nicht legen")
              }
            }
          }
          println("Die zu legende Karte ist nicht auf deiner Hand :(")
        } else {
          for (i <- 1 to handCards.length) {
            if (wf(1).equals(handCards(i-1).toString)) {
              if(controller.pushable(handCards(i-1))) {
                controller.pushCard(handCards(i - 1))
                println(controller.toString())
                println("Gegner ist an der Reihe")
                return controller.enemy()
              } else {
                println("Du kannst diese Karte nicht legen")
              }
            }
          }
          println("Die zu legende Karte ist nicht auf deiner Hand :(")
        }
      }
      case "g" => {
        if(controller.pullable()) {
          controller.pull()
          println(controller.toString())
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

  override def update: Unit = { println(controller.gameToString);true}
}

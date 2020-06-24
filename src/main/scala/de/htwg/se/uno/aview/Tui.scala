package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.{Controller, GameChanged, GameSizeChanged}
import de.htwg.se.uno.util.{State, idleEvent, unknownCommandEvent}

import scala.swing.Reactor


class Tui(controller: Controller) extends Reactor {
  listenTo(controller)
  def size = controller.game.numOfCards


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
        controller.set(input.substring(2))
      }
      case "g" => {
        controller.get()
      }
      case "r" => {
        controller.redo
        controller.redo
      }
      case "u" => {
        controller.undo
        controller.undo
      }
      case _ => State.handle(unknownCommandEvent())
    }
  }

  reactions += {
      case event: GameSizeChanged => printTui
      case event: GameChanged => printTui
  }

  def printTui: Unit = {
    println(controller.gameToString)
    println(State.state)
  }
}

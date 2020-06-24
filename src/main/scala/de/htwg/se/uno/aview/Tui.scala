package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.{Controller, GameChanged, GameNotChanged, GameSizeChanged, State, unknownCommandEvent}
import de.htwg.se.uno.model.InitializeTestGameStrategy

import scala.swing.Reactor


class Tui(controller: Controller) extends Reactor {
  listenTo(controller)

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
      case "t" => {
        controller.createTestGame()
      }
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
      case event: GameNotChanged => println(State.state)
  }

  def printTui: Unit = {
    println(controller.gameToString)
    println(State.state)
  }
}

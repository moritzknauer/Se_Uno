package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.{Controller, GameChanged, GameNotChanged, GameSizeChanged, State, unknownCommandEvent}

import scala.swing.Reactor
import scala.util.{Failure, Success, Try}


class Tui(controller: Controller) extends Reactor {
  listenTo(controller)

  def processInputLine(input: String): Try[String] = {
    val wf:Array[String] = input.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü^0-9/+]+")
    wf(0) match {
      case "n" => {
        if (wf.length == 2) {
          controller.createGame(wf(1).toInt)
          Success("Valid Command: " + input)
        } else {
          controller.createGame()
          Success("Valid Command: " + input)
        }
      }
      case "q" => Success("Valid Command: " + input)
      case "t" => {
        controller.createTestGame()
        Success("Valid Command: " + input)
      }
      case "s" => {
        controller.set(input.substring(2))
        Success("valid command: " + input)
      }
      case "p" => {
        val index = controller.getIndex(input.substring(2))
        controller.showPushable(2, index)
        Success("Valid Command: " + input)
      }
      case "g" => {
        controller.get()
        Success("Valid Command: " + input)
      }
      case "r" => {
        controller.redo
        controller.redo
        Success("Valid Command: " + input)
      }
      case "u" => {
        controller.undo
        controller.undo
        Success("Valid Command: " + input)
      }
      case _ => State.handle(unknownCommandEvent())
        Failure(new IllegalArgumentException("Wrong input: " + input))
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

package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.controllerComponent.{ControllerInterface, GameChanged, GameEnded, GameNotChanged, GameSizeChanged}

import scala.swing.Reactor
import scala.util.{Failure, Success, Try}


class Tui(controller: ControllerInterface) extends Reactor {
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
      case "q" => {
        System.exit(0)
        Success("Valid Command: " + input)
      }
      case "t" => {
        controller.createTestGame()
        Success("Valid Command: " + input)
      }
      case "s" => {
        controller.set(input.substring(2))
        Success("valid command: " + input)
      }
      case "g" => {
        controller.get()
        Success("Valid Command: " + input)
      }
      case "r" => {
        controller.redo
        Success("Valid Command: " + input)
      }
      case "u" => {
        controller.undo
        Success("Valid Command: " + input)
      }
      case "d" => {
        if (controller.nextTurn()) {
          controller.gameStatus("yourTurn")
          controller.publish(new GameNotChanged)
        } else {
          controller.enemy()
        }
        Success("Valid Command: " + input)
      }
      case _ => controller.gameStatus("unknownCommand")
        Failure(new IllegalArgumentException("Wrong input: " + input))
    }
  }

  reactions += {
      case event: GameSizeChanged => printTui
      case event: GameChanged => printTui
      case event: GameNotChanged => println(controller.gameStatus("idle"))
      case event: GameEnded => {
        println(controller.gameStatus("idle"))
        println("Starte neues Spiel oder beende")
      }
  }

  def printTui: Unit = {
    println(controller.gameToString)
    println(controller.gameStatus("idle"))
  }
}

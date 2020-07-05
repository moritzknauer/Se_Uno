package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.controllerComponent.{ControllerInterface, GameNotChanged}

import scala.swing._

class CardPanel (list: Int, index: Int, controller: ControllerInterface) extends FlowPanel {

  val coveredColor = new Color(0,0,0)
  val specialColor = new Color(128,128,128)
  val blueColor = new Color(0,0,255)
  val greenColor = new Color(0,255,0)
  val yellowColor = new Color(255,255,0)
  val redColor = new Color(255,0,0)
  val whiteColor = new Color(255,255,255)

  def cardText(list: Int, index: Int) : String = {
    controller.getCardText(list, index)
  }

  def cardTextColor(list: Int, index: Int) : Char = {
    val s = cardText(list, index)
    val c = s.charAt(0)
    c
  }

  def cardColor(list: Int, index: Int) : Color = {
    if (cardTextColor(list, index) == 'U') {
      coveredColor
    } else if (cardTextColor(list, index) == 'D') {
      whiteColor
    } else if (cardTextColor(list, index) == 'R') {
      redColor
    } else if (cardTextColor(list, index) == 'Y') {
      yellowColor
    } else if (cardTextColor(list, index) == 'G') {
      greenColor
    } else if (cardTextColor(list, index) == 'B') {
      blueColor
    } else {
      specialColor
    }
  }

  val card = new BoxPanel(Orientation.Vertical) {
    val button = Button(controller.getGuiCardText(list, index)) {
      if (list == 3 && index == 0) {
        controller.get
      } else if (list == 3 && index == 2) {
        if (controller.nextTurn()) {
          controller.gameStatus("yourTurn")
          controller.publish(new GameNotChanged)
        } else {
          controller.enemy()
        }
      } else if (list == 4) {
        controller.set(cardText(list, index))
      } else {
        controller.notPush
      }
    }
    if ((controller.getNumOfPlayers() == 2 && list == 3 && index != 2) || (controller.getNumOfPlayers() == 2 &&
          list != 3) || (list == 3 && index != 2) || (list == 4 && controller.getNumOfPlayers() == 3)) {
      button.font = new Font("Verdana", 1, 25)
      button.preferredSize_=(new Dimension(100,180))
      button.maximumSize_= (new Dimension(100, 180))
      button.minimumSize_=(new Dimension(100, 180))
    } else if (list == 3 && index == 2) {
      button.font = new Font("Verdana", 1, 25)
      button.preferredSize_=(new Dimension(200,180))
      button.maximumSize_= (new Dimension(200, 180))
      button.minimumSize_=(new Dimension(200, 180))
    } else {
      button.font = new Font("Verdana", 1, 10)
      button.preferredSize_=(new Dimension(60,108))
      button.maximumSize_= (new Dimension(60, 108))
      button.minimumSize_=(new Dimension(60, 108))
    }
    button.background = cardColor(list: Int, index: Int)
    contents += button
    listenTo(button)
    background = java.awt.Color.WHITE
    listenTo(controller)
  }
}

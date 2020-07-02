package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.controllerComponent.{ControllerInterface, GameEvent, GameNotChanged, pushCardNotAllowedEvent}
import de.htwg.se.uno.model.Card

import scala.swing._

class CardPanel (list: Int, index: Int, controller: ControllerInterface) extends FlowPanel {

  val coveredColor = new Color(0,0,0)
  val specialColor = new Color(128,128,128)
  val blueColor = new Color(0,0,255)
  val greenColor = new Color(0,255,0)
  val yellowColor = new Color(255,255,0)
  val redColor = new Color(255,0,0)

  def myCard(): Card = {
    controller.getCard(list, index)
  }

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

  val label =
    new Label {
      text = cardText(list, index)
      font = new Font("Verdana", 1, 36)
    }

  val card = new BoxPanel(Orientation.Vertical) {
    val button = Button(controller.getGuiCardText(list, index)) {
      if (list == 0 || (list == 1 && index == 1)) {
        controller.notPush
        GameEvent.handle(pushCardNotAllowedEvent())
        publish(new GameNotChanged)
      } else if (index == 0 && list == 1) {
        controller.get
      } else {
        controller.set(cardText(list, index))
      }
    }
    button.font = new Font("Verdana", 1, 25)
    button.preferredSize_=(new Dimension(100,180))
    button.maximumSize_= (new Dimension(100, 180))
    button.minimumSize_=(new Dimension(100, 180))
    button.background = cardColor(list: Int, index: Int)
    contents += button
    listenTo(button)
    background = java.awt.Color.WHITE
    listenTo(controller)
  }
}

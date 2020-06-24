package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.{Controller, GameChanged}
import de.htwg.se.uno.model.Card

import scala.swing._
import scala.swing.event.MouseClicked

class CardPanel (list: Int, index: Int, controller: Controller) extends FlowPanel {

  val coveredColor = new Color(0,0,0)
  val specialColor = new Color(128,128,128)
  val blueColor = new Color(0,0,255)
  val greenColor = new Color(0,255,0)
  val yellowColor = new Color(255,255,0)
  val redColor = new Color(255,0,0)

  def myCard(): Card = {
    list match{
      case 0 => controller.game.init.enemy.enemyCards(index)
      case 1 => {
        index match {
          case 0 => controller.game.init.cardsCovered.head
          case 1 => controller.game.init.cardsRevealed.head
        }
      }
      case 2 => controller.game.init.player.handCards(index)
    }
  }

  def cardText(list: Int, index: Int) : String = {
    if (list == 1 && index == 1) {
      controller.game.init.cardsRevealed.head.toString
    } else if (list == 2) {
      controller.game.init.player.handCards(index).toString
    } else {
      "Uno"
    }
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
    contents += label
    preferredSize = new Dimension(100, 180)
    background = cardColor(list: Int, index:Int)
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) => {
        controller.showPushable(list, index)
        repaint
      }
    }
  }



  def redraw = {}
}

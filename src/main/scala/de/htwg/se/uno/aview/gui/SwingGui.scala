package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.Controller

import scala.swing._
import scala.swing.event.Event
import scala.swing.Swing.LineBorder

class CardClicked(val index: Int) extends Event

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Uno"

  def pushpanel = new FlowPanel {
    contents += new Label("HandCards:")
    for {index <- 0 to controller.game.init.player.handCards.size} {
      val button = Button(if (index == 0) "Get" else controller.game.init.player.handCards(index-1).toString) {
      }
      button.preferredSize_=(new Dimension(30,30))
      contents += button
      listenTo(button)
    }

  }

  def gamePanel = new CardPanel(controller.game.numOfCards, controller) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK
    for {
      playerRow <- 0 until controller.game.init.player.handCards.length + controller.game.init.enemy.enemyCards.length-1
    } {
      contents += new CardPanel(controller.game.numOfCards, controller) {
        border = LineBorder(java.awt.Color.BLACK, 2)
        for {
          inner
        }
      }
    }
  }

  val statusline = new TextField(controller.statusText, 20)

  contents = new BorderPanel {
    add(pushpanel, BorderPanel.Position.North)
    add(gamePanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

}

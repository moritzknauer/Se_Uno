package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.Controller
import de.htwg.se.uno.util.State

import scala.swing._
import scala.swing.event.Event
import scala.swing.Swing.LineBorder
import scala.io.Source._

class CardClicked(val index: Int) extends Event

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Uno"
  var cards = Array.ofDim[CardPanel](3, controller.game.numOfCards)

  def pushpanel = new FlowPanel {
    contents += new Label("HandCards:")
    for {index <- 0 to controller.game.init.player.handCards.size} {
        val button = new Button(if (index == 0) "Get" else controller.game.init.player.handCards(index-1).toString) {
          controller.set(controller.game.init.player.handCards(index-1).toString)
      }
      button.preferredSize_=(new Dimension(30,30))
      contents += button
      listenTo(button)
    }

  }

  def gamePanel = new FlowPanel {
    contents += new FlowPanel()
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.WHITE
    for {
      enemy <- 0 until controller.game.init.enemy.enemyCards.length
    } {
      val cardPanel = new CardPanel(0, enemy, controller)
      cards(0)(enemy) = cardPanel
      contents += cardPanel
      listenTo(cardPanel)
    }
    contents += new FlowPanel()
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.WHITE
    for (i <- 0 to 1) {
      val cardPanel = new CardPanel(1, i, controller)
      cards(1)(i) = cardPanel
      contents += cardPanel
      listenTo(cardPanel)
    }
    contents += new FlowPanel()
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.WHITE
    for {
      player <- 0 until controller.game.init.player.handCards.length
    } {
      val cardPanel = new CardPanel(2, player, controller)
      cards(2)(player) = cardPanel
      contents += cardPanel
      listenTo(cardPanel)
    }
  }

  val statusline = new TextField(controller.statusText + State, 20)

  contents = new BorderPanel {
    add(pushpanel, BorderPanel.Position.North)
    add(gamePanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }



}
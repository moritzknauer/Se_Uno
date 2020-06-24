package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.{Controller, GameChanged, GameNotChanged, GameSizeChanged, State}

import scala.swing.BorderPanel.Position
import scala.swing._
import scala.swing.event.Key
import scala.swing.Swing.LineBorder

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Uno"

  def pushpanel = new FlowPanel {
    contents += new Label("HandCards:")
    for {index <- 1 to controller.game.init.player.handCards.length} {
        val button = Button(if (index == 0) "Get" else controller.game.init.player.handCards(index-1).toString) {
          controller.pushNext(controller.game.init.player.handCards(index-1).toString)
      }
      button.preferredSize_=(new Dimension(90,90))
      contents += button
      listenTo(button)
    }
  }

  def gamePanel = new GridPanel(3, 1) {
    contents += new GridPanel(1, controller.game.init.enemy.enemyCards.length) {
      border = LineBorder(java.awt.Color.WHITE, 20)
      background = java.awt.Color.WHITE
      for (enemy <- 1 to controller.game.init.enemy.enemyCards.length) {
        val cardPanel = new CardPanel(0, enemy - 1, controller)
        contents += cardPanel.card
        listenTo(cardPanel)
      }
    }
    contents += new GridPanel(1, 2) {
      border = LineBorder(java.awt.Color.WHITE, 20)
      background = java.awt.Color.WHITE
      for (i <- 0 to 1) {
        val cardPanel = new CardPanel(1, i, controller)
        contents += cardPanel.card
        listenTo(cardPanel)
      }
    }
    contents += new GridPanel(1, controller.game.init.player.handCards.length) {
      border = LineBorder(java.awt.Color.WHITE, 20)
      background = java.awt.Color.WHITE
      for (player <- 1 to controller.game.init.player.handCards.length) {
        val cardPanel = new CardPanel(2, player - 1, controller)
        contents += cardPanel.card
        listenTo(cardPanel)
      }
    }
  }

  var statusline = new TextField(State.state,30) {
    preferredSize = new Dimension(1,100)
    font = new Font("Verdana", 5, 36)
    background = java.awt.Color.WHITE
  }

  contents = new BorderPanel {
    add(pushpanel, BorderPanel.Position.North)
    add(gamePanel, Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Test") {controller.createTestGame()})
      contents += new MenuItem(Action("Random") {controller.createGame()})
      contents += new MenuItem(Action("Quit") {System.exit(0)})
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {controller.undo})
      contents += new MenuItem(Action("Redo") {controller.redo})
    }
    contents += new Menu("Get") {
      mnemonic = Key.G
      contents += new MenuItem(Action("Get") {controller.get()})
    }
    contents += new Menu("Set") {
      mnemonic = Key.S
      contents += new MenuItem(Action("Set") {controller.set(controller.s)})
    }
  }

  visible = true
  redraw

  reactions += {
    case event: GameSizeChanged => redraw
    case event: GameChanged => redraw
    case event: GameNotChanged => statusline.text = State.state
  }

  def redraw = {
    statusline.text = State.state
    contents = new BorderPanel {
      add(pushpanel, BorderPanel.Position.North)
      add(gamePanel, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
    }
    repaint
  }
}

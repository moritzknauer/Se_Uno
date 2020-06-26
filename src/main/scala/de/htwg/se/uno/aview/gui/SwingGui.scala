package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.{Controller, GameChanged, GameEnded, GameNotChanged, GameSizeChanged, State}
import javax.swing.SwingConstants

import scala.swing.BorderPanel.Position
import scala.swing._
import scala.swing.event.Key
import scala.swing.Swing.LineBorder

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Uno"

  /*def pushpanel = new FlowPanel {
    //contents += new Label("Aktionen:")
    /*
    for (index <- 0 to 1) {
      val button = Button(if (index == 0) "Get" else "Set") {
        if (index == 0) {
          controller.get()
        } else {
          controller.set(controller.s)
        }
      }
      button.preferredSize_=(new Dimension(90,90))
      contents += button
      listenTo(button)
    }

     */
  }

   */

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
    //add(pushpanel, BorderPanel.Position.North)
    add(gamePanel, Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Test") {controller.createTestGame()})
      contents += new Menu("Random") {
        mnemonic = Key.R
        contents += new MenuItem(Action("1") {controller.createGame(1)})
        contents += new MenuItem(Action("7") {controller.createGame()})
        contents += new MenuItem(Action("15") {controller.createGame(15)})
      }
      contents += new MenuItem(Action("Quit") {System.exit(0)})
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {controller.undo
                                                controller.undo})
      contents += new MenuItem(Action("Redo") {controller.redo
                                                controller.redo})
    }
  }

  visible = true
  redraw

  reactions += {
    case event: GameSizeChanged => redraw
    case event: GameChanged => redraw
    case event: GameNotChanged => statusline.text = State.state
    case event: GameEnded => ended
  }

  def redraw = {
    statusline.text = State.state
    contents = new BorderPanel {
      //add(pushpanel, BorderPanel.Position.North)
      add(gamePanel, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
    }
    repaint
  }

  def ended: Unit = {
    statusline.text = State.state
    contents = new BorderPanel {
      add(statusline, BorderPanel.Position.North)
      add(buttons, BorderPanel.Position.Center)
      add(gameEndedLine, BorderPanel.Position.South)
    }
  }

  def buttons = new FlowPanel {
    for (i <- 0 to 4) {
      val button = Button(
        if(i == 0) {
          "New Testgame"
        } else if(i == 1) {
          "Random Game with size 1"
        } else if(i == 2) {
          "Random Game with size 7"
        } else if (i == 3){
          "Random Game with size 15"
        }else {
          "Quit"
        }
      ) {
        if(i == 0) {
          controller.createTestGame()
        } else if(i == 1) {
          controller.createGame(1)
        } else if(i == 2) {
          controller.createGame()
        } else if (i == 3){
          controller.createGame(15)
        } else {
          System.exit(0)
        }
      }
      button.preferredSize_=(new Dimension(200,90))
      if(i <= 3) {
        button.background = java.awt.Color.GREEN
      } else {
        button.background = java.awt.Color.RED
      }
      contents += button
      listenTo(button)
    }
    background = java.awt.Color.WHITE
  }

  val gameEndedLine = new TextField("Was möchtest du tun?",30) {
    preferredSize = new Dimension(1,100)
    font = new Font("Verdana", 5, 36)
    background = java.awt.Color.WHITE
    setLocationRelativeTo(buttons)
  }
}

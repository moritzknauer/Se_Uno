package de.htwg.se.uno.aview.gui

import de.htwg.se.uno.controller.controllerComponent.{ControllerInterface, GameChanged, GameEnded, GameNotChanged, GameSizeChanged}
import scala.swing.BorderPanel.Position
import scala.swing._
import scala.swing.event.Key
import scala.swing.Swing.LineBorder
import de.htwg.se.uno.Uno.gui

class SwingGui(controller: ControllerInterface) extends Frame {

  listenTo(controller)

  title = "HTWG Uno"

  def gamePanel = new GridPanel(3, 1) {
    if(controller.getNumOfPlayers() <= 3) {
      contents += new GridPanel(1, controller.getNumOfPlayers()-1) {
        border = LineBorder(java.awt.Color.WHITE, 20)
        background = java.awt.Color.WHITE
        for (i <- 0 to controller.getNumOfPlayers() - 2) {
          contents += new GridPanel(1, controller.getLength(i)) {
            border = LineBorder(java.awt.Color.WHITE, 20)
            background = java.awt.Color.WHITE
            for (enemy <- 1 to controller.getLength(i)) {
              val cardPanel = new CardPanel(i, enemy - 1, controller)
              contents += cardPanel.card
              listenTo(cardPanel)
            }
          }
        }
      }
      contents += new GridPanel(1, 3) {
        border = LineBorder(java.awt.Color.WHITE, 40)
        background = java.awt.Color.WHITE
        for (i <- 0 to 2) {
          val cardPanel = new CardPanel(3, i, controller)
          contents += cardPanel.card
          listenTo(cardPanel)
        }
      }
      contents += new GridPanel(1, controller.getLength(3)) {
        border = LineBorder(java.awt.Color.WHITE, 40)
        background = java.awt.Color.WHITE
        for (player <- 1 to controller.getLength(3)) {
          val cardPanel = new CardPanel(4, player - 1, controller)
          contents += cardPanel.card
          listenTo(cardPanel)
        }
      }
    } else {
      contents += new GridPanel(1, controller.getNumOfPlayers()-1) {
        border = LineBorder(java.awt.Color.WHITE, 20)
        background = java.awt.Color.WHITE
        for (i <- 0 to 1) {
          contents += new GridPanel(1, controller.getLength(i)) {
            border = LineBorder(java.awt.Color.WHITE, 20)
            background = java.awt.Color.WHITE
            for (enemy <- 1 to controller.getLength(i)) {
              val cardPanel = new CardPanel(i, enemy - 1, controller)
              contents += cardPanel.card
              listenTo(cardPanel)
            }
          }
        }
      }
      contents += new GridPanel(1, 3) {
        border = LineBorder(java.awt.Color.WHITE, 40)
        background = java.awt.Color.WHITE
        for (i <- 0 to 2) {
          val cardPanel = new CardPanel(3, i, controller)
          contents += cardPanel.card
          listenTo(cardPanel)
        }
      }
      contents += new GridPanel(1, controller.getNumOfPlayers()-1) {
        border = LineBorder(java.awt.Color.WHITE, 20)
        background = java.awt.Color.WHITE
        for (i <- 0 to 1) {
          if (i == 1) {
            contents += new GridPanel(1, controller.getLength(2)) {
              border = LineBorder(java.awt.Color.WHITE, 20)
              background = java.awt.Color.WHITE
              for (enemy <- 1 to controller.getLength(2)) {
                val cardPanel = new CardPanel(2, enemy - 1, controller)
                contents += cardPanel.card
                listenTo(cardPanel)
              }
            }
          } else {
            contents += new GridPanel(1, controller.getLength(3)) {
              border = LineBorder(java.awt.Color.WHITE, 20)
              background = java.awt.Color.WHITE
              for (player <- 1 to controller.getLength(3)) {
                val cardPanel = new CardPanel(4, player - 1, controller)
                contents += cardPanel.card
                listenTo(cardPanel)
              }
            }
          }
        }
      }
    }
  }

  var statusline = new TextField(controller.gameStatus("idle"),30) {
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
        contents += new MenuItem(Action("2 Players") {controller.createGame(2)})
        contents += new MenuItem(Action("3 Players") {controller.createGame(3)})
        contents += new MenuItem(Action("4 Players") {controller.createGame(4)})
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
    case event: GameNotChanged => statusline.text = controller.gameStatus("idle")
    case event: GameEnded => ended
  }

  def redraw = {
    statusline.text = controller.gameStatus("idle")
    contents = new BorderPanel {
      //add(pushpanel, BorderPanel.Position.North)
      add(gamePanel, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
    }
    repaint
  }

  def ended: Unit = {
    statusline.text = controller.gameStatus("idle")
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
          "Random Game with 2 Players"
        } else if(i == 2) {
          "Random Game with 3 Players"
        } else if (i == 3){
          "Random Game with 4 Players"
        }else {
          "Quit"
        }
      ) {
        if(i == 0) {
          controller.createTestGame()
        } else if(i == 1) {
          controller.createGame(2)
        } else if(i == 2) {
          controller.createGame(3)
        } else if (i == 3){
          controller.createGame(4)
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

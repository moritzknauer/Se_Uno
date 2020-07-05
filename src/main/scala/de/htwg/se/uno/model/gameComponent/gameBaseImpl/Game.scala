package de.htwg.se.uno.model.gameComponent.gameBaseImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.uno.model.gameComponent.GameInterface

case class Game @Inject() (@Named("DefaultPlayers") numOfPlayers:Int) extends GameInterface{
  var init = InitializeGameStrategy()

  init.initializeGame(numOfPlayers)

  private var activePlayer = numOfPlayers - 1
  private var direction = true

  def createGame() : Game = {
    init = InitializeGameStrategy()
    init = init.initializeGame(numOfPlayers)
    activePlayer = numOfPlayers - 1
    direction = true
    this
  }

  def createTestGame() : Game = {
    init = InitializeGameStrategy(1)
    init = init.initializeGame(numOfPlayers)
    activePlayer = numOfPlayers - 1
    direction = true
    this
  }

  def enemy() : Game = {
    init.enemy = init.enemy.enemy(this)
    this
  }

  def enemy2() : Game = {
    init.enemy2 = init.enemy2.enemy(this)
    this
  }

  def enemy3() : Game = {
    init.enemy3 = init.enemy3.enemy(this)
    this
  }

  def nextEnemy() : Int = {
    if (numOfPlayers == 2) {
      1
    } else if (numOfPlayers == 3) {
      if (activePlayer == 0) {
        if (direction) {
          1
        } else {
          2
        }
      } else if (activePlayer == 1) {
        2
      } else {
        1
      }
    } else {
      if (activePlayer == 0) {
        if (direction) {
          1
        } else {
          3
        }
      } else if (activePlayer == 1) {
        2
      } else if (activePlayer == 2) {
        if (direction) {
          3
        } else {
          1
        }
      } else {
        2
      }
    }
  }

  def enemyUndo() : Game = {
    init.enemy = init.enemy.undo(this)
    this
  }

  def enemyUndo2() : Game = {
    init.enemy2 = init.enemy2.undo(this)
    this
  }

  def enemyUndo3() : Game = {
    init.enemy3 = init.enemy3.undo(this)
    this
  }

  def pullMove() : Game = {
    init.player = init.player.pullMove(this)
    this
  }

  def playerUndo() : Game = {
    init.player = init.player.undo(this)
    this
  }

  def pushMove(string : String) : Game = {
    init.player = init.player.pushMove(string, this)
    this
  }

  def getLength(list:Integer) : Int = {
    if (list == 0)
      init.enemy.enemyCards.length
    else if (list == 1)
      init.enemy2.enemyCards.length
    else if (list == 2)
      init.enemy3.enemyCards.length
    else
      init.player.handCards.length
  }

  def getCardText(list : Int, index : Int) : String = {
    if (list == 3 && index == 1) {
      init.cardsRevealed.head.toString
    } else if (list == 3 && index == 2) {
      "Do Step"
    } else if (list == 4) {
      init.player.handCards(index).toString
    } else {
      "Uno"
    }
  }

  def getGuiCardText(list : Int, index : Int) : String = {
     if (list == 3 && index == 1) {
      init.cardsRevealed.head.toGuiString
    } else if (list == 3 && index == 2) {
       "Do Step"
     } else if (list == 4) {
      init.player.handCards(index).toGuiString
    } else {
      "Uno"
    }
  }

  def getNumOfPlayers() : Int = {
    numOfPlayers
  }

  def nextTurn() : Boolean = {
    if ((activePlayer == 1 && (!direction || numOfPlayers == 2)) ||
        (activePlayer == 2 && direction && numOfPlayers == 3) || (activePlayer == 3 && direction && numOfPlayers== 4)) {
      true
    } else {
      false
    }
  }

  def setActivePlayer() : Game = {
    if (nextTurn()) {
      activePlayer = 0
    } else {
      activePlayer = nextEnemy()
    }
    this
  }

  def setDirection() : Game = {
    if (direction) {
      direction = false
    } else {
      direction = true
    }
    this
  }

  override def toString: String = {
    val a = "┌-------┐  "
    val b = "|       |  "
    val c = "|  Uno  |  "
    val d = "└-------┘  "
    var e, f, g, h, i, j, k, l, m, n, o, p = ""

    for (_ <- 1 to init.enemy.enemyCards.length) {
      e = e.concat(a)
      f = f.concat(b)
      g = g.concat(c)
      h = h.concat(d)
    }
    for (i <- 1 to init.player.handCards.length) {
      m = m.concat(a)
      n = n.concat(b)
      o = o.concat("|  " + init.player.handCards(i-1).toString + "  |  ")
      p = p.concat(d)
    }
    i = i.concat(a).concat("           ┌-------┐") + "\n"
    j = j.concat(b).concat("           |       |") + "\n"
    k = k.concat(c).concat("           |  " + init.cardsRevealed.head.toString + "  |") + "\n"
    l = l.concat(d).concat("           └-------┘") + "\n\n"

    val playingField = e + "\n" + f + "\n" + g + "\n" + f + "\n" + h + "\n\n" + i + j + k + j + l + m + "\n" + n +
                        "\n" + o + "\n" + n + "\n" + p
    playingField
  }
}

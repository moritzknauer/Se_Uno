package de.htwg.se.uno.model.gameComponent.gameBaseImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.uno.model.gameComponent.GameInterface

import scala.collection.mutable
import scala.swing.Color

case class Game @Inject() (@Named("DefaultPlayers") numOfPlayers:Int) extends GameInterface{
  var init = InitializeGameStrategy()

  init.initializeGame(numOfPlayers)

  private var activePlayer = numOfPlayers - 1
  private var direction = true
  var anotherPull = false
  var special = mutable.Stack[Integer](0)
  var color = 0
  var hv = false

  def createGame() : Game = {
    init = InitializeGameStrategy()
    init = init.initializeGame(numOfPlayers)
    activePlayer = numOfPlayers - 1
    direction = true
    special.popAll()
    special.push(0)
    this
  }

  def createTestGame() : Game = {
    init = InitializeGameStrategy(1)
    init = init.initializeGame(numOfPlayers)
    activePlayer = numOfPlayers - 1
    direction = true
    special.popAll()
    special.push(0)
    this
  }

  def enemy() : Game = {
    if (special.top != - 1) {
      init.enemy = init.enemy.enemy(this)
      hv = false
    } else {
      special.push(0)
      init.enemy.stack1.push("Suspend")
      init.enemy.stack2.push(-1)
      hv = true
    }
    this
  }

  def enemy2() : Game = {
  if (special.top != - 1) {
    init.enemy2 = init.enemy2.enemy(this)
    hv = false
  } else {
    special.push(0)
    init.enemy2.stack1.push("Suspend")
    init.enemy2.stack2.push(-1)
    hv = true
  }
    this
  }

  def enemy3() : Game = {
    if (special.top != - 1) {
      init.enemy3 = init.enemy3.enemy(this)
      hv = false
    } else {
      special.push(0)
      init.enemy3.stack1.push("Suspend")
      init.enemy3.stack2.push(-1)
      hv = true
    }
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
    if(special.top != - 1) {
      init.player = init.player.pullMove(this)
      hv = false
    } else {
      special.push(0)
      init.player.stack1.push("Suspend")
      init.player.stack2.push(-1)
      hv = true
      setActivePlayer()
    }
    this
  }

  def playerUndo() : Game = {
    init.player = init.player.undo(this)
    this
  }

  def pushMove(string : String, color : Int) : Game = {
    if(special.top != - 1) {
      if (color != 0) {
        this.color = color
      }
      init.player = init.player.pushMove(string, color, this)
      hv = false
    } else {
      special.push(0)
      init.player.stack1.push("Suspend")
      init.player.stack2.push(-1)
      hv = true
      setActivePlayer()
    }
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

  def getActivePlayer() : Int = activePlayer
  def getDirection() : Boolean = direction

  def getColor() : Color = {
    if (color == 1) {
      new Color(0, 0, 255)
    } else if (color == 2) {
      new Color(0, 255, 0)
    } else if (color == 3) {
      new Color(255, 255, 0)
    } else if (color == 4) {
      new Color(255, 0, 0)
    } else {
      new Color(128, 128, 128)
    }
  }

  def getNextEnemy() : Enemy = {
    val i = nextEnemy()
    if (i == 1) {
      init.enemy
    } else if (i == 2) {
      init.enemy2
    } else {
      init.enemy3
    }
  }

  def getAnotherPull() : Boolean = anotherPull

  def setAnotherPull(b : Boolean = false) : Game = {
    anotherPull = b
    this
  }

  def getHv() : Boolean = hv

  /*def getNextEnemy() : Enemy = {
    if (numOfPlayers == 3) {
      if (activePlayer == 0) {
        if (direction) {
          init.enemy2
        } else {
          init.enemy
        }
      } else if (activePlayer == 1) {
        init.enemy2
      } else {
        init.enemy
      }
    } else {
      if (activePlayer == 0) {
        init.enemy2
      } else if (activePlayer == 1) {
        init.enemy3
      } else {
        init.enemy
      }
    }
  }

  def getNextPlayer() : Boolean = {
    if ((activePlayer == 0 && numOfPlayers == 2) ||
        ((activePlayer == 1 && direction) || (activePlayer == 2 && !direction) && numOfPlayers == 3) ||
        (activePlayer == 2 && numOfPlayers== 4)) {
      true
    } else {
      false
    }
  }*/

  override def toString: String = {
    val a = "┌-------┐  "
    val b = "|       |  "
    val c = "|  Uno  |  "
    val d = "└-------┘  "
    var e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x = ""

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


    if (numOfPlayers == 3) {
      for (_ <- 1 to init.enemy2.enemyCards.length) {
        q = q.concat(a)
        r = r.concat(b)
        s = s.concat(c)
        t = t.concat(d)
      }
    } else if (numOfPlayers == 4) {
      for (_ <- 1 to init.enemy2.enemyCards.length) {
        q = q.concat(a)
        r = r.concat(b)
        s = s.concat(c)
        t = t.concat(d)
      }
      for (_ <- 1 to init.enemy3.enemyCards.length) {
        u = u.concat(a)
        v = v.concat(b)
        w = w.concat(c)
        x = x.concat(d)
      }
    }

    val playingField = e + "\t\t\t\t\t" + q + "\n" +
                        f + "\t\t\t\t\t" + r + "\n" +
                        g + "\t\t\t\t\t" + s + "\n" +
                        f + "\t\t\t\t\t" + r + "\n" +
                        h + "\t\t\t\t\t" + t + "\n\n" +
                        i + j + k + j + l +
                        m + "\t\t\t\t\t" + u + "\n" +
                        n + "\t\t\t\t\t" + v + "\n" +
                        o + "\t\t\t\t\t" + w + "\n" +
                        n + "\t\t\t\t\t" + v + "\n" +
                        p + "\t\t\t\t\t" + x
    playingField
  }
}

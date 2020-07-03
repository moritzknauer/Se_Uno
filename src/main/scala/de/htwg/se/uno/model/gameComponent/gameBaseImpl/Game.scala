package de.htwg.se.uno.model.gameComponent.gameBaseImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.uno.model.gameComponent.GameInterface

case class Game @Inject() (@Named("DefaultSize") numOfCards:Int) extends GameInterface{
  var init = InitializeGameStrategy()

  init.initializeGame(numOfCards)

  def createTestGame() : Game = {
    init = InitializeGameStrategy(1)
    init = init.initializeGame(numOfCards)
    this
  }

  def enemy() : Game = {
    init.enemy = init.enemy.enemy(this)
    this
  }

  def enemyUndo() : Game = {
    init.enemy = init.enemy.undo(this)
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
    else
      init.player.handCards.length
  }

  def getCardText(list : Int, index : Int) : String = {
    if (list == 1 && index == 1) {
      init.cardsRevealed.head.toString
    } else if (list == 2) {
      init.player.handCards(index).toString
    } else {
      "Uno"
    }
  }

  def getGuiCardText(list : Int, index : Int) : String = {
    if (list == 0 || (list == 1 && index == 0)) {
      "Uno"
    } else if (list == 1 && index == 1) {
      init.cardsRevealed.head.toGuiString
    } else {
      init.player.handCards(index).toGuiString
    }
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

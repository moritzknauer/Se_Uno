package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.uno.model.{Color, Value}

case class Game(numOfCards: Int = 7) {
  var init = new InitializeRandomGameStrategy

  init.initializeGame(numOfCards)

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

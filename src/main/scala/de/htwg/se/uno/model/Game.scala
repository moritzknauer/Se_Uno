package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.uno.model.{Color, Value}

case class Game(numOfCards: Int = 7) {
  var cardsCovered = new ListBuffer[Card]()
  var cardsRevealed = new ListBuffer[Card]()
  var player = new Player
  var enemy = new Enemy

  initializeGame(numOfCards)

  override def toString: String = {
    val a = "┌-------┐  "
    val b = "|       |  "
    val c = "|  Uno  |  "
    val d = "└-------┘  "
    var e, f, g, h, i, j, k, l, m, n, o, p = ""

    for (_ <- 1 to enemy.enemyCards.length) {
      e = e.concat(a)
      f = f.concat(b)
      g = g.concat(c)
      h = h.concat(d)
    }
    for (i <- 1 to player.handCards.length) {
      m = m.concat(a)
      n = n.concat(b)
      o = o.concat("|  " + player.handCards(i-1).toString + "  |  ")
      p = p.concat(d)
    }
    i = i.concat(a).concat("           ┌-------┐") + "\n"
    j = j.concat(b).concat("           |       |") + "\n"
    k = k.concat(c).concat("           |  " + cardsRevealed.head.toString + "  |") + "\n"
    l = l.concat(d).concat("           └-------┘") + "\n\n"

    val playingField = e + "\n" + f + "\n" + g + "\n" + f + "\n" + h + "\n\n" + i + j + k + j + l + m + "\n" + n +
                        "\n" + o + "\n" + n + "\n" + p
    playingField
  }

  def initializeGame(numOfPlayerCards: Int){
    var cards = new ListBuffer[Card]()

    for (color <- Color.values) {
      for (value <- Value.values) {
        if (value == Value.Zero && color != Color.Schwarz) {
          cards += Card(color, value)
        } else if (color == Color.Schwarz && (value == Value.ColorChange || value == Value.PlusFour)) {
          for (_ <- 0 to 3)
            cards += Card(color, value)
        } else if (color != Color.Schwarz && (value != Value.PlusFour && value != Value.ColorChange)) {
          for (_ <- 0 to 1)
            cards += Card(color, value)
        }
      }
    }

    var n = 108
    for (_ <- 0 to 107) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsCovered = cards(p - 1) +: cardsCovered
      cards = cards.take(p - 1) ++ cards.drop(p)
      n -= 1
    }

    for (i <- 1 to numOfPlayerCards) {
      player.handCards = cardsCovered(0) +: player.handCards
      cardsCovered = cardsCovered.drop(1)
      enemy.enemyCards = cardsCovered(0) +: enemy.enemyCards
      cardsCovered = cardsCovered.drop(1)
    }

    cardsRevealed = cardsCovered(0) +: cardsRevealed
    cardsCovered = cardsCovered.drop(1)
  }
}

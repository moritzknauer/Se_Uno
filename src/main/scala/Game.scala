package scala

import scala.CardOptions.{Color, Value}
import scala.Uno.{cardsCovered, cardsRevealed, enemyCards, handCards}
import scala.collection.mutable.ListBuffer

case class Game(numOfCards: Int = 7) {
  initializeGame(numOfCards)

  override def toString() : String = {
    val a = "┌-------┐  "
    val b = "|       |  "
    val c = "|  Uno  |  "
    val d = "└-------┘  "
    var e = ""
    var f = ""
    var g = ""
    var h = ""
    var i = ""
    var j = ""
    var k = ""
    var l = ""
    var m = ""
    var n = ""
    var o = ""
    var p = ""

    for (i <- 1 to enemyCards.length) {
      e = e.concat(a)
      f = f.concat(b)
      g = g.concat(c)
      h = h.concat(d)
    }
    e = e + "\n"
    f = f + "\n"
    g = g + "\n"
    h = h + "\n\n"

    i = i.concat(a).concat("           ┌-------┐") + "\n"
    j = j.concat(b).concat("           |       |") + "\n"
    k = k.concat(c).concat("           |  " + cardsRevealed.head.toString + "  |  ") + "\n"
    l = l.concat(d).concat("           └-------┘") + "\n\n"

    for (i <- 1 to handCards.length) {
      m = m.concat(a)
      n = n.concat(b)
      o = o.concat("|  " + handCards(i-1).toString + "  |  ")
      p = p.concat(d)
    }
    m = m + "\n"
    n = n + "\n"
    o = o + "\n"
    p = p + "\n"

    val enemyPlayingField = e + f + g + f + h
    val middlePlayingField = i + j + k + j + l
    val playerPlayingField = m + n + o + n + p

    val playingField = enemyPlayingField + middlePlayingField + playerPlayingField

    playingField
  }

  def initializeGame(numOfPlayerCards: Int){
    var cards = new ListBuffer[Card]()

    for (color <- Color.values) {
      for (value <- Value.values) {
        if (value == Value.Zero && color != Color.Black) {
          cards += Card(color, value)
        } else if (color == Color.Black && (value == Value.ColorChange || value == Value.PlusFour)) {
          for (i <- 0 to 3)
            cards += Card(color, value)
        } else if (color != Color.Black && (value != Value.PlusFour && value != Value.ColorChange)) {
          for (i <- 0 to 1)
            cards += Card(color, value)
        }
      }
    }

    //println(cards)

    var cardsMixed = cards

    var n = 108
    for (i <- 0 to 107) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsCovered = cardsMixed.slice(p - 1, p) ++ cardsCovered
      cardsMixed = cardsMixed.take(p - 1) ++ cardsMixed.drop(p)
      n -= 1
    }

    n = numOfPlayerCards
    for (i <- 1 to numOfPlayerCards) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      handCards = cardsCovered.slice(p - 1, p) ++ handCards
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }

    n = numOfPlayerCards
    for (i <- 1 to numOfPlayerCards) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      enemyCards = cardsCovered.slice(p - 1, p) ++ enemyCards
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }

    n = 1
    for (i <- 0 to 0) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsRevealed = cardsCovered.slice(p - 1, p) ++ cardsRevealed
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }
    //println(cardsCovered)
    //println(enemyCards)
    //println("")
  }
}

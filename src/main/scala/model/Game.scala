package scala.model

import scala.collection.mutable.ListBuffer
import scala.model.CardOptions.{Color, Value}

case class Game(numOfCards: Int = 7) {
  var cardsCovered = new ListBuffer[Card]()
  var cardsRevealed = new ListBuffer[Card]()
  var enemyCards = new ListBuffer[Card]()
  var handCards = new ListBuffer[Card]()
  initializeGame(numOfCards)

  override def toString: String = {
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

    for (_ <- 1 to enemyCards.length) {
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

    var cardsMixed = cards

    var n = 108
    for (_ <- 0 to 107) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsCovered = cardsMixed.slice(p - 1, p) ++ cardsCovered
      cardsMixed = cardsMixed.take(p - 1) ++ cardsMixed.drop(p)
      n -= 1
    }

    n = numOfPlayerCards
    for (_ <- 1 to numOfPlayerCards) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      handCards = cardsCovered.slice(p - 1, p) ++ handCards
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }

    n = numOfPlayerCards
    for (_ <- 1 to numOfPlayerCards) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      enemyCards = cardsCovered.slice(p - 1, p) ++ enemyCards
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }

    n = 1
    for (_ <- 0 to 0) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsRevealed = cardsCovered.slice(p - 1, p) ++ cardsRevealed
      cardsCovered = cardsCovered.take(p - 1) ++ cardsCovered.drop(p)
      n -= 1
    }
  }

  def pushable(card: Card) : Boolean = {
    if (card.color == cardsRevealed.head.color) {
      return true
    } else if (card.value == cardsRevealed.head.value) {
      return true
    } else if (card.value == Value.PlusFour) {
      for (i <- 1 to handCards.length) {
        if (handCards(i - 1).color == cardsRevealed.head.color) {
          false
        }
      }
      return true
    } else if (card.value == Value.ColorChange) {
      return true
    } else if (cardsRevealed.head.color == Color.Schwarz) {
      return true
    }
    false
  }

  def pushCard(card: Card) : Game = {
    var c = 0
    for (i <- 2 to handCards.length) {
      if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
          cardsRevealed = handCards.slice(i - 2, i-1) ++ cardsRevealed
          handCards = handCards.take(i - 2) ++ handCards.drop(i-1)
        c += 1
      }
    }
    if (c == 0) {
      cardsRevealed = handCards.drop(handCards.length-1) ++ cardsRevealed
      handCards = handCards.take(handCards.length - 1)
    }
    this
  }

  def pullable() : Boolean = {
    for (i <- 1 to handCards.length) {
      if(pushable(handCards(i-1))) {
        return false
      }
    }
    true
  }

  def pull() : Game = {
    handCards += Card(cardsCovered.head.color, cardsCovered.head.value)
    cardsCovered = cardsCovered.drop(1)
    this
  }

  def pullEnemy() : Game = {
    enemyCards += Card(cardsCovered.head.color, cardsCovered.head.value)
    cardsCovered = cardsCovered.drop(1)
    this
  }

  def enemy() : Game = {
    for (i <- 1 to enemyCards.length) {
      if(pushableEnemy(enemyCards(i-1))) {
        return pushCardEnemy(enemyCards(i-1))
      }
    }
    pullEnemy()
  }

  def pushableEnemy(card: Card) : Boolean = {
    if (card.color == cardsRevealed.head.color) {
      return true
    } else if (card.value == cardsRevealed.head.value) {
      return true
    } else if (card.value == Value.PlusFour) {
      for (i <- 1 to enemyCards.length) {
        if (enemyCards(i - 1).color == cardsRevealed.head.color) {
          false
        }
      }
      return true
    } else if (card.value == Value.ColorChange) {
      return true
    } else if (cardsRevealed.head.color == Color.Schwarz) {
      return true
    }
    false
  }

  def pushCardEnemy(card: Card) : Game = {
    var c = 0
    for (i <- 2 to enemyCards.length) {
      if (enemyCards(i - 2).color == card.color && enemyCards(i - 2).value == card.value && c == 0) {
        cardsRevealed = enemyCards.slice(i - 2, i-1) ++ cardsRevealed
        enemyCards = enemyCards.take(i - 2) ++ enemyCards.drop(i-1)
        c += 1
      }
    }
    if (c == 0) {
      cardsRevealed = enemyCards.drop(enemyCards.length-1) ++ cardsRevealed
      enemyCards = enemyCards.take(enemyCards.length - 1)
    }
    this
  }

  def equalsCard(s: String): Boolean = {
    for (i <- 1 to handCards.length) {
      if (s.equals(handCards(i - 1).toString)) {
        return true
      }
    }
    false
  }

  def getCard(s: String): Card = {
    var c = 0
    for (i <- 1 to handCards.length) {
      if (s.equals(handCards(i - 1).toString)) {
        c = i-1
      }
    }
    handCards(c)
  }
}

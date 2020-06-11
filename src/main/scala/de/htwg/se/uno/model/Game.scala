package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.uno.model.{Color, Value}

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
    var e, f, g, h, i, j, k, l, m, n, o, p = ""

    for (_ <- 1 to enemyCards.length) {
      e = e.concat(a)
      f = f.concat(b)
      g = g.concat(c)
      h = h.concat(d)
    }
    for (i <- 1 to handCards.length) {
      m = m.concat(a)
      n = n.concat(b)
      o = o.concat("|  " + handCards(i-1).toString + "  |  ")
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
      handCards = cardsCovered(0) +: handCards
      cardsCovered = cardsCovered.drop(1)
      enemyCards = cardsCovered(0) +: enemyCards
      cardsCovered = cardsCovered.drop(1)
    }

    cardsRevealed = cardsCovered(0) +: cardsRevealed
    cardsCovered = cardsCovered.drop(1)
  }

  def pushCard(card: Card) : Game = {
    var c = 0
    for (i <- 2 to handCards.length) {
      if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
        cardsRevealed = handCards(i - 2) +: cardsRevealed
        handCards = handCards.take(i - 2) ++ handCards.drop(i-1)
        c += 1
      }
    }
    if (c == 0) {
      cardsRevealed = handCards(handCards.length-1) +: cardsRevealed
      handCards = handCards.take(handCards.length - 1)
    }
    this
  }

  def pushable(card: Card) : Boolean = {
    if (card.value == Value.PlusFour) {
      for (i <- 1 to handCards.length) {
        if (handCards(i - 1).color == cardsRevealed.head.color) {
          return false
        }
      }
      return true
    } else if (card.color == cardsRevealed.head.color || card.value == cardsRevealed.head.value
      || card.value == Value.ColorChange || cardsRevealed.head.color == Color.Schwarz) {
      return true
    }
    false
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

  def pushCardEnemy(card: Card) : Game = {
    var c = 0
    for (i <- 2 to enemyCards.length) {
      if (enemyCards(i - 2).color == card.color && enemyCards(i - 2).value == card.value && c == 0) {
        cardsRevealed = enemyCards(i - 2) +: cardsRevealed
        enemyCards = enemyCards.take(i - 2) ++ enemyCards.drop(i-1)
        c += 1
      }
    }
    if (c == 0) {
      cardsRevealed = enemyCards(enemyCards.length - 1) +: cardsRevealed
      enemyCards = enemyCards.take(enemyCards.length - 1)
    }
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
    if (card.value == Value.PlusFour) {
      for (i <- 1 to enemyCards.length) {
        if (enemyCards(i - 1).color == cardsRevealed.head.color) {
          return false
        }
      }
      return true
    } else if (card.color == cardsRevealed.head.color || card.value == cardsRevealed.head.value
      || card.value == Value.ColorChange || cardsRevealed.head.color == Color.Schwarz) {
      return true
    }
    false


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

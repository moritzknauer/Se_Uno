package scala

import scala.CardOptions.{Color, Value}
import scala.collection.mutable.ListBuffer

case class Game {

  def initializeGame(numOfPlayerCards: Int, cardsCovered: ListBuffer[Card], cardsRevealed: ListBuffer[Card],
                     enemyCards: ListBuffer[Card], handCards: ListBuffer[Card]): Boolean = {
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

    println(cards)

    var cardsMixed = cards

    var n = 108
    for(i <- 0 to 107) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsCovered = cardsMixed.slice(p-1, p) ++ cardsCovered
      cardsMixed = cardsMixed.take(p-1) ++ cardsMixed.drop(p)
      n -= 1
    }

    n = numOfPlayerCards
    for(i <- 1 to numOfPlayerCards) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      handCards = cardsCovered.slice(p-1, p) ++ handCards
      cardsCovered = cardsCovered.take(p-1) ++ cardsCovered.drop(p)
      n -= 1
    }

    n = numOfPlayerCards
    for(i <- 1 to numOfPlayerCards) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      enemyCards = cardsCovered.slice(p-1, p) ++ enemyCards
      cardsCovered = cardsCovered.take(p-1) ++ cardsCovered.drop(p)
      n -= 1
    }

    n = 1
    for(i <- 0 to 0) {
      val r = new scala.util.Random
      val p = 1 + r.nextInt(n)
      cardsRevealed = cardsCovered.slice(p-1, p) ++ cardsRevealed
      cardsCovered = cardsCovered.take(p-1) ++ cardsCovered.drop(p)
      n -= 1
    }
  }
}

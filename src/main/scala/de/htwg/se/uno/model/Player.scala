package de.htwg.se.uno.model

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack

class Player() {
  var handCards = new ListBuffer[Card]()
  var stack1 = Stack[String]("")
  var stack2 = Stack[Integer](-1)


  def pushMove(string:String, game: Game) : Player = {
    if(equalsCard(string)) {
      val card = getCard(string)
      if (pushable(card, game)) {
        stack1.push(" ")
        return pushCard(card, game)
      }
    }
    this
  }

  def pullMove(game:Game) : Player = {
    if (pullable(game)) {
      stack1.push(game.cardsCovered.head.toString)
      stack2.push(-1)
      pull(game)
    } else {
      this
    }
  }

  def undo(game: Game) : Player = {
    if(stack1.top.equals(" ")) {
      handCards = handCards.take(stack2.top) ++ game.cardsRevealed.take(1) ++ handCards.drop(stack2.top)
      game.cardsRevealed = game.cardsRevealed.drop(1)
      stack1.pop()
      stack2.pop()
    } else {
      val card = getCard(stack1.top)
      game.cardsCovered = card +: game.cardsCovered
      var c = 0
      for (i <- 2 to handCards.length) {
        if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
          handCards = handCards.take(i - 2) ++ handCards.drop(i - 1)
          c = 1
        }
      }
      if (c==0) {
        handCards = handCards.take(handCards.length - 1)
      }
      stack1.pop()
      stack2.pop()
    }
    this
  }

  def pushCard(card: Card, game: Game) : Player = {
    var c = 0
    for (i <- 2 to handCards.length) {
      if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
        game.cardsRevealed = handCards(i - 2) +: game.cardsRevealed
        handCards = handCards.take(i - 2) ++ handCards.drop(i-1)
        stack2.push(i-2)
        c += 1
      }
    }
    if (c == 0) {
      game.cardsRevealed = handCards(handCards.length-1) +: game.cardsRevealed
      handCards = handCards.take(handCards.length - 1)
      stack2.push(handCards.length-1)
    }
    this
  }

  def pushable(card: Card, game: Game) : Boolean = {
    if (card.value == Value.PlusFour) {
      for (i <- 1 to handCards.length) {
        if (handCards(i - 1).color == game.cardsRevealed.head.color) {
          return false
        }
      }
      return true
    } else if (card.color == game.cardsRevealed.head.color || card.value == game.cardsRevealed.head.value
      || card.value == Value.ColorChange || game.cardsRevealed.head.color == Color.Schwarz) {
      return true
    }
    false
  }

  def pullable(game: Game) : Boolean = {
    for (i <- 1 to handCards.length) {
      if(pushable(handCards(i-1), game)) {
        return false
      }
    }
    true
  }

  def pull(game: Game) : Player = {
    handCards += Card(game.cardsCovered.head.color, game.cardsCovered.head.value)
    game.cardsCovered = game.cardsCovered.drop(1)
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
        c = i - 1
      }
    }
    handCards(c)
  }
}

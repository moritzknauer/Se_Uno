package de.htwg.se.uno.model

import java.util

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack

class Enemy() {
  var enemyCards = new ListBuffer[Card]()
  var stack1 = Stack[String](" ")
  var stack2 = Stack[Integer](-1)

  def undo(game: Game) : Enemy = {
    if(stack1.top.equals("")) {
      enemyCards = enemyCards.take(stack2.top) ++ game.cardsRevealed.take(1) ++ enemyCards.drop(stack2.top)
      game.cardsRevealed = game.cardsRevealed.drop(1)
      stack1.pop()
      stack2.pop()
    } else {
      var c = 0
      for(i <- 1 to enemyCards.length) {
        if(enemyCards(i - 1).toString.equals(stack1.top) && c == 0) {
          c = i-1
        }
      }
      val card = enemyCards(c)
      c = 0
      game.cardsCovered = card +: game.cardsCovered
      for (i <- 2 to enemyCards.length) {
        if (enemyCards(i - 2).color == card.color && enemyCards(i - 2).value == card.value && c == 0) {
          enemyCards = enemyCards.take(i - 2) ++ enemyCards.drop(i - 1)
          c = 1
        }
      }
      if (c==0) {
        enemyCards = enemyCards.take(enemyCards.length - 1)
      }
      stack1.pop()
      stack2.pop()
    }
    this
  }

  def pushCardEnemy(card: Card, game: Game) : Enemy = {
    var c = 0
    for (i <- 2 to enemyCards.length) {
      if (enemyCards(i - 2).color == card.color && enemyCards(i - 2).value == card.value && c == 0) {
        game.cardsRevealed = enemyCards(i - 2) +: game.cardsRevealed
        enemyCards = enemyCards.take(i - 2) ++ enemyCards.drop(i-1)
        c += 1
        stack2.push(i-2)
      }
    }
    if (c == 0) {
      game.cardsRevealed = enemyCards(enemyCards.length - 1) +: game.cardsRevealed
      enemyCards = enemyCards.take(enemyCards.length - 1)
      stack2.push(enemyCards.length - 1)
    }
    stack1.push("")
    this
  }

  def pullEnemy(game: Game) : Enemy = {
    stack1.push(game.cardsCovered.head.toString)
    stack2.push(-1)
    enemyCards += Card(game.cardsCovered.head.color, game.cardsCovered.head.value)
    game.cardsCovered = game.cardsCovered.drop(1)
    this
  }

  def enemy(game: Game) : Enemy = {
    for (i <- 1 to enemyCards.length) {
      if(pushableEnemy(enemyCards(i-1), game)) {
        return pushCardEnemy(enemyCards(i-1), game)
      }
    }
    pullEnemy(game)
  }

  def pushableEnemy(card: Card, game: Game) : Boolean = {
    if (card.value == Value.PlusFour) {
      for (i <- 1 to enemyCards.length) {
        if (enemyCards(i - 1).color == game.cardsRevealed.head.color) {
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
}

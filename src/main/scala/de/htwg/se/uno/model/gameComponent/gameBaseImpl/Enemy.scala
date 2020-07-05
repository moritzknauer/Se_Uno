package de.htwg.se.uno.model.gameComponent.gameBaseImpl

import scala.collection.mutable.{ListBuffer, Stack}
import scala.swing.Color

class Enemy() {
  var enemyCards = new ListBuffer[Card]()
  var stack1 = Stack[String]("Start")
  var stack2 = Stack[Integer](-1)
  var stack3 = Stack[Card]()

  def undo(game: Game) : Enemy = {
    if(stack1.top.equals(" ")) {
      enemyCards = enemyCards.take(stack2.top) ++ game.init.cardsRevealed.take(1) ++ enemyCards.drop(stack2.top)
      game.init.cardsRevealed = game.init.cardsRevealed.drop(1)
      if (stack3.top.value == Value.DirectionChange) {
        game.setDirection()
      }
      stack1.pop()
      stack2.pop()
      stack3.pop()
    } else {
      var c = 0
      for(i <- 1 to enemyCards.length) {
        if(enemyCards(i - 1).toString.equals(stack1.top) && c == 0) {
          c = i
        }
      }
      if(c == 0) {
        return this
      }
      val card = enemyCards(c-1)
      c = 0
      game.init.cardsCovered = card +: game.init.cardsCovered
      for (i <- 2 to enemyCards.length) {
        if (enemyCards(i - 2).color == card.color && enemyCards(i - 2).value == card.value && c == 0) {
          enemyCards = enemyCards.take(i - 2) ++ enemyCards.drop(i - 1)
          c = 1
        }
      }
      if (c == 0) {
        enemyCards = enemyCards.take(enemyCards.length - 1)
      }
      stack1.pop()
      stack2.pop()
    }
    this
  }

  def pushCardEnemy(card: Card, game: Game) : Enemy = {
    var c = 0
    var max = 0
    var myCard = card
    var color = 0
    if (card.color == Color.Schwarz) {
      for (i <- 0 to 3) {
        c = 0
        for (j <- 0 to enemyCards.length - 1) {
          if (i == 0) {
            if (enemyCards(j).color == Color.Blue) {
              c += 1
            }
          } else if (i == 1) {
            if (enemyCards(j).color == Color.Green) {
              c += 1
            }
          } else if (i == 2) {
            if (enemyCards(j).color == Color.Yellow) {
              c += 1
            }
          } else {
            if (enemyCards(j).color == Color.Red) {
              c += 1
            }
          }
        }
        if (c > max) {
          if (i == 0) {
            max = c
            myCard = Card(Color.Blue, card.value)
            color = i
          } else if (i == 1) {
            max = c
            myCard = Card(Color.Green, card.value)
            color = i
          } else if (i == 2) {
            max = c
            myCard = Card(Color.Yellow, card.value)
            color = i
          } else {
            max = c
            myCard = Card(Color.Red, card.value)
            color = i
          }
        }
      }
    }
    game.color = color
    c = 0
    stack3.push(card)
    for (i <- 2 to enemyCards.length) {
      if (enemyCards(i - 2).color == card.color && enemyCards(i - 2).value == card.value && c == 0) {
        game.init.cardsRevealed = myCard +: game.init.cardsRevealed
        enemyCards = enemyCards.take(i - 2) ++ enemyCards.drop(i-1)
        c += 1
        stack2.push(i-2)
        stack1.push(" ")
      }
    }
    if (c == 0) {
      game.init.cardsRevealed = myCard +: game.init.cardsRevealed
      enemyCards = enemyCards.take(enemyCards.length - 1)
      stack2.push(enemyCards.length - 1)
      stack1.push(" ")
    }
    if (stack3.top.value == Value.DirectionChange) {
      game.setDirection()
      game.special.push(0)
    } else if (stack3.top.value == Value.PlusTwo) {
      game.special.push(game.special.top + 2)
    } else if (stack3.top.value == Value.PlusFour) {
      game.special.push(game.special.top + 4)
    } else if (stack3.top.value == Value.Suspend) {
      game.special.push(-1)
    } else {
      game.special.push(0)
    }
    this
  }

  def pullEnemy(game: Game) : Enemy = {
    stack1.push(game.init.cardsCovered.head.toString)
    stack2.push(-1)
    enemyCards += Card(game.init.cardsCovered.head.color, game.init.cardsCovered.head.value)
    game.init.cardsCovered = game.init.cardsCovered.drop(1)
    if (game.special.top > 0) {
      for (_ <- 2 to game.special.top) {
        stack1.push(game.init.cardsCovered.head.toString)
        stack2.push(-1)
        enemyCards += Card(game.init.cardsCovered.head.color, game.init.cardsCovered.head.value)
        game.init.cardsCovered = game.init.cardsCovered.drop(1)
        game.special.push(0)
      }
    }
    game.special.push(0)
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
    if (game.init.cardsRevealed.head.value == Value.PlusTwo && card.value != Value.PlusTwo && game.special.top != 0) {
      return false
    } else if (game.init.cardsRevealed.head.value == Value.PlusFour && card.value != Value.PlusFour && game.special.top != 0) {
      return false
    } else if (card.value == Value.PlusFour) {
      for (i <- 1 to enemyCards.length) {
        if (enemyCards(i - 1).color == game.init.cardsRevealed.head.color && game.init.cardsRevealed.head.color != Color.Schwarz) {
          return false
        }
      }
      return true
    } else if (card.color == game.init.cardsRevealed.head.color || card.value == game.init.cardsRevealed.head.value
      || card.value == Value.ColorChange || game.init.cardsRevealed.head.color == Color.Schwarz) {
      return true
    }
    false
  }
}

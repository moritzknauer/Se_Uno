package de.htwg.se.uno.model.gameComponent.gameBaseImpl


import scala.collection.mutable.{ListBuffer, Stack}

class Player() {
  var handCards = new ListBuffer[Card]()
  var stack1 = Stack[String]("Start")
  var stack2 = Stack[Integer](-1)
  var stack3 = Stack[Card]()
  var stack4 = Stack[Boolean]()

  def pushMove(string:String, color : Int, game: Game) : Player = {
    if(equalsCard(string)) {
      val card = getCard(string)
      if (pushable(card, game)) {
        return pushCard(card, color, game)
      }
    }
    this
  }

  def pullMove(game:Game) : Player = {
    if (!game.anotherPull) {
      pull(game)
    } else {
      game.anotherPull = false
      game.special.push(0)
      stack1.push("Suspend")
      stack2.push(-1)
      stack4.push(false)
      this
    }
  }

  def undo(game: Game) : Player = {
    if (stack1.top.equals(" ")) { //Wenn gelegt wurde
      handCards = handCards.take(stack2.top) :+ stack3.top :++ handCards.drop(stack2.top)
      game.init.cardsRevealed = game.init.cardsRevealed.drop(1)
      if (stack3.top.value == Value.DirectionChange) {
        game.setDirection()
      }
      stack1.pop()
      stack2.pop()
      stack3.pop()
      game.special.pop()
      game.hv2 = true
    } else {
      var c = 0
      if (equalsCard(stack1.top)) {
        val card = getCard(stack1.top)
        game.init.cardsCovered = card +: game.init.cardsCovered
        c = 0
        for (i <- 2 to handCards.length) {
          if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
            handCards = handCards.take(i - 2) ++ handCards.drop(i - 1)
            c = 1
          }
        }
        if (c == 0) {
          handCards = handCards.take(handCards.length - 1)
        }
      }
      stack1.pop()
      stack2.pop()
      game.hv2 = true
      if(stack4.top) {
        game.hv2 = false
      }
      stack4.pop()

      game.special.pop()
      if (game.special.top > 0) {
        for (_ <- 2 to game.special.top) {
          if (equalsCard(stack1.top)) {
            val card = getCard(stack1.top)
            game.init.cardsCovered = card +: game.init.cardsCovered
            c = 0
            for (i <- 2 to handCards.length) {
              if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
                handCards = handCards.take(i - 2) ++ handCards.drop(i - 1)
                c = 1
              }
            }
            if (c == 0) {
              handCards = handCards.take(handCards.length - 1)
            }
            stack1.pop()
            stack2.pop()
          }
        }
      }
    }
    this
  }

  def pushCard(card: Card, color : Int, game: Game) : Player = {
    var c = 0
    var myCard = card
    if(color == 1) {
      myCard = Card(Color.Blue, card.value)
    } else if (color == 2) {
      myCard = Card(Color.Green, card.value)
    } else if (color == 3) {
      myCard = Card(Color.Yellow, card.value)
    } else if (color == 4) {
      myCard = Card(Color.Red, card.value)
    }
    stack3.push(card)
    for (i <- 2 to handCards.length) {
      if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
        game.init.cardsRevealed = myCard +: game.init.cardsRevealed
        handCards = handCards.take(i - 2) ++ handCards.drop(i-1)
        stack2.push(i-2)
        c += 1
        stack1.push(" ")
      }
    }
    if (c == 0) {
      game.init.cardsRevealed = myCard +: game.init.cardsRevealed
      handCards = handCards.take(handCards.length - 1)
      stack2.push(handCards.length-1)
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
    game.anotherPull = false
    this
  }

  def pushable(card: Card, game: Game) : Boolean = {
    if (game.init.cardsRevealed.head.value == Value.PlusTwo && card.value != Value.PlusTwo && game.special.top > 0) {
      return false
    } else if (game.init.cardsRevealed.head.value == Value.PlusFour && card.value != Value.PlusFour && game.special.top > 0) {
      return false
    } else if (card.value == Value.PlusFour) {
      for (i <- 1 to handCards.length) {
        if (handCards(i - 1).color == game.init.cardsRevealed.head.color &&
            game.init.cardsRevealed.head.color != Color.Schwarz && game.init.cardsRevealed.head.value != Value.PlusFour) {
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

  def pull(game: Game) : Player = {
    stack1.push(game.init.cardsCovered.head.toString)
    stack2.push(-1)
    handCards += Card(game.init.cardsCovered.head.color, game.init.cardsCovered.head.value)
    game.init.cardsCovered = game.init.cardsCovered.drop(1)
    if (game.special.top > 0) {
      game.anotherPull = false
      stack4.push(false)
      for (_ <- 2 to game.special.top) {
        stack1.push(game.init.cardsCovered.head.toString)
        stack2.push(-1)
        handCards += Card(game.init.cardsCovered.head.color, game.init.cardsCovered.head.value)
        game.init.cardsCovered = game.init.cardsCovered.drop(1)
      }
    } else {
      game.anotherPull = true
      game.hv = true
      stack4.push(true)
    }
    game.special.push(0)
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

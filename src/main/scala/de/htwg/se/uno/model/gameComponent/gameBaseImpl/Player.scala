package de.htwg.se.uno.model.gameComponent.gameBaseImpl


import scala.collection.mutable.{ListBuffer, Stack}

class Player() {
  var handCards = new ListBuffer[Card]()
  var stack1 = Stack[String]("Start")
  var stack2 = Stack[Integer](-1)
  var stack3 = Stack[Card]()



  def pushMove(string:String, game: Game) : Player = {
    if(equalsCard(string)) {
      val card = getCard(string)
      if (pushable(card, game)) {
        return pushCard(card, game)
      }
    }
    this
  }

  def pullMove(game:Game) : Player = {
    if (pullable(game)) {
      pull(game)
    } else {
      this
    }
  }

  def undo(game: Game) : Player = {
    if (stack1.top.equals(" ")) {
      handCards = handCards.take(stack2.top) ++ game.init.cardsRevealed.take(1) ++ handCards.drop(stack2.top)
      game.init.cardsRevealed = game.init.cardsRevealed.drop(1)
      if (stack3.top.value == Value.DirectionChange) {
        game.setDirection()
      }
      stack1.pop()
      stack2.pop()
      stack3.pop()
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
        stack1.pop()
        stack2.pop()
      }
    }
    this
  }

  def pushCard(card: Card, game: Game) : Player = {
    var c = 0
    stack3.push(card)
    for (i <- 2 to handCards.length) {
      if (handCards(i - 2).color == card.color && handCards(i - 2).value == card.value && c == 0) {
        game.init.cardsRevealed = handCards(i - 2) +: game.init.cardsRevealed
        handCards = handCards.take(i - 2) ++ handCards.drop(i-1)
        stack2.push(i-2)
        c += 1
        stack1.push(" ")
      }
    }
    if (c == 0) {
      game.init.cardsRevealed = handCards(handCards.length-1) +: game.init.cardsRevealed
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
    this
  }

  def pushable(card: Card, game: Game) : Boolean = {
    if (game.init.cardsRevealed.head.value == Value.PlusTwo && card.value != Value.PlusTwo && game.special.top != 0) {
      return false
    } else if (game.init.cardsRevealed.head.value == Value.PlusFour && card.value != Value.PlusFour && game.special.top != 0) {
      return false
    } else if (card.value == Value.PlusFour) {
      for (i <- 1 to handCards.length) {
        if (handCards(i - 1).color == game.init.cardsRevealed.head.color) {
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

  def pullable(game: Game) : Boolean = {
    for (i <- 1 to handCards.length) {
      if(pushable(handCards(i-1), game)) {
        return false
      }
    }
    true
  }

  def pull(game: Game) : Player = {
    stack1.push(game.init.cardsCovered.head.toString)
    stack2.push(-1)
    handCards += Card(game.init.cardsCovered.head.color, game.init.cardsCovered.head.value)
    game.init.cardsCovered = game.init.cardsCovered.drop(1)
    if (game.special.top > 0) {
      for (_ <- 2 to game.special.top) {
        stack1.push(game.init.cardsCovered.head.toString)
        stack2.push(-1)
        handCards += Card(game.init.cardsCovered.head.color, game.init.cardsCovered.head.value)
        game.init.cardsCovered = game.init.cardsCovered.drop(1)
        game.special.push(0)
      }
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

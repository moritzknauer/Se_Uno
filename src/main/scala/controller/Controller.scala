package scala.controller

import model.{Card, Game}

import util.Observable

class Controller(var game:Game) extends Observable {
  def createGame(size: Int = 7):Unit = {
    game = new Game(size)
    notifyObservers
  }

  def gameToString: String = game.toString

  def pushCard(card : Card):Unit = {
    game = game.pushCard(card)
    notifyObservers
  }

  def pushable(card : Card):Boolean = {
    game.pushable(card)
  }

  def pushableEnemy(card: Card): Boolean = {
    game.pushableEnemy(card)
  }

  def pushCardEnemy(card : Card):Unit = {
    game = game.pushCardEnemy(card)
    notifyObservers
  }

  def pullable():Boolean = {
    game.pullable()
  }

  def pullEnemy():Unit = {
    game = game.pullEnemy()
    notifyObservers
  }

  def enemy():Unit = {
    game = game.enemy()
    notifyObservers
  }

  def pull():Unit = {
    game = game.pull()
    notifyObservers
  }

  def equalsCard(s: String):Boolean = {
    game.equalsCard(s)
  }

  def getCard(s: String):Card = {
    game.getCard(s)
  }
}

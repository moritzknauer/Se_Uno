package scala.controller

import scala.model.{Card, Game}
import util.Observable

class Controller(var game:Game) extends Observable {
  def createGame(size: Int = 7):Unit = {
    game = Game(size)
    notifyObservers
  }

  def gameToString: String = game.toString

  def pushable(s: String):Boolean = {
    if (game.equalsCard(s)) {
      val card = game.getCard(s)
      game.pushable(card)
    } else {
      false
    }
  }

  def pushCard(s: String):Unit = {
    val card = game.getCard(s)
    game = game.pushCard(card)
    notifyObservers
  }

  def pullable():Boolean = {
    game.pullable()
  }

  def pull():Unit = {
    game = game.pull()
    notifyObservers
  }

  def enemy():Unit = {
    game = game.enemy()
    notifyObservers
  }

}

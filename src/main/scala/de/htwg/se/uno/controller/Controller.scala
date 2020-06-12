package de.htwg.se.uno.controller

import de.htwg.se.uno.model.Game
import de.htwg.se.uno.util.Observable


class Controller(var game:Game) extends Observable {
  def createGame(size: Int = 7):Unit = {
    game = Game(size)
    notifyObservers
  }

  def gameToString: String = game.toString

  def pushable(s: String):Boolean = {
    if (game.player.equalsCard(s)) {
      val card = game.player.getCard(s)
      game.player.pushable(card, game)
    } else {
      false
    }
  }

  def pushCard(s: String):Unit = {
    val card = game.player.getCard(s)
    game.player = game.player.pushCard(card, game)
    notifyObservers
  }

  def pullable():Boolean = {
    game.player.pullable(game)
  }

  def pull():Unit = {
    game.player = game.player.pull(game)
    notifyObservers
  }

  def enemy():Unit = {
    game.enemy = game.enemy.enemy(game)
    notifyObservers
  }
}

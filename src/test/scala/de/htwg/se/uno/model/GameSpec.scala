package de.htwg.se.uno.model

import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.Matchers._
import org.scalatest._

class GameSpec extends WordSpec {
  "A Game" when {
    "new" should {
      var newGame = Game(2)
      "Should be able to create a Game" in {
        newGame = newGame.createGame()
        newGame.getLength(4) should be(7)
      }
      "Should be able to create a Testgame" in {
        newGame = newGame.createTestGame()
        newGame.getLength(4) should be(9)
      }
      "Should be able to do a pushMove" in {
        newGame = newGame.pushMove("S C", 4)
        newGame.getLength(4) should be(8)
      }
      "Should not be able to do a pushMove" in {
        newGame.special.push(-1)
        newGame = newGame.pushMove("Hey", 0)
        newGame.getLength(4) should be(8)
      }
      "Should be able to do a pullMove" in {
        newGame = newGame.pullMove
        newGame.getLength(4) should be(9)
      }
      "Should not be able to do a pullMove" in {
        newGame.special.push(-1)
        newGame = newGame.pullMove
        newGame.getLength(4) should be(9)
      }
      "Should be able to do the enemy's run" in {
        newGame = newGame.enemy()
        newGame.getLength(0) should be(8)
      }
      "Should not be able to do the enemy's run" in {
        newGame.special.push(-1)
        newGame = newGame.enemy()
        newGame.getLength(0) should be(8)
      }
      "Should be able to do the enemy2's run" in {
        newGame = newGame.enemy2()
        newGame.getLength(1) should be(8)
      }
      "Should not be able to do the enemy2's run" in {
        newGame.special.push(-1)
        newGame = newGame.enemy2()
        newGame.getLength(1) should be(8)
      }
      "Should be able to do the enemy3's run" in {
        newGame = newGame.enemy3()
        newGame.getLength(2) should be(8)
      }
      "Should not be able to do the enemy3's run" in {
        newGame.special.push(-1)
        newGame = newGame.enemy3()
        newGame.getLength(2) should be(8)
      }
      "Should be able to undo the enemy's move" in {
        newGame = newGame.enemyUndo()
        newGame.getLength(0) should be(8)
      }
      "Should be able to undo the enemy2's move" in {
        newGame = newGame.enemyUndo2()
        newGame.getLength(1) should be(8)
      }
      "Should be able to undo the enemy3's move" in {
        newGame = newGame.enemyUndo3()
        newGame.getLength(2) should be(8)
      }

      "Should be able to undo the player's move" in {
        newGame = newGame.playerUndo()
        newGame.getLength(4) should be(9)
      }
      "Have a nice String representation" in{
        newGame.toString
      }
    }
  }
}

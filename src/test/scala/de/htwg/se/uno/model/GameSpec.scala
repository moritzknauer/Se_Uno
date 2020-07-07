package de.htwg.se.uno.model

import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.Matchers._
import org.scalatest._

import scala.swing.Color

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

      "Should be able to set the Length" in {
        newGame.setLength(1)
      }
      "Should be able to get the Length" in {
        newGame.getLength(0) should be(0)
      }
      "Should be able to get the Length again" in {
        newGame.getLength(0) should be(newGame.init.enemy.enemyCards.length)
      }
      "Should be able to set the Length again" in {
        newGame.setLength(2)
      }
      "Should be able to get the Length a third time" in {
        newGame.getLength(1) should be(0)
      }
      "Should be able to get the Length a fourth time" in {
        newGame.getLength(1) should be(newGame.init.enemy2.enemyCards.length)
      }
      "Should be able to set the Length a third time" in {
        newGame.setLength(3)
      }
      "Should be able to get the Length a fifth time" in {
        newGame.getLength(2) should be(0)
      }
      "Should be able to get the Length a sixth time" in {
        newGame.getLength(2) should be(newGame.init.enemy3.enemyCards.length)
      }
      "Should be able to set the Length a fourth time" in {
        newGame.setLength(4)
      }
      "Should be able to get the Length a seventh timt" in {
        newGame.getLength(3) should be(0)
      }
      "Should be able to get the Length a eigth time" in {
        newGame.getLength(3) should be(newGame.init.player.handCards.length)
      }

      "Should be able to get the Text of a Card" in {
        newGame.getCardText(3,1) should be(newGame.init.cardsRevealed.head.toString)
      }
      "Should be able to get the Text of another Card" in {
        newGame.getCardText(3,2) should be("Do Step")
      }
      "Should be able to get the Text of a third Card" in {
        newGame.getCardText(4,0) should be(newGame.init.player.handCards.head.toString)
      }
      "Should be able to get the Text of a fourth Card" in {
        newGame.getCardText(0,0) should be("Uno")
      }

      "Should be able to get the Gui Text of a Card" in {
        newGame.getGuiCardText(3,1) should be(newGame.init.cardsRevealed.head.toGuiString)
      }
      "Should be able to get the Gui Text of another Card" in {
        newGame.getGuiCardText(3,2) should be("Do Step")
      }
      "Should be able to get the Gui Text of a third Card" in {
        newGame.getGuiCardText(4,0) should be(newGame.init.player.handCards.head.toGuiString)
      }
      "Should be able to get the Gui Text of a fourth Card" in {
        newGame.getGuiCardText(0,0) should be("Uno")
      }

      "Should be able to get the number of players" in {
        newGame.getNumOfPlayers() should be(2)
      }

      "Should be able to get the next Enemy" in {
        newGame.nextEnemy() should be (1)
      }
      "Should be able to get the next Enemy a second Time" in {
        newGame = new Game(3)
        newGame.activePlayer = 0
        newGame.nextEnemy() should be(1)
      }
      "Should be able to get the next Enemy a third Time" in {
        newGame.setDirection()
        newGame.nextEnemy() should be(2)
      }
      "Should be able to get the next Enemy a fourth Time" in {
        newGame.activePlayer = 1
        newGame.nextEnemy() should be(2)
      }
      "Should be able to get the next Enemy a fifth Time" in {
        newGame.activePlayer = 2
        newGame.nextEnemy() should be(1)
      }
      "Should be able to get the next Enemy a sixth Time" in {
        newGame = new Game(4)
        newGame.activePlayer = 0
        newGame.nextEnemy() should be(1)
      }
      "Should be able to get the next Enemy a seventh Time" in {
        newGame.setDirection()
        newGame.nextEnemy() should be(3)
      }
      "Should be able to get the next Enemy a eigth Time" in {
        newGame.activePlayer = 1
        newGame.nextEnemy() should be(2)
      }
      "Should be able to get the next Enemy a nineth Time" in {
        newGame.activePlayer = 2
        newGame.setDirection()
        newGame.nextEnemy() should be(3)
      }
      "Should be able to get the next Enemy a tenth Time" in {
        newGame.setDirection()
        newGame.nextEnemy() should be(1)
      }
      "Should be able to get the next Enemy a elenth Time" in {
        newGame.activePlayer = 3
        newGame.nextEnemy() should be(2)
      }

      "Should know if it's not the players turn" in {
        newGame.nextTurn() should be(false)
      }
      "Should know if it's the players turn" in {
        newGame.setDirection()
        newGame.nextTurn() should be(true)
      }

      "Should know the next Enemy" in {
        newGame.setActivePlayer()
        newGame.getNextEnemy() should be(newGame.init.enemy)
      }
      "Should know the next Enemy a second time" in {
        newGame.setActivePlayer()
        newGame.getNextEnemy() should be(newGame.init.enemy2)
      }
      "Should know the next Enemy a third time" in {
        newGame.setActivePlayer()
        newGame.getNextEnemy() should be(newGame.init.enemy3)
      }

      "Should be able to set the active Player" in {
        newGame = newGame.setActivePlayer()
        newGame.getActivePlayer() should be(3)
      }
      "Should be able to set the active Player again" in {
        newGame = newGame.setActivePlayer()
        newGame.getActivePlayer() should be(0)
      }

      "Should be able to set the Direction" in {
        newGame = newGame.setDirection()
        newGame.getDirection() should be (false)
      }
      "Should be able to set the Direction again" in {
        newGame = newGame.setDirection()
        newGame.getDirection() should be (true)
      }
      "Should be able to set AnotherPull" in {
        newGame = newGame.setAnotherPull()
        newGame.anotherPull should be (false)
      }
      "Should be able to set the help variable" in {
        newGame = newGame.setHv()
        newGame.hv should be (true)
      }

      "Should be able to get the active Player" in {
        newGame.getActivePlayer() should be(newGame.activePlayer)
      }
      "Should be able to get the Direction" in {
        newGame.getDirection() should be(true)
      }
      "Should be able to get Another Pull" in {
        newGame.getAnotherPull() should be(newGame.anotherPull)
      }
      "Should be able to get the help variable 1" in {
        newGame.getHv() should be(newGame.hv)
      }
      "Should be able to get the help variable 2" in {
        newGame.getHv2() should be(newGame.hv2)
      }

      "Have a nice String representation" in{
        newGame.toString
      }
    }
  }
}

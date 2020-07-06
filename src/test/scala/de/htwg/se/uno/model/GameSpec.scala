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
      "Have a nice String representation" in{
        newGame.toString
      }
    }
  }
}

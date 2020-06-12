package de.htwg.se.uno.model

import org.scalatest.Matchers._
import org.scalatest._

class GameSpec extends WordSpec {
  "A Game" when {
    "new" should {
      var newGame = Game(1)
      "Have 10 Cards per Player" in {
        newGame.numOfCards should be (1)
      }
      "Have a List of Hand Cards" in {
        newGame.player.handCards.size should be (1)
      }
      "Have a List of Enemy Cards" in {
        newGame.enemy.enemyCards.size should be (1)
      }
      "Have a List of Revealed Cards" in {
        newGame.cardsRevealed.size should be (1)
      }
      "Have a List of Covered Cards" in {
        newGame.cardsCovered.size should be (105)
      }
      "Have a nice String representation" in{
        newGame.toString should be("┌-------┐  \n|       |  \n|  Uno  |  \n|       |  \n└-------┘  \n\n" +
          "┌-------┐             ┌-------┐\n|       |             |       |\n|  Uno  |             |  " +
          newGame.cardsRevealed.head.toString + "  |\n|       |             |       |\n" +
          "└-------┘             └-------┘\n\n┌-------┐  \n|       |  \n|  " +
          newGame.player.handCards(0).toString + "  |  \n|       |  \n└-------┘  ")
      }

      "be able to initialize the Lists for the Test Cases" in {
        newGame.initializeTestGame() should be(newGame)
      }


      "Have a new List of Hand Cards" in {
        newGame.player.handCards.size should be(5)
      }
      "Have a new List of Enemy Cards" in {
        newGame.enemy.enemyCards.size should be (5)
      }
      "Have a new List of Revealed Cards" in {
        newGame.cardsRevealed.size should be (1)
      }
      "Have a new List of Covered Cards" in {
        newGame.cardsCovered.size should be (97)
      }



    }
  }
}

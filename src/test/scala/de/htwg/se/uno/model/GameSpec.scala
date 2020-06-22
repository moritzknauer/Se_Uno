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
      "Have a nice String representation" in{
        newGame.toString should be("┌-------┐  \n|       |  \n|  Uno  |  \n|       |  \n└-------┘  \n\n" +
          "┌-------┐             ┌-------┐\n|       |             |       |\n|  Uno  |             |  " +
          newGame.init.cardsRevealed.head.toString + "  |\n|       |             |       |\n" +
          "└-------┘             └-------┘\n\n┌-------┐  \n|       |  \n|  " +
          newGame.init.player.handCards(0).toString + "  |  \n|       |  \n└-------┘  ")
      }
    }
  }
}

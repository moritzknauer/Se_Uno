package de.htwg.se.uno.model

import org.scalatest.Matchers._
import org.scalatest._

class InitializeRandomGameStrategySpec extends WordSpec {
  "An InitializeRandomGameStrategy" when {
    "new" should {
      var init = new InitializeRandomGameStrategy
      "Have a standard number of Cards of size seven" in {
        init = init.initializeGame()
      }
      "Have a List of Hand Cards" in {
        init.player.handCards.length should be(7)
      }
      "Have a List of Enemy Cards" in {
        init.enemy.enemyCards.length should be(7)
      }
      "Have a List of Revealed Cards" in {
        init.cardsRevealed.length should be(1)
      }
      "Have a List of Covered Cards" in {
        init.cardsCovered.length should be(93)
      }
    }
  }
}

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
        newGame.handCards.size should be (1)
      }
      "Have a List of Enemy Cards" in {
        newGame.enemyCards.size should be (1)
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
          newGame.handCards(0).toString + "  |  \n|       |  \n└-------┘  ")
      }
      "be able to check if a Card can be pushed" in{
        newGame.pushable(Card(newGame.cardsRevealed.head.color, newGame.cardsRevealed.head.value)) should be (true)
      }
      "be able to check if a second Card can be pushed" in{
        newGame.pushable(Card(Color.Blue, Value.Zero)) should be {
          if (newGame.cardsRevealed.head.color == Color.Blue || newGame.cardsRevealed.head.value == Value.Zero
                || newGame.cardsRevealed.head.color == Color.Schwarz) {
            true
          } else {
            false
          }
        }
      }
      "be able to check if a third Card can be pushed" in{
        newGame.pushable(Card(Color.Red, Value.One)) should be {
          if (newGame.cardsRevealed.head.color == Color.Red || newGame.cardsRevealed.head.value == Value.One
            || newGame.cardsRevealed.head.color == Color.Schwarz) {
            true
          } else {
            false
          }
        }
      }
      "be able to check if a fourth Card can be pushed" in{
        newGame.pushable(Card(Color.Yellow, Value.Three)) should be {
          if (newGame.cardsRevealed.head.color == Color.Yellow || newGame.cardsRevealed.head.value == Value.Three
                || newGame.cardsRevealed.head.color == Color.Schwarz
               ) {
            true
          } else {
            false
          }
        }
      }
      "be able to check if a fifth Card can be pushed" in{
        newGame.pushable(Card(Color.Schwarz, Value.ColorChange)) should be (true)
      }
      "be able to push a Card" in{
        newGame.pushCard(Card(newGame.handCards.head.color, newGame.handCards.head.value)) should be(newGame)
      }
      "be able to check if a sixth Card can be pushed" in{
        newGame.pushable(Card(Color.Schwarz, Value.PlusFour)) should be (true)
      }
      "be able to check if a Card can be pulled" in{
        newGame.pullable() should be (true)
      }
      "be able to pull a Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull another Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a third Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a forth Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a fifth Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a sixth Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a seventh Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a eighth Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a ninth Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a tenth Card" in{
        newGame.pull() should be (newGame)
      }
      "be able to check if a seventh Card can be pushed" in{
        newGame.pushable(Card(Color.Schwarz, Value.PlusFour)) should be {
          var j = 0
          for(i <- 1 to newGame.handCards.length) {
            if (newGame.handCards(i-1).color == newGame.cardsRevealed.head.color) {
              j += 1
            }
          }
          if(j != 0) {
            false
          } else {
            true
          }
        }
      }
      "be able to push another Card" in{
        newGame.pushCard(Card(newGame.handCards.head.color, newGame.handCards.head.value)) should be(newGame)
      }
      "be able to check if another Card can be pulled" in{
        newGame.pullable() should be (false)
      }
      "be able to Check if a String equals a Card of the List of Hand Cards" in{
        newGame.equalsCard(newGame.handCards(0).toString) should be (true)
      }
      "be able to Check if another String equals a Card of the List of Hand Cards" in{
        newGame.equalsCard("Hallo") should be (false)
      }
      "be able to return a Card of the List of Hand Cards that equals the String" in{
        newGame.getCard(newGame.handCards(0).toString) should be (newGame.handCards(0))
      }


      "be able to check if a Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(newGame.cardsRevealed.head.color, newGame.cardsRevealed.head.value)) should be (true)
      }
      "be able to check if a second Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(Color.Blue, Value.Zero)) should be {
          if (newGame.cardsRevealed.head.color == Color.Blue || newGame.cardsRevealed.head.value == Value.Zero
            || newGame.cardsRevealed.head.color == Color.Schwarz) {
            true
          } else {
            false
          }
        }
      }
      "be able to check if a third Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(Color.Red, Value.One)) should be {
          if (newGame.cardsRevealed.head.color == Color.Red || newGame.cardsRevealed.head.value == Value.One
            || newGame.cardsRevealed.head.color == Color.Schwarz) {
            true
          } else {
            false
          }
        }
      }
      "be able to check if a fourth Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(Color.Yellow, Value.Three)) should be {
          if (newGame.cardsRevealed.head.color == Color.Yellow || newGame.cardsRevealed.head.value == Value.Three
            || newGame.cardsRevealed.head.color == Color.Schwarz) {
            true
          } else {
            false
          }
        }
      }
      "be able to check if a fifth Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(Color.Schwarz, Value.ColorChange)) should be (true)
      }
      "be able to push a Card of the Enemys Cards" in{
        newGame.pushCardEnemy(Card(newGame.enemyCards.head.color, newGame.enemyCards.head.value)) should be(newGame)
      }
      "be able to check if a sixth Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(Color.Schwarz, Value.PlusFour)) should be (true)
      }
      "be able to do the Enemys Run" in{
        newGame.enemy() should be (newGame)
      }
      "be able to pull a Card to the Enemys Cards" in{
        newGame.pullEnemy() should be (newGame)
      }
      "be able to pull another Card to the Enemys Cards" in{
        newGame.pullEnemy() should be (newGame)
      }
      "be able to pull a third Card to the Enemys Cards" in{
        newGame.pullEnemy() should be (newGame)
      }
      "be able to pull a forth Card to the Enemys Cards" in{
        newGame.pullEnemy() should be (newGame)
      }
      "be able to pull a fifth Card to the Enemys Cards" in{
        newGame.pullEnemy() should be (newGame)
      }
      "be able to pull a sixth Card to the Enemys Cards" in{
        newGame.pullEnemy() should be (newGame)
      }
      "be able to pull a seventh Card to the Enemys Cards" in{
        newGame.pullEnemy() should be (newGame)
      }
      "be able to pull a eighth Card to the Enemys Cards" in{
        newGame.pullEnemy() should be (newGame)
      }
      "be able to check if a seventh Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(Color.Schwarz, Value.PlusFour)) should be {
          var j = 0
          for(i <- 1 to newGame.enemyCards.length) {
            if (newGame.enemyCards(i-1).color == newGame.cardsRevealed.head.color) {
              j += 1
            }
          }
          if(j != 0) {
            false
          } else {
            true
          }
        }
      }
      "be able to push another Card of the Enemys Cards" in{
        newGame.pushCardEnemy(Card(newGame.enemyCards.head.color, newGame.enemyCards.head.value)) should be(newGame)
      }
      "be able to do the Enemys Run again" in{
        newGame.enemy() should be (newGame)
      }
    }
  }
}

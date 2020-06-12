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

      "be able to initialize the Lists for the Test Cases" in {
        newGame.initializeTestGame() should be(newGame)
      }


      "Have a new List of Hand Cards" in {
        newGame.handCards.size should be(5)
      }
      "Have a new List of Enemy Cards" in {
        newGame.enemyCards.size should be (5)
      }
      "Have a new List of Revealed Cards" in {
        newGame.cardsRevealed.size should be (1)
      }
      "Have a new List of Covered Cards" in {
        newGame.cardsCovered.size should be (97)
      }


      "be able to check if a Card of the Players Card List can be pushed" in{
        newGame.pushable(Card(newGame.handCards(4).color, newGame.handCards(4).value)) should be (false)
      }
      "be able to check if a Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(newGame.enemyCards(4).color, newGame.enemyCards(4).value)) should be (false)
      }
      "be able to check if a second Card of the Players Card List can be pushed" in{
        newGame.pushable(Card(newGame.handCards(1).color, newGame.handCards(1).value)) should be (false)
      }
      "be able to check if a second Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(newGame.enemyCards(1).color, newGame.enemyCards(1).value)) should be (false)
      }


      "be able to check if a third Card of the Players Card List can be pushed" in{
        newGame.pushable(Card(newGame.handCards(0).color, newGame.handCards(0).value)) should be (true)
      }
      "be able to check if a third Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(newGame.enemyCards(0).color, newGame.enemyCards(0).value)) should be (true)
      }
      "be able to push a Card of the Players Card List" in{
        newGame.pushCard(Card(newGame.handCards.head.color, newGame.handCards.head.value)) should be(newGame)
      }
      "be able to push a Card of the Enemys Card List" in{
        newGame.pushCardEnemy(Card(newGame.enemyCards.head.color, newGame.enemyCards.head.value)) should be(newGame)
      }


      "be able to check if a fourth Card of the Players Card List can be pushed" in{
        newGame.pushable(Card(newGame.handCards(0).color, newGame.handCards(0).value)) should be (true)
      }
      "be able to check if a fourth Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(newGame.enemyCards(0).color, newGame.enemyCards(0).value)) should be (true)
      }
      "be able to push a second Card of the Players Card List" in{
        newGame.pushCard(Card(newGame.handCards.head.color, newGame.handCards.head.value)) should be(newGame)
      }
      "be able to push a second Card of the Enemys Card List" in{
        newGame.pushCardEnemy(Card(newGame.enemyCards.head.color, newGame.enemyCards.head.value)) should be(newGame)
      }


      "be able to check if a fifth Card of the Players Card List can be pushed" in{
        newGame.pushable(Card(newGame.handCards(1).color, newGame.handCards(1).value)) should be (true)
      }
      "be able to check if a fifth Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(newGame.enemyCards(1).color, newGame.enemyCards(1).value)) should be (true)
      }
      "be able to push a third Card of the Players Card List" in{
        newGame.pushCard(Card(newGame.handCards(1).color, newGame.handCards(1).value)) should be(newGame)
      }
      "be able to push a third Card of the Enemys Card List" in{
        newGame.pushCardEnemy(Card(newGame.enemyCards(1).color, newGame.enemyCards(1).value)) should be(newGame)
      }


      "be able to check if a sixth Card of the Players Card List can be pushed" in{
        newGame.pushable(Card(newGame.handCards(0).color, newGame.handCards(0).value)) should be (true)
      }
      "be able to check if a sixth Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(newGame.enemyCards(0).color, newGame.enemyCards(0).value)) should be (true)
      }
      "be able to push a fourth Card of the Players Card List" in{
        newGame.pushCard(Card(newGame.handCards(0).color, newGame.handCards(0).value)) should be(newGame)
      }
      "be able to push a fourth Card of the Enemys Card List" in{
        newGame.pushCardEnemy(Card(newGame.enemyCards(0).color, newGame.enemyCards(0).value)) should be(newGame)
      }


      "be able to check if a Card of the Players Card List can be pulled" in{
        newGame.pullable() should be (false)
      }


      "be able to check if a seventh Card of the Players Card List can be pushed" in{
        newGame.pushable(Card(newGame.handCards.head.color, newGame.handCards.head.value)) should be (true)
      }
      "be able to check if a seventh Card of the Enemys Card List can be pushed" in{
        newGame.pushableEnemy(Card(newGame.enemyCards.head.color, newGame.enemyCards.head.value)) should be (true)
      }
      "be able to push a fifth Card of the Players Card List" in{
        newGame.pushCard(Card(newGame.handCards.head.color, newGame.handCards.head.value)) should be(newGame)
      }
      "be able to push a fifth Card of the Enemys Card List" in{
        newGame.pushCardEnemy(Card(newGame.enemyCards.head.color, newGame.enemyCards.head.value)) should be(newGame)
      }


      "be able to check if a second Card of the Players Card List can be pulled" in{
        newGame.pullable() should be (true)
      }
      "be able to pull a Card to the Players Card List" in{
        newGame.pull() should be (newGame)
      }
      "be able to pull a Card to the Enemys Card List" in{
        newGame.pullEnemy() should be (newGame)
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


      "be able to do the Enemys Run" in{
        newGame.enemy() should be (newGame)
      }
      "be able to do the Enemys Run again" in{
        newGame.enemy() should be (newGame)
      }
    }
  }
}

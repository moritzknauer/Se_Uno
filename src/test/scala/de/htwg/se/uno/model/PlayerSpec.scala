package de.htwg.se.uno.model

import org.scalatest._
import org.scalatest.Matchers._

class PlayerSpec extends WordSpec {
  "A Player" when{
    "new" should{
      var newGame = Game()
      newGame = newGame.initializeTestGame()
      "be able to check if a Card of the Players Card List can be pushed" in{
        newGame.player.pushable(Card(newGame.player.handCards(4).color, newGame.player.handCards(4).value), newGame) should be (false)
      }
      "be able to check if a second Card of the Players Card List can be pushed" in{
        newGame.player.pushable(Card(newGame.player.handCards(1).color, newGame.player.handCards(1).value), newGame) should be (false)
      }


      "be able to check if a third Card of the Players Card List can be pushed" in{
        newGame.player.pushable(Card(newGame.player.handCards(0).color, newGame.player.handCards(0).value), newGame) should be (true)
      }
      "be able to push a Card of the Players Card List" in{
        newGame.player.pushCard(Card(newGame.player.handCards.head.color, newGame.player.handCards.head.value), newGame) should be(newGame.player)
      }


      "be able to check if a fourth Card of the Players Card List can be pushed" in{
        newGame.player.pushable(Card(newGame.player.handCards(0).color, newGame.player.handCards(0).value), newGame) should be (true)
      }
      "be able to push a second Card of the Players Card List" in{
        newGame.player.pushCard(Card(newGame.player.handCards.head.color, newGame.player.handCards.head.value), newGame) should be(newGame.player)
      }


      "be able to check if a fifth Card of the Players Card List can be pushed" in{
        newGame.player.pushable(Card(newGame.player.handCards(1).color, newGame.player.handCards(1).value), newGame) should be (true)
      }
      "be able to push a third Card of the Players Card List" in{
        newGame.player.pushCard(Card(newGame.player.handCards(1).color, newGame.player.handCards(1).value), newGame) should be(newGame.player)
      }


      "be able to check if a sixth Card of the Players Card List can be pushed" in{
        newGame.player.pushable(Card(newGame.player.handCards(0).color, newGame.player.handCards(0).value), newGame) should be (true)
      }
      "be able to push a fourth Card of the Players Card List" in{
        newGame.player.pushCard(Card(newGame.player.handCards(0).color, newGame.player.handCards(0).value), newGame) should be(newGame.player)
      }


      "be able to check if a Card of the Players Card List can be pulled" in{
        newGame.player.pullable(newGame) should be (false)
      }


      "be able to check if a seventh Card of the Players Card List can be pushed" in{
        newGame.player.pushable(Card(newGame.player.handCards.head.color, newGame.player.handCards.head.value), newGame) should be (true)
      }
      "be able to push a fifth Card of the Players Card List" in{
        newGame.player.pushCard(Card(newGame.player.handCards.head.color, newGame.player.handCards.head.value), newGame) should be(newGame.player)
      }


      "be able to undo a Step" in {
        newGame.player.undo(newGame) should be(newGame.player)
      }
      "be able to push a sixth Card of the Players Card List" in{
        newGame.player.pushCard(Card(newGame.player.handCards.head.color, newGame.player.handCards.head.value), newGame) should be(newGame.player)
      }


      "be able to check if a second Card of the Players Card List can be pulled" in{
        newGame.player.pullable(newGame) should be (true)
      }
      "be able to pull a Card to the Players Card List" in{
        newGame.player.pull(newGame) should be (newGame.player)
      }

      "be able to Check if a String equals a Card of the List of Hand Cards" in{
        newGame.player.equalsCard(newGame.player.handCards(0).toString) should be (true)
      }
      "be able to Check if another String equals a Card of the List of Hand Cards" in{
        newGame.player.equalsCard("Hallo") should be (false)
      }
      "be able to return a Card of the List of Hand Cards that equals the String" in{
        newGame.player.getCard(newGame.player.handCards(0).toString) should be (newGame.player.handCards(0))
      }
      "be able to undo another step" in {
        newGame.player.undo(newGame) should be(newGame.player)
      }
      "be able to undo a third step" in {
        newGame.player.undo(newGame) should be(newGame.player)
      }
      "be able to do a push move" in {
        newGame.player.pushMove(newGame.player.handCards.head.toString, newGame) should be(newGame.player)
      }
      "be able to do a pull Move" in {
        newGame.player.pullMove(newGame) should be(newGame.player)
      }
    }
  }
}

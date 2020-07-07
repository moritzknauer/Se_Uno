package de.htwg.se.uno.model

import de.htwg.se.uno.model.gameComponent.gameBaseImpl.{Card, Color, Game, InitializeGameStrategy, Value}
import org.scalatest._
import org.scalatest.Matchers._

class EnemySpec extends WordSpec {
  "A Enemy" when {
    "new" should {
      var newGame = Game(4)
      newGame.init = InitializeGameStrategy(1)
      newGame.init = newGame.init.initializeGame(4)
      "Be able to do the enemys run" in {
        newGame = newGame.createTestGame()
        newGame.init.player.handCards = newGame.init.player.handCards.drop(8)
        newGame.activePlayer = 3
        newGame.init.enemy3.enemy(newGame) should be (newGame.init.enemy3)
      }
      "Be able to do the enemy's run again" in {
        newGame = newGame.createTestGame()
        newGame.init.enemy2.enemyCards = newGame.init.enemy2.enemyCards.drop(8)
        newGame.activePlayer = 1
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "Be able to do the enemy's run a third time" in {
        newGame = newGame.createTestGame()
        newGame.pushMove("R+2", 0)
        newGame.activePlayer = 0
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "Be able to do the enemy's run a fourth time" in {
        newGame = newGame.createTestGame()
        newGame.activePlayer = 0
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "Be able to do the enemy's run a fifth time" in {
        newGame = newGame.createTestGame()
        newGame.init.enemy.enemyCards = newGame.init.enemy.enemyCards.take(2) ++ newGame.init.enemy.enemyCards.drop(4)
        newGame.activePlayer = 0
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "Be able to do the enemy's run a sixth time" in {
        newGame = newGame.createTestGame()
        newGame.init.cardsRevealed = Card(Color.Blue, Value.Four) +: newGame.init.cardsRevealed
        newGame.activePlayer = 0
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "Be able to do the enemy's run a seventh time" in {
        newGame = newGame.createTestGame()
        newGame.init.cardsRevealed = Card(Color.Blue, Value.DirectionChange) +: newGame.init.cardsRevealed
        newGame.activePlayer = 0
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "Be able to do the enemy's run a eigth time" in{
        newGame = newGame.createTestGame()
        newGame.init.enemy.enemyCards = newGame.init.enemy.enemyCards.take(1) ++ newGame.init.enemy.enemyCards.drop(2)
        newGame.activePlayer = 0
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "Be able to do the enemy's run a nineth time" in{
        newGame = newGame.createTestGame()
        newGame.init.enemy.enemyCards = newGame.init.enemy.enemyCards.take(2)
        newGame.init.cardsRevealed = Card(Color.Blue, Value.Five) +: newGame.init.cardsRevealed
        newGame.activePlayer = 0
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "Be able to do the enemy's run a tenth time" in{
        newGame = newGame.createTestGame()
        newGame.init.cardsRevealed = Card(Color.Schwarz, Value.ColorChange) +: newGame.init.cardsRevealed
        newGame.init.enemy.enemyCards = newGame.init.enemy.enemyCards.take(2) ++ newGame.init.enemy.enemyCards.drop(4)
        newGame.activePlayer = 0
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }













      /*
      "be able to check if a Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushable1(Card(newGame.init.enemy.enemyCards(4).color, newGame.init.enemy.enemyCards(4).value), newGame) should be (false)
      }
      "be able to check if a second Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushable1(Card(newGame.init.enemy.enemyCards(1).color, newGame.init.enemy.enemyCards(1).value), newGame) should be (false)
      }


      "be able to check if a third Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushable1(Card(newGame.init.enemy.enemyCards(0).color, newGame.init.enemy.enemyCards(0).value), newGame) should be (true)
      }
      "be able to push a Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards.head.color, newGame.init.enemy.enemyCards.head.value), newGame) should be(newGame.init.enemy)
      }


      "be able to check if a fourth Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushable1(Card(newGame.init.enemy.enemyCards(0).color, newGame.init.enemy.enemyCards(0).value), newGame) should be (true)
      }
      "be able to push a second Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards.head.color, newGame.init.enemy.enemyCards.head.value), newGame) should be(newGame.init.enemy)
      }


      "be able to check if a fifth Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushable1(Card(newGame.init.enemy.enemyCards(1).color, newGame.init.enemy.enemyCards(1).value), newGame) should be (true)
      }
      "be able to push a third Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards(1).color, newGame.init.enemy.enemyCards(1).value), newGame) should be(newGame.init.enemy)
      }


      "be able to check if a sixth Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushable1(Card(newGame.init.enemy.enemyCards(0).color, newGame.init.enemy.enemyCards(0).value), newGame) should be (true)
      }
      "be able to push a fourth Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards(0).color, newGame.init.enemy.enemyCards(0).value), newGame) should be(newGame.init.enemy)
      }


      "be able to check if a seventh Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushable1(Card(newGame.init.enemy.enemyCards.head.color, newGame.init.enemy.enemyCards.head.value), newGame) should be (true)
      }
      "be able to push a fifth Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards.head.color, newGame.init.enemy.enemyCards.head.value), newGame) should be(newGame.init.enemy)
      }


      "be able to do the Enemys Run" in{
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }
      "be able to do the Enemys Run again" in{
        newGame.init.enemy.enemy(newGame) should be (newGame.init.enemy)
      }

      "be able to pull a Card to the Enemys Card List" in {
        newGame.init.enemy.pullEnemy(newGame) should be(newGame.init.enemy)
      }
      "be able to pull another Card to the Enemys Card List" in {
        newGame.init.enemy.pullEnemy(newGame) should be(newGame.init.enemy)
      }
      "be able to pull a third Card to the Enemys Card List" in {
        newGame.init.enemy.pullEnemy(newGame) should be(newGame.init.enemy)
      }
      "be able to Undo a Step" in {
        newGame.init.enemy.undo(newGame) should be (newGame.init.enemy)
      }
      "be able to Undo another Step" in {
        newGame.init.enemy.undo(newGame) should be (newGame.init.enemy)
      }
      "be able to Undo a third Step" in {
        newGame.init.enemy.undo(newGame) should be (newGame.init.enemy)
      }
      "be able to Undo a fourth Step" in {
        newGame.init.enemy.undo(newGame) should be (newGame.init.enemy)
      }
      "be able to Undo a fifth Step" in {
        newGame.init.enemy.undo(newGame) should be (newGame.init.enemy)
      }
      "be able to Undo a sixth Step" in {
        newGame.init.enemy.undo(newGame) should be (newGame.init.enemy)
      }
      "be able to Undo a seventh Step" in {
        newGame.init.enemy.undo(newGame) should be (newGame.init.enemy)
      }
       */
    }
  }
}

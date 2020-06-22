package de.htwg.se.uno.model

import org.scalatest._
import org.scalatest.Matchers._

class EnemySpec extends WordSpec {
  "A Enemy" when {
    "new" should {
      var newGame = Game()
      newGame.init = InitializeGameStrategy(1)
      newGame.init = newGame.init.initializeGame()
      "be able to check if a Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushableEnemy(Card(newGame.init.enemy.enemyCards(4).color, newGame.init.enemy.enemyCards(4).value), newGame) should be (false)
      }
      "be able to check if a second Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushableEnemy(Card(newGame.init.enemy.enemyCards(1).color, newGame.init.enemy.enemyCards(1).value), newGame) should be (false)
      }


      "be able to check if a third Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushableEnemy(Card(newGame.init.enemy.enemyCards(0).color, newGame.init.enemy.enemyCards(0).value), newGame) should be (true)
      }
      "be able to push a Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards.head.color, newGame.init.enemy.enemyCards.head.value), newGame) should be(newGame.init.enemy)
      }


      "be able to check if a fourth Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushableEnemy(Card(newGame.init.enemy.enemyCards(0).color, newGame.init.enemy.enemyCards(0).value), newGame) should be (true)
      }
      "be able to push a second Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards.head.color, newGame.init.enemy.enemyCards.head.value), newGame) should be(newGame.init.enemy)
      }


      "be able to check if a fifth Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushableEnemy(Card(newGame.init.enemy.enemyCards(1).color, newGame.init.enemy.enemyCards(1).value), newGame) should be (true)
      }
      "be able to push a third Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards(1).color, newGame.init.enemy.enemyCards(1).value), newGame) should be(newGame.init.enemy)
      }


      "be able to check if a sixth Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushableEnemy(Card(newGame.init.enemy.enemyCards(0).color, newGame.init.enemy.enemyCards(0).value), newGame) should be (true)
      }
      "be able to push a fourth Card of the Enemys Card List" in{
        newGame.init.enemy.pushCardEnemy(Card(newGame.init.enemy.enemyCards(0).color, newGame.init.enemy.enemyCards(0).value), newGame) should be(newGame.init.enemy)
      }


      "be able to check if a seventh Card of the Enemys Card List can be pushed" in{
        newGame.init.enemy.pushableEnemy(Card(newGame.init.enemy.enemyCards.head.color, newGame.init.enemy.enemyCards.head.value), newGame) should be (true)
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
    }
  }
}

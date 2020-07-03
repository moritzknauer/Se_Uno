package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers{

  "A Game Tui" should {
    val controller = new Controller(Game(20))
    val tui = new Tui(controller)
    "create an empty Game on input 'n'" in {
      tui.processInputLine("n")
      controller.game should be(Game(7))
    }
    "create an empty Game on input 'n 20'" in {
      tui.processInputLine("n 20")
      controller.game should be(Game(20))
    }
    /*
    "Do nothing on input 'q" in{
      tui.processInputLine("q")
    }

     */
    "Only print on bad input like 'Something'" in {
      val old = controller.gameToString
      tui.processInputLine("Something")
      controller.gameToString should be(old)
    }
    "Set a Card on input s [Karte]" in{
      /*controller.game = controller.game.initializeTestGame()
      val s1 = controller.game.player.handCards.head.toString
      val s2 = controller.game.player.handCards.head.toString
      tui.processInputLine("s " + s1)
      controller.game.cardsRevealed(1).toString should be(s1)
      controller.game.cardsRevealed(0).toString should be(s2)*/
      tui.processInputLine("s " + controller.game.getCardText(1, 1))
    }
    "Dont set a Card on wrong input" in{
      val s3 = controller.gameToString
      tui.processInputLine("s Hallo")
      controller.gameToString should be (s3)
    }
    "Get no Card on input 'g' if not possible" in {
      tui.processInputLine("g")
    }
    "Get a Card on input 'g' if possible" in {
      /*val s4 = controller.game.cardsCovered.head.toString
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
      tui.processInputLine("g")
      controller.game.player.handCards(1).toString should be(s4)*/
      tui.processInputLine("g")
    }
    "Undo a Step on Input 'u' if possible" in {
      /*val s5 = controller.game.player.handCards(1).toString
      tui.processInputLine("u")
      controller.game.cardsCovered.head.toString should be(s5)*/
      tui.processInputLine("u")
    }
    "Redo a Step on Input 'r' if possible" in {
      /*val s6 = controller.game.cardsCovered.head.toString
      tui.processInputLine("r")
      controller.game.player.handCards(1).toString should be(s6)*/
      tui.processInputLine("r")
    }
  }
}

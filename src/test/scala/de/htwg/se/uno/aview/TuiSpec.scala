package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.Controller
import de.htwg.se.uno.model.{Game, Value}
import de.htwg.se.uno.aview.Tui
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers{
  "A Game Tui" should {
    val controller = new Controller(Game(20))
    val tui = new Tui(controller)
    "create an empty Game on input 'n'" in {
      tui.processInputLine("n")
      controller.game should be(Game())
    }
    "create an empty Game on input 'n 20'" in {
      tui.processInputLine("n 20")
      controller.game should be(Game(20))
    }
    "Do nothing on input 'q" in{
      tui.processInputLine("q")
    }
    "Only print on bad input like 'Something'" in {
      val old = controller.gameToString
      tui.processInputLine("Something")
      controller.gameToString should be(old)
    }
    "Set another Card on input s [Karte]" in{
      var c = 0
      for (i <- 1 to controller.game.handCards.length) {
        if(controller.game.pushable(controller.game.handCards(i-1))
            && controller.game.equalsCard(controller.game.handCards(i-1).toString)
            && controller.game.cardsRevealed.head.value != Value.PlusTwo
            && controller.game.cardsRevealed.head.value != Value.PlusFour) {
          c = i-1
        }
      }
      if (c != 0) {
        controller.pushable(controller.game.handCards(c).toString) should be(true)
        tui.processInputLine("s " + controller.game.handCards(c).toString)
      }
    }
    "Dont set a Card on wrong input" in{
      tui.processInputLine("s Hallo")
      controller.pushable("Hallo") should be (false)
    }
    "Get no Card on input 'g' if not possible" in {
      controller.pullable() should be(controller.game.pullable())
      tui.processInputLine("g")
    }
    "Get a Card on input 'g' if possible" in {
      var c1 = 1
      val c2 = controller.game.handCards.length
      for (_ <- 1 to c2) {
        val c3 = controller.game.handCards.length
        for (i <- 1 to c3) {
          if (controller.pushable(controller.game.handCards(i - c1).toString)) {
            controller.pushCard(controller.game.handCards(i - c1).toString)
            c1 += 1
          }
        }
        c1 = 1
      }
      controller.pullable() should be(true)
      tui.processInputLine("g")
    }
  }
}

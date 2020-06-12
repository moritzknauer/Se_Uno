package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.Controller
import de.htwg.se.uno.model.{Game, Value}
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
    "Set a Card on input s [Karte]" in{
      controller.game = controller.game.initializeTestGame()
      controller.pushable(controller.game.player.handCards.head.toString) should be(true)
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
    }
    "Dont set a Card on wrong input" in{
      tui.processInputLine("s Hallo")
      controller.pushable("Hallo") should be (false)
    }
    "Get no Card on input 'g' if not possible" in {
      controller.pullable() should be(controller.game.player.pullable(controller.game))
      tui.processInputLine("g")
    }
    "Get a Card on input 'g' if possible" in {
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
      tui.processInputLine("s " + controller.game.player.handCards.head.toString)
      controller.pullable() should be(true)
      tui.processInputLine("g")
    }
  }
}

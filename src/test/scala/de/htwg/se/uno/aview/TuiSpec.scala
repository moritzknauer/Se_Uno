package de.htwg.se.uno.aview

import de.htwg.se.uno.controller.Controller
import de.htwg.se.uno.model.Game
import de.htwg.se.uno.aview.Tui
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers{

  "A Game Tui" should {
    val controller = new Controller(Game())
    val tui = new Tui(controller)
    "create an empty Game on input 'n'" in {
      tui.processInputLine("n")
      controller.game should be(Game())
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
      tui.processInputLine("s " + controller.game.handCards.head.toString)
      controller.pushable(controller.game.handCards.head.toString) should be(controller.game.pushable(controller.game.handCards.head))
    }
    "Dont set a Card on wrong input" in{
      tui.processInputLine("s Hallo")
      controller.pushable("Hallo") should be (false)
    }
    "Get a Card on input 'g' if possible" in{
      tui.processInputLine("g")
      controller.pullable() should be(controller.game.pullable())
    }
  }
}

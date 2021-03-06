package de.htwg.se.uno.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.name.Names
import de.htwg.se.uno.model.gameComponent.GameInterface
import org.scalatest.Matchers._
import org.scalatest._
import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.uno.UnoModule
import net.codingwell.scalaguice.InjectorExtensions._

class FileIOSpec @Inject() extends WordSpec {
  "A FileIO" when {
    "new" should {
      val injector: Injector = Guice.createInjector(new UnoModule)
      var game = injector.instance[GameInterface](Names.named("4 Players"))
      game.createTestGame()
      val fileIo = new FileIO
      "Should be able to save and then load the game" in {
        fileIo.save(game)
        game.setActivePlayer()
        game.getActivePlayer() should be(0)
        game = fileIo.load
        game.getActivePlayer() should be (3)
      }
      "Should be able to save and then load the game again" in {
        game = injector.instance[GameInterface](Names.named("2 Players"))
        game.createGame()
        game.setDirection()
        game.setActivePlayer()
        fileIo.save(game)
        game.setActivePlayer()
        game.getActivePlayer() should be(1)
        game = fileIo.load
        game.getActivePlayer() should be(0)
      }
      "Should be able to save and then load the game a third time" in {
        game = injector.instance[GameInterface](Names.named("3 Players"))
        game.createGame()
        fileIo.save(game)
        game.setDirection()
        game.getDirection() should be(false)
        game = fileIo.load
        game.getDirection() should be(true)
      }
    }
  }
}
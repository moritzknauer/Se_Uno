package de.htwg.se.uno.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import org.scalatest.Matchers._
import org.scalatest._

class FileIOSpec extends WordSpec {
  "A FileIO" when {
    "new" should {
      var game = new Game(4)
      val fileIo = new FileIO
      "Should be able to save the game" in {
        fileIo.save(game)
      }
      "Should be able to load the game" in {
        fileIo.load
      }
      "Should be able to save the game again" in {
        game = new Game(2)
        game.createGame()
        game.setDirection()
        game.setActivePlayer()
        fileIo.save(game)
      }
      "Should be able to load the game again" in {
        fileIo.load
      }
      "Should be able to save the game a third time" in {
        game = new Game(3)
        game.createGame()
        fileIo.save(game)
      }
      "Should be able to load the game a third time" in {
        fileIo.load
      }
    }
  }
}
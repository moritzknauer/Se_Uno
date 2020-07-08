package de.htwg.se.uno.model.gameComponent.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.uno.UnoModule
import de.htwg.se.uno.model.gameComponent.GameInterface
import de.htwg.se.uno.model.gameComponent.fileIoComponent.FileIOInterface

import scala.xml.{Node, PrettyPrinter}

class FileIO extends FileIOInterface{
  override def load: GameInterface = {
    var game: GameInterface = null
    val file = scala.xml.XML.loadFile("game.xml")
    val injector = Guice.createInjector(new UnoModule)

    val numOfPlayers = (file \\ "game" \ "@numOfPlayers").text.toInt
    //val numOfPlayers = numOfPlayersAttr.text.toInt
    numOfPlayers match {
      case 2 => game = injector.instance[GameInterface](Names.named("2 Players"))
      case 3 => game = injector.instance[GameInterface](Names.named("3 Players"))
      case 4 => game = injector.instance[GameInterface](Names.named("4 Players"))
    }

    val activePlayer = (file \\ "game" \ "@activePlayer").text.toInt
    while (activePlayer != game.getActivePlayer()) {
      game = game.setActivePlayer()
    }

    val direction = (file \\ "game" \ "@direction").text.toBoolean
    if (direction != game.getDirection()) {
      game = game.setDirection()
    }

    val anotherPull = (file \\ "game" \ "@anotherPull").text.toBoolean
    game = game.setAnotherPull(anotherPull)




    game
  }

  override def save(game: GameInterface): Unit = saveString(game)

  def saveString(game: GameInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameToXml(game))
    pw.write(xml)
    pw.close()
  }

  def gameToXml(game: GameInterface): Node = {
    <game>
      <numOfPlayers>{ game.getNumOfPlayers().toString }</numOfPlayers>
      <activePlayer>{ game.getActivePlayer().toString }</activePlayer>
      <direction>{ game.getDirection().toString }</direction>
      <anotherPull>{ game.getAnotherPull().toString }</anotherPull>
      <specialTop>{ game.getSpecialTop().toString }</specialTop>
      <cardLists>
        {
          for {
            listNumber <- 0 to 5
            cardNumber <- 0 until game.getLength(listNumber)
          } yield {
            <card>{ game.getAllCards(listNumber, cardNumber) }</card>
          }
        }
      </cardLists>
      <listLengths>
        {
          for {
            i <- 0 to 5
          } yield {
            <length>{ game.getLength(i).toString }</length>
          }
        }
      </listLengths>
    </game>
  }
}

package de.htwg.se.uno

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.uno.controller.controllerComponent._
import de.htwg.se.uno.model.fileIoComponent.FileIOInterface
import de.htwg.se.uno.model.fileIoComponent.fileIoXmlImpl.FileIO
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game
import de.htwg.se.uno.model.gameComponent.GameInterface

class UnoModule extends AbstractModule with ScalaModule {
  val defaultPlayers:Int = 2

  override def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultPlayers")).to(defaultPlayers)
    bind[GameInterface].to[Game]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]

    bind[GameInterface].annotatedWithName("2 Players").toInstance(new Game(2))
    bind[GameInterface].annotatedWithName("3 Players").toInstance(new Game(3))
    bind[GameInterface].annotatedWithName("4 Players").toInstance(new Game(4))

    //bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
    bind[FileIOInterface].to[FileIO]
  }
}

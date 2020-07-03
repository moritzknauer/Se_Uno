package de.htwg.se.uno

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.uno.controller.controllerComponent._
import de.htwg.se.uno.model.gameComponent.GameInterface
import de.htwg.se.uno.model.gameComponent.gameBaseImpl.Game

class UnoModule extends AbstractModule with ScalaModule {
  val defaultSize:Int = 7

  override def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[GameInterface].to[Game]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[GameInterface].annotatedWithName("1 Card").toInstance(new Game(1))
    bind[GameInterface].annotatedWithName("7 Cards").toInstance(new Game(7))
    bind[GameInterface].annotatedWithName("15 Cards").toInstance(new Game(11))
  }
}

package de.htwg.se.uno.controller

import scala.swing.event.Event

class GameChanged extends Event
case class GameSizeChanged(newSize: Int = 7) extends Event

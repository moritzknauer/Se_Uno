package de.htwg.se.uno.util

trait Event

case class pushCardNotAllowedEvent() extends Event

case class enemyTurnEvent() extends Event

case class pullCardNotAllowedEvent() extends Event

case class unknownCommandEvent() extends Event
package de.htwg.se.uno.model.gameComponent.fileIoComponent

import de.htwg.se.uno.model.gameComponent.GameInterface

trait FileIOInterface {
  def load: GameInterface
  def save(grid: GameInterface): Unit
}

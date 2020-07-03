package de.htwg.se.uno.model

import de.htwg.se.uno.model.gameComponent.gameBaseImpl.{Card, Color, Value}
import org.scalatest.Matchers._
import org.scalatest._

class CardSpec extends WordSpec {

  "A Card" when {
    "new" should {
      val newCard = Card(Color.Blue, Value.Zero)
      "have color Blue" in {
        newCard.color should be(Color.Blue)
      }
      "have value Zero" in {
        newCard.value should be(Value.Zero)
      }
      "have a nice String representation" in{
        newCard.toString should be("B 0")
      }
    }
  }
}


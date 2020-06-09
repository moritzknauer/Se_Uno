import org.scalatest._
import Matchers._
import model.Card
import model.CardOptions._

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
    }
  }
}


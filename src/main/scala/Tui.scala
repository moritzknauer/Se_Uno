package scala

class Tui {

  def processInputLine(input: String, game: Game): Game = {
    val wf:Array[String] = input.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü^0-9]+")
    wf(0) match {
      case "q" => game
      case _ => {
        println("Eingegebener Befehl nicht bekannt. Mögliche Befehle: q, s [Karte], p \n")
        game
      }
    }
  }

}

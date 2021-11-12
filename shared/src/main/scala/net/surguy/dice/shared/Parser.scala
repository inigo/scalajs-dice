package net.surguy.dice.shared

/** Parse a string like "2d6" into a set of dice. */
class Parser {

  // Matches a string like 3d6! or 2d8 or d10
  private val regex = """.*?(\d+)?d(\d+)(!)?.*?""".r

  def parse(s: String): Seq[Die] = {
    s match {
      case regex(null, sides, null) => Seq(Die(sides.toInt, dieType = Standard))
      case regex(count, sides, null) => (1 to count.toInt).map(_ => Die(sides.toInt, dieType = Standard))
      case regex(null, sides, _) => Seq(Die(sides.toInt, dieType = Exploding))
      case regex(count, sides, _) => (1 to count.toInt).map(_ => Die(sides.toInt, dieType = Exploding))
      case _ => Nil
    }
  }

}

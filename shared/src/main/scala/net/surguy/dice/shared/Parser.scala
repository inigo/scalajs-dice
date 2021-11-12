package net.surguy.dice.shared

/** Parse a string like "2d6" into a set of dice. */
class Parser {

  // Matches a string like 3d6! or 2d8 or d10
  private val regex = """.*?(\d+)?d(\d+)(!)?(.*)""".r

  def parse(s: String): Seq[Die] = {
    s match {
      case regex(null, sides, null, remainder) => Seq(Die(sides.toInt, dieType = Standard)) ++ parse(remainder)
      case regex(count, sides, null, remainder) => (1 to count.toInt).map(_ => Die(sides.toInt, dieType = Standard)) ++ parse(remainder)
      case regex(null, sides, _, remainder) => Seq(Die(sides.toInt, dieType = Exploding)) ++ parse(remainder)
      case regex(count, sides, _, remainder) => (1 to count.toInt).map(_ => Die(sides.toInt, dieType = Exploding)) ++ parse(remainder)
      case _ => Nil
    }
  }

}

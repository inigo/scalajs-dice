package net.surguy.dice.shared

import org.specs2.mutable.Specification

class ParserTest extends Specification {

  private val parser = new Parser()
  import parser._

  private val d6 = Die(6)

  "parsing a dice description" should {
    "return nothing for an empty string" in { parse("") mustEqual Nil }
    "return nothing for a non matching string" in { parse("Fish") mustEqual Nil }
    "return a single 6 sided die for d6" in { parse("d6") mustEqual Seq(d6) }
    "return a single 12 sided die for d12" in { parse("d12") mustEqual Seq(Die(12)) }
    "return multiple dice for 3d6" in { parse("3d6") mustEqual Seq(d6, d6, d6) }
    "return ten dice for 10d6" in { parse("10d6") mustEqual Seq(d6, d6, d6, d6, d6, d6, d6, d6, d6, d6) }
    "return a single exploding die for d6!" in { parse("d6!") mustEqual Seq(Die(6, dieType = Exploding)) }
    "return multiple 6 sided die for 2d6!" in { parse("2d6!") mustEqual Seq(Die(6, dieType = Exploding), Die(6, dieType = Exploding)) }
    "ignore extraneous text" in { parse("Roll 2d6 now") mustEqual Seq(d6, d6) }
  }

  "parsing a dice description for multiple patterns" should {
    "match repeated dice patterns in the description" in { parse("d12 + d6") mustEqual Seq(Die(12), d6) }
    "still break out multiple dice in an individual description" in { parse("d12 + 2d6 + 1d10") mustEqual Seq(Die(12), d6, d6, Die(10)) }
    "still ignore extraneous text" in { parse("roll d12 and 2d6 then 1d10 please") mustEqual Seq(Die(12), d6, d6, Die(10)) }
  }


}

package net.surguy.dice.shared

import org.specs2.mutable.Specification

class RollerTest extends Specification with UsesDiceHelpers {

  private val roller = new Roller(new RandomRandomizer(Some(5)))

  "rolling a die" should {
    "return a single result" in { roller.roll(Die(6)).results must haveLength(1) }
    "return a reasonable result" in { roller.roll(Die(6)).results.head must beGreaterThanOrEqualTo(1).and(beLessThanOrEqualTo(6)) }
    "return an expected average" in { many(roller.roll(Die(6))) must haveAverage(3.5) }
    "return an expected average for a die with 8 sides" in { many(roller.roll(Die(8))) must haveAverage(4.5) }
  }

}

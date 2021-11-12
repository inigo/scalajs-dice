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

  "exploding dice" should {
    val explodingDie = Die(6, dieType = Exploding)

    "return a higher average" in { many(roller.roll(explodingDie)) must haveAverage(4.2) }
    "return multiple results if the max value is rolled" in {
      val fixedRoller = new Roller(new FixedRandomizer(6, 4, 3))
      fixedRoller.roll(explodingDie).results mustEqual Seq(6, 4)
    }
    "return many results if the max value is rolled several times" in {
      val fixedRoller = new Roller(new FixedRandomizer(6, 6, 6, 4, 3))
      fixedRoller.roll(explodingDie).results mustEqual Seq(6, 6, 6, 4)
    }
    "return a single result if lower values are rolled" in {
      val fixedRoller = new Roller(new FixedRandomizer(3, 4, 5))
      fixedRoller.roll(explodingDie).results mustEqual Seq(3)
      fixedRoller.roll(explodingDie).results mustEqual Seq(4)
      fixedRoller.roll(explodingDie).results mustEqual Seq(5)
    }
  }

}

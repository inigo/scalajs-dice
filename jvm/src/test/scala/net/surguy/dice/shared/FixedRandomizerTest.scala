package net.surguy.dice.shared

import org.specs2.mutable.Specification

class FixedRandomizerTest extends Specification {

  "returning random numbers" should {
    "repeat the provided sequence" in {
      val r = new FixedRandomizer(3, 5)
      (r.random(6), r.random(6), r.random(6), r.random(6)) mustEqual (3, 5, 3, 5)
    }
    "return a single number if only one provided" in {
      val r = new FixedRandomizer(3)
      (r.random(6), r.random(6), r.random(6), r.random(6)) mustEqual (3, 3, 3, 3)
    }
    "return the maximum if the provided sequence is higher" in {
      val r = new FixedRandomizer(4)
      (r.random(2), r.random(3), r.random(4), r.random(5)) mustEqual (2, 3, 4, 4)
    }
  }
}

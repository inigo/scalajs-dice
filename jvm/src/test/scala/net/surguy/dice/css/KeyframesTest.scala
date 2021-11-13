package net.surguy.dice.css

import org.specs2.mutable.Specification

class KeyframesTest extends Specification {

  "converting keyframes to CSS" should {
    "number frames appropriately" in {
      val k = Keyframes("test", Seq(TranslateKeyframe(0,0,0), TranslateKeyframe(50,30,20), TranslateKeyframe(200,200,200)))
      k.toCss.trim mustEqual
        """@keyframes test {
          | 0% { transform: translate3d(0px, 0px, 0px) }
          | 50% { transform: translate3d(50px, 30px, 20px) }
          | 100% { transform: translate3d(200px, 200px, 200px) }
          |}""".stripMargin.replaceAll("\r\n", "\n")
    }
  }

  "creating keyframes" should {
    "throw an exception if there are no keyframes" in {
      Keyframes("zog", Seq()) must throwAn[IllegalArgumentException]
    }
    "throw an exception if there is only one keyframe" in {
      Keyframes("zog", Seq(TranslateKeyframe(0,0,0))) must throwAn[IllegalArgumentException]
    }
    "throw an exception if there are different types of keyframe" in {
      // We can't have both numbered and unnumbered keyframes in the same group
      Keyframes("zog", Seq(TranslateKeyframe(0,0,0), RuleKeyframe(0, "transform"))) must throwAn[IllegalArgumentException]
    }
  }

}

package net.surguy.dice.css

/** CSS animation keyframe */
trait Keyframe {
   def toCss: String
}

case class TranslateKeyframe(x: Int, y: Int, z: Int) extends Keyframe {
  override def toCss: String = s"transform: translate3d(${x}px, ${y}px, ${z}px)"
}

case class RuleKeyframe(pct: Int, rule: String) extends Keyframe {
  def toCss: String = s""" $pct% { $rule }"""
}

case class Keyframes[T <: Keyframe](name: String, frames: Seq[T]) {
  if (frames.length <= 1) throw new IllegalArgumentException("Must be at least two keyframes")
  if (frames.exists(f => f.getClass != frames.head.getClass)) throw new IllegalArgumentException("Keyframes must all be of same type")

  def toCss: String = {
    val framesCss = frames match {
      case _ if frames.head.isInstanceOf[TranslateKeyframe] =>
        frames.zipWithIndex.collect{ case (k: Keyframe, i: Int) =>
          val pct = (i * 100 / (frames.length.toDouble-1)).toInt
          s""" $pct% { ${k.toCss} }"""
        }.mkString("\n")
      case _ => frames.map(_.toCss).mkString("\n")
    }
    s"""@keyframes $name {\n$framesCss\n}\n\n"""
  }
}

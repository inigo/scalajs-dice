package net.surguy.dice.shared

import java.util.UUID

sealed trait DieType
case object Standard extends DieType
case object Exploding extends DieType

case class Appearance(color: String)
object Appearance {
  val default: Appearance = Appearance("red")
}

case class Die(sides: Int, appearance: Appearance = Appearance.default, dieType: DieType = Standard, id: String = UUID.randomUUID().toString)

case class Result(die: Die, results: Seq[Int])
package net.surguy.dice.shared

import scala.util.Random

class Roller(randomizer: Randomizer) {

  def roll(dice: Seq[Die]): Seq[Result] = dice.map(roll)

  def roll(die: Die): Result = {
    val rolls = die.dieType match {
      case Standard => Seq(result(die))
      case Exploding => explode(die)
    }
    Result(die, rolls)
  }

  private def result(die: Die): Int = randomizer.random(die.sides)

  private def explode(die: Die): Seq[Int] = {
    result(die) match {
      case r if r == die.sides => Seq(r) ++ explode(die)
      case r => Seq(r)
    }
  }

}

trait Randomizer {
  def random(max: Int): Int
}

class RandomRandomizer(seed: Option[Int]) extends Randomizer {
  private val rnd = seed.map(s => new Random(s)).getOrElse(new Random())

  override def random(max: Int): Int = rnd.nextInt(max) + 1
}


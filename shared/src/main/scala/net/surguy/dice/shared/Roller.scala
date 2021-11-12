package net.surguy.dice.shared

import scala.util.Random

class Roller(randomizer: Randomizer) {

  def roll(dice: Seq[Die]): Seq[Result] = dice.map(roll)

  def roll(die: Die): Result = {
    val roll = randomizer.random(die.sides)
    Result(die, Seq(roll))
  }

}

trait Randomizer {
  def random(max: Int): Int
}

class RandomRandomizer(seed: Option[Int]) extends Randomizer {
  private val rnd = seed.map(s => new Random(s)).getOrElse(new Random())

  override def random(max: Int): Int = rnd.nextInt(max) + 1
}


package net.surguy.dice.shared

/** A [[Randomizer]] that cycles through the provided values, useful for testing. */
class FixedRandomizer(values: Int*) extends Randomizer {
  private var offset = 0

  override def random(max: Int): Int = {
    val result = Math.min(values(offset), max)
    if (offset+1 >= values.length) {
      offset = 0
    } else {
      offset = offset + 1
    }
    result
  }
}

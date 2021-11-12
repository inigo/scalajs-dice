package net.surguy.dice.shared

import org.specs2.matcher.Matcher
import org.specs2.matcher.Matchers._

trait UsesDiceHelpers {

  private val MANY = 500

  /** Repeat a block many times, for example to help in getting an average. */
  def many[T](fn: => T): Seq[T] = {
    (1 to MANY).map(_ => fn)
  }

  /** Check the average of a sequence of Result */
  def haveAverage(avg: Double): Matcher[Seq[Result]] = {
    val toAverage: Seq[Result] => Double = (r: Seq[Result]) => r.map(r => r.results.sum).sum / r.length.toDouble
    toAverage ^^ beCloseTo(avg, avg / 10D)
  }

}

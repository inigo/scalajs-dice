package net.surguy.dice

import org.scalajs.dom.CSSStyleSheet
import org.scalajs.dom.html.Canvas

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/**
  * Entry point
  */
@JSExportTopLevel("dice")
object Game {

  import org.scalajs.dom._

  @JSExport
  def play(canvas: Canvas): Unit = {
    val sheet = newStylesheet()

    val keyframes = Keyframes("bounce", Seq(
      Keyframe(0, "transform: translate3d(0, 0, 0)"),
      Keyframe(20, "transform: translate3d(100px, 500px, 20px)"),
      Keyframe(40, "transform: translate3d(200px, 100px, 40px)"),
      Keyframe(60, "transform: translate3d(300px, 500px, 60px)"),
      Keyframe(80, "transform: translate3d(400px, 200px, 80px)"),
      Keyframe(100, "transform: translate3d(500px, 500px, 100px)"),
    ))

    keyframes.addToSheet(sheet)
  }

  def addKeyframes(frames: Seq[Keyframes]): CSSStyleSheet = {
    val sheet = newStylesheet()
    frames.foreach(f => sheet.insertRule(f.toCss))
    sheet
  }

  def newStylesheet(): CSSStyleSheet = {
    val stylesheetEl = document.createElement("style").asInstanceOf[HTMLStyleElement]
    document.head.appendChild(stylesheetEl)
    stylesheetEl.sheet.asInstanceOf[CSSStyleSheet]
  }

}

case class Keyframe(pct: Int, rule: String) {
  def toCss: String = s""" $pct% { $rule }"""
}

case class Keyframes(name: String, frames: Seq[Keyframe]) {
  def toCss: String = s"""@keyframes $name {\n ${frames.map(_.toCss).mkString("\n") } }\n\n"""
  def addToSheet(sheet: CSSStyleSheet): Unit = sheet.insertRule(toCss)
}

package net.surguy.dice

import net.surguy.dice.css.{Keyframes, TranslateKeyframe}
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
      TranslateKeyframe(0,0,0),
      TranslateKeyframe(100,500,20),
      TranslateKeyframe(200,100,40),
      TranslateKeyframe(300,500,60),
      TranslateKeyframe(400,200,80),
      TranslateKeyframe(500,500,100),
     ))
    sheet.insertRule(keyframes.toCss)

    //    val textarea = document.createElement("textarea" ).asInstanceOf[HTMLTextAreaElement]
//    textarea.value = keyframes.toCss
//    document.body.appendChild(textarea)
  }

  def newStylesheet(): CSSStyleSheet = {
    val stylesheetEl = document.createElement("style").asInstanceOf[HTMLStyleElement]
    document.head.appendChild(stylesheetEl)
    stylesheetEl.sheet.asInstanceOf[CSSStyleSheet]
  }

}

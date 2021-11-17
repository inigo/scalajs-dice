package net.surguy.dice

import net.surguy.dice.css.{Keyframes, TranslateKeyframe}
import net.surguy.dice.shared.Die
import org.scalajs.dom.html.{Canvas, Div}
import scalatags.JsDom

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/**
  * Entry point
  */
@JSExportTopLevel("dice")
object Game {

  import org.scalajs.dom._
  import scalatags.JsDom.all._

  @JSExport
  def play(canvas: Canvas): Unit = {
    val sheet = newStylesheet()
    val (id1, id2) = ("one", "two")

    val keyframes1 = Keyframes(s"die-animation-$id1",
      (0 to 5).map(i => TranslateKeyframe(i * 120,if (i%2!=0) 500 else i * 50,i * 20))
    )
    sheet.insertRule(keyframes1.toCss)

    val keyframes2 = Keyframes(s"die-animation-$id2",
      (0 to 7).map(i => TranslateKeyframe(i * 120,if (i%2!=0) 500 else i * 20,i * -50))
    )
    sheet.insertRule(keyframes2.toCss)

//    document.body.appendChild(textarea(keyframes2.toCss).render)

    val dice = div(cls := "dice", displayDie(6, "red", id1), displayDie(6, "purple", id2))
    document.body.appendChild(dice.render)
  }

  def newStylesheet(): CSSStyleSheet = {
    val stylesheetEl = document.createElement("style").asInstanceOf[HTMLStyleElement]
    document.head.appendChild(stylesheetEl)
    stylesheetEl.sheet.asInstanceOf[CSSStyleSheet]
  }

  private def displayDie(die: Die): JsDom.TypedTag[Div] = displayDie(die.sides, die.appearance.color, die.id.getOrElse("noid"))

  private def displayDie(sides: Int, color: String, id: String): JsDom.TypedTag[Div] = {
    div(cls := s"die d$sides die-$color", style := s"animation-name: die-animation-$id",
      div(cls := "die-faces",
        (1 to sides).map(d => div(d.toString))
      )
    )
  }

}

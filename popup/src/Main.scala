package timeywimey

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.scalajs.dom

@main
def Main(): Unit =
  dom.console.log("Hello Content")
  val button = dom.document.getElementById("logTime").asInstanceOf[dom.html.Button]

  button.addEventListener("click", (_) => dom.console.log("Hello Content"))

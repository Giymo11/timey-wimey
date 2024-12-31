package timeywimey

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.scalajs.dom
import castor.SimpleActor
import scalachrome.ChromeGlobals.chrome
// import castor.Context

// implicit val ac: Context = new Context.Test()

// object PopupActor extends SimpleActor[String]:
//   def run(msg: String): Unit =
//     println(s"Popup: $msg")
//     if msg == "buttonClick" then chrome.runtime.sendMessage("buttonClick", (_) => ())

@main
def Main(): Unit =
  dom.console.log("Hello Content")
  val button = dom.document.getElementById("logTime").asInstanceOf[dom.html.Button]
  // button.addEventListener("click", (_) => PopupActor.send("buttonClick"))
  button.addEventListener("click", (_) => chrome.runtime.sendMessage("buttonClick", (_) => ()))

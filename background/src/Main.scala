package timeywimey

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.scalajs.dom

// import castor._
import scalachrome.ChromeGlobals.*

// implicit val ac: Context = new Context.Test()

// object BackgroundActor extends SimpleActor[String]:
//   def run(msg: String): Unit = dom.console.log(s"Service Worker received: $msg")

@main
def Main(): Unit =
  dom.console.log("Hello Background")
  chrome.runtime.onMessage.addListener((message: js.Any, _, _) =>
    // BackgroundActor.send(message.toString)
    println(message)
  )

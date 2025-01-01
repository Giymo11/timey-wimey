package timeywimey

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import scala.scalajs.js.timers.*

import org.scalajs.dom
import upickle.default.*
import castor.*

import Context.Simple.global
import scala.util.Try

object BackgroundActor extends SimpleActor[Message]:
  def run(msg: Message): Unit = msg match
    case Message.Log(content) => println(s"Logging: $content")
    case Message.Ping         => println("Ping received!")

@main def main(): Unit =
  dom.console.log("Hello Background")
  import chrome.runtime.Runtime
  Runtime.onMessage.listen: rawMessage =>
    val message = for
      value <- rawMessage.value.collect { case s: String => s }
      message <- Try(read[Message](value)).toOption
    yield message
    message.foreach(BackgroundActor.send)

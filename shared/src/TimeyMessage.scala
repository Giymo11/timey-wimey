package timeywimey

import upickle.default.*
import chrome.runtime.Runtime

enum Message derives ReadWriter:
  case Log(content: String)
  case Ping

extension (message: Message)
  def sendViaRuntime: Unit = Runtime.sendMessage(message = write(message))

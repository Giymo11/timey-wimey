package scalachrome

import scala.scalajs.js
import scala.scalajs.js.annotation.*

@js.native
@JSGlobalScope
object ChromeGlobals extends js.Object:
  /** Represents the `runtime` namespace in the Chrome API */
  val chrome: Chrome = js.native

@js.native
trait Chrome extends js.Object:
  val runtime: ChromeRuntime = js.native

/** Facade for `chrome.runtime` */
@js.native
trait ChromeRuntime extends js.Object:
  def sendMessage(message: js.Any, responseCallback: js.Function1[js.Any, Unit]): Unit = js.native

  def onMessage: ChromeRuntimeOnMessage = js.native

/** Facade for `chrome.runtime.onMessage` */
@js.native
trait ChromeRuntimeOnMessage extends js.Object:
  def addListener(
      callback: js.Function3[js.Any, js.UndefOr[js.Any], js.Function1[js.Any, Unit], Unit]
  ): Unit = js.native

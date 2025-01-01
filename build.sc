import mill._, mill.scalalib._, mill.scalajslib._

import $ivy.`com.github.lolgab::mill-scalablytyped::0.1.12`
import com.github.lolgab.mill.scalablytyped._

def resources = T.source(millSourcePath / "resources")

trait TimeyJsModule extends ScalaJSModule with ScalablyTyped {
  def scalaVersion = "3.6.2"
  def scalaJSVersion = "1.17.0"

  def ivyDeps = Agg(
    ivy"org.scala-js::scalajs-dom::2.8.0",
    ivy"com.lihaoyi::castor::0.3.0",
    ivy"com.lihaoyi::upickle::4.0.2",
    ivy"com.alexitc::scala-js-chrome::0.9.0"
  )
}

object shared extends TimeyJsModule

trait TimeyScriptModule extends TimeyJsModule {
  override def moduleDeps = Seq(shared)
}

object background extends TimeyScriptModule
object content extends TimeyScriptModule
object popup extends TimeyScriptModule

def bundleExtension = T {
  val extensionDir = resources().path
  os.copy.over(extensionDir, T.dest)

  def copyJsFiles(from: mill.api.PathRef, name: String) = {
    val files = os.list(from.path)
    files
      .find(_.ext == "js")
      .foreach(file => os.copy.over(file, T.dest / name, createFolders = true))
    files
      .find(_.ext == "map")
      .foreach(file => os.copy.over(file, T.dest / s"$name.map", createFolders = true))
  }

  copyJsFiles(background.fastLinkJS().dest, "background.js")
  copyJsFiles(content.fastLinkJS().dest, "content.js")
  copyJsFiles(popup.fastLinkJS().dest, "popup.js")

  T.dest
}

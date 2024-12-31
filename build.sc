import mill._, mill.scalalib._, mill.scalajslib._

// import $ivy.`com.github.lolgab::mill-scalablytyped::0.1.12`
// import com.github.lolgab.mill.scalablytyped._

// object `scalablytyped-module` extends ScalaJSModule with ScalablyTyped {
//   def scalaVersion = "3.6.2"
//   def scalaJSVersion = "1.17.0"
// }

def resources = T.source(millSourcePath / "resources")

trait TimeyModule extends ScalaJSModule {
  def scalaVersion = "3.6.2"
  def scalaJSVersion = "1.17.0"

  def ivyDeps = Agg(
    ivy"org.scala-js::scalajs-dom::2.8.0"
  )
}

object background extends TimeyModule
object content extends TimeyModule
object popup extends TimeyModule

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
      .foreach(file =>
        os.copy.over(file, T.dest / s"$name.map", createFolders = true)
      )
  }

  copyJsFiles(background.fastLinkJS().dest, "background.js")
  copyJsFiles(content.fastLinkJS().dest, "content.js")
  copyJsFiles(popup.fastLinkJS().dest, "popup.js")

  T.dest
}

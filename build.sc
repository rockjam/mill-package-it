import mill._, scalalib._
import mill.modules.Jvm
import mill.define.Task
import ammonite.ops._

object root extends Cross[Root]("2.11.12", "2.12.4")
class Root(val crossScalaVersion: String) extends CrossScalaModule {
  def scalaVersion = crossScalaVersion

  def millSourcePath = pwd

  def moduleDeps = Seq(models(crossScalaVersion))

  def ivyDeps = Agg(
    ivy"com.typesafe.akka::akka-http:10.0.13",
    ivy"de.heikoseeberger::akka-http-circe:1.20.0"
  )

  def packageIt = T {
    val dest = T.ctx().dest
    val libDir = dest / 'lib
    val binDir = dest / 'bin

    mkdir(libDir)
    mkdir(binDir)

    val allJars = packageSelfModules() ++ runClasspath()
      .map(_.path)
      .filter(path => exists(path) && !path.isDir)
      .toSeq

    allJars.foreach { file =>
      cp.into(file, libDir)
    }

    val runnerFile = Jvm.createLauncher(
      finalMainClass(),
      Agg.from(ls(libDir)),
      forkArgs()
    )

    mv.into(runnerFile.path, binDir)

    PathRef(dest)
  }

  // package root and dependent modules with meaningfull names
  def packageSelfModules = T {
    Task.traverse(moduleDeps :+ this) { module =>
      module.jar
        .zip(module.artifactName)
        .zip(module.artifactSuffix)
        .map {
          case ((jar, name), suffix) =>
            val namedJar = jar.path / up / s"${name}${suffix}.jar"
            cp(jar.path, namedJar)

            namedJar
        }
    }
  }

}

object models extends Cross[Models]("2.11.12", "2.12.4")
class Models(val crossScalaVersion: String) extends CrossScalaModule {
  def scalaVersion = crossScalaVersion

  def ivyDeps = Agg(
    ivy"io.circe::circe-core:0.9.3",
    ivy"io.circe::circe-generic:0.9.3",
    ivy"io.circe::circe-parser:0.9.3"
  )
}

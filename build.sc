import mill._, scalalib._
import ammonite.ops._

object root extends ScalaModule {
  def millSourcePath = pwd
  def scalaVersion = "2.12.4"

  def ivyDeps = Agg(
    ivy"com.typesafe.akka::akka-http:10.0.13",
    ivy"de.heikoseeberger::akka-http-circe:1.20.0",
    ivy"io.circe::circe-core:0.9.3",
    ivy"io.circe::circe-generic:0.9.3",
    ivy"io.circe::circe-parser:0.9.3"
  )

}

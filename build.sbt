ThisBuild / version      := "0.0.1-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.16"

val V = new {
  val catsEffect = "3.5.7"
  val zio        = "2.1.16"
  val pekko      = "1.1.3"
  val scalatest  = "3.2.19"
  val scalacheck = "3.2.19.0"
  val munit      = "1.0.7"
}

lazy val root = (project in file("."))
  .settings(
    organization := "com.tds",
    name         := "scala-sandbox",
    libraryDependencies ++= Seq(
      "org.typelevel"     %% "cats-effect"          % V.catsEffect,
      "dev.zio"           %% "zio"                  % V.zio,
      "dev.zio"           %% "zio-streams"          % V.zio,
      "org.apache.pekko"  %% "pekko-stream"         % V.pekko,
      "org.scalatest"     %% "scalatest"            % V.scalatest  % Test,
      "org.scalatestplus" %% "scalacheck-1-18"      % V.scalacheck % Test,
      "dev.zio"           %% "zio-test"             % V.zio        % Test,
      "dev.zio"           %% "zio-test-sbt"         % V.zio        % Test,
      "dev.zio"           %% "zio-test-magnolia"    % V.zio        % Test,
      "org.apache.pekko"  %% "pekko-stream-testkit" % V.pekko      % Test,
      "org.typelevel"     %% "munit-cats-effect-3"  % V.munit      % Test
    )
  )

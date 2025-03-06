ThisBuild / version      := "0.0.1-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    organization := "com.tds",
    name         := "scala-sandbox",
    libraryDependencies ++= Seq(
      "org.scalatest"     %% "scalatest"       % "3.2.19"   % "test",
      "org.scalatestplus" %% "scalacheck-1-18" % "3.2.19.0" % "test"
    )
  )

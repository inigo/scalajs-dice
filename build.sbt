
ThisBuild / scalaVersion := "2.13.7"

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val root = project.in(file(".")).
  aggregate(diceJS, diceJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val dice = crossProject.in(file(".")).
  settings(
    name := "scalajs-dice",
    organization := "net.surguy",
    version := "0.1"
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % "4.13.0" % "test"
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.0.0"
      , "com.lihaoyi" %%% "scalatags" % "0.10.0"
      , "com.lihaoyi" %%% "utest" % "0.7.10" % "test"
    )
  )

lazy val diceJVM = dice.jvm
lazy val diceJS = dice.js

testFrameworks += new TestFramework("utest.runner.Framework")


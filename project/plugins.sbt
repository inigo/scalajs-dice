addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.7.1")

//addSbtPlugin("cf.janga" % "sbts3" % "0.10.5")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.0")

addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")

// Workaround for "scalajsCom is not defined" bug
// https://github.com/scala-js/scala-js-js-envs/issues/12
libraryDependencies += "org.scala-js" %% "scalajs-env-nodejs" % "1.2.1"
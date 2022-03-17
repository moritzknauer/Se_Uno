name := "ScalaProject"

organization  := "de.htwg.se"

version := "0.6"

scalaVersion := "2.13.2"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.13" % "3.0.0"

libraryDependencies += "com.google.inject" % "guice" % "5.1.0"

libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.2"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2"
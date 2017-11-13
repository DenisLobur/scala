name := "scalaStuff"

version := "1.0"

scalaVersion := "2.12.3"
scalacOptions += "-feature"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.2.1",
//  "org.slf4j" % "slf4j-nop" % "1.7.10",
  "org.postgresql" % "postgresql" % "42.1.4",
  "com.h2database" % "h2" % "1.4.185",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)
        
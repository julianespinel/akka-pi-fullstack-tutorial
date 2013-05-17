// sbt build file

name := "pi-fullstack-tutorial"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.1"

resolvers ++= Seq(
//  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.1.4",
  "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
)

name := "DatarinoHomework"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.19",
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.akka" %% "akka-http" % "10.1.7",
  "io.circe" %% "circe-core" % "0.11.1",
  "io.circe" %% "circe-generic" % "0.11.1",
  "org.jsoup" % "jsoup" % "1.11.3",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

scalacOptions += "-target:jvm-1.8"
assemblyJarName in assembly := "scrapeBashOrgPl.jar"
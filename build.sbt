name := """play-java-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
scalaVersion := "2.13.8"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

lazy val akkaVersion = "2.6.0-M8"
lazy val akkaHttpVersion = "10.1.10"

libraryDependencies += guice
libraryDependencies ++= Seq(
  javaWs
)
libraryDependencies += ehcache
libraryDependencies += "com.ibm.async" % "asyncutil" % "0.1.0"
libraryDependencies += "org.projectlombok" % "lombok" % "1.18.2"
libraryDependencies += "eu.crydee" % "syllable-counter" % "3.0.0"
libraryDependencies += "org.mockito" % "mockito-core" % "2.22.0" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion % Test
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion % Test
libraryDependencies += "com.typesafe.akka" %% "akka-http-jackson" % akkaHttpVersion % Test
libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion % Test

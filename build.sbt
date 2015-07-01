//build.sbt

// Project name (artifact name in Maven)

lazy val commonSettings = Seq(
  version := "v0.3",
  organization := "fabiomgpinheiro@gmail.com",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "AndreiaProblem",
    scalacOptions += "-deprecation"
    //seq(assemblySettings: _*)
    //assemblyJarName in assembly := "something.jar"
  )





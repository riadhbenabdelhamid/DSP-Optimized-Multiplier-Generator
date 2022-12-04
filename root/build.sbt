scalaVersion := "2.12.13"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

scalacOptions ++= Seq("-deprecation","-unchecked","-Xsource:2.11","-feature","-language:reflectiveCalls")

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.4.4"

libraryDependencies += "edu.berkeley.cs" %% "chisel-iotesters" % "1.5.3"

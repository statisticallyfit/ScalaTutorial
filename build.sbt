addCommandAlias("namaste", "~test-only org.functionalkoans.forscala.Koans")

name := "Scala Koans"

version := "1.0"

scalaVersion := "2.11.8" //"2.12.0-RC1"

traceLevel := -1

logLevel := Level.Info

// disable printing timing information, but still print [success]
showTiming := false

// disable printing a message indicating the success or failure of running a task
showSuccess := false

// append -deprecation to the options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:postfixOps")

// disable updating dynamic revisions (including -SNAPSHOT versions)
offline := true

libraryDependencies ++= Seq(/*
	"org.scalatest" %% "scalatest" % "1.9.1" % "test" withSources() withJavadoc()*/
     //"org.specs2"      %% "specs2-core"    % "3.7" % Test
     "org.specs2" % "specs2-core_2.11" % "3.8.4-20160711064123-77be371" % Test,
     "org.scala-lang" % "scala-reflect" % "2.11.8",
     "org.apache.commons" % "commons-lang3" % "3.5"
     /*"org.scala-lang" % "scala-reflect" % "2.10.0" % Test*/
)

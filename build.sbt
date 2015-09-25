
import sbt._

import java.io.File

name := "csc155"

version := "1.0"

scalaVersion := "2.11.6"

unmanagedBase <<= baseDirectory { base => base / "lib" } 

mainClass in Compile := Some("com.celestia.Main")

libraryDependencies ++= Seq(
  "org.jogamp.gluegen" % "gluegen-rt-main" % "2.3.1",
  "org.jogamp.jogl" % "jogl-all-main" % "2.3.1"
)

libraryDependencies ++= Seq(
  "org.jruby" % "jruby-complete" % "1.6.5" 
)

// To Add Assembly function to Sbt:
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.7") 
//mainClass in Assembly := Some("com.celestia.Main") 


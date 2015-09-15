

name:="csc155"
version:="1.0"
scalaVersion:="2.11.6"

unmanagedBase<<=baseDirectory { base => base / "lib" } 
mainClass in Compile:=Some("com.celestia.Main")

// To Add Assembly function to Sbt:
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.7") 
//mainClass in Assembly := Some("com.celestia.Main") 


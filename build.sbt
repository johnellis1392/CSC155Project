
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

/*class Project(info:ProjectInfo) extends DefaultProject(info) {
  override def mainClass:Option[String]=Some("com.celestia.Main")
  def nativeJOGL:String={
    var os = System.getProperty("os.name").toLowerCase
    var arch = System.getProperty("os.arch").toLowerCase
    if(arch.matches("i386")) arch = "i586"
    if(os.contains("windows")) {
      os = "windows"
      arch = "i586" 
    }
    "./lib/jogl-2.0-%s-%s".format(os, arch) 
  }

  override def fork = forkRun("-Djava.library.path=%s".format(nativeJOGL) :: Nil) 
}*/

// To Add Assembly function to Sbt:
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.7") 
//mainClass in Assembly := Some("com.celestia.Main") 


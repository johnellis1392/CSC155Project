
# CSC 155 Class Project

This is the class project for CSC 155 - Advanced Graphics Programming

This project is written in Scala and uses Sbt as a build system.
In order to run the project, all you need is java installed; Scala
compiles down to JVM bytecode and can be packaged inside a jar file.

Included inside this directory are scripts for running the project
on linux and windows.

# To run:

Linux:
./run.sh

Windows:
./run.bat

Mac:
I have no idea


This program runs using Scala's "Simple Build Tool". It can be installed
on linux systems by issuing the command "apt-get install scala sbt".

To run the project, simply type "sbt run" after the build tool has been
installed. This will download the JOGL and JRuby repositories from Maven's
plugin repository, and then will run the project. I have also included the
project as an assembly inside the "target" directory, which can be run
using the command:

java -jar -cp target/[file-name-that-i-dont-remember].jar com.celestia.Main

Note that on windows systems you may need to include the option
-Djava.j2d.d3d=false to disable the DirectX Stupid Pointless Inverted
Coordinate system thing that no one likes.

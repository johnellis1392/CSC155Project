
:: java -jar target\scala-2.11\csc155-assembly-1.0.jar

java -cp %CLASSPATH%;lib\clojure-1.7.0.jar;target;src/main/clojure;lib\jruby-complete-9.0.1.0.jar -Dsun.java2d.d3d=false com.celestia.csc155.Main


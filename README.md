# This project is not endorsed by VVS, SSB, DB or other affiliated transport organizations!

# vvstray
A little app that shows the next train/subway/bus going your way in the system tray.

# Building

## Maven
Build the project using maven

### Required software
- Java JDK
- Maven
- jedit.jar (get from here: http://www.java2s.com/Code/Jar/j/Downloadjeditjar.htm)

### Install jedit
mvn install:install-file -Dfile=jedit.jar -DgroupId=org.gjt.sp -DartifactId=jedit -DgeneratePom=true -Dversion=1.0.0 -Dpackaging=jar

### Start build
mvn package -f pom.xml

### Run
mvn exec:java

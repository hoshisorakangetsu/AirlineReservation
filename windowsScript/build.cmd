set JAVA_HOME=".\.dev-kits\jdk-18.0.1.1"
".\.dev-kits\maven\bin\mvn.cmd" compile -f ".\pom.xml"

"%JAVA_HOME%\bin\java.exe" -cp ".\target\classes" com.mycompany.airlinereservation.AirlineReservation

set JAVA_HOME=.\.dev-kits\jdk-18.0.1.1
@Rem vanilla java build
"%JAVA_HOME%\bin\javac.exe" -cp ".\src\main\java" -d ".\target\classes" .\src\main\java\com\mycompany\airlinereservation\*.java
"%JAVA_HOME%\bin\java.exe" -cp ".\target\classes" com.mycompany.airlinereservation.AirlineReservation
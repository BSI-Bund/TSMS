@echo off

echo.
echo ############################
echo ### generate java-client ###
echo ############################
echo.

set MAVEN_OPTS=--add-opens=java.base/java.util=ALL-UNNAMED
call mvn -f yaml2javaclient/pom.xml clean install

echo.
pause
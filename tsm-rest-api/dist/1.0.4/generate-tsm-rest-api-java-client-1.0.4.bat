@echo off

echo.
echo ############################
echo ### generate java-client ###
echo ############################
echo.

set MAVEN_OPTS=--add-opens=java.base/java.util=ALL-UNNAMED
call mvn -f generate-tsm-rest-api-java-client-1.0.4-pom.xml clean install

echo.
pause
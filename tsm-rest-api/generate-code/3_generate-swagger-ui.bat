@echo off

echo.
echo ###########################
echo ### generate swagger-ui ###
echo ###########################
echo.

call mvn -f json2swaggerui/pom.xml clean install

echo.
pause
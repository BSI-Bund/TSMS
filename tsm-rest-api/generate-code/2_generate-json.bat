@echo off

echo.
echo #####################
echo ### generate json ###
echo #####################
echo.

call mvn -f yaml2json/pom.xml clean install

echo.
pause
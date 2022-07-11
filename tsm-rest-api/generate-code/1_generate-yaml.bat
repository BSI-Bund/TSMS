@echo off

echo.
echo #####################
echo ### generate yaml ###
echo #####################
echo.

call mvn -f pdf2yaml/pom.xml clean install

echo.
pause
@echo off

echo.
echo ##########################
echo ### build tsm-rest-api ###
echo ##########################
echo.

REM call mvn clean install --projects -:pdf2yaml
call mvn clean install -e

echo.

pause
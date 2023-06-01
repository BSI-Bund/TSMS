@echo off

echo.
echo ##########################
echo ### build tsm-rest-api ###
echo ##########################
echo.

REM call mvn clean install --projects -:pdf2yaml
REM call mvn clean install -e
call mvn clean install --projects -:pdf2yaml

echo.

pause
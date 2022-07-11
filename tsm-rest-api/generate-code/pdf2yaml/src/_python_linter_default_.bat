echo off

cls

pip install pylint

cd /D "%~dp0"

echo.
echo.
echo.

for %%F in (*.py) do (
	echo.
	echo pylint %%~nxF --module-naming-style camelCase --variable-naming-style snake_case --function-naming-style camelCase --argument-naming-style snake_case --method-naming-style camelCase
	echo.
	pylint %%~nxF --module-naming-style camelCase --variable-naming-style snake_case --function-naming-style camelCase --argument-naming-style snake_case --method-naming-style camelCase
	echo.
)

echo on
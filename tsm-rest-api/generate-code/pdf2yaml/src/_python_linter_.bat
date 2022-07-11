echo off

cls

pip install pylint

cd /D "%~dp0"

echo.
echo.
echo.

for %%F in (*.py) do (
	echo.
	echo pylint %%~nxF --module-naming-style camelCase --variable-naming-style snake_case --function-naming-style camelCase --argument-naming-style snake_case --method-naming-style camelCase --max-line-length 300 --max-returns 12 --max-statements 300 --max-branches 100 --max-attributes 30 --max-nested-blocks 10 --max-locals 30
	echo.
	pylint %%~nxF --module-naming-style camelCase --variable-naming-style snake_case --function-naming-style camelCase --argument-naming-style snake_case --method-naming-style camelCase --max-line-length 300 --max-returns 12 --max-statements 300 --max-branches 100 --max-attributes 30 --max-nested-blocks 10 --max-locals 30
	echo.
)

echo on
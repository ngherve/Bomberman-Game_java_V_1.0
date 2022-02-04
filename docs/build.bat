@echo off
cls
echo Setup JDK bin path before this script
echo Build script set to run in Project folder like Eclipse IDE
cd ..
SET PATH=%PATH%;"C:\Program Files\Java\jdk1.8.0_121\bin"
echo *** Setup folder variables ***
set PRAC_BIN=./bin
set PRAC_DOCS=./docs
set PRAC_LIB=./lib
set PRAC_SRC=./src
echo *** Compiling ***
javac -sourcepath %PRAC_SRC% -cp %PRAC_BIN%;%PRAC_LIB% -d %PRAC_BIN% %PRAC_SRC%/Main.java
IF %ERRORLEVEL% GTR 0 GOTO ERROR
echo *** Building JavaDoc ***
javadoc -sourcepath %PRAC_SRC% -classpath %PRAC_BIN%;%PRAC_LIB% -use -author -d %PRAC_DOCS%\JavaDocs -subpackages .
IF %ERRORLEVEL% GTR 0 GOTO ERROR
echo *** Running application ***
java -cp %PRAC_BIN%;%PRAC_LIB% Main
IF %ERRORLEVEL% GTR 0 GOTO ERROR
GOTO END
:ERROR
echo ! An error has occured !
echo Error number is %ERRORLEVEL%
:END
echo *** Completed ***
cd %PRAC_DOCS%
pause

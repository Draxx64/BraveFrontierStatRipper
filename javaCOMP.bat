echo off

cls

echo Compiling...

javac -Xprefer:newer -Xlint:unchecked -d %~dp0bin src\Main.java

echo Done compiling
pause

cls

cd %~dp0

javaEXEC.bat
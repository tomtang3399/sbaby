@echo off
echo [INFO] Use maven tomcat-plugin run the project.

cd %~dp0
cd ..

call mvn clean tomcat7:run -Dmaven.tomcat.port=8080

cd bin
pause
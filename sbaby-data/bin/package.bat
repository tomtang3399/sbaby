@echo off
echo [Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME.
echo [Pre-Requirement] Makesure install Maven 3.3.3+ and set the PATH.

set MVN=mvn
set MAVEN_OPTS=%MAVEN_OPTS% 
cd ..\
call %MVN% clean package  -Dmaven.test.skip=true

goto end
:error
echo Error Happen!!
:end
pause
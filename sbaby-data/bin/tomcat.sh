#!/bin/bash

echo [INFO] Use maven tomcat-plugin run the project.

cd ..

mvn clean tomcat7:run -Dmaven.tomcat.port=8080
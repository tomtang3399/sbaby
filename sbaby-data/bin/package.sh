#!/bin/bash

echo [INFO] Use maven tomcat-plugin run the project.

cd ..

mvn clean package  -Dmaven.test.skip=true

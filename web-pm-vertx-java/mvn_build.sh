#!/bin/bash

export JAVA_HOME=/usr/java/jdk-17.0.3+7
mvn -version

mvn clean install package -DskipTests

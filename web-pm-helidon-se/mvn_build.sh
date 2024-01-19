#!/bin/bash
export JAVA_HOME=/usr/java/jdk-21.0.1+12
mvn -version

mvn clean install package

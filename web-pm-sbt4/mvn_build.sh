#!/bin/bash

export JAVA_HOME=/usr/java/jdk-25.0.1+8
export M2_HOME=/opt/apache-maven-3.9.12
export MAVEN_HOME=/opt/apache-maven-3.9.12
export PATH=${M2_HOME}/bin:$PATH
mvn -version

mvn clean install package
#!/bin/bash

export JAVA_HOME=/usr/java/jdk-17.0.3+7
export MAVEN_HOME=/opt/apache-maven-3.6.3
${MAVEN_HOME}/bin/mvn -version

${MAVEN_HOME}/bin/mvn quarkus:dev -Dsmallrye.config.locations=./external.yml

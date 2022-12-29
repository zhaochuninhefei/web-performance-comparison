#!/bin/bash

export JAVA_HOME=/usr/java/jdk-17.0.3+7

${JAVA_HOME}/bin/java -jar target/web-pm-quarkus-0.0.1-runner.jar -Dsmallrye.config.locations=./external.yml

#!/bin/bash

export JAVA_HOME=/usr/java/jdk-21.0.1+12
# export JAVA_OPTS="-Djdk.virtualThreadScheduler.parallelism=200"

${JAVA_HOME}/bin/java ${JAVA_OPTS} -jar ./target/web-pm-sbt-vt-0.0.1.jar

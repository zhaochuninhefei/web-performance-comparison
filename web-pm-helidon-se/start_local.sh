#!/bin/bash
export JAVA_HOME=/usr/java/jdk-21.0.1+12
#export JAVA_OPS="-Djdk.tracePinnedThreads=full"

${JAVA_HOME}/bin/java ${JAVA_OPS} -jar ./target/web-pm-helidon-se.jar

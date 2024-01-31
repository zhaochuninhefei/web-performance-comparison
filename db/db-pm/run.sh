#!/bin/bash

export JAVA_HOME=/usr/java/jdk-21.0.1+12
#export JAVA_OPTS="-Djdk.tracePinnedThreads=short"

${JAVA_HOME}/bin/java ${JAVA_OPTS} -jar ./target/db-pm-*-jar-with-dependencies.jar 3

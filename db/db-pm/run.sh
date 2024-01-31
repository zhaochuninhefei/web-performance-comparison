#!/bin/bash

export JAVA_HOME=/usr/java/jdk-21.0.1+12
# export JAVA_OPTS="-Djdk.virtualThreadScheduler.parallelism=200"

${JAVA_HOME}/bin/java ${JAVA_OPTS} -cp ./target/db-pm-*-jar-with-dependencies.jar com.zhaochuninhefei.dbpm.DbpmMain 3

#!/bin/bash

export JAVA_HOME=/usr/java/jdk-17.0.3+7

${JAVA_HOME}/bin/java -jar ./target/web-pm-vertx-java-0.0.1-fat.jar run cn.yjl.vertx.MainVerticle -conf ./conf/application.json

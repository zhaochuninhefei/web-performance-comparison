#!/bin/bash

export JAVA_HOME=/usr/java/jdk-17.0.3+7

java -jar ./target/web-pm-vertx-0.0.1-fat.jar run cn.yjl.vertx.MainVerticle -conf ./conf/application.json
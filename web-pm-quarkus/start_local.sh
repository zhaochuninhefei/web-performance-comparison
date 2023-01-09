#!/bin/bash

set -e

echo
read -r -p "请选择启动方式: 1:uber-jar;2:native (默认 1:uber-jar ):" start_mode
if [ ! "${start_mode}" == "2" ]
then
  # jar包启动
  export JAVA_HOME=/usr/java/jdk-17.0.3+7
  ${JAVA_HOME}/bin/java -jar ./target/web-pm-quarkus-0.0.1-runner.jar -Dsmallrye.config.locations=./external.yml
else
  # native启动
  ./target/web-pm-quarkus-0.0.1-runner -Dsmallrye.config.locations=./external.yml
fi



#!/bin/bash

set -e

echo
read -r -p "请选择编译方式: 1:uber-jar;2:native (默认 1:uber-jar ):" build_mode
if [ ! "${build_mode}" == "2" ]
then
  build_mode=uber-jar
else
  build_mode=native
fi

export JAVA_HOME=/usr/java/jdk-17.0.3+7
mvn -version

mvn clean install package -Dquarkus.package.type=${build_mode}
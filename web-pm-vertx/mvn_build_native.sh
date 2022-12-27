#!/bin/bash

export GRAALVM_HOME=/opt/graalvm-ce-java17-22.3.0
export JAVA_HOME=/opt/graalvm-ce-java17-22.3.0
mvn -version

mvn clean package -Pnative
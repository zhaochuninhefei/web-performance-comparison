#!/bin/bash
img_name=web-pm-java
img_version=$(awk '/<version>[^<]+<\/version>/{gsub(/<version>|<\/version>/,"",$1);print $1;exit;}' pom.xml)

docker build -t ${img_name}:${img_version} ./
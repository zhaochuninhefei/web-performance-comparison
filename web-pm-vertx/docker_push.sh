#!/bin/bash
registry_host=172.17.4.86
registry_port=5000
img_name=web-pm-vertx
img_version=$(awk '/<version>[^<]+<\/version>/{gsub(/<version>|<\/version>/,"",$1);print $1;exit;}' pom.xml)

docker tag ${img_name}:${img_version} ${registry_host}:${registry_port}/${img_name}:${img_version}
docker push ${registry_host}:${registry_port}/${img_name}:${img_version}

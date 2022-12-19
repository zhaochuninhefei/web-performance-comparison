#!/bin/sh

registry_host=172.17.4.86
registry_port=5000
img_name=web-pm-go
img_version=0.0.1

docker tag ${img_name}:${img_version} ${registry_host}:${registry_port}/${img_name}:${img_version}
docker push ${registry_host}:${registry_port}/${img_name}:${img_version}

#!/bin/bash

set -e

DEFAULT_REGISTRY_HOST=172.17.4.86
DEFAULT_REGISTRY_PORT=5000
DEFAULT_IMG_NAME=yjl/web-pm-quarkus
DEFAULT_IMG_VERSION=0.0.1

echo
read -r -p "请输入要推送的镜像名(默认 ${DEFAULT_IMG_NAME} ):" IMG_NAME
if [ "${IMG_NAME}" == "" ]
then
  IMG_NAME=${DEFAULT_IMG_NAME}
fi
echo
echo "该镜像版本列表如下:"
docker images | grep ${IMG_NAME}
echo
read -r -p "请输入要推送的镜像版本(默认 ${DEFAULT_IMG_VERSION} ):" IMG_VERSION
if [ "${IMG_VERSION}" == "" ]
then
  IMG_VERSION=${DEFAULT_IMG_VERSION}
fi
echo
read -r -p "请输入docker仓库Host(默认 ${DEFAULT_REGISTRY_HOST} ):" REGISTRY_HOST
if [ "${REGISTRY_HOST}" == "" ]
then
  REGISTRY_HOST=${DEFAULT_REGISTRY_HOST}
fi
echo
read -r -p "请输入docker仓库Port(默认 ${DEFAULT_REGISTRY_PORT} ):" REGISTRY_PORT
if [ "${REGISTRY_PORT}" == "" ]
then
  REGISTRY_PORT=${DEFAULT_REGISTRY_PORT}
fi

echo
echo "您输入的镜像名与镜像版本是: ${IMG_NAME}:${IMG_VERSION}"
echo "您推送的目标仓库是: ${REGISTRY_HOST}:${REGISTRY_PORT}"
echo
read -r -p "请确定是否无误，是否推送?(y/n) " goon
if [ ! "$goon" == "y" ]
then
 exit 1
fi
echo
echo "开始给目标镜像打标签并推送..."
docker tag ${IMG_NAME}:${IMG_VERSION} ${REGISTRY_HOST}:${REGISTRY_PORT}/${IMG_NAME}:${IMG_VERSION}
docker push ${REGISTRY_HOST}:${REGISTRY_PORT}/${IMG_NAME}:${IMG_VERSION}
echo
echo "推送结束."

#!/bin/bash

set -e

DEFAULT_APP_NAME=web-pm-gin
DEFAULT_APP_VERSION=0.0.1

echo "您现在位于: "
pwd
echo
read -r -p "请输入应用名(默认 ${DEFAULT_APP_NAME} ):" APP_NAME
if [ "${APP_NAME}" == "" ]
then
  APP_NAME=${DEFAULT_APP_NAME}
fi
echo
read -r -p "请输入应用版本(默认 ${DEFAULT_APP_VERSION} ):" APP_VERSION
if [ "${APP_VERSION}" == "" ]
then
  APP_VERSION=${DEFAULT_APP_VERSION}
fi
echo
echo "您输入的应用名与应用版本是: ${APP_NAME}:${APP_VERSION}"
echo
read -r -p "请确定是否无误，是否继续?(y/n) " goon
if [ ! "$goon" == "y" ]
then
 exit 1
fi

echo
echo "===== 开始编译二进制可执行文件: ${APP_NAME} ====="
CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o ${APP_NAME} .
echo "编译二进制文件: "
ls -l ${APP_NAME}

echo
echo "===== 开始编译docker镜像: ${APP_NAME}:${APP_VERSION} ====="
docker build -t ${APP_NAME}:${APP_VERSION} .
echo
echo "docker编译结果:"
docker images | grep ${APP_NAME}
echo
echo "编译结束."
echo
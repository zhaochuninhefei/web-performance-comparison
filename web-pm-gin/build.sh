#!/bin/bash

set -e

DEFAULT_APP_NAME=web-pm-gin

echo "您现在位于: "
pwd
echo
read -r -p "请输入应用名(默认 ${DEFAULT_APP_NAME} ):" APP_NAME
if [ "${APP_NAME}" == "" ]
then
  APP_NAME=${DEFAULT_APP_NAME}
fi
echo
echo "您输入的应用名是: ${APP_NAME}"
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
echo "编译结束."
echo
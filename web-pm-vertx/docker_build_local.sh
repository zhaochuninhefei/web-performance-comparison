#!/bin/bash

set -e

img_name=web-pm-vertx
img_version=$(awk '/<version>[^<]+<\/version>/{gsub(/<version>|<\/version>/,"",$1);print $1;exit;}' pom.xml)

echo
read -r -p "请选择编译方式: 1:jar;2:native (默认 1:jar ):" build_mode
if [ ! "${build_mode}" == "2" ]
then
  build_mode=1
fi

if [ "${build_mode}" == "1" ]
then
  img_name=${img_name}-jar
  echo
  echo "您选择编译jar包镜像，镜像名与版本是: ${img_name}:${img_version}"
  echo
  read -r -p "请确定是否无误，是否继续?(y/n) " goon
  if [ ! "$goon" == "y" ]
  then
   exit 1
  fi
  echo
  echo "开始编译jar包"
  ./mvn_build_jar.sh
  echo
  echo "jar镜像编译开始..."
  rm -rf ./Dockerfile
  cp ./Dockerfile_jar ./Dockerfile
  docker build -t ${img_name}:${img_version} ./
  echo "jar镜像编译完成..."
else
  img_name=${img_name}-native
  echo
  echo "您选择编译native镜像，镜像名与版本是: ${img_name}:${img_version}"
  echo
  read -r -p "请确定是否无误，是否继续?(y/n) " goon
  if [ ! "$goon" == "y" ]
  then
   exit 1
  fi
#  echo
#  echo "开始编译二进制文件"
#  ./mvn_build_native.sh
  echo
  echo "native镜像编译开始..."
  rm -rf ./Dockerfile
  cp ./Dockerfile_native ./Dockerfile
  docker build -t ${img_name}:${img_version} ./
  echo "native镜像编译完成..."
fi
echo
echo "docker编译结果:"
docker images | grep ${img_name}
echo
echo "编译结束."
echo

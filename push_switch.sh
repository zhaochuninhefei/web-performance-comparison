#!/bin/bash

set -e

echo
echo "当前远程仓库地址:"
git remote -v

echo
read -r -p "请选择准备推送的远程仓库(默认: origin):" registry_sel
if [ "${registry_sel}" == "" ]
then
  registry_sel=origin
fi
echo
echo "目标远程仓库分支:"
git branch -r | grep "${registry_sel}"
echo
read -r -p "请选择准备推送的远程仓库分支(默认: main):" registry_branch
if [ "${registry_branch}" == "" ]
then
  registry_branch=main
fi
echo
echo "=========="
echo "您选择推送到: ${registry_sel}/${registry_branch}"
echo
read -r -p "请确定是否无误，是否继续?(y/n) " goon
if [ ! "$goon" == "y" ]
then
  exit 1
fi
echo
echo "开始推送..."
git push "${registry_sel}" "${registry_branch}"
echo
echo "推送结束."
echo

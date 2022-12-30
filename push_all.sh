#!/bin/bash

echo "----- 查看所有远程仓库:"
git remote -v

echo "----- 推送到github远程仓库:"
git push origin -all

echo "----- 推送到gitee远程仓库:"
git push gitee -all

echo "----- 推送结束"

#!/bin/bash

echo "----- 查看所有远程仓库:"
git remote -v

echo "----- 推送到所有远程仓库:"
git push origin --all

echo "----- 推送结束"

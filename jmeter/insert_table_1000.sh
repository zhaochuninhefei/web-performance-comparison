#!/bin/bash

curdir=$(pwd)
work_dir=~/work/sources/github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/db/mysql

cd "${work_dir}" || exit
go test -v -run "^TestInsert1000$"

cd "${curdir}" || exit

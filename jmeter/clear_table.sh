#!/bin/bash

curdir=$(pwd)
work_dir=~/sources/github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/db/mysql

cd "${work_dir}" || exit
go test -v -run TestTruncateTable

cd "${curdir}" || exit

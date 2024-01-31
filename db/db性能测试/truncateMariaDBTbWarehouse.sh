#!/bin/bash
curdir=$(pwd)
work_dir=~/work/sources/github.com/zhaochuninhefei/web-performance-comparison/db/db-pm

cd "${work_dir}" || exit
./run.sh -type p -method truncateMariaDBTbWarehouse

cd "${curdir}" || exit
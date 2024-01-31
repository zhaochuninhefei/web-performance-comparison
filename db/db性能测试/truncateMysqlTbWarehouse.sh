#!/bin/bash
set -e
cur_dir=$(pwd)
cd ../db-pm || exit
./run.sh -type p -method truncateMysqlTbWarehouse

cd "${cur_dir}" || exit
#!/bin/bash

set -e

work_dir=$(pwd)
test_target=mysql8
prefix_all=200_2m
test_threads_num=200
test_cnt=3


# 1. insert测试开始
echo
echo "insert测试开始..."
cd ./${test_target}-insert-${test_threads_num}

for((i=1;i<=test_cnt;i++)); do
    echo "${i}"
    echo
    echo "insert测试 第${i}轮..."
    # 第i轮
    # 清空测试表
    echo
    echo "清空测试表"
    ../truncateMysqlTbWarehouse.sh
    echo
    echo "等待5秒"
    sleep 5s
    # 执行测试计划
    echo
    echo "执行测试计划"
    ./run_jmeter.sh ${test_target}-insert-${test_threads_num}.jmx ${prefix_all}_${i}
    echo
    if [ ! "${i}" == "${test_cnt}" ]
    then
        echo
        echo "等待1分钟"
        sleep 60s
    fi
done

cd ../
echo
echo "insert测试结束..."

# 2. joinselect测试开始
echo
echo "joinselect测试开始..."
cd ./${test_target}-joinselect-${test_threads_num}

# 准备测试数据
echo
echo "准备测试数据"
../prepareMysqlDataForPT.sh
echo
echo "等待5秒"
sleep 5s

for((i=1;i<=test_cnt;i++)); do
    echo "${i}"
    echo
    echo "joinselect测试 第${i}轮..."
    # 第i轮
    # 执行测试计划
    echo
    echo "执行测试计划"
    ./run_jmeter.sh ${test_target}-joinselect-${test_threads_num}.jmx ${prefix_all}_${i}
    echo
    if [ ! "${i}" == "${test_cnt}" ]
    then
        echo
        echo "等待1分钟"
        sleep 60s
    fi
done

cd ../
echo
echo "joinselect测试结束..."

cd "${work_dir}"

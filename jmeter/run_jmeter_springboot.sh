#!/bin/bash

set -e

work_dir=$(pwd)
test_target=web-pm-springboot
prefix_all=5000_3m


# 1. 接口/account/add测试开始
echo
echo "接口/account/add测试开始..."
cd ./${test_target}-account_add

for((i=1;i<4;i++)); do
    echo "${i}"
    echo
    echo "接口/account/add测试 第${i}轮..."
    # 第i轮
    # 清空测试表
    echo
    echo "清空测试表"
    ../clear_table.sh
    echo
    echo "等待5秒"
    sleep 5s
    # 执行测试计划
    echo
    echo "执行测试计划"
    ./run_jmeter.sh ${test_target}-account_add.jmx ${prefix_all}_${i}
    echo
    echo "等待1分钟"
    sleep 60s
done

cd ../
echo
echo "接口/account/add测试结束..."

# 2. 接口/account/list测试开始
echo
echo "接口/account/list测试开始..."
cd ./${test_target}-account_list

# 清空测试表
echo
echo "清空测试表"
../clear_table.sh
echo
echo "插入1000条测试数据"
../insert_table_1000.sh
echo
echo "等待5秒"
sleep 5s

for((i=1;i<4;i++)); do
    echo "${i}"
    echo
    echo "接口/account/list测试 第${i}轮..."
    # 第i轮
    # 执行测试计划
    echo
    echo "执行测试计划"
    ./run_jmeter.sh ${test_target}-account_list.jmx ${prefix_all}_${i}
    echo
    echo "等待1分钟"
    sleep 60s
done

cd ../
echo
echo "接口/account/list测试结束..."

# 3. 接口/account/query测试开始
echo
echo "接口/account/query测试开始..."
cd ./${test_target}-account_query

for((i=1;i<4;i++)); do
    echo "${i}"
    echo
    echo "接口/account/query测试 第${i}轮..."
    # 第i轮
    # 执行测试计划
    echo
    echo "执行测试计划"
    ./run_jmeter.sh ${test_target}-account_query.jmx ${prefix_all}_${i}
    echo
    echo "等待1分钟"
    sleep 60s
done

cd ../
echo
echo "接口/account/query测试结束..."

# 4. 接口/asset/query测试开始
echo
echo "接口/asset/query测试开始..."
cd ./${test_target}-asset_query

for((i=1;i<4;i++)); do
    echo "${i}"
    echo
    echo "接口/asset/query测试 第${i}轮..."
    # 第i轮
    # 执行测试计划
    echo
    echo "执行测试计划"
    ./run_jmeter.sh ${test_target}-asset_query.jmx ${prefix_all}_${i}
    if [ ! "${i}" == "3" ]
    then
        echo
        echo "等待1分钟"
        sleep 60s
    fi
done

cd ../
echo
echo "接口/asset/query测试结束..."


cd "${work_dir}"

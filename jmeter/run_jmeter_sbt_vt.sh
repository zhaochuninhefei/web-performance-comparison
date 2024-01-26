#!/bin/bash

set -e

work_dir=$(pwd)
test_target=web-pm-sbt-vt
prefix_all=500_3m
test_cnt=3


# # 1. 接口/account/add测试开始
# echo
# echo "接口/account/add测试开始..."
# cd ./${test_target}-account_add

# for((i=1;i<=test_cnt;i++)); do
#     echo "${i}"
#     echo
#     echo "接口/account/add测试 第${i}轮..."
#     # 第i轮
#     # 清空测试表
#     echo
#     echo "清空测试表"
#     ../clear_table_maria.sh
#     echo
#     echo "等待5秒"
#     sleep 5s
#     # 执行测试计划
#     echo
#     echo "执行测试计划"
#     ./run_jmeter.sh ${test_target}-account_add.jmx ${prefix_all}_${i}
#     echo
#     if [ ! "${i}" == "${test_cnt}" ]
#     then
#         echo
#         echo "等待1分钟"
#         sleep 60s
#     fi
# done

# cd ../
# echo
# echo "接口/account/add测试结束..."

# # 2. 接口/account/list测试开始
# echo
# echo "接口/account/list测试开始..."
# cd ./${test_target}-account_list

# # 清空测试表
# echo
# echo "清空测试表"
# ../clear_table_maria.sh
# echo
# echo "插入1000条测试数据"
# ../insert_table_100000_maria.sh
# echo
# echo "等待5秒"
# sleep 5s

# for((i=1;i<=test_cnt;i++)); do
#     echo "${i}"
#     echo
#     echo "接口/account/list测试 第${i}轮..."
#     # 第i轮
#     # 执行测试计划
#     echo
#     echo "执行测试计划"
#     ./run_jmeter.sh ${test_target}-account_list.jmx ${prefix_all}_${i}
#     echo
#     if [ ! "${i}" == "${test_cnt}" ]
#     then
#         echo
#         echo "等待1分钟"
#         sleep 60s
#     fi
# done

# cd ../
# echo
# echo "接口/account/list测试结束..."

# # 3. 接口/account/query测试开始
# echo
# echo "接口/account/query测试开始..."
# cd ./${test_target}-account_query

# for((i=1;i<=test_cnt;i++)); do
#     echo "${i}"
#     echo
#     echo "接口/account/query测试 第${i}轮..."
#     # 第i轮
#     # 执行测试计划
#     echo
#     echo "执行测试计划"
#     ./run_jmeter.sh ${test_target}-account_query.jmx ${prefix_all}_${i}
#     if [ ! "${i}" == "${test_cnt}" ]
#     then
#         echo
#         echo "等待1分钟"
#         sleep 60s
#     fi
# done

# cd ../
# echo
# echo "接口/account/query测试结束..."

# # 4. 接口/asset/query测试开始
# echo
# echo "接口/asset/query测试开始..."
# cd ./${test_target}-asset_query

# for((i=1;i<=test_cnt;i++)); do
#     echo "${i}"
#     echo
#     echo "接口/asset/query测试 第${i}轮..."
#     # 第i轮
#     # 执行测试计划
#     echo
#     echo "执行测试计划"
#     ./run_jmeter.sh ${test_target}-asset_query.jmx ${prefix_all}_${i}
#     if [ ! "${i}" == "${test_cnt}" ]
#     then
#         echo
#         echo "等待1分钟"
#         sleep 60s
#     fi
# done

# cd ../
# echo
# echo "接口/asset/query测试结束..."

# 5. 接口/account/queryAmountByCtmLevel测试开始
echo
echo "接口/account/queryAmountByCtmLevel测试开始..."
cd ./${test_target}-cpo

for((i=1;i<=test_cnt;i++)); do
    echo "${i}"
    echo
    echo "接口/account/queryAmountByCtmLevel测试 第${i}轮..."
    # 第i轮
    echo
    echo "等待5秒"
    sleep 5s
    # 执行测试计划
    echo
    echo "执行测试计划"
    ./run_jmeter.sh ${test_target}-cpo.jmx ${prefix_all}_${i}
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
echo "接口/account/queryAmountByCtmLevel测试结束..."


cd "${work_dir}"

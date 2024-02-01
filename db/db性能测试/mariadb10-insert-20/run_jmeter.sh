#!/bin/bash

set -e

jmx_name=mariadb10-insert-20.jmx
prefix_all=20_2m

if [ ! "$1" == "" ]
then
    jmx_name=$1
fi
if [ ! "$2" == "" ]
then
    prefix_all=$2
fi

# 删除报告文件与目录
if [ -d "./${prefix_all}_report" ]; then
    echo "删除当前目录下的 ${prefix_all}_report 子目录..." 
    rm -rf "./${prefix_all}_report"
fi

if [ -f "./jmeter.log" ]; then
    echo "删除当前目录下的 jmeter.log 文件 ..." 
    rm -rf ./jmeter.log
fi

if [ -f "./monitor_report.jtl" ]; then
    echo "删除当前目录下的 monitor_report.jtl 文件 ..." 
    rm -rf ./monitor_report.jtl
fi

if [ -f "./${prefix_all}_monitor_report.png" ]; then
    echo "删除当前目录下的 ${prefix_all}_monitor_report.png 文件 ..." 
    rm -rf "./${prefix_all}_monitor_report.png"
fi

if [ -f "./${prefix_all}_result_report.jtl" ]; then
    echo "删除当前目录下的 ${prefix_all}_result_report.jtl 文件 ..." 
    rm -rf "./${prefix_all}_result_report.jtl"
fi

if [ -f "./${prefix_all}_aggregate.csv" ]; then
    echo "删除当前目录下的 ${prefix_all}_aggregate.csv 文件 ..." 
    rm -rf "./${prefix_all}_aggregate.csv"
fi

# 执行jmeter测试计划
/opt/apache-jmeter-5.1.1/bin/jmeter -n -t "${jmx_name}" -l "${prefix_all}_result_report.jtl" -e -o "${prefix_all}_report"

# 绘制PerfMon统计结果
/opt/apache-jmeter-5.1.1/bin/JMeterPluginsCMD.sh --generate-png "${prefix_all}_monitor_report.png" --input-jtl monitor_report.jtl --plugin-type PerfMon --width 800 --height 600

# 输出聚合报告
/opt/apache-jmeter-5.1.1/bin/JMeterPluginsCMD.sh --generate-csv "${prefix_all}_aggregate.csv" --input-jtl "${prefix_all}_result_report.jtl" --plugin-type AggregateReport

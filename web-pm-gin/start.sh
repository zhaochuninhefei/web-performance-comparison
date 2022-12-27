#!/bin/sh

if [ ! -d config/ ]
then
  mkdir config
fi

if [ ! -f config/app.yaml ]
then
  cp ./app.yaml config/
fi

./web-pm-go --app_config=/src/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config/app.yaml

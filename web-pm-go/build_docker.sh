#!/bin/bash

set -e

APP_NAME=web-pm-go
APP_VERSION=0.0.1

CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o ${APP_NAME} .

docker build -t ${APP_NAME}:${APP_VERSION} .

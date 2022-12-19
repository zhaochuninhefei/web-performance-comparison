#!/bin/bash

#CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o web-pm-go .

docker build -t web-pm-go:0.0.1 .

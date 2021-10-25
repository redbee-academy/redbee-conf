#!/usr/bin/env bash

set -e

./gradlew build

docker build -t io.redbee.conf/ms-conferences .
docker tag io.redbee.conf/ms-conferences:latest registry.dev.redbee.io/conf/ms-conferences:0
docker push registry.dev.redbee.io/conf/ms-conferences:0

kubectl apply -n redbee-conf manifests/deployment.yaml
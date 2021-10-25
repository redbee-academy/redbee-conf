#!/usr/bin/env bash

set -e

./gradlew build

docker build -t io.redbee.conf/ms-talks .
docker tag io.redbee.conf/ms-talks:latest registry.dev.redbee.io/conf/ms-talks:0
docker push registry.dev.redbee.io/conf/ms-talks:0

kubectl apply -n redbee-conf manifests/deployment.yaml
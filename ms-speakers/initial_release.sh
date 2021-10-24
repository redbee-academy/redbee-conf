#!/usr/bin/env bash

set -e

./gradlew build

docker build -t io.redbee.conf/ms-speakers .
docker tag io.redbee.conf/ms-speakers:latest registry.dev.redbee.io/conf/ms-speakers:0
docker push registry.dev.redbee.io/conf/ms-speakers:0

kubectl apply -n redbee-conf manifests/deployment.yaml
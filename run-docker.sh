#!/bin/bash

docker-machine create -d virtualbox --virtualbox-memory 4096 api-gateway-vb-node
eval $(docker-machine env api-gateway-vb-node)
mvn clean package
docker login -u="$DOCKER_USER" -p="$DOCKER_PASSWORD"
docker-compose up -d
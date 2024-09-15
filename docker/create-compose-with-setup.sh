#!/bin/bash

source docker-var.sh

## insert springboot entrypoint_template var
export SPRING_V1_ENTRYPOINT=$(./create-entrypoint.sh template/entrypoint_template)
export SPRING_V2_ENTRYPOINT=$(./create-entrypoint.sh template/entrypoint_v2_template)

envsubst < template/springboot-compose.template > docker-compose-springboot.yml
envsubst < template/mysql-compose.template > docker-compose-mysql.yml

## create docker network if not exists
if [ -z "$(docker network ls --filter name=^${DOCKER_NETWORK} --format '{{ .Name }}')" ]; then
    docker network create $DOCKER_NETWORK
fi

# create mysql volume if not exists
if [ -z "$(docker volume ls --filter name=^${MYSQL_DOCKER_VOLUME} --format '{{ .Name }}')" ]; then
    docker volume create $MYSQL_DOCKER_VOLUME
fi

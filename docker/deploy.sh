#!/bin/bash

# usage: ./deploy.sh mysql or ./deploy.sh springboot

CONTAINER="$1" # mysql or springboot
COMPOSE_FILE="docker-compose-$CONTAINER.yml"

# create docker compose and setup(create volume, network..)
./create-compose-with-setup.sh

# start container
docker compose -f $COMPOSE_FILE down
docker compose -f $COMPOSE_FILE pull
docker compose -f $COMPOSE_FILE up -d

# remove unused images
docker image prune -f
#!/bin/bash

## variables for create docker-compose file(template/mysql-compose.template, template/springboot-compose.template)
## used in create-compose-with-setup.sh
## format: export key="value"
## e.g. export DOCKER_USERNAME="foo"

## fill with your own value
## docker common
export DOCKER_NETWORK= # docker network name
export DOCKER_USERNAME=

## springboot
export SPRINGBOOT_CONTAINER= # springboot container name

## mysql
export MYSQL_VERSION=
export MYSQL_CONTAINER= # mysql container name
export MYSQL_PORT=
export MYSQL_DATABASE=
export MYSQL_USER=
export MYSQL_PASSWORD=
export MYSQL_ROOT_PASSWORD=
export MYSQL_DOCKER_VOLUME= # mysql docker volume name
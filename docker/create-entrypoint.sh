#!/bin/bash

## script for creating entrypoint
# $1: entrypoint template file
# result: ["java", "-jar", "-Dkey1=value1", "-Dkey2=value2", "/app.jar"]

ENV_FILE="tmp"
envsubst < $1 > $ENV_FILE

# create
IFS_PREV=$IFS
IFS=$'\n'

PREFIX="[\"java\", \"-jar\""
SUFFIX=", \"/app.jar\"]"

for line in $(cat $ENV_FILE); do
  # ignore comment and empty line
  # only process key=value line
  if [[ ! "$line" =~ ^# ]] [[ -n "$line" ]]; then
    key=${line%%=*}
    value=${line#*=}
    PREFIX="$PREFIX, \"-D$key=$value\""
  fi
done

RESULT="$PREFIX$SUFFIX"

echo "$RESULT"

IFS=$IFS_PREV
rm -f tmp

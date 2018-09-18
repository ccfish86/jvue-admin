#!/bin/bash

echo "[deploying jar for jvue-api]"
if [ -e /data/jvue-api-0.0.1-SNAPSHOT.jar ]; then
  echo "deploy jvue-admin [jvue-api]"
  systemctl stop jvue-admin
  mv /data/jvue-api-0.0.1-SNAPSHOT.jar /data/java-server/ -f 
  chmod +x /data/java-server/jvue-api-0.0.1-SNAPSHOT.jar
  systemctl start jvue-admin
fi

echo "[deploying done]"

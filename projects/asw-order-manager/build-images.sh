#!/bin/bash

for svc in api-gateway order-service product-service order-validation-service; do
  echo building $svc
  docker build . --build-arg JAR=${svc} -t ghcr.io/buonhobo/asw-${svc}:latest
done

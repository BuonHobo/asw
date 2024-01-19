#!/bin/bash

for svc in api-gateway order-service product-service order-validation-service; do
  echo pushing $svc
  docker push ghcr.io/buonhobo/asw-${svc}:latest
done

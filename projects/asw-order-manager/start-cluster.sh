#!/bin/bash

#Start Kafka using helm
echo "installing kafka"
helm install kafka \
  --set listeners.client.protocol=PLAINTEXT \
  --set controller.replicaCount=1 \
  --set controller.persistence.enabled=false \
  --set broker.persistence.enabled=false \
  oci://registry-1.docker.io/bitnamicharts/kafka
echo "waiting for kafka"
kubectl wait --for=condition=ready svc kafka

#Start postgres databases using helm
echo "installing order-service-db"
helm install order-service-db \
  --set primary.persistence.enabled=false \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql
echo "waiting for order-service-db"
kubectl wait --for=condition=ready svc order-service-db-postgresql

echo "installing product-service-db"
helm install product-service-db \
  --set primary.persistence.enabled=false \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql
echo "waiting for product-service-db"
kubectl wait --for=condition=ready svc product-service-db-postgresql

echo "installing order-validation-service-db"
helm install order-validation-service-db \
  --set primary.persistence.enabled=false \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql
echo "waiting for order-validation-service-db"
kubectl wait --for=condition=ready svc order-validation-service-db-postgresql

#Start ordermanager
kubectl apply -f kubernetes

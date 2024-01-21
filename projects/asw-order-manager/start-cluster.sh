#!/bin/bash

# Persistence of helm and kafka requires a dynamic provisioner.
# Minikube and Kind provide one automatically but the Vagrant VM environment doesn't.
# For this reason, persistence is disabled by default.

if [ -z "$PERSISTENCE_ENABLED" ]; then
  PERSISTENCE_ENABLED=false
fi

#Start Kafka using helm
echo "installing kafka"
helm install kafka \
  --set listeners.client.protocol=PLAINTEXT \
  --set controller.persistence.enabled=$PERSISTENCE_ENABLED \
  --set volumePermissions.enabled=true \
  --set controller.replicaCount=1 \
  --set controller.persistence.size=1Gi \
  --set broker.persistence.enabled=$PERSISTENCE_ENABLED \
  --set broker.persistence.size=1Gi \
  oci://registry-1.docker.io/bitnamicharts/kafka

#Start postgres databases using helm
echo "installing order-service-db"
helm install order-service-db \
  --set primary.persistence.enabled=$PERSISTENCE_ENABLED \
  --set primary.persistence.size=1Gi \
  --set volumePermissions.enabled=true \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql

echo "installing product-service-db"
helm install product-service-db \
  --set primary.persistence.enabled=$PERSISTENCE_ENABLED \
  --set primary.persistence.size=1Gi \
  --set volumePermissions.enabled=true \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql

echo "installing order-validation-service-db"
helm install order-validation-service-db \
  --set primary.persistence.enabled=$PERSISTENCE_ENABLED \
  --set primary.persistence.size=1Gi \
  --set volumePermissions.enabled=true \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql

#Start ordermanager
kubectl apply -f kubernetes

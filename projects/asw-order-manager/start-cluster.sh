#!/bin/bash



#Start Kafka using helm
helm install kafka --set listeners.client.protocol=PLAINTEXT oci://registry-1.docker.io/bitnamicharts/kafka

#Start postgres databases using helm
helm install order-service-db \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql
helm install product-service-db \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql
helm install order-validation-service-db \
  --set global.postgresql.auth.postgresPassword=ordermanager \
  oci://registry-1.docker.io/bitnamicharts/postgresql

#Start ordermanager
kubectl apply -f kubernetes
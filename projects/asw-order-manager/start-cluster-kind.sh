#!/bin/bash

#Start cluster
kind create cluster --config kind-config.yaml

#This makes the ingress-nginx work with KIND
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
echo "waiting for kind patch"
kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=90s #This is just waiting for that "patch" to apply

source start-cluster.sh

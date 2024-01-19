#!/bin/bash

kind create cluster --config kind-config.yaml
kubectl create namespace ordermanager
kubectl apply -f kubernetes -n ordermanager
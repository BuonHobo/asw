#!/bin/bash

#Start cluster
minikube start --driver='docker' --addons=['ingress'] --nodes=3
minikube addons enable ingress

source start-cluster.sh

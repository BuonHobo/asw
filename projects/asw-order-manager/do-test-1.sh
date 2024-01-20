#!/bin/bash

if [ -z $HOST ]; then
  HOST=localhost:8080
fi

source do-init-products.sh 
source do-init-orders.sh 
source do-validate-orders-123.sh 

source do-update-products.sh
source do-validate-orders-123.sh
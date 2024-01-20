#!/bin/bash

if [ -z $HOST ]; then
  echo "PLEASE SET THE HOST ENVIRONMENT VARIABLE"
  exit 1
fi

# aggiorna il db dei prodotti 

curl -X PATCH "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"Guerra e Pace\", \"stockLevelVariation\": \"-1\" }"
echo 

curl -X PATCH "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"Anna Karenina\", \"stockLevelVariation\": \"2\" }"
echo 

curl -X PATCH "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"I promessi sposi\", \"stockLevelVariation\": \"-2\" }"
echo 

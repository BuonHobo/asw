#!/bin/bash

if [ -z $HOST ]; then
  echo "PLEASE SET THE HOST ENVIRONMENT VARIABLE"
  exit 1
fi

# inizializza il db dei prodotti
curl -X POST "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -H "Host: ordermanager.asw.it" \
     -d "{ \"name\": \"Guerra e Pace\", \"category\": \"Libro\", \"stockLevel\": \"3\", \"price\": \"19.99\" }"
echo 

curl -X POST "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -H "Host: ordermanager.asw.it" \
     -d "{ \"name\": \"Anna Karenina\", \"category\": \"Libro\", \"stockLevel\": \"1\", \"price\": \"10.99\" }"
echo 

curl -X POST "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -H "Host: ordermanager.asw.it" \
     -d "{ \"name\": \"I promessi sposi\", \"category\": \"Libro\", \"stockLevel\": \"3\", \"price\": \"9.99\" }"
echo 

curl -X POST "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -H "Host: ordermanager.asw.it" \
     -d "{ \"name\": \"Il Signore degli Anelli\", \"category\": \"Libro\", \"stockLevel\": \"3\", \"price\": \"29.99\" }"
echo 

curl -X POST "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -H "Host: ordermanager.asw.it" \
     -d "{ \"name\": \"1984\", \"category\": \"Libro\", \"stockLevel\": \"5\", \"price\": \"8.99\" }"
echo 

curl -X POST "http://$HOST/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -H "Host: ordermanager.asw.it" \
     -d "{ \"name\": \"The Dark Side Of The Moon\", \"category\": \"Musica\", \"stockLevel\": \"4\", \"price\": \"19.99\" }"
echo 
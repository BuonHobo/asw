#!/bin/bash

# convalida alcuni ordini 

echo "# convalida l'ordine 1" 
echo $(curl -s localhost:8080/ordervalidationservice/ordervalidations/1)
echo 

echo "# convalida l'ordine 2" 
echo $(curl -s localhost:8080/ordervalidationservice/ordervalidations/2)
echo 

echo "# convalida l'ordine 3" 
echo $(curl -s localhost:8080/ordervalidationservice/ordervalidations/3)
echo 


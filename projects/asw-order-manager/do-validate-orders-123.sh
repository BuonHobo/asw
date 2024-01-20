#!/bin/bash

if [ -z $HOST ]; then
  echo "PLEASE SET THE HOST ENVIRONMENT VARIABLE"
  exit 1
fi

# convalida alcuni ordini 

echo "# convalida l'ordine 1" 
echo $(curl -s $HOST/ordervalidationservice/ordervalidations/1 -H "Host: ordermanager.asw.it")
echo 

echo "# convalida l'ordine 2" 
echo $(curl -s $HOST/ordervalidationservice/ordervalidations/2 -H "Host: ordermanager.asw.it")
echo 

echo "# convalida l'ordine 3" 
echo $(curl -s $HOST/ordervalidationservice/ordervalidations/3 -H "Host: ordermanager.asw.it")
echo 


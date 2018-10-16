#! /bin/bash

cc AdjacencyMatrixGraph.c -o test
if [ $? -eq 0 ]
then
    ./test
fi

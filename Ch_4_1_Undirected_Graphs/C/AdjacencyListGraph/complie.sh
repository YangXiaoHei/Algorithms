#! /bin/bash

cc AdjacencyListGraph_test.c AdjacencyListGraph.c -o test
if [ $? -eq 0 ]
then
    ./test
fi

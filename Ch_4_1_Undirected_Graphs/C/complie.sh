#! /bin/bash

cc Graph_test.c Graph.c -o test_Graph
if [ $? -eq 0 ]
then
    ./test_Graph
fi

#include <stdio.h>
#include "Graph.h"

void display(int v) {
    printf("%d ", v);
}

void test(void) {

    setbuf(stdout, NULL);

    /* 测试 createGraph API */
    struct G *g = createGraph(13);

    /* 测试 addEdge API */
    addEdge(g, 0, 1);  
    addEdge(g, 0, 2);
    addEdge(g, 0, 6);
    addEdge(g, 0, 5);
    addEdge(g, 3, 5);
    addEdge(g, 3, 4);
    addEdge(g, 5, 4);
    addEdge(g, 6, 4);
    addEdge(g, 7, 8);
    addEdge(g, 9, 10);
    addEdge(g, 9, 11);
    addEdge(g, 9, 12);
    addEdge(g, 11, 12);

    /* 测试 mark API */
    mark(g, 3);   
    mark(g, 5);
    mark(g, 8);
    mark(g, 3);
    mark(g, 9);
    mark(g, 12);

    /* 测试 toString API */
    printf("%s", toString(g));  

    /* 测试 marked 和 getVertexCount API */
    for (int i = 0; i < getVertexCount(g); i++)
        if (marked(g, i))
            printf("%d marked!\n", i);

    /* 测试 unmark API */    
    unmark(g, 3);   
    unmark(g, 8);
    unmark(g, 9);

    printf("%s", toString(g));

    /* 测试 clearAllMarked API */   
    clearAllMarked(g);

    printf("%s", toString(g));

    /* 测试 DFS API */   
    DFS(g, 0, display);
    clearAllMarked(g);

    DFS(g, 6, display);
    clearAllMarked(g);

    DFS(g, 7, display);
    clearAllMarked(g);

    DFS(g, 11, display);
    clearAllMarked(g);

    /* 测试 path */
    for (int i = 0; i < getVertexCount(g); i++) 
        for (int j = 0; j < getVertexCount(g); j++)
            printf("from %d to %d: %s",i, j, path(g, i, j));

    /* 测试 hasEdge */
    for (int i = 0; i < getVertexCount(g); i++)
        for (int j = 0; j < getVertexCount(g); j++)
            if (hasEdge(g, i, j))
                printf("%d connect %d\n", i, j);

    struct G *copyG = dupGraph(g);

    /* 测试 destroyGraph */
    destroyGraph(&g);
}

int main(int argc, char const *argv[]) {
    
    test();

    return 0;
}
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "Graph.h"

static char _internal_buffer[1 << 20];

void mark(struct G *graph, int v) {
    if (!graph || v < 0 || v >= graph->vertex_count)
        return;
    char *buf = graph->marked;
    buf += (v / 8);
    *buf |= (1 << (v % 8));
}

int marked(struct G *graph, int v) {
    if (!graph || v < 0 || v >= graph->vertex_count)
        return 0;
    char *buf = graph->marked;
    buf += (v / 8);
    return (*buf >> (v % 8)) & 1;
}

void unmark(struct G *graph, int v) {
    if (!graph || v < 0 || v >= graph->vertex_count)
        return;
    char *buf = graph->marked;
    buf += (v / 8);
    *buf &= ~(1 << (v % 8));
}

void clearAllMarked(struct G *graph) {
    bzero(graph->marked, ceil(graph->vertex_count / 8.0));
}

struct G* createGraph(int vertex_count) {
    struct G *g = NULL;
    int nbytes = 0;

    if ((g = malloc(sizeof(struct G))) == NULL)
        goto err_1;

    if ((g->adjs = malloc(sizeof(struct adj) * vertex_count)) == NULL)
        goto err_2;

    nbytes = ceil(vertex_count / 8.0);
    if ((g->marked = malloc(sizeof(char) * nbytes)) == NULL)
        goto err_3; 

    for (int i = 0; i < vertex_count; i++) {
        g->adjs[i].size = 0;
        g->adjs[i].head = NULL;
    }

    g->vertex_count = vertex_count;
    g->edge_count = 0;

    return g;

err_3:
    free(g->adjs);
err_2:
    free(g);
err_1:
    return NULL;
}

int destroyGraph(struct G **graph) {
    if (!graph)
        return 0;

    int i, n;
    struct G *g = *graph;
    struct adj_vertex_t *tmp = NULL;

    for (i = 0; i < g->vertex_count; i++) {
        n = g->adjs[i].size;
        while (n--) {
            tmp = g->adjs[i].head;
            g->adjs[i].head = g->adjs[i].head->next;
            free(tmp);
        }
    }
    free(g->adjs);
    free(g->marked);
    free(g);
    *graph = (struct G *)NULL;
    return 0;
}

void adj(struct G *graph, int v, iterator it) {
    if (!graph || v < 0 || v >= graph->vertex_count)
        return;

    int i;
    struct G *g = graph;
    struct adj_vertex_t *cur;

    for (cur = g->adjs[i].head; cur; cur = cur->next) 
        if (it) (*it)(cur->v);
}

int addEdge(struct G *graph, int v, int w) {
    if (!graph || v < 0 || w < 0 || v >= graph->vertex_count || w >= graph->vertex_count)
        return -1;

    struct G *g = graph;
    struct adj_vertex_t *newnode_1, *newnode_2;
    struct adj_vertex_t *tmp;

    if ((newnode_1 = malloc(sizeof(struct adj_vertex_t))) == NULL)
        return -1;

    if ((newnode_2 = malloc(sizeof(struct adj_vertex_t))) == NULL) {
        free(newnode_1);
        return -1;
    }

    newnode_1->v = w;
    newnode_1->next = g->adjs[v].head;
    g->adjs[v].head = newnode_1;

    newnode_2->v = v;
    newnode_2->next = g->adjs[w].head;
    g->adjs[w].head = newnode_2;

    g->edge_count++;

    return 0;
}

int getEdgeCount(struct G *graph) {
    if (!graph) return -1;
    return graph->edge_count;
}

int getVertexCount(struct G *graph) {
    if (!graph) return -1;
    return graph->vertex_count;
}

static void _DFS(struct G *g, int v, iterator it) {
    mark(g, v);
    if (it) (*it)(v);
    struct adj_vertex_t *cur;
    for (cur = g->adjs[v].head; cur; cur = cur->next) 
        if (!marked(g, cur->v))
            _DFS(g, cur->v, it);
}

void DFS(struct G *graph, int v, iterator it) {
    if (!graph || v < 0 || v >= graph->vertex_count)
        return;
    printf("\n*********** DFS from %d *************\n", v);
    _DFS(graph, v, it);
    printf("\n*************************************\n");
}

const char *toString(struct G *graph) {
    ssize_t len = 0;
    int i;
    struct G *g = graph;
    struct adj_vertex_t *cur;
    int nbytes = ceil(graph->vertex_count / 8.0);

#define SNPRINTF(_format_, ...) (len += snprintf(_internal_buffer + len, sizeof(_internal_buffer) - len, _format_, ##__VA_ARGS__))
    SNPRINTF("\n**************************\n");
    for (int i = 0; i < g->vertex_count; i++)
        SNPRINTF("%-4d", i);
    SNPRINTF("\n");
    char *buf = g->marked;
    for (int j = 0; j < g->vertex_count; j++) {
        buf = g->marked + (j / 8);
        SNPRINTF("%-4d", (*buf >> (j % 8)) & 1);
    }
    SNPRINTF("\n-------------------------\n");
    for (int i = 0; i < g->vertex_count; i++) {
        SNPRINTF("%d :", i);
        for (cur = g->adjs[i].head; cur; cur = cur->next) 
            SNPRINTF("%d ", cur->v);
        SNPRINTF("\n");
    }
    SNPRINTF("**************************\n");
#undef SNPRINTF

    _internal_buffer[len] = 0;
    return _internal_buffer;
}


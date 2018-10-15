#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "AdjacencyListGraph.h"

static char _internal_buffer[1 << 20];
static char _internal_path_buffer[1 << 10];

struct _internal_node_t {
    struct _internal_node_t *next;
    int value;
};
struct _internal_stack {
    struct _internal_node_t *head;
    int size;
};

static struct _internal_stack *_createStack() {
    struct _internal_stack *stack;
    if ((stack = malloc(sizeof(struct _internal_stack))) == NULL)
        return NULL;
    stack->size = 0;
    stack->head = NULL;
    return stack;
}

static void _push(struct _internal_stack *stack, int value) {
    struct _internal_node_t *newnode;
    if ((newnode = malloc(sizeof(struct _internal_node_t))) == NULL)
        return;
    newnode->value = value;
    newnode->next = stack->head;
    stack->head = newnode;
    stack->size++;
}

static int _size(struct _internal_stack *stack) {
    return stack->size;
}

static int _pop(struct _internal_stack *stack) {
    int value = stack->head->value;
    struct _internal_node_t *tmp = stack->head;
    stack->head = stack->head->next;
    free(tmp);
    stack->size--;
    return value;
}

static void _destroyStack(struct _internal_stack **stack) {
    struct _internal_stack *S = *stack;
    struct _internal_node_t *tmp;
    while(S->size--) {
        tmp = S->head;
        S->head = S->head->next;
        free(tmp);
    }
    free(S);
    *stack = NULL;
}

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

int hasEdge(struct G *graph, int v, int w) {
    if (!graph || v < 0 || w < 0 || v >= graph->vertex_count || w >= graph->vertex_count)
        return 0;
    
    int isV = graph->adjs[v].size < graph->adjs[w].size;
    struct adj_vertex_t *cur = isV ? graph->adjs[v].head : graph->adjs[w].head;
    
    for (; cur; cur = cur->next)
        if (isV) {
            if (cur->v == w)
                return 1;
        } else {
            if (cur->v == v)
                return 1;
        }
    return 0;
}

struct G* dupGraph(struct G *graph) {
    /* TODO */
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

static void _DFSRecord(struct G *g, int v, int *records) {
    mark(g, v);
    struct adj_vertex_t *cur;
    for (cur = g->adjs[v].head; cur; cur = cur->next)
        if (!marked(g, cur->v)) {
            records[cur->v] = v;
            _DFSRecord(g, cur->v, records);
        }
}

static int findRoot(int *records, int from) {
    int root = from;
    while (records[root] != root)
        root = records[root];
    return root;
} 

const char *path(struct G *graph, int from, int to) {
    if (!graph || from < 0 || to < 0 || from >= graph->vertex_count || to >= graph->vertex_count)
        return NULL;

    clearAllMarked(graph);

    int i;
    ssize_t len = 0;
    int *records;
    struct _internal_stack *S = _createStack();

    if ((records = malloc(sizeof(int) * graph->vertex_count)) == NULL)
        return NULL;
    for (i = 0; i < graph->vertex_count; i++)
        records[i] = i;

    _DFSRecord(graph, from, records);

#define SNPRINTF(_format_, ...) (len += snprintf(_internal_path_buffer + len, sizeof(_internal_path_buffer) - len, _format_, ##__VA_ARGS__))
    if (findRoot(records, to) != findRoot(records, from)) {
        SNPRINTF("⚠️ not find a path from %d to %d!\n", from, to);
    } else {
        for (i = to; i != from; i = records[i])
            _push(S, i);
        _push(S, from);

        while (_size(S))
            SNPRINTF("%d -> ", _pop(S));
        len -= 4;
        SNPRINTF("\n");
    }
    _internal_path_buffer[len] = 0;
#undef SNPRINTF    

    clearAllMarked(graph);
    _destroyStack(&S);

    return _internal_path_buffer;
}

int addEdge(struct G *graph, int v, int w) {
    if (!graph || v < 0 || w < 0 || v >= graph->vertex_count || w >= graph->vertex_count)
        return -1;

    struct G *g = graph;
    struct adj_vertex_t *newnode_1, *newnode_2;
    struct adj_vertex_t *tmp;

    /* 禁止自环 */
    if (v == w)
        return 0;

    /* 禁止平行边 */
    if (hasEdge(graph, v, w))
        return 0;

    if ((newnode_1 = malloc(sizeof(struct adj_vertex_t))) == NULL)
        return -1;

    if ((newnode_2 = malloc(sizeof(struct adj_vertex_t))) == NULL) {
        free(newnode_1);
        return -1;
    }

    newnode_1->v = w;
    newnode_1->next = g->adjs[v].head;
    g->adjs[v].head = newnode_1;
    g->adjs[v].size++;

    newnode_2->v = v;
    newnode_2->next = g->adjs[w].head;
    g->adjs[w].head = newnode_2;
    g->adjs[w].size++;

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


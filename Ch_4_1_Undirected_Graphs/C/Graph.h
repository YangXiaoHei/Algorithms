
#ifndef _GRAPH_H_
#define _GRAPH_H_

struct adj_vertex_t {
    struct adj_vertex_t *next;
    int v;
};

struct adj {
    int size;
    struct adj_vertex_t *head;
};

struct G {
    struct adj *adjs;
    int vertex_count;
    int edge_count;
    char *marked;
};

typedef int(*iterator)(int v);

struct G* createGraph(int vertex_count);

int destroyGraph(struct G **graph);

void adj(struct G *graph, int v, iterator it);

void mark(struct G *graph, int v);

int marked(struct G *graph, int v);

int addEdge(struct G *graph, int v, int w);

int getEdgeCount(struct G *graph);

int getVertexCount(struct G *graph);

void DFS(struct G *grahp, int v, iterator it);

const char *toString(struct G *graph);

#endif



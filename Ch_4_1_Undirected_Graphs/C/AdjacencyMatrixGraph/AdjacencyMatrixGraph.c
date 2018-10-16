#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

struct G {
    int *vertex;
    int vertex_count;  
    int edge_count;
    int iterator;
    char *edge; 
};

#define G_IT_END (-1)
static char __internal_buffer[1 << 10];

struct G *createGraph(int V);
void      destroyGraph(struct G **g);
void      addEdge(struct G *g, int v, int w);
int       getVertexCount(struct G *g);
int       getEdgeCount(struct G *g);
int       adjs(struct G *g, int v);
const char *toString(struct G *g);


static inline void setExist(struct G *g, int v, int w) 
{
    char *cursor;
    int loc;

    loc = v * g->vertex_count + w;

    cursor = g->edge;
    cursor += (loc / 8);
    *cursor |= (1 << (loc % 8));
}

static inline void clearExist(struct G *g, int v, int w) 
{
    char *cursor;
    int loc;

    loc = v * g->vertex_count + w;

    cursor = g->edge;
    cursor += (loc / 8);
    *cursor &= ~(1 << (loc % 8));
}

static inline int isExist(struct G *g, int v, int w)
{
    char *cursor;
    int loc;

    loc = v * g->vertex_count + w;

    cursor = g->edge;
    cursor += (loc / 8);
    return (*cursor >> (loc % 8)) & 1;
}


struct G *createGraph(int V) 
{
    struct G *g;
    int i, nbytes;

    if (V < 0) return NULL;

    if ((g = malloc(sizeof(struct G))) == NULL)
        goto err_1;

    if ((g->vertex = malloc(sizeof(int) * V)) == NULL)
        goto err_2;

    for (i = 0; i < V; i++) 
        g->vertex[i] = i;

    g->vertex_count = V;
    g->edge_count = 0;
    g->iterator = 0;

    nbytes = ceil(V * V / 8.0);
    if ((g->edge = malloc(sizeof(char) * nbytes)) == NULL)
        goto err_3;

    bzero(g->edge, nbytes);
    return g;

err_3:
    free(g->vertex);
err_2:
    free(g);
err_1:
    return NULL;
}
void destroyGraph(struct G **g) 
{
    if (!g) return;

    struct G *gg = *g;
    free(gg->vertex);
    free(gg->edge);
    free(gg);
    *g = NULL;
}

void addEdge(struct G *g, int v, int w) 
{
    if (!g || v < 0 || w < 0 || 
        v >= g->vertex_count || 
        w >= g->vertex_count) return;
        
    setExist(g, v, w);
    setExist(g, w, v);
    g->edge_count++;
}

int getVertexCount(struct G *g) 
{
    if (!g) return -1;
    return g->vertex_count;
}

int getEdgeCount(struct G *g)
{
    if (!g) return -1;
    return g->edge_count;
}

void resetIterator(struct G *g)
{
    if (!g) return;
    g->iterator = 0;
}

int adjs(struct G *g, int v)
{
    int it, n;

    if (!g || v < 0 || 
        v >= g->vertex_count) return G_IT_END;

    it = g->iterator;
    n = g->vertex_count;

    while ((it != G_IT_END && it < n) && !isExist(g, v, it))
        it++;
    g->iterator = (it != G_IT_END && it < n) ? it : G_IT_END;

    return g->iterator;
}

const char *toString(struct G *g)
{
    if (!g) return NULL;
#define APPEND_BEGIN ssize_t len = 0;
#define APPEND(_format_, ...) (len += snprintf(__internal_buffer + len, sizeof(__internal_buffer) - len, _format_, ##__VA_ARGS__))
#define APPEND_END (__internal_buffer[len] = 0)
    APPEND_BEGIN;
    APPEND("vertex: ");
    for (int i = 0; i < g->vertex_count; i++)
        APPEND("%-3d", i);
    APPEND("\n");
    APPEND("edge: \n");
    APPEND("%-3c", ' ');
    for (int i = 0; i < g->vertex_count; i++)
        APPEND("%-3d", i);
    APPEND("\n");
    for (int i = 0; i < g->vertex_count; i++) {
        APPEND("%-3d", i);
        for (int j = 0; j < g->vertex_count; j++) 
            APPEND("%-3c", isExist(g, i, j) ? 'X' : 'O');
        APPEND("\n");
    }
    APPEND("\n");
    APPEND_END;
#undef APPEND
#undef APPEND_END
    return __internal_buffer;
}

int main(int argc, char const *argv[]) 
{
    setbuf(stdout, NULL);

    struct G *g = createGraph(13);

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

    printf("%s", toString(g));

    return 0;
}
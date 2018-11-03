package Review;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;
import java.util.regex.*;

public class DigraphGenerator {
    static class Edge 
   // implements Comparable<Edge> 
    {
        int v;
        int w;
        public Edge(int v, int w) { this.v = v; this.w = w; }
        public String toString() { return String.format("{ %d %d }", v, w); }
    }
    /*
     * 生成一个环
     */
    public static Digraph cycle(int V) {
        Digraph g = new Digraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V - 1; i++)
            g.addEdge(vertices[i], vertices[i + 1]);
        g.addEdge(vertices[V - 1], vertices[0]);
        return g;
    }
    /*
     * 生成一个简单图，不包含平行边和自环 
     */
    public static Digraph simple(int V, int E) {
        if (E > V * (V - 1)) 
            throw new IllegalArgumentException("too many edges");
        if (E < 0)
            throw new IllegalArgumentException("too less edges");
        Digraph g = new Digraph(V);
        while (g.E() < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            if ((v != w) && !g.hasEdge(v, w)) 
                g.addEdge(v, w);
        }
        return g;
    }
    /*
     * 生成一个一般的图，可能包含平行边和自环
     */
    public static Digraph normal(int V, int E) {
        if (E > V * (V - 1)) 
            throw new IllegalArgumentException("too many edges");
        if (E < 0)
            throw new IllegalArgumentException("too less edges");
        Digraph g = new Digraph(V);
        Edge[] edges = new Edge[E];
        int i = 0;
        while (E-- > 0) {
            edges[i++] = new Edge(StdRandom.uniform(V), 
                                  StdRandom.uniform(V));
            g.addEdge(edges[i - 1].v, edges[i - 1].w);
        }
        for (int j = 0; j < i; j++)
            StdOut.print(edges[j] + " ");
        StdOut.println();
        return g;
    }
    public static Digraph normal(int V, int E, _Queue<Edge> q) {
        if (E > V * (V - 1)) 
            throw new IllegalArgumentException("too many edges");
        if (E < 0)
            throw new IllegalArgumentException("too less edges");
        q.clear();
        Digraph g = new Digraph(V);
        Edge[] edges = new Edge[E];
        int i = 0;
        while (E-- > 0) {
            edges[i++] = new Edge(StdRandom.uniform(V), 
                                  StdRandom.uniform(V));
            q.enqueue(edges[i - 1]);
            g.addEdge(edges[i - 1].v, edges[i - 1].w);
        }
        return g;
    }
    public static String genCycleDigraphWithVertexCount(int V, int E, int nVertices) {
        _Queue<Edge> q = new _Queue<>();
        String s = null;
         while (true) {
             Digraph g = DigraphGenerator.normal(V, E, q);
             DirectedCycle c = new DirectedCycle(g);
             if (c.hasCycle() && c.cycle().size() > nVertices) {
                 s = q.toString();
                 StdOut.print("环: ");
                 for (int w : c.cycle())
                     StdOut.print(w + " ");
                 StdOut.println();
                 break;
             }
         }
         return s;
    }
    public static void main(String[] args) {
        Digraph g = cycle(10);
        StdOut.println(g);
    }
}

package Review;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.In;

public class AdjMatrixEdgeWeightedDigraph {
    private int E;
    private int V;
    private DirectedEdge[][] adjs;
    public AdjMatrixEdgeWeightedDigraph(int V) {
        adjs = new DirectedEdge[V][];
        for (int i = 0; i < V; i++) 
            adjs[i] = new DirectedEdge[V];
        this.V = V;
    }
    public AdjMatrixEdgeWeightedDigraph(int V, int E) {
        this(V);
        while (E-- > 0) 
            addEdge(new DirectedEdge(StdRandom.uniform(V),
                                     StdRandom.uniform(V),
                                     StdRandom.uniform(1, 100)));
    }
    public AdjMatrixEdgeWeightedDigraph(In in) {
        int V = in.readInt();
        int E = in.readInt();
        adjs = new DirectedEdge[V][];
        for (int i = 0; i < V; i++) 
            adjs[i] = new DirectedEdge[V];
        this.V = V;
        while (E-- > 0)
            addEdge(new DirectedEdge(in.readInt(),
                                     in.readInt(),
                                     in.readDouble()));
    }
    public int E() { return E; }
    public int V() { return V; }
    public void addEdge(DirectedEdge e) {
        if (adjs[e.from()][e.to()] == null) {
            adjs[e.from()][e.to()] = e;
            E++;
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-7c", ' '));
        for (int i = 0; i < V; i++) 
            sb.append(String.format("%-7d", i));
        sb.append("\n");
        for (int i = 0; i < V; i++) {
            sb.append(String.format("%-7d", i));
            for (int j = 0; j < V; j++) {
                if (adjs[i][j] != null) {
                    sb.append(String.format("%-7.0f",  adjs[i][j].weight()));
                } else {
                    sb.append(String.format("%-7.0f", 0.0));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public Iterable<DirectedEdge> adj(int v) {
        return new Iterable<DirectedEdge>() {
            public Iterator<DirectedEdge> iterator() {
                return new Iterator<DirectedEdge>() {
                    private int i = 0;
                    public boolean hasNext() {
                        while (i < V && adjs[v][i] == null) i++;
                        return i != V;
                    }
                    public DirectedEdge next() {
                        return adjs[v][i++];
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    public static void main(String[] args) {
        AdjMatrixEdgeWeightedDigraph g = new AdjMatrixEdgeWeightedDigraph(10, 30);
        StdOut.println(g);
        
        for (int i = 0; i < g.V(); i++) {
            for (DirectedEdge e : g.adj(i))
                StdOut.print(e + " ");
            StdOut.println();
        }
    }
}

package code;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Graph {
    private _Bag<Integer> adjs[];
    private int E;
    private int V;
    @SuppressWarnings("unchecked")
    public Graph(int V) {
        adjs = (_Bag<Integer>[])new _Bag[V];
        this.V = V;
        for (int i = 0; i < V; i++)
            adjs[i] = new _Bag<Integer>();
    }
    public Graph(In in) {
        this(in.readInt() /* read V vertexes */);
        int E = in.readInt();
        while (E-- > 0) 
            addEdge(in.readInt(), in.readInt());
    }
    public Graph(Graph g) {
        this(g.V);
        E = g.E;
        for (int i = 0; i < V; i++) {
            _Stack<Integer> reverse = new _Stack<>();
            for (int w : g.adj(i))
                reverse.push(w);
            for (int w : reverse)
                adjs[i].add(w);
        }
    }
    public int E() { return E; }
    public int V() { return V; }  
    public boolean hasEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        return adjs[v].contains(w) && adjs[w].contains(v);
    }
    public void checkVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException();
    }
    public void addEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        adjs[v].add(w);
        adjs[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v) {
        checkVertex(v);
        return adjs[v];
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++)
            sb.append(String.format("%d : %s", i, adjs[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        Graph g = new Graph(10);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 9);
        g.addEdge(9, 0);
        StdOut.println(g);
        
        Graph copy = new Graph(g);
        StdOut.println(copy);
        
        Graph file = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        StdOut.println(file);
    }
}

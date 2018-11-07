package Review;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class EdgeWeightedGraph {
    private _Bag<Edge> adjs[];
    private int V;
    private int E;
    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int V) {
        adjs = new _Bag[V];
        this.V = V;
        for (int i = 0; i < V; i++)
            adjs[i] = new _Bag<>();
    }
    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(In in) {
        V = in.readInt();
        int E = in.readInt();
        adjs = new _Bag[V];
        for (int i = 0; i < V; i++)
            adjs[i] = new _Bag<>();
        while (E-- > 0)
            addEdge(in.readInt(), 
                    in.readInt(),
                    in.readDouble());
    }
    public EdgeWeightedGraph(int nV, int nE) {
        this(nV);
        while (nE-- > 0) 
            addEdge(StdRandom.uniform(nV),
                    StdRandom.uniform(nV),
                    StdRandom.uniform(0, 100));
            
    }
    public int E() { return E; }
    public int V() { return V; }
    public Iterable<Edge> edges() {
        _Queue<Edge> q = new _Queue<>();
        int selfLoops = 0;
        for (int i = 0; i < V; i++) {
            for (Edge e : adj(i)) {
                if (e.v == e.w) {
                    if (selfLoops % 2 == 0)
                        q.enqueue(e);
                    selfLoops++;
                } else if (e.other(i) > i) {
                    q.enqueue(e);
                }
            }
        }
        return q;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++)
            sb.append(String.format("%d : %s", i, adjs[i]));
        sb.append("\n");
        return sb.toString();
    }
    public Iterable<Edge> adj(int v) { return adjs[v]; }
    public void addEdge(int v, int w, double weight) {
        addEdge(new Edge(v, w, weight));
    }
    public void addEdge(Edge e) {
        adjs[e.v].add(e);
        adjs[e.w].add(e);
        E++;
    }
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(10, 30);
        StdOut.println(g);
        int i = 0;
        for (Edge e : g.edges())
            StdOut.printf("%d : %s\n",i++, e);
    }
}

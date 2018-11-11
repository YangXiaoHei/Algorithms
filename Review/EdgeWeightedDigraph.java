package Review;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedDigraph {
    private _Bag<DirectedEdge> adjs[];
    private int E;
    private int V;
    private int indegree[];
    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        indegree = new int[V];
        adjs = (_Bag<DirectedEdge>[])new _Bag[V];
        for (int i = 0; i < V; i++)
            adjs[i] = new _Bag<DirectedEdge>();
    }
    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        while (E-- > 0) 
            addEdge(new DirectedEdge(StdRandom.uniform(V),
                    StdRandom.uniform(V),
                    StdRandom.uniform(1.0, 100.0)));    
    }
    public EdgeWeightedDigraph(int V, int E, boolean negative) {
        this(V);
        if (negative) {
            while (E-- > 0) 
                addEdge(new DirectedEdge(StdRandom.uniform(V),
                                         StdRandom.uniform(V),
                                         StdRandom.uniform(-50.0, 100.0)));  
        } else {
            while (E-- > 0) 
                addEdge(new DirectedEdge(StdRandom.uniform(V),
                        StdRandom.uniform(V),
                        StdRandom.uniform(1.0, 100.0))); 
        }
    }
    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(In in) {
        int V = in.readInt();
        int E = in.readInt();
        this.V = V;
        indegree = new int[V];
        adjs = (_Bag<DirectedEdge>[])new _Bag[V];
        for (int i = 0; i < V; i++)
            adjs[i] = new _Bag<DirectedEdge>();
        while (E-- > 0) 
            addEdge(new DirectedEdge(in.readInt(),
                                     in.readInt(),
                                     in.readDouble()));      
    }
    public EdgeWeightedDigraph(EdgeWeightedDigraph g) {
        this(g.V());
        _Stack<DirectedEdge> S = new _Stack<>();
        for (int i = 0; i < g.V(); i++) {
            for (DirectedEdge e : g.adj(i))
                S.push(e);
            while (!S.isEmpty())
                addEdge(S.pop());
        }
    }
    
    public Iterable<DirectedEdge> adj(int v) { return adjs[v]; }
    public int E() { return E; }
    public int V() { return V; }
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        adjs[v].add(e);
        indegree[w]++;
        E++;
    }
    public int outdegree(int v) { return  adjs[v].size(); }
    public int indegree(int w) { return indegree[w]; }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) 
            sb.append(String.format("%-2d: %s", i, adjs[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(10, 30);
        StdOut.println(g);
    }
}

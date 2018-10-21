package Ch_4_3_Minimum_Spanning_Trees;

import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private __Bag<Edge> adjs[];
    public EdgeWeightedGraph(int V) {
        this.V = V;
        adjs = (__Bag<Edge>[])new __Bag[V];
        for (int i = 0; i < V; i++)
            adjs[i] = new __Bag<Edge>();
    }
    public int V() { return V; }
    public int E() { return E; }
    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adjs[v].add(e);
        adjs[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v) {
        return adjs[v];
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++)
            sb.append(String.format("%-3d : %s\n", i, adjs[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        EdgeWeightedGraph wg = new EdgeWeightedGraph(10);
        wg.addEdge(new Edge(4, 5, 0.23));
        wg.addEdge(new Edge(4, 7, 0.23));
        wg.addEdge(new Edge(5, 7, 0.23));
        wg.addEdge(new Edge(0, 7, 0.23));
        wg.addEdge(new Edge(1, 5, 0.23));
        wg.addEdge(new Edge(0, 4, 0.23));
        wg.addEdge(new Edge(2, 3, 0.23));
        wg.addEdge(new Edge(1, 7, 0.23));
        wg.addEdge(new Edge(0, 2, 0.23));
        wg.addEdge(new Edge(1, 2, 0.23));
        wg.addEdge(new Edge(1, 3, 0.23));
        
        StdOut.println(wg);
    }
    // output
    /*
     *  0   : {0-7 0.23} {0-4 0.23} {0-2 0.23} 
        1   : {1-5 0.23} {1-7 0.23} {1-2 0.23} {1-3 0.23} 
        2   : {2-3 0.23} {0-2 0.23} {1-2 0.23} 
        3   : {2-3 0.23} {1-3 0.23} 
        4   : {4-5 0.23} {4-7 0.23} {0-4 0.23} 
        5   : {4-5 0.23} {5-7 0.23} {1-5 0.23} 
        6   : 
        7   : {4-7 0.23} {5-7 0.23} {0-7 0.23} {1-7 0.23} 
        8   : 
        9   : 
     */
}

package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedDigraph {
    private int V;
    private int E;
    private EG eg;
    private __Bag<DirectedEdge> adjs[];
    public EdgeWeightedDigraph(int V) {
        adjs = (__Bag<DirectedEdge>[])new __Bag[V];
        this.V = V;
        eg = new EG(V);
        for (int i = 0; i < V; i++)
            adjs[i] = new __Bag<DirectedEdge>();
    }
    public void genRandom(int edgeCount) {
        while (edgeCount-- > 0) 
            addEdge(eg.nextW());
    }
    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        while (E-- > 0)
            addEdge(new DirectedEdge(in.readInt(), 
                                     in.readInt(), 
                                     in.readDouble()));
    }
    public int V() { return V; }
    public int E() { return E; }
    public boolean hasEdge(int v, int w) {
        return adjs[v].contains(new DirectedEdge(v, w, 0)) ||
               adjs[w].contains(new DirectedEdge(w, v, 0));
    }
    public Iterable<DirectedEdge> adj(int v) { return adjs[v]; }
    public void addEdge(DirectedEdge e) {
        if (e.from() == e.to() || hasEdge(e.from(), e.to()))
            return;
        adjs[e.from()].add(e);
        E++;
    }
    public Iterable<DirectedEdge> edges() {
        __Bag<DirectedEdge> bag = new __Bag<>();
        for (int i = 0; i < V; i++)
            for (DirectedEdge e : adjs[i])
                bag.add(e);
        return bag;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) 
            sb.append(String.format("%d : %s\n", i, adjs[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        String s = "/Users/bot/Desktop/algs4-data/tinyEWD.txt";
        StdOut.println(new EdgeWeightedDigraph(new In(s)));
    }
    // output
    /*
     *  0 : 0->4 0.380 0->2 0.260 
        1 : 1->3 0.290 
        2 : 2->7 0.340 
        3 : 3->6 0.520 
        4 : 4->5 0.350 4->7 0.370 
        5 : 5->4 0.350 5->7 0.280 5->1 0.320 
        6 : 6->2 0.400 6->0 0.580 6->4 0.930 
        7 : 7->5 0.280 7->3 0.390 
     */
}

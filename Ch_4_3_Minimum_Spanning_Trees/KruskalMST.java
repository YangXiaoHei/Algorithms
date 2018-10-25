package Ch_4_3_Minimum_Spanning_Trees;

import edu.princeton.cs.algs4.StdOut;

public class KruskalMST {
    private __Queue<Edge> mst;
    public KruskalMST(EdgeWeightedGraph g) {
        mst = new __Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        
        for (Edge e : g.edges()) 
            pq.insert(e);
        
        WeightedQuickUnion uf = new WeightedQuickUnion(g.V());
        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }
    public Iterable<Edge> edges() {
        return mst;
    }
    public double weight() {
        double sum = 0;
        for (Edge e : mst)
            sum += e.weight();
        return sum;
    }
    public static void main(String[] args) {
        EdgeWeightedGraph wg = new EdgeWeightedGraph(8);
        wg.addEdge(new Edge(4, 5, .35));
        wg.addEdge(new Edge(4, 7, .37));
        wg.addEdge(new Edge(5, 7, .28));
        wg.addEdge(new Edge(0, 7, .16));
        wg.addEdge(new Edge(1, 5, .32));
        wg.addEdge(new Edge(0, 4, .38));
        wg.addEdge(new Edge(2, 3, .17));
        wg.addEdge(new Edge(1, 7, .19));
        wg.addEdge(new Edge(0, 2, .26));
        wg.addEdge(new Edge(1, 2, .36));
        wg.addEdge(new Edge(1, 3, .29));
        wg.addEdge(new Edge(2, 7, .34));
        wg.addEdge(new Edge(6, 2, .40));
        wg.addEdge(new Edge(3, 6, .52));
        wg.addEdge(new Edge(6, 0, .58));
        wg.addEdge(new Edge(6, 4, .93));
        StdOut.println(wg);
        
        KruskalMST mst = new KruskalMST(wg);
        for (Edge w : mst.edges())
            StdOut.println(w);
        StdOut.printf("total sum = %.3f\n", mst.weight());
    }
    // output
    /*
     *  0   : {0-7 0.16} {0-4 0.38} {0-2 0.26} {6-0 0.58} 
        1   : {1-5 0.32} {1-7 0.19} {1-2 0.36} {1-3 0.29} 
        2   : {2-3 0.17} {0-2 0.26} {1-2 0.36} {2-7 0.34} {6-2 0.40} 
        3   : {2-3 0.17} {1-3 0.29} {3-6 0.52} 
        4   : {4-5 0.35} {4-7 0.37} {0-4 0.38} {6-4 0.93} 
        5   : {4-5 0.35} {5-7 0.28} {1-5 0.32} 
        6   : {6-2 0.40} {3-6 0.52} {6-0 0.58} {6-4 0.93} 
        7   : {4-7 0.37} {5-7 0.28} {0-7 0.16} {1-7 0.19} {2-7 0.34} 
        
        
        {0-7 0.16}
        {2-3 0.17}
        {1-7 0.19}
        {0-2 0.26}
        {5-7 0.28}
        {4-5 0.35}
        {6-2 0.40}
        total sum = 1.810
     */
}

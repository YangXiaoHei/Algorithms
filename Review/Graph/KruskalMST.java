package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class KruskalMST {
    private UF uf;
    private MinPQ<Edge> pq;
    private _Queue<Edge> mst;
    private double totalWeight;
    public KruskalMST(EdgeWeightedGraph g) {
        mst = new _Queue<>();
        uf = new UF(g.V());
        pq = new MinPQ<>();
        for (Edge e : g.edges())
            pq.insert(e);
        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                totalWeight += e.weight();
            }
        }
    }
    public Iterable<Edge> edges() {
        return mst;
    }
    public double totalWeight() { return totalWeight; }
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(10, 20);
        StdOut.println(g);
        
        PrimMST p = new PrimMST(g);
        LazyPrimMST lp = new LazyPrimMST(g);
        KruskalMST k = new KruskalMST(g);
        StdOut.println("------------------");
        for (Edge e : p.edges())
            StdOut.println(e);
        StdOut.printf("weight = %.2f\n", p.totalWeight());
        StdOut.println("\n------------------");
        for (Edge e : lp.edges())
            StdOut.println(e);
        StdOut.printf("weight = %.2f\n", lp.totalWeight());
        StdOut.println("\n------------------");
        for (Edge e : k.edges())
            StdOut.println(e);
        StdOut.printf("weight = %.2f\n", k.totalWeight());
    }
}

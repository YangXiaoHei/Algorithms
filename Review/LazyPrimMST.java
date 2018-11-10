package Review;

import edu.princeton.cs.algs4.StdOut;

public class LazyPrimMST {
    private _Queue<Edge> mst;
    private MinPQ<Edge> pq;
    private boolean marked[];
    private EdgeWeightedGraph g;
    private double totalWeight;
    public LazyPrimMST(EdgeWeightedGraph g) {
        this.g = g;
        marked = new boolean[g.V()];
        mst = new _Queue<>();
        pq = new MinPQ<>();
        for (int i = 0; i < g.V(); i++)
            if (!marked[i])
                lazyPrim(i);
    }
    private void lazyPrim(int s) {
        visit(s);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            totalWeight += e.weight;
            mst.enqueue(e);
            if (!marked[v])
                visit(v);
            if (!marked[w])
                visit(w);
        }
    }
    public int mstSize() { return mst.size(); }
    public double totalWeight() { return totalWeight; }
    public Iterable<Edge> edges() { return mst; }
    private void visit(int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) 
            if (!marked[e.other(v)]) 
                pq.insert(e);
    }
    
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(10, 25);
        StdOut.println(g);
        LazyPrimMST mst = new LazyPrimMST(g);
        for (Edge e : mst.edges())
            StdOut.println(e);
        StdOut.printf("totalWeight : %.2f\n", mst.totalWeight());
        StdOut.printf("mstSize : %d\n", mst.mstSize());
    }
}

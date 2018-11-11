package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class PrimMST {
    private Edge edgeTo[];
    private double disTo[];
    private boolean marked[];
    private IndexMinPQ<Double> pq;
    private EdgeWeightedGraph g;
    private _Queue<Edge> mst;
    public PrimMST(EdgeWeightedGraph g) {
        this.g = g;
        mst = new _Queue<>();
        marked = new boolean[g.V()];
        disTo = new double[g.V()];
        edgeTo = new Edge[g.V()];
        for (int i = 0; i < g.V(); i++)
            disTo[i] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<>(g.V());
        for (int i = 0; i < g.V(); i++)
            if (!marked[i])
                prim(i);
    }
    private void prim(int s) {
        disTo[s] = 0.0;
        pq.insert(s, disTo[0]);
        while (!pq.isEmpty())
            visit(pq.delMin());
    }
    private void visit(int v) {
        /*
         * 将 v 加入最小生成树中
         */
        marked[v] = true;
        /*
         * 遍历 v 的每条边，
         * 但并不都将其作为待选的横切边加入堆中
         */
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;  /* v, w 都在最小生成树中，v -> w 不是横切边 */
            
            /* e 有望成为一个最小横切边的选择，因此将其加入堆
             * 如果堆中已经存在到达 w 的横切边，说明又发现一个更小的
             * （但还有没有更小的呢？目前还不知道），只要更改其优先级 */
            if (e.weight() < disTo[w]) {
                edgeTo[w] = e;
                disTo[w] = e.weight();
                if (pq.contains(w)) pq.decreaseKey(w, disTo[w]);
                else                pq.insert(w, disTo[w]);
            }
        }
    }
    public double totalWeight() {
        double sum = 0;
        for (int i = 0; i < edgeTo.length; i++) 
            if (edgeTo[i] != null)
                sum += edgeTo[i].weight();
        return sum;
    }
    public Iterable<Edge> edges() {
        for (int i = 0; i < edgeTo.length; i++) 
            if (edgeTo[i] != null)
                mst.enqueue(edgeTo[i]);
        return mst;
    }
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(10, 20);
        StdOut.println(g);
        
        PrimMST p = new PrimMST(g);
        LazyPrimMST lp = new LazyPrimMST(g);
        StdOut.println("------------------");
        for (Edge e : p.edges())
            StdOut.println(e);
        StdOut.printf("weight = %.2f\n", p.totalWeight());
        StdOut.println("\n------------------");
        for (Edge e : lp.edges())
            StdOut.println(e);
        StdOut.printf("weight = %.2f\n", lp.totalWeight());
    }
}

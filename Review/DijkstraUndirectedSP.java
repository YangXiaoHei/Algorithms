package Review;

import edu.princeton.cs.algs4.StdOut;

public class DijkstraUndirectedSP {
    private IndexMinPQ<Double> pq;
    private double disTo[];
    private Edge edgeTo[];
    private int source;
    public DijkstraUndirectedSP(EdgeWeightedGraph g, int s) {
        disTo = new double[g.V()];
        source = s;
        edgeTo = new Edge[g.V()];
        pq = new IndexMinPQ<>(g.V());
        for (int i = 0; i < g.V(); i++)
            disTo[i] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;
        pq.insert(s, disTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : g.adj(v)) 
                relax(e, v);
        }
    }
    private void relax(Edge e, int from) {
        int to = e.other(from);
        if (disTo[to] > disTo[from] + e.weight()) {
            disTo[to] = disTo[from] + e.weight();
            edgeTo[to] = e;
            if (pq.contains(to)) pq.decreaseKey(to, disTo[to]);
            else                 pq.insert(to, disTo[to]);
        }
    }
    public double disTo(int v) { return disTo[v]; }
    public boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v))
            throw new RuntimeException("no path!");
        _Stack<Edge> s = new _Stack<>();
        for (Edge e = edgeTo[v]; e != null; v = e.other(v), e = edgeTo[v]) 
            s.push(e);
        return s;
    }
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(10, 30);
        StdOut.println(g);
        
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(g, 0);
        for (int i = 0; i < g.V(); i++) {
            if (sp.hasPathTo(i)) {
                StdOut.printf("from %d to %d dis : %.2f ",0, i, sp.disTo(i));
                for (Edge e : sp.pathTo(i))
                    StdOut.printf("%s", e);
                StdOut.println();
            }
        }
    }
}

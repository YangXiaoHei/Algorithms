package Review;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class DijkstraSP {
    private IndexMinPQ<Double> pq;
    private double disTo[];
    private DirectedEdge edgeTo[];
    public DijkstraSP(EdgeWeightedDigraph g, int s) {
        pq = new IndexMinPQ<>(g.V());
        disTo = new double[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        for (int i = 0; i < g.V(); i++)
            disTo[i] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;
        pq.insert(s, disTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : g.adj(v))
                relax(e);
        }
    }
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (disTo[w] > disTo[v] + e.weight()) {
            disTo[w] = disTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, disTo[w]);
            else                pq.insert(w, disTo[w]);
        }
    }
    public double disTo(int v) { return disTo[v]; }
    public boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v) {
        _Stack<DirectedEdge> s = new _Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            s.push(e);
        return s;
    }
    public static void main(String[] args) {
        String s = "/Users/bot/Desktop/Algorithms4/Algorithms4/src/Review/DijkstraSP_test_data.txt";    
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In(s));
        StdOut.println(g);
        
        DijkstraSP sp = new DijkstraSP(g, 0);
        for (int i = 0; i < g.V(); i++) {
            if (sp.hasPathTo(i)) {
                StdOut.printf("from %d to %d dis : %.2f ",0, i, sp.disTo(i));
                for (DirectedEdge e : sp.pathTo(i))
                    StdOut.printf("%s", e);
                StdOut.println();
            }
        }
        
    }
}

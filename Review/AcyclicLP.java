package Review;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class AcyclicLP {
    private DirectedEdge edgeTo[];
    private double disTo[];
    public AcyclicLP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        
        TopologicalX top = new TopologicalX(g);
        if (!top.hasOrder()) 
            throw new IllegalArgumentException("graph is cyclic");
        
        for (int w : top.order()) {
            for (DirectedEdge e : g.adj(w))
                relax(e);
        }
    }
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (disTo[w] < disTo[v] + e.weight()) {
            disTo[w] = disTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
    public double disTo(int v) { return disTo[v]; }
    public boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v))
            throw new RuntimeException("no path!");
        _Stack<DirectedEdge> s = new _Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            s.push(e);
        return s;
    }
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In("/Users/bot/Desktop/algs4-data/tinyEWDAG.txt"));
        StdOut.println(g);
        
        int s = 5;
        AcyclicLP lp = new AcyclicLP(g, s);
        for (int i = 0; i < g.V(); i++)
            if (lp.hasPathTo(i)) {
                StdOut.printf("from %d to %d [%.2f] : ", s, i, lp.disTo(i));
                for (DirectedEdge e : lp.pathTo(i)) 
                    StdOut.print(e + " ");
                StdOut.println();
            }
    }
}

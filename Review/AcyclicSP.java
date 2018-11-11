package Review;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    public AcyclicSP(EdgeWeightedDigraph g, int s) {
        TopologicalX topo = new TopologicalX(g);
        if (!topo.hasOrder())
            throw new IllegalArgumentException("g is cyclic");
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        for (int i = 0; i < g.V(); i++)
            disTo[i] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;
        
        for (int w : topo.order())
            for (DirectedEdge e : g.adj(w))
                relax(e);
    }
    private void relax(DirectedEdge e) {
         int v = e.from(), w = e.to();
         StdOut.printf("松弛边 %s\n", e);
         if (disTo[w] > disTo[v] + e.weight()) {
             StdOut.printf("update disTo[%d] from %.2f to %.2f, "
                     + "[old_edge=%s]"
                     + "[new_edge=%s]\n", 
                     w, 
                     disTo[w], 
                     disTo[v] + e.weight(),
                     edgeTo[w],
                     e
                     );
             disTo[w] = disTo[v] + e.weight();
             edgeTo[w] = e;
         }
    }
    public boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v))
            throw new RuntimeException("no path!");
        _Stack<DirectedEdge> s = new _Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            s.push(e);
        return s;
    }
    public double disTo(int v) { return disTo[v]; }
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In("/Users/bot/Desktop/algs4-data/tinyEWDAG.txt"));
        StdOut.println(g);
        int s = 5;
        AcyclicSP sp = new AcyclicSP(g, s);
        for (int i = 0; i < g.V(); i++)
            if (sp.hasPathTo(i)) {
                StdOut.printf("from %d to %d [%.2f] : ", s, i, sp.disTo(i));
                for (DirectedEdge e : sp.pathTo(i)) 
                    StdOut.print(e + " ");
                StdOut.println();
            }
    }
}

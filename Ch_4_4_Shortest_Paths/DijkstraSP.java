package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    private IndexMinPQ<Double> pq;
    public DijkstraSP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        disTo = new double[g.V()];
        pq = new IndexMinPQ<Double>(g.V());
        for (int i = 0; i < g.V(); i++)
            disTo[i] = Double.POSITIVE_INFINITY;
        disTo[0] = 0.0;
        
        pq.insert(s, 0.0);
        while (!pq.isEmpty())
            relax(g, pq.delMin());
    }
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, disTo[w]);
                else                pq.insert(w, disTo[w]);
            }
        }
    }
    public double disTo(int v) { return disTo[v]; }
    public boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v) {
        __Stack<DirectedEdge> S = new __Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) 
            S.push(e);
        return S;
    }
    public static void main(String[] args) {
        String s = "/Users/bot/Desktop/algs4-data/tinyEWD.txt";
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In(s));
        DijkstraSP sp = new DijkstraSP(g, 0);
        for (int i = 0; i < g.V(); i++) {
            if (sp.hasPathTo(i)) {
                StdOut.printf("from %d to %d : ",0, i);
                for (DirectedEdge e : sp.pathTo(i))
                    StdOut.print(e + "  ");
                StdOut.println();
            } else 
                StdOut.printf("%d cannot reach to %d\n", 0, i); 
        }
    }
    // output
    /*
     *  from 0 to 0 : 
        from 0 to 1 : 0->4 0.380  4->5 0.350  5->1 0.320  
        from 0 to 2 : 0->2 0.260  
        from 0 to 3 : 0->2 0.260  2->7 0.340  7->3 0.390  
        from 0 to 4 : 0->4 0.380  
        from 0 to 5 : 0->4 0.380  4->5 0.350  
        from 0 to 6 : 0->2 0.260  2->7 0.340  7->3 0.390  3->6 0.520  
        from 0 to 7 : 0->2 0.260  2->7 0.340  
     */
}

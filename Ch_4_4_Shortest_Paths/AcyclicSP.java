package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.StdOut;

public class AcyclicSP {
    private double disTo[];
    private DirectedEdge edgeTo[];
    private EdgeWeightedDigraph g;
    public AcyclicSP(EdgeWeightedDigraph g) {
        this.g = g;
        disTo = new double[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        for (int i = 0; i < g.V(); i++)
            disTo[i] = Double.POSITIVE_INFINITY;
        disTo[0] = 0.0;
        
        TopologicSort tp = new TopologicSort(g, 0);
        for (int v : tp.sortOrder()) {
            StdOut.print(v + " - ");
            relax(v);
        }
        StdOut.println();
    }
    private void relax(int v) {
        for (DirectedEdge e : g.adj(v)) {
            int to  = e.to();
            if (disTo[to] > disTo[v] + e.weight()) {
                disTo[to] = disTo[v] + e.weight();
                edgeTo[to] = e;
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
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(10);
        g.genRandom(20);
        StdOut.println(g);
        AcyclicSP sp = new AcyclicSP(g);
        for (int i = 0; i < 10; i++) {
            if (sp.hasPathTo(i)) {
                for (DirectedEdge e : sp.pathTo(i))
                    StdOut.print(e + "  ");
                StdOut.println();
            } else
                StdOut.println("cannot reach to " + i);
        }
    }
    // output
    /*
     * 0 : {0->3 19} {0->4 16} {0->7 5} 
        1 : {1->3 15} {1->7 1} {1->5 8} 
        2 : {2->9 19} {2->1 6} 
        3 : 
        4 : 
        5 : 
        6 : {6->5 9} 
        7 : {7->5 7} {7->6 10} 
        8 : {8->3 9} 
        9 : {9->8 8} {9->7 8} {9->5 16} {9->3 7} 
        
        
        0 - 7 - 6 - 5 - 4 - 3 - 
        
        cannot reach to 1
        cannot reach to 2
        {0->3 19}  
        {0->4 16}  
        {0->7 5}  {7->5 7}  
        {0->7 5}  {7->6 10}  
        {0->7 5}  
        cannot reach to 8
        cannot reach to 9

     */
}

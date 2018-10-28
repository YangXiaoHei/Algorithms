package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.*;

public class AcyclicLP {
    private EdgeWeightedDigraph g;
    private double disTo[];
    private DirectedEdge[] edgeTo; 
    public AcyclicLP(EdgeWeightedDigraph g, int beg) {
        this.g = g;
        disTo = new double[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        for (int i = 0; i < g.V(); i++) 
            disTo[i] = Double.NEGATIVE_INFINITY;
        disTo[beg] = 0.0;
        
        TopologicSort tp = new TopologicSort(g, beg);
        for (int v : tp.sortOrder())
            relax(v);
    }
    public double disTo(int v) { return disTo[v]; }
    public boolean hasPathTo(int v) { return disTo[v] > Double.NEGATIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v) {
        __Stack<DirectedEdge> S = new __Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            S.push(e);
        return S;
    }
    private void relax(int v) {
        for (DirectedEdge e : g.adj(v)) {
            if (disTo[e.to()] < disTo[v] + e.weight()) {
                disTo[e.to()] = disTo[v] + e.weight();
                edgeTo[e.to()] = e;
            }
        }
    }
    public static void main(String[] args) {
        String path = "/Users/bot/Desktop/algs4-data/tinyEWDAG.txt";
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In(path));
        AcyclicLP lp = new AcyclicLP(g, 5);
        for (int i = 0; i < g.V(); i++) {
            if (lp.hasPathTo(i)) {
                StdOut.printf("%d to %d ", 5, i);
                for (DirectedEdge e : lp.pathTo(i))
                    StdOut.print(e + "  ");
                StdOut.println();
            } else 
                StdOut.println("cannot reach to " + i);
        }
    }
    // output
    /*
     * 5 to 0 {5->1 0}  {1->3 0}  {3->6 1}  {6->4 1}  {4->0 0}  
        5 to 1 {5->1 0}  
        5 to 2 {5->1 0}  {1->3 0}  {3->6 1}  {6->4 1}  {4->7 0}  {7->2 0}  
        5 to 3 {5->1 0}  {1->3 0}  
        5 to 4 {5->1 0}  {1->3 0}  {3->6 1}  {6->4 1}  
        5 to 5 
        5 to 6 {5->1 0}  {1->3 0}  {3->6 1}  
        5 to 7 {5->1 0}  {1->3 0}  {3->6 1}  {6->4 1}  {4->7 0}  

     */
}

package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.*;

public class BellmanFordSP {
    private double[] disTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private __Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;
    private EdgeWeightedDigraph g;
    public BellmanFordSP(EdgeWeightedDigraph g, int s) {
        this.g = g;
        disTo = new double[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        onQ = new boolean[g.V()];
        queue = new __Queue<>();
        for (int v = 0; v < g.V(); v++)
            disTo[v] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(g, v);
        }
    }
    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % g.V() == 0) {
                print();
                findNegativeCycle();
                StdOut.printf("queue size = %d queue = %s\n", queue.size(), queue);
            }
        }
    }
    private void print() {
        for (int i = 0; i < g.V(); i++)
            StdOut.printf("%-14d", i);
        StdOut.println();
        for (int i = 0; i < g.V(); i++) {
            if (edgeTo[i] != null)
                StdOut.printf("%-14s", edgeTo[i]);
            else
                StdOut.printf("%-14d", -100);
        }
        StdOut.println("\n");
    }
    private double disTo(int v) { return disTo[v]; }
    private boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public boolean hasNegativeCycle() {
        return cycle != null;
    }
    public Iterable<DirectedEdge> pathTo(int v) {
        __Stack<DirectedEdge> S = new __Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            S.push(e);
        return S;
    }
    private void findNegativeCycle() {
        int v = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(v);
        for (int i = 0; i < v; i++)
            if (edgeTo[i] != null)
                spt.addEdge(edgeTo[i]);
        
        EdgeWeightedDigraphCycle cf = new EdgeWeightedDigraphCycle(spt);
        cycle = cf.cycle();
    }
    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }
    public static void main(String[] args) {
        String path = "/Users/bot/Desktop/algs4-data/tinyEWDnc.txt";
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In(path));
        StdOut.println(g);
        BellmanFordSP sp = new BellmanFordSP(g, 0);
        if (sp.hasNegativeCycle()) {
            for (DirectedEdge e : sp.negativeCycle())
                StdOut.print(e + " ");
        } else {
            for (int v = 0; v < g.V(); v++) {
                if (sp.hasPathTo(v)) {
                    StdOut.printf("%d to %d ", 0, v);
                    for (DirectedEdge e : sp.pathTo(v)) 
                        StdOut.print(e + "   ");
                    StdOut.println();
                }
                else {
                    StdOut.printf("%d to %d           no path\n", 0, v);
                }
            }
        }
        
    }
    // output
    /*
     * 0 to 0 ( 0.00)  
     *  0 to 1 ( 0.93)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25   4->5  0.35   5->1  0.32
     *  0 to 2 ( 0.26)  0->2  0.26   
     *  0 to 3 ( 0.99)  0->2  0.26   2->7  0.34   7->3  0.39   
     *  0 to 4 ( 0.26)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25   
     *  0 to 5 ( 0.61)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25   4->5  0.35
     *  0 to 6 ( 1.51)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   
     *  0 to 7 ( 0.60)  0->2  0.26   2->7  0.34   
     */
}

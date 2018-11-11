package Review;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class BellmanFordSP {
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    private boolean[] onQ;
    private _Queue<Integer> queue;
    private int cost;
    private EdgeWeightedDigraph g;
    private Iterable<DirectedEdge> cycle;
    public BellmanFordSP(EdgeWeightedDigraph g, int s) {
        this.g = g;
        disTo = new double[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        onQ = new boolean[g.V()];
        queue = new _Queue<>();
        for (int i = 0; i < g.V(); i++)
            disTo[i] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;
        queue.enqueue(s);
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(v);
        }
    }
    private void relax(int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            StdOut.printf("正在遍历 %d 的邻边 %s\n", v, e);
            if (disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
                StdOut.println("队列内容 : " + queue);
            }
            if (cost++ % g.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) return;
            }
        }
    }
    public int cost() { return cost; }
    public boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v))
            throw new IllegalArgumentException("no path!");
        _Stack<DirectedEdge> s = new _Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            s.push(e);
        return s;
    }
    private void findNegativeCycle() {
        EdgeWeightedDigraph gg = new EdgeWeightedDigraph(g.V());
        for (int i = 0; i < edgeTo.length; i++)
            if (edgeTo[i] != null)
                gg.addEdge(edgeTo[i]);
        EdgeWeightedDirectedCycle c = new EdgeWeightedDirectedCycle(gg);
        cycle = c.cycle();
    }
    public double disTo(int v) { return disTo[v]; }
    public Iterable<DirectedEdge> negativeCycle() { 
        if (!hasNegativeCycle())
            throw new RuntimeException("not negative cycle");
        return cycle;
    }
    private boolean hasNegativeCycle() {
        return cycle != null;
    }
    public static void main(String[] args) {
        String s = "/Users/bot/Desktop/Algorithms4/Algorithms4/src/Review/BellmanFordSP_test_data_negative_cycle.txt";    
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In(s));
        StdOut.println(g);
        
        BellmanFordSP sp = new BellmanFordSP(g, 0);
        if (sp.hasNegativeCycle()) {
            StdOut.printf("负权重的环 [%d]: ", sp.cost());
            for (DirectedEdge e : sp.negativeCycle())
                StdOut.print(e + " ");
            StdOut.println();
        } else {
            for (int i = 0; i < g.V(); i++) {
                if (sp.hasPathTo(i)) {
                    StdOut.printf("%d to %d [%.2f] ", 0, i, sp.disTo(i));
                    for (DirectedEdge e : sp.pathTo(i)) 
                        StdOut.print(e + " ");
                    StdOut.println();
                }
            }
        }
    }
}

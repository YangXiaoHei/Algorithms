package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedDigraphCycle {
    private __Stack<DirectedEdge> cycle;
    private boolean marked[];
    private boolean onStack[];
    private DirectedEdge[] edgeTo;
    private EdgeWeightedDigraph g;
    public EdgeWeightedDigraphCycle(EdgeWeightedDigraph g) {
        this.g = g;
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        
        for (int i = 0; i < g.V(); i++)
            if (!marked[i])
                dfs(i);
    }
    public Iterable<DirectedEdge> cycle() { return cycle; }
    private void dfs(int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int to = e.to();
            if (hasCycle()) return;
            if (!marked[to]) {
                edgeTo[to] = e;
                dfs(to);
            } else if (onStack[to]) {
                cycle = new __Stack<>();
                DirectedEdge f = e;
                while (f.from() != to) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);
                return;
            }
        }
        onStack[v] = false;
    }
    private boolean hasCycle() {
        return cycle != null;
    }
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(7);
        g.genRandom(50);
        StdOut.println(g);
        EdgeWeightedDigraphCycle c = new EdgeWeightedDigraphCycle(g);
        if (c.hasCycle()) {
            for (DirectedEdge e : c.cycle()) 
                StdOut.print(e + "  ");
        } else 
            StdOut.println("无环");
    }
}

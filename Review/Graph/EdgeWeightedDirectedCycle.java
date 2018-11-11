package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedDirectedCycle {
    private boolean onStack[];
    private boolean marked[];
    private DirectedEdge edgeTo[];
    private _Stack<DirectedEdge> cycle;
    private EdgeWeightedDigraph g;
    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph g) {
        this.g = g;
        onStack = new boolean[g.V()];
        marked = new boolean[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        for (int i = 0; i < g.V(); i++)
            if (!marked[i])
                dfs(i);
    }
    public Iterable<DirectedEdge> cycle() { return cycle; }
    public boolean hasCycle() { return cycle != null; }
    private void dfs(int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (hasCycle()) return;
            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(w);
            } else if (onStack[w]) {
                cycle = new _Stack<>();
                DirectedEdge f = e;
                for (; f.from() != w; f = edgeTo[f.from()])
                    cycle.push(f);
                cycle.push(f);
                return;
            }
        }
        onStack[v] = false;
    }
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(10, 50);
        StdOut.println(g);
        
        EdgeWeightedDirectedCycle c = new EdgeWeightedDirectedCycle(g);
        if (c.hasCycle())
            for (DirectedEdge e : c.cycle())
                StdOut.print(e + " ");
    }
}

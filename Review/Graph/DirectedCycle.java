package Review.Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedCycle {
    private boolean marked[];
    private _Stack<Integer> cycle;
    private int edgeTo[];
    private Digraph g;
    private boolean onStack[];
    public DirectedCycle(Digraph g) {
        this.g = g;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        onStack = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) 
            if (!marked[i] && cycle == null) 
                dfs(i);
    }
    private void dfs(int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) return;
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            } else if (onStack[w]) {
                cycle = new _Stack<>();
                for (int i = v; i != w; i = edgeTo[i])
                    cycle.push(i);
                cycle.push(w);
                cycle.push(v);
                return;
            }
        }
        onStack[v] = false;
    }
    public _Stack<Integer> cycle() { 
        if (!hasCycle())
            throw new RuntimeException("no cycle");
        return cycle; 
    }
    public boolean hasCycle() {
        return cycle != null;
    }
    public void printCycle() {
        if (!hasCycle())
            throw new RuntimeException("no cycle");
        for (int w : cycle())
            StdOut.printf("%-7d", w);
        StdOut.println();
    }
    public static void main(String[] args) {
        Digraph g = new Digraph(new In("/Users/bot/Desktop/algs4-data/mediumDG.txt"));      ;
        StdOut.println(g);
        DirectedCycle cycle = new DirectedCycle(g);
        if (cycle.hasCycle())
            cycle.printCycle();
    }
}

package Review;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class DepthFirstSearch {
    private int count;
    private boolean marked[];
    private Graph g;
    public DepthFirstSearch(Graph g, int s) {
        this.g = g;
        checkVertex(s);
        marked = new boolean[g.V()];
        dfs(s);
    }
    public boolean marked(int v) { checkVertex(v); return marked[v]; }
    public boolean isConnectedGraph() { return count == g.V(); }
    public boolean areConnected(int v, int w) { 
        checkVertex(v);
        checkVertex(w);
        return marked[v] && marked[w]; 
    }
    private void dfs(int v) {
        marked[v] = true;
        count++;
        for (int w : g.adj(v))
            if (!marked[w])
                dfs(w);
    }
    private void checkVertex(int v) {
        if (v < 0 || v >= g.V())
            throw new IllegalArgumentException();
    }
    
    public static void main(String[] args) {
        Graph g = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        DepthFirstSearch dfs = new DepthFirstSearch(g, 0);
        if (dfs.isConnectedGraph())
            StdOut.println("连通图");
        if (dfs.areConnected(0, 3))
            StdOut.println("0 3 connected!");
    }
}

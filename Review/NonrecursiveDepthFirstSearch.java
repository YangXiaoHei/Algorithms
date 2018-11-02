package code;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class NonrecursiveDepthFirstSearch {
    private Graph g;
    private boolean marked[];
    private _Stack<Integer> S;
    private int count;
    public NonrecursiveDepthFirstSearch(Graph g, int s) {
        this.g = g;
        marked = new boolean[g.V()];
        S = new _Stack<Integer>();
        checkVertex(s);
        dfs(s);
    }
    private void dfs(int v) {
        marked[v] = true;
        S.push(v);
        count++;
        boolean allMarked = true;
        while (!S.isEmpty()) {
            v = S.top();
            allMarked = true;
            for (int w : g.adj(v)) 
                if (!marked[w]) {
                    count++;
                    marked[w] = true;
                    allMarked = false;
                    S.push(w);
                    break;
                }
            if (allMarked)
                S.pop();
        }
    }
    public boolean isConnectedGraph() {
        return count == g.V();
    }
    public boolean areConnected(int v, int w) {
        return marked[v] && marked[w];
    }
    private void checkVertex(int v) {
        if (v < 0 || v >= g.V())
            throw new IllegalArgumentException();
    }
    public static void main(String[] args) {
        Graph g = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        NonrecursiveDepthFirstSearch dfs = new NonrecursiveDepthFirstSearch(g, 0);
        if (dfs.isConnectedGraph())
            StdOut.println("连通图");
        if (dfs.areConnected(0, 3))
            StdOut.println("0 3 connected!");
    }
}

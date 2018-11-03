package Review;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPath {
    private boolean marked[];
    private Graph g;
    private int[] edgeTo;
    private int source;
    public DepthFirstPath(Graph g, int s) {
        this.g = g;
        source = s;
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        dfs(s);
    }
    private void dfs(int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            }
        }
    }
    public boolean hasPathTo(int v) { return marked[v]; }
    public Iterable<Integer> pathTo(int v) {
        _Stack<Integer> S = new _Stack<>();
        for (int i = v; i != source; i = edgeTo[i])
            S.push(i);
        S.push(source);
        return S;
    }
    public void printPath(int v) {
        if (!hasPathTo(v))
            throw new IllegalArgumentException("no path to " + v);
        for (int w : pathTo(v))
            if (w == source)
                StdOut.printf("%d", w);
            else
                StdOut.printf(" -> %d", w);
        StdOut.println();
    }
    public static void main(String[] args) {
        Graph g = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        DepthFirstPath dfs = new DepthFirstPath(g, 0);
        for (int i = 0; i < g.V(); i++)
            if (dfs.hasPathTo(i))
                dfs.printPath(i);
    }
}

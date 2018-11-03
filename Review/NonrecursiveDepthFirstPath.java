package Review;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class NonrecursiveDepthFirstPath {
    private Graph g;
    private int[] edgeTo;
    private boolean marked[];
    private int source;
    public NonrecursiveDepthFirstPath(Graph g, int s) {
        this.g = g;
        source = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(s);
    }
    private void dfs(int v) {
        _Stack<Integer> S = new _Stack<>();
        S.push(v);
        marked[v] = true;
        boolean allMarked = true;
        while (!S.isEmpty()) {
            v = S.top();
            allMarked = true;
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    allMarked = false;
                    marked[w] = true;
                    edgeTo[w] = v;
                    S.push(w);
                    break;
                }
            }
            if (allMarked)
                S.pop();
        }
    }
    public boolean hasPathTo(int v) { return marked[v]; }
    public Iterable<Integer> pathTo(int v) {
        _Stack<Integer> S = new _Stack<>();
        for(int i = v; i != source;  i = edgeTo[i])
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
        NonrecursiveDepthFirstPath dfs = new NonrecursiveDepthFirstPath(g, 0);
        for (int i = 0; i < g.V(); i++)
            if (dfs.hasPathTo(i))
                dfs.printPath(i);
    }
}

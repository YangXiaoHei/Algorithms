package code;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstDirectedPath {
    private Digraph g;
    private int[] disTo;
    private int[] edgeTo;
    private boolean marked[];
    private int source;
    public DepthFirstDirectedPath(Digraph g, int s) {
        disTo = new int[g.V()];
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        this.g = g;
        source = s;
        dfs(s);
    }
    private void dfs(int v) {
        marked[v] = true;
        _Stack<Integer> S = new _Stack<>();
        S.push(v);
        boolean allMarked = true;
        while (!S.isEmpty()) {
            v = S.top();
            allMarked = true;
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    disTo[w] = disTo[v] + 1;
                    edgeTo[w] = v;
                    S.push(w);
                    allMarked = false;
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
        for (int i = v; i != source; i = edgeTo[i])
            S.push(i);
        S.push(source);
        return S;
    }
    public int disTo(int v) { return disTo[v]; }
    public void printPath(int v) {
        StdOut.printf("from %d to %d [%d]: ", source, v, disTo[v]);
        for (int w : pathTo(v))
            StdOut.printf("%d ", w);
        StdOut.println();
    }
    public static void main(String[] args) {
        Digraph g = new Digraph(new In("/Users/bot/Desktop/algs4-data/mediumDG.txt"));      ;
        StdOut.println(g);
        DepthFirstDirectedPath dfs = new DepthFirstDirectedPath(g, 0);
        for (int i = 0; i < g.V(); i++)
            if (dfs.hasPathTo(i))
                dfs.printPath(i);
    }
}

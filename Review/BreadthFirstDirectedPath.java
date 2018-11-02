package code;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstDirectedPath {
    private Digraph g;
    private int disTo[];
    private int edgeTo[];
    private boolean marked[];
    private int source;
    public BreadthFirstDirectedPath(Digraph g, int s) {
        source = s;
        this.g = g;
        disTo = new int[g.V()];
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        bfs(s);
    }
    private void bfs(int v) {
        marked[v] = true;
        _Queue<Integer> q = new _Queue<>();
        q.enqueue(v);
        while (!q.isEmpty()) {
            v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    disTo[w] = disTo[v] + 1;
                    edgeTo[w] = v;
                    q.enqueue(w);
                }
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
        StdOut.printf("from %d to %d [%d]: ", source, v, disTo[v]);
        for (int w : pathTo(v))
            StdOut.printf("%d ", w);
        StdOut.println();
    }
    public static void main(String[] args) {
        Digraph g = new Digraph(new In("/Users/bot/Desktop/algs4-data/mediumDG.txt"));      ;
        StdOut.println(g);
        BreadthFirstDirectedPath dfs = new BreadthFirstDirectedPath(g, 0);
        for (int i = 0; i < g.V(); i++)
            if (dfs.hasPathTo(i))
                dfs.printPath(i);
            else
                StdOut.printf("no path from %d to %d\n", 0, i);
    }
}

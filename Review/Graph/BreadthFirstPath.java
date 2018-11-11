package Review.Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPath {
    private Graph g;
    private boolean marked[];
    private int edgeTo[];
    private int source;
    public BreadthFirstPath(Graph g, int s) {
        this.g = g;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        source = s;
        bfs(s);
    }
    private void bfs(int v) {
        _Queue<Integer> q = new _Queue<>();
        marked[v] = true;
        q.enqueue(v);
        while (!q.isEmpty()) {
            v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
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
        if (!hasPathTo(v))
            throw new IllegalArgumentException("no path to " + v);
        for (int w : pathTo(v))
            if (w == source)
                StdOut.printf("%d", w);
            else
                StdOut.printf("-> %d", w);
        StdOut.println();
    }
    public static void main(String[] args) {
        Graph g = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        BreadthFirstPath dfs = new BreadthFirstPath(g, 0);
        for (int i = 0; i < g.V(); i++)
            if (dfs.hasPathTo(i))
                dfs.printPath(i);
    }
}

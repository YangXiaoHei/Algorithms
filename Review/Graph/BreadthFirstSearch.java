package Review.Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstSearch {
    private boolean marked[];
    private Graph g;
    private int count;
    public BreadthFirstSearch(Graph g, int s) {
        this.g = g;
        marked = new boolean[g.V()];
        bfs(s);
    }
    private void bfs(int v) {
        _Queue<Integer> q = new _Queue<>();
        marked[v] = true;
        q.enqueue(v);
        count++;
        while (!q.isEmpty()) {
            v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    count++;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }
    public boolean isConnectedGraph() {
        return count == g.V();
    }
    public boolean areConnected(int v, int w) {
        return marked[v] && marked[w];
    }
    public static void main(String[] args) {
        Graph g = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        BreadthFirstSearch dfs = new BreadthFirstSearch(g, 0);
        if (dfs.isConnectedGraph())
            StdOut.println("连通图");
        if (dfs.areConnected(0, 3))
            StdOut.println("0 3 connected!");
    }
}

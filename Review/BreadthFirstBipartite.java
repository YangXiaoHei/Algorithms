package Review;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstBipartite {
    private Graph g;
    private int edgeTo[];
    private boolean marked[];
    private boolean color[];
    private _Queue<Integer> cycle;
    private boolean isBipartite = true;
    public BreadthFirstBipartite(Graph g) {
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        this.g = g;
        for (int i = 0; i < g.V(); i++)
            if (!marked[i])
                bfs(i);
    }
    private void bfs(int v) {
        if (!isBipartite) return;
        marked[v] = true;
        _Queue<Integer> bfsQ = new _Queue<>();
        bfsQ.enqueue(v);
        while (!bfsQ.isEmpty()) {
            v = bfsQ.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    color[w] = !color[v];
                    marked[w] = true;
                    bfsQ.enqueue(w);
                } else if (color[w] == color[v]) {
                    isBipartite = false;
                    cycle = new _Queue<>();
                    _Stack<Integer> S = new _Stack<>();
                    int x = v, y = w;
                    while (x != y) {
                        cycle.enqueue(y);
                        S.push(x);
                        x = edgeTo[x];
                        y = edgeTo[y];
                    }
                    S.push(x);
                    while (!S.isEmpty())
                        cycle.enqueue(S.pop());
                    cycle.enqueue(w);
                    return;
                }
            }
        }
    }
    public boolean isBipartite() { return isBipartite; }
    public boolean color(int v) {
        if (!isBipartite())
            throw new RuntimeException("not a bipartite!");
        return color[v];
    }
    public Iterable<Integer> oddCycle() { return cycle; }
    public static void main(String[] args) {
        Graph g = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        StdOut.println(g);
        BreadthFirstBipartite b = new BreadthFirstBipartite(g);
        if (b.isBipartite()) 
            StdOut.println("Bipartite!");
         else 
            for (int w : b.oddCycle()) 
                StdOut.printf("%-3d", w);
    }
}

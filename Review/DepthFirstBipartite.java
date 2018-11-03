package Review;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstBipartite {
    private Graph g;
    private boolean marked[];
    private boolean color[];
    private int edgeTo[];
    private boolean isBipartite = true;
    private _Stack<Integer> S;
    public DepthFirstBipartite(Graph g) {
        this.g = g;
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++)
            if (!marked[i])
                dfs(i);
    }
    private void dfs(int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            
            if (!isBipartite) return;
            
            if (!marked[w]) { 
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                S = new _Stack<>();
                S.push(w);
                for (int i = v; i != w; i = edgeTo[i])
                    S.push(i);
                S.push(w);
            }
        }
    }
    public boolean color(int v) { 
        if (!isBipartite)
            throw new RuntimeException("not a bipartite!");
        return color[v]; 
    }
    public boolean isBipartite() { return isBipartite; }
    public Iterable<Integer> oddCycle() {
        return S;
    }
    public static void main(String[] args) {
        Graph g = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        StdOut.println(g);
        DepthFirstBipartite b = new DepthFirstBipartite(g);
        if (b.isBipartite()) 
            StdOut.println("Bipartite!");
         else 
            for (int w : b.oddCycle()) 
                StdOut.printf("%-3d", w);
    }
}

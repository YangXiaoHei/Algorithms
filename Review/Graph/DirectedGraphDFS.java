package Review.Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedGraphDFS {
    private Digraph g;
    private boolean marked[];
    private int count;
    public DirectedGraphDFS(Digraph g, int s) {
        this.g = g;
        marked = new boolean[g.V()];
        dfs(s);
    }
    private void dfs(int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                count++;
                dfs(w);
            }
        }
    }
    public boolean reachable(int v) { return marked[v]; }
    public int count() { return count; }
    public static void main(String[] args) {
        Digraph g = new Digraph(new In("/Users/bot/Desktop/algs4-data/mediumDG.txt"));
        DirectedGraphDFS dfs = new DirectedGraphDFS(g, 0);
        StdOut.printf("可达顶点数目 : %d\n", dfs.count());
        for (int i = 0; i < g.V(); i++)
            if (dfs.reachable(i))
                StdOut.printf("%d 可达\n", i);
    }
}

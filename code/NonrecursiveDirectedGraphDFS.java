package code;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class NonrecursiveDirectedGraphDFS {
    private Digraph g;
    private boolean marked[];
    private int count;
    public NonrecursiveDirectedGraphDFS(Digraph g, int s) {
        marked = new boolean[g.V()];
        this.g = g;
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
                    count++;
                    marked[w] = true;
                    allMarked = false;
                    S.push(w);
                    break;
                }
            }
            if (allMarked)
                S.pop();
        }
    }
    public int count() { return count; }
    public boolean reachable(int v) { return marked[v]; }
    public static void main(String[] args) {
        Digraph g = new Digraph(new In("/Users/bot/Desktop/algs4-data/mediumDG.txt"));
        DirectedGraphDFS dfs = new DirectedGraphDFS(g, 0);
        StdOut.printf("可达顶点数目 : %d\n", dfs.count());
        for (int i = 0; i < g.V(); i++)
            if (dfs.reachable(i))
                StdOut.printf("%d 可达\n", i);
    }
    
}

package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class KosarajuSCC {
    private Digraph g;
    private boolean marked[];
    private int[] id;
    private int[] size;
    private int count;
    public KosarajuSCC(Digraph g) {
        this.g = g;
        marked = new boolean[g.V()];
        id = new int[g.V()];
        size = new int[g.V()];
        DepthFirstOrder o = new DepthFirstOrder(g.reverse());
        for (int w : o.postReverseOrder()) {
            if (!marked[w]) {
                dfs(w);
                count++;
            }
        }
    }
    private void dfs(int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) 
            if (!marked[w])
                dfs(w);
    }
    public int count() { return count; }
    public int size(int v) { 
        if (v < 0 || v > count)
            throw new IllegalArgumentException("sdfsdfsf");
        return size[v];
    }
    public int id(int v) { checkVertex(v); return id[v]; }
    public boolean stronglyConnected(int v, int w) { checkVertex(v, w); return id[v] == id[w]; }
    private void checkVertex(int ...s) {
        for (int i = 0; i < s.length; i++)
            if (s[i] < 0 || s[i] >= g.V())
                throw new IllegalArgumentException("vertex is invalid");
    }
    public static void main(String[] args) {
        Digraph g = DigraphGenerator.simple(30, 80);
        StdOut.println(g);
        KosarajuSCC scc = new KosarajuSCC(g);
        for (int i = 0; i < scc.count(); i++)
            StdOut.printf("id[%d] : %d 个顶点\n", i, scc.size(i));
    }
}

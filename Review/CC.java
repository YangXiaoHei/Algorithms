package code;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CC {
    private Graph g;
    private int count; 
    private int size[];
    private int id[];
    private boolean marked[];
    public CC(Graph g) {
        count = 1;
        size = new int[g.V()];
        id = new int[g.V()];
        marked = new boolean[g.V()];
        this.g = g;
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(i);
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
    public int size(int v) { return size[id[v]]; }
    public int id(int v) { return id[v]; }
    public int count() { return count; }
    public boolean areConnected(int v, int w) { return id[v] == id[w]; }
    public boolean isConnectedGraph() {
        int sum = 0;
        for (int i = 0; i < size.length; i++)
            sum += size[i];
        return sum == g.V();
    }
    public static void main(String[] args) {
        Graph g = new Graph(new In("/Users/bot/Desktop/algs4-data/tinyCG.txt"));
        CC cc = new CC(g);
        for (int i = 0; i < g.V(); i++)
            StdOut.println(cc.id(i));
        if (cc.isConnectedGraph())
            StdOut.println("连通图");
    }
}

package Ch_4_3_Minimum_Spanning_Trees;

import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnion {
    private int[] ids;
    private int[] sz;
    public WeightedQuickUnion(int n) {
        ids = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sz[i] = 1;
        }
    }
    public int find(int v) {
        while (ids[v] != v)
            v = ids[v];
        return v;
    }
    public boolean connected(int v, int w) {
        return find(v) == find(w);
    }
    public void union(int v, int w) {
        int pRoot = find(v);
        int qRoot = find(w);
        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            /* p 是小树，将其接到大树 */
            ids[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            /* q 是小树，将其接到大树 */
            ids[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(10);
        EG eg = new EG(10);
        int k = 10;
        while (k-- > 0) {
            Pair p = eg.next();
            if (uf.connected(p.v, p.w))
                StdOut.printf("{%d %d} connected!\n", p.v, p.w);
            else
                uf.union(p.v, p.w);
        }
    }
}

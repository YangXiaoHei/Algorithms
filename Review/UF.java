package Review;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class UF {
    static class Pair {
        final int v;
        final int w;
        Pair(int v, int w) { this.v = v; this.w = w; }
        public String toString() {
            return String.format("{ %d %d }", v, w);
        }
    }
    private int[] ids;
    private int[] sz;
    public UF(int v) {
        ids = new int[v];
        sz = new int[v];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
            sz[i] = 1;
        }
    }
    public int find(int p) {
        int root = p;
        while (root != ids[root])
            root = ids[root];
        int tmp = 0;
        while (p != ids[p]) {
            tmp = ids[p];
            ids[p] = root;
            p = tmp;
        }
        return p;
    }
    public void union(int v, int w) {
        int pRoot = find(v);
        int qRoot = find(w);
        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            ids[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            ids[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
    public int depth(int v) {
        int n = 0;
        while (v != ids[v]) {
            v = ids[v];
            n++;
        }
        return n;
    }
    public int maxDepth() {
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < ids.length; i++)
            if ((tmp = depth(i)) > max)
                max = tmp;
        return max;
    }
    public boolean connected(int v, int w) { return find(v) == find(w); }
    public Pair next() {
        return new Pair(StdRandom.uniform(ids.length), 
                        StdRandom.uniform(ids.length));
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++)
            sb.append(String.format("%-3d", i));
        sb.append("\n");
        for (int i = 0; i < ids.length; i++)
            sb.append(String.format("%-3d", ids[i]));
        sb.append("\n");
        for (int i = 0; i < sz.length; i++)
            sb.append(String.format("%-3d", sz[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        UF uf = new UF(10000000);
        int k = 10000000;
        while (k-- > 0) {
            Pair p = uf.next();
//            StdOut.println(p);
            if (uf.connected(p.v, p.w)) {
//                StdOut.printf("%d %d connected!\n", p.v, p.w);
                continue;
            }
            uf.union(p.v, p.w);
        }
//        StdOut.println(uf);
        StdOut.printf("最大树深度 : %d\n", uf.maxDepth());
    }
 }

package Review;

import Tool.ArrayGenerator.RandomPair;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id;
    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    public int find(int p) { return id[p]; }
    public boolean connected(int p, int q) { return find(p) == find(q); }
    public int height(int p) {
        int height = 0;
        while (id[p] != p) {
            ++height;
            p = id[p];
        }
        return height;
    }
    public int maxHeight() {
        int max = height(0), tmp = 0;
        for (int i = 1; i < id.length; i++) {
            if ((tmp = height(i)) > max) 
                max = tmp;
        }
        return max;
    }
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        for (int i = 0; i < id.length; i++)
            if (id[i] == pRoot) id[i] = qRoot;
    }
    public static void main(String[] args) {
        int N = 20, pairCount = 30;
        RandomPair gen = new RandomPair(N);
        QuickFindUF uf = new QuickFindUF(N);
        for (int i = 0; i < pairCount; i++) {
            int[] pair = gen.nextPair();
            if (!uf.connected(pair[0], pair[1])) {
                StdOut.printf("连接 %d  %d\n", pair[0], pair[1]);
                uf.union(pair[0], pair[1]);
            } else {
                StdOut.printf("%d %d 已连通\n", pair[0], pair[1]);
            }
        }
        StdOut.printf("最大树高 ：%d\n", uf.maxHeight());
    }
    // output
    /*
     *  连接 11  13
        连接 5  15
        连接 2  15
        连接 19  2
        连接 0  7
        连接 17  10
        连接 15  12
        连接 5  10
        连接 1  19
        10 17 已连通
        连接 10  0
        连接 17  8
        连接 18  6
        连接 16  14
        0 7 已连通
        连接 15  3
        连接 5  6
        连接 9  13
        连接 17  9
        12 8 已连通
        15 9 已连通
        10 5 已连通
        7 17 已连通
        0 9 已连通
        连接 15  14
        18 8 已连通
        8 13 已连通
        连接 10  4
        2 17 已连通
        4 5 已连通
        最大树高 ：1
     */
}

package 第一章_Union_Find_算法;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_5_17 {
    static class CompressedWeightedQuickUnion {
        private int[] id;
        private int[] size;
        CompressedWeightedQuickUnion(int N) {
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        boolean connected(int p, int q) { return find(p) == find(q); }
        boolean allConnected() {
            int count = 0;
            for (int i = 0; i < id.length; i++)
                if (id[i] == i) count++;
            return count == 1;
        }
        int find(int p) {
            int root = p;
            while (id[root] != root)
                root = id[root];
            while (id[p] != p) {
                int parent = id[p];
                id[p] = root;
                size[parent] -= size[p];
                p = parent;
            }
            return root;
        }
        void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (size[pRoot] < size[qRoot]) {
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            } else {
                id[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }
        }
        static int count(int N) {
            int count = 0;
            Text_Generator gen = new Text_RandomPairGenerator(N);
            CompressedWeightedQuickUnion cwqu = new CompressedWeightedQuickUnion(N);
            while (!cwqu.allConnected()) {
                count++;
                int[] pair = gen.nextPair();
                if (cwqu.connected(pair[0], pair[1])) continue;
                cwqu.union(pair[0], pair[1]);
            }
            return count;
        }
    }
    public static void main(String[] args) {
        int N = 1000;
        StdOut.printf("使 %d 个触点全部连通，共生成 %d 对随机连接\n",
                N, CompressedWeightedQuickUnion.count(N));
    }
    // output
    /*
     * 使 1000 个触点全部连通，共生成 3341 对随机连接

     */
}

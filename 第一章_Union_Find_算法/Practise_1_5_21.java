package 第一章_Union_Find_算法;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_5_21 {
    static class WeightedQuickUnion {
        private int[] id;
        private int[] size;
        private int count;
        WeightedQuickUnion(int N) {
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = N;
        }
        int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;
        }
        void reset() {
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = id.length;
        }
        boolean connected(int p, int q) { return find(p) == find(q); }
        boolean allConnected() { return count == 1; }
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
            count--;
        }
        static int count(int N, int M) {
            int count = 0;
            Text_Generator gen = new Text_RandomPairGenerator(N);
            WeightedQuickUnion wqu = new WeightedQuickUnion(N);
            for (int i = 0; i < M; i++) {
                while (!wqu.allConnected()) {
                    int[] pair = gen.nextPair();
                    wqu.union(pair[0], pair[1]);
                    count++;
                }
                wqu.reset();
            }
            return count / M;
        }
    }
    public static void main(String[] args) {
        int N = 10000, M = 1000;
        StdOut.printf("N = %d 总连接数实验值 : %d  理论值 : %d\n",
                N, WeightedQuickUnion.count(N, M), (int)(0.5 * N * Math.log(N)));
    }
    // output
    /*
     * N = 10000 总连接数实验值 : 48717  理论值 : 46051
     */
}

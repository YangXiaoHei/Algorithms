package Ch_1_5_Case_Study_Union_Find;

import edu.princeton.cs.algs4.StdOut;
import static Tool.ArrayGenerator.RandomPair;

public class __Review {
    /*
     * 带路径压缩的加权 quick-union
     */
    static class CWQU {
        private int[] id;
        private int[] sz;
        CWQU(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }
        int find(int p) {
            int root = p;
            while (root != id[root])
                root = id[root];
            while (id[p] != root) {
                int parent = id[p];
                id[p] = root;
                p = parent;
            }
            return root;
        }
        boolean connected(int p, int q) { return find(p) == find(q); }
        void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (sz[pRoot] < sz[qRoot]) {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            } else {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            }
        }
        int height(int p) {
            int height = 0;
            while (p != id[p]) {
                p = id[p];
                height++;
            }
            return height;
        }
        int maxTreeHeight() {
            int max = height(0), tmp = max;
            for (int i = 0; i < id.length; i++) 
                if ((tmp = height(i)) > max) max = tmp;
            return max;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < id.length; i++)
                sb.append(String.format("%-4d", i));
            sb.append("\n");
            for (int i = 0; i < id.length; i++)
                sb.append(String.format("%-4d", id[i]));
            sb.append("\n------------- 尺寸 ------------------\n");
            for (int i = 0; i < id.length; i++)
                sb.append(String.format("%-4d", sz[i]));
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        int N = 10000000, count = 10000000;
        RandomPair gen = new RandomPair(N);
        CWQU cwqu = new CWQU(N);
        for (int i = 0; i < count; i++) {
            int[] pair = gen.nextPair();
            cwqu.union(pair[0], pair[1]);
        }
        StdOut.printf("最大树高 : %d\n", cwqu.maxTreeHeight());
    }
}

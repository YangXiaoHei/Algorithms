package 第一章_Union_Find_算法;

import static 第一章_Union_Find_算法.Practise_1_5_18.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_5_25 {
    interface UF {
        int find(int p);
        boolean connected(int p, int q);
        boolean allConnected();
        void union(int p, int q);
        void reset();
    }
    static void doubleRationTest(int T, int begN, Class<?> type) throws Exception {
        double prev = 0, cur = 0;
        int pairCount = 0;
        for (int i = begN, j = 0; j < T; i += i, j++) {
            int tmpI = (int)Math.sqrt(i);
            UF uf = (UF)type.getDeclaredConstructor(int.class).newInstance(tmpI * tmpI);
            RandomBag<Connection> rb = randomGridGenerator(tmpI);
            cur = 0;
            pairCount = 0;
            int testCount = 3;
            for (int k = 0; k < testCount; k++) {
                Stopwatch timer = new Stopwatch();
                for (Connection c : rb) {
                    if (uf.allConnected()) break;
                    pairCount++;
                    if (uf.connected(c.p, c.q)) continue;
                    uf.union(c.p, c.q);
                }
                cur += timer.elapsedTime();
            }
            cur /= (testCount * 1.0);
            pairCount /= (testCount * 1.0);
            
            StdOut.printf("规模 : %d "
                    + "连接数 : %d "
                    + "耗时 : %f "
                    + "倍率 : %f\n", tmpI * tmpI, pairCount, cur, cur / prev);
            prev = cur;
        } 
    }
    /*
     * Quick-Find
     */
    static class QF implements UF {
        private int[] id;
        private int count;
        QF (int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
            count = N;
        }
        public void reset() {
            for (int i = 0; i < id.length; i++) 
                id[i] = i;
            count = id.length;
        }
        public int find(int p) { return id[p]; }
        public boolean connected(int p, int q) { return find(p) == find(q); }
        public boolean allConnected() { return count == 1; }
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            for (int i = 0; i < id.length; i++) 
                if (id[i] == pRoot) id[i] = qRoot;
            count--;
        }
    }
    /*
     * Quick-Union
     * 
     *  规模 : 100 连接数 : 51 耗时 : 0.000333 倍率 : Infinity
        规模 : 196 连接数 : 105 耗时 : 0.000000 倍率 : 0.000000
        规模 : 400 连接数 : 208 耗时 : 0.000333 倍率 : Infinity
        规模 : 784 连接数 : 445 耗时 : 0.000667 倍率 : 2.000000
        规模 : 1600 连接数 : 866 耗时 : 0.001000 倍率 : 1.500000
        规模 : 3136 连接数 : 1957 耗时 : 0.002000 倍率 : 2.000000
        规模 : 6400 连接数 : 3905 耗时 : 0.003667 倍率 : 1.833333
        规模 : 12769 连接数 : 7874 耗时 : 0.010000 倍率 : 2.727273
        规模 : 25600 连接数 : 16359 耗时 : 0.051000 倍率 : 5.100000
        规模 : 51076 连接数 : 31807 耗时 : 0.208667 倍率 : 4.091503
        规模 : 102400 连接数 : 64107 耗时 : 1.256000 倍率 : 6.019169
        规模 : 204304 连接数 : 132653 耗时 : 8.717667 倍率 : 6.940817
        规模 : 409600 连接数 : 263954 耗时 : 42.266000 倍率 : 4.848316
     */
    static class QU implements UF {
        private int[] id;
        private int count;
        QU(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) 
                id[i] = i;
            count = N;
        }
        public int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;  
        }
        public void reset() {
            for (int i = 0; i < id.length; i++) 
                id[i] = i;
            count = id.length;
        }
        public boolean connected(int p, int q) { return find(p) == find(q); }
        public boolean allConnected() { return count == 1; }
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            id[pRoot] = qRoot;
            count--;
        }
    }
    /*
     * Weighted-Quick-Union
     * 
     *  规模 : 100 连接数 : 12 耗时 : 0.000200 倍率 : Infinity
        规模 : 196 连接数 : 30 耗时 : 0.000100 倍率 : 0.500000
        规模 : 400 连接数 : 61 耗时 : 0.000300 倍率 : 3.000000
        规模 : 784 连接数 : 138 耗时 : 0.000300 倍率 : 1.000000
        规模 : 1600 连接数 : 286 耗时 : 0.000300 倍率 : 1.000000
        规模 : 3136 连接数 : 549 耗时 : 0.000800 倍率 : 2.666667
        规模 : 6400 连接数 : 1129 耗时 : 0.000900 倍率 : 1.125000
        规模 : 12769 连接数 : 2348 耗时 : 0.001300 倍率 : 1.444444
        规模 : 25600 连接数 : 4745 耗时 : 0.009100 倍率 : 7.000000
        规模 : 51076 连接数 : 9527 耗时 : 0.015200 倍率 : 1.670330
        规模 : 102400 连接数 : 19899 耗时 : 0.031500 倍率 : 2.072368
        规模 : 204304 连接数 : 38574 耗时 : 0.060600 倍率 : 1.923810
        规模 : 409600 连接数 : 78498 耗时 : 0.123000 倍率 : 2.029703
        规模 : 819025 连接数 : 158526 耗时 : 0.320000 倍率 : 2.601626
     */
    static class WQU  implements UF {
        private int[] id;
        private int[] size;
        private int count;
        WQU(int N) {
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) { 
                id[i] = i;
                size[i] = 1;
            }
            count = N;
        }
        public void reset() {
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = id.length;
        }
        public int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;  
        }
        public boolean connected(int p, int q) { return find(p) == find(q); }
        public boolean allConnected() { return count == 1; }
        public void union(int p, int q) {
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
    }
    public static void main(String[] args) throws Exception {
        doubleRationTest(14, 100, QF.class);
//        doubleRationTest(14, 100, QU.class);
//        doubleRationTest(14, 100, WQU.class);
    }
}

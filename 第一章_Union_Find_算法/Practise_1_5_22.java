package 第一章_Union_Find_算法;

import java.lang.reflect.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_5_22 {
    /*
     * 并查集接口
     */
    interface UF {
        int find(int p);
        boolean connected(int p, int q);
        boolean allConnected();
        void union(int p, int q);
        void reset();
    }
    /*
     * 性能测试用例
     * 
     * @param T     规模加倍次数
     * @param begN  起始规模
     * @param type  并查集实现类型, 一定要实现 UF 接口
     */
    static void doubleRatioTest(int T, int begN, Class<?> type) 
            throws NoSuchMethodException, 
                   SecurityException,
                   InstantiationException, 
                   IllegalAccessException,
                   IllegalArgumentException, 
                   InvocationTargetException {
        double prev = 0, cur = 0;
        // 规模扩大
        for (int i = begN, j = 0; j < T; i += i, j++) {
            UF uf = (UF)type.getDeclaredConstructor(int.class).newInstance(i);
            Text_Generator gen = new Text_RandomPairGenerator(i);
            cur = 0; 
            int testCount = 10; // 多次测验，取平均值
            for (int k = 0; k < testCount; k++) { 
                uf.reset(); // 重置
                Stopwatch timer = new Stopwatch();
                while (!uf.allConnected()) {
                    int[] pair = gen.nextPair();
                    if (uf.connected(pair[0], pair[1])) continue;
                    uf.union(pair[0], pair[1]);
                }
                cur += timer.elapsedTime(); 
            }
            cur /= testCount * 1.0;
            StdOut.printf("规模 : %d 耗时 %f 倍率 : %f\n", i, cur, cur / prev);
            prev = cur;
        }
    }
    /*
     * Quick-Find
     * 
     * output
     *  规模 : 100 耗时 0.000667 倍率 : Infinity
        规模 : 200 耗时 0.000333 倍率 : 0.500000
        规模 : 400 耗时 0.001667 倍率 : 5.000000
        规模 : 800 耗时 0.000667 倍率 : 0.400000
        规模 : 1600 耗时 0.002000 倍率 : 3.000000
        规模 : 3200 耗时 0.005667 倍率 : 2.833333
        规模 : 6400 耗时 0.023000 倍率 : 4.058824
        规模 : 12800 耗时 0.098667 倍率 : 4.289855
        规模 : 25600 耗时 0.407000 倍率 : 4.125000
        规模 : 51200 耗时 1.635667 倍率 : 4.018837
        规模 : 102400 耗时 7.030667 倍率 : 4.298349
        规模 : 204800 耗时 28.814333 倍率 : 4.098379

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
     * output
     *  规模 : 100 耗时 0.000500 倍率 : Infinity
        规模 : 200 耗时 0.000200 倍率 : 0.400000
        规模 : 400 耗时 0.000500 倍率 : 2.500000
        规模 : 800 耗时 0.001200 倍率 : 2.400000
        规模 : 1600 耗时 0.004700 倍率 : 3.916667
        规模 : 3200 耗时 0.018500 倍率 : 3.936170
        规模 : 6400 耗时 0.098200 倍率 : 5.308108
        规模 : 12800 耗时 0.597100 倍率 : 6.080448
        规模 : 25600 耗时 3.311000 倍率 : 5.545135
        规模 : 51200 耗时 17.161400 倍率 : 5.183147
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
        public void reset() {
            for (int i = 0; i < id.length; i++) 
                id[i] = i;
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
            id[pRoot] = qRoot;
            count--;
        }
    }
    /*
     * Weighted-Quick-Union
     * 
     * output
     *  规模 : 100 耗时 0.000700 倍率 : Infinity
        规模 : 200 耗时 0.000100 倍率 : 0.142857
        规模 : 400 耗时 0.000500 倍率 : 5.000000
        规模 : 800 耗时 0.000600 倍率 : 1.200000
        规模 : 1600 耗时 0.001100 倍率 : 1.833333
        规模 : 3200 耗时 0.003200 倍率 : 2.909091
        规模 : 6400 耗时 0.006200 倍率 : 1.937500
        规模 : 12800 耗时 0.006900 倍率 : 1.112903
        规模 : 25600 耗时 0.011200 倍率 : 1.623188
        规模 : 51200 耗时 0.024700 倍率 : 2.205357
        规模 : 102400 耗时 0.051500 倍率 : 2.085020
        规模 : 204800 耗时 0.122900 倍率 : 2.386408
        规模 : 409600 耗时 0.393300 倍率 : 3.200163
        规模 : 819200 耗时 0.997000 倍率 : 2.534961
        规模 : 1638400 耗时 2.576900 倍率 : 2.584654
     */
    static class WQU implements UF {
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
//        doubleRatioTest(12, 100, QF.class);
//        doubleRatioTest(12, 100, QU.class);
        doubleRatioTest(15, 100, WQU.class);
    }
}

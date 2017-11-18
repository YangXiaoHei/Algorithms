package 第一章_Union_Find_算法;

import java.awt.Color;

import edu.princeton.cs.algs4.*;
import 第一章_Union_Find_算法.Practise_1_5_25.UF;

public class Practise_1_5_26 {
    interface UF {
        int find(int p);
        boolean connected(int p, int q);
        boolean allConnected();
        void union(int p, int q);
        void cleanPrevAccess();
        int prevAccess();
        int totalAccess();
    }
    static void amortizedDraw(int N, Class<?> type) throws Exception {
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-N, N * 2);
        StdDraw.setPenRadius(.001);
        UF uf = (UF)type.getDeclaredConstructor(int.class).newInstance(N);
        Text_Generator gen = new Text_RandomPairGenerator(N);
        int i = 0;
        while (!uf.allConnected()) {
            int[] pair = gen.nextPair();
            i++; uf.cleanPrevAccess();
            if (!uf.connected(pair[0], pair[1]))
                uf.union(pair[0], pair[1]);
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.point(i, uf.prevAccess());
            StdDraw.setPenColor(Color.red);
            StdDraw.point(i, uf.totalAccess() / i);
        }
        
    }
    /*
     * Quick-Find
     * 
     */
    static class QF implements UF {
        private int[] id;
        private int count;
        private int accessTotalTimes;
        private int eachLoopAccessTimes;
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
        public int prevAccess() { return eachLoopAccessTimes; }
        public int totalAccess() { return accessTotalTimes; }
        public void cleanPrevAccess() { eachLoopAccessTimes = 0; }
        public int find(int p) { return id[p]; }
        public boolean connected(int p, int q) { 
            eachLoopAccessTimes += 2; 
            return find(p) == find(q); 
        }
        public boolean allConnected() { return count == 1; }
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            eachLoopAccessTimes += 2;
            if (pRoot != qRoot) {
                for (int i = 0; i < id.length; i++) {
                    eachLoopAccessTimes++;
                    if (id[i] == pRoot) {
                        id[i] = qRoot;
                        eachLoopAccessTimes++;
                    }
                }
                count--;
            } 
            accessTotalTimes += eachLoopAccessTimes;
        }
    }
    /*
     * Quick-Union
     * 
     */
    static class QU implements UF {
        private int[] id;
        private int count;
        private int accessTotalTimes;
        private int eachLoopAccessTimes;
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
        public int prevAccess() { return eachLoopAccessTimes; }
        public int totalAccess() { return accessTotalTimes; }
        public void cleanPrevAccess() { eachLoopAccessTimes = 0; }
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
     */
    static class WQU  implements UF {
        private int[] id;
        private int[] size;
        private int count;
        private int accessTotalTimes;
        private int eachLoopAccessTimes;
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
        public int prevAccess() { return eachLoopAccessTimes; }
        public int totalAccess() { return accessTotalTimes; }
        public void cleanPrevAccess() { eachLoopAccessTimes = 0; }
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
        amortizedDraw(10000, QF.class);
    }
}

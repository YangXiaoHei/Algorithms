package 第一章_Union_Find_算法;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_5_23 {
    /*
     * 测试框架
     */
    static void test(int T, int begN) {
        ArrayList<Object> list = new ArrayList<Object>();
        double qfTime = 0, quTime = 0;
        for (int i = begN, j = 0; j < T; i += i, j++) {
            QF qf = new QF(i);
            QU qu = new QU(i);
            list.clear();
            qfTime = 0; quTime = 0;
            Text_Generator gen = new Text_RandomPairGenerator(i);
            while (!qu.allConnected()) {
                int[] pair = gen.nextPair();
                list.add(pair);
                qu.union(pair[0], pair[1]);
            }
            qu.reset();
            
            // 对 quick-find 计时
            Stopwatch timer = new Stopwatch();
            for (Object a : list) {
                int[] arr = (int[])a;
                if (!qf.connected(arr[0], arr[1]))
                    qf.union(arr[0], arr[1]);
            }
            qfTime = timer.elapsedTime();
            
            // 对 quick-union计时
            timer = new Stopwatch();
            for (Object a : list) {
                int[] arr = (int[])a;
                if (!qu.connected(arr[0], arr[1]))
                    qu.union(arr[0], arr[1]);
            }
            quTime = timer.elapsedTime();
            StdOut.printf("【规模 : %d  连接数 : %d】 "
                    + "\tQU 用时 : %f  "
                    + "\tQF 用时 : %f,  "
                    + "\tQU / QF = %f\n", 
                    i, list.size(), 
                    quTime,
                    qfTime, 
                    quTime / qfTime);
            
            if (!qf.allConnected() || 
                !qu.allConnected())
                throw new RuntimeException("qf 或 qu 没有全部连通");
        }
    }
    
    /*
     * Quick-Find
     */
    static class QF {
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
        int find(int p) { return id[p]; }
        boolean connected(int p, int q) { return find(p) == find(q); }
        boolean allConnected() { return count == 1; }
        void union(int p, int q) {
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
     */
    static class QU  {
        private int[] id;
        private int count;
        QU(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) 
                id[i] = i;
            count = N;
        }
        int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;  
        }
        public void reset() {
            for (int i = 0; i < id.length; i++) 
                id[i] = i;
            count = id.length;
        }
        boolean connected(int p, int q) { return find(p) == find(q); }
        boolean allConnected() { return count == 1; }
        void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            id[pRoot] = qRoot;
            count--;
        }
    }
    public static void main(String[] args) throws Exception {
        test(10, 100);
    }
    // output
    /*
     *  【规模 : 100  连接数 : 209】   QU 用时 : 0.000000    QF 用时 : 0.000000,   QU / QF = NaN
        【规模 : 200  连接数 : 593】   QU 用时 : 0.000000    QF 用时 : 0.001000,   QU / QF = 0.000000
        【规模 : 400  连接数 : 1150】  QU 用时 : 0.001000    QF 用时 : 0.001000,   QU / QF = 1.000000
        【规模 : 800  连接数 : 2512】  QU 用时 : 0.001000    QF 用时 : 0.004000,   QU / QF = 0.250000
        【规模 : 1600  连接数 : 6234】     QU 用时 : 0.005000    QF 用时 : 0.002000,   QU / QF = 2.500000
        【规模 : 3200  连接数 : 13779】    QU 用时 : 0.021000    QF 用时 : 0.008000,   QU / QF = 2.625000
        【规模 : 6400  连接数 : 28350】    QU 用时 : 0.112000    QF 用时 : 0.035000,   QU / QF = 3.200000
        【规模 : 12800  连接数 : 74208】   QU 用时 : 0.686000    QF 用时 : 0.115000,   QU / QF = 5.965217
        【规模 : 25600  连接数 : 124883】  QU 用时 : 2.806000    QF 用时 : 0.488000,   QU / QF = 5.750000
     */
}

package Ch_1_5_Case_Study_Union_Find;

import java.util.*;

import Tool.ArrayGenerator.RandomPair;
import edu.princeton.cs.algs4.*;

public class Practise_1_5_24 {
    /*
     * 测试框架
     */
    static void test(int T, int begN) {
        ArrayList<Object> list = new ArrayList<Object>();
        double qfTime = 0, quTime = 0, wquTime = 0, cwquTime = 0;
        for (int i = begN, j = 0; j < T; i += i, j++) {
            // 每次循环初始，生成并查集的四种实现
            QF qf = new QF(i);
            QU qu = new QU(i);
            WQU wqu = new WQU(i);
            CWQU cwqu = new CWQU(i);
            
            // 清空所有连接
            list.clear();
            
            // 随机连接生成器
            RandomPair gen = new RandomPair(i);
            
            // 生成一组可以使并查集完全连通的连接集
            while (!wqu.allConnected()) {
                int[] pair = gen.nextPair();
                list.add(pair);
                wqu.union(pair[0], pair[1]);
            }
            wqu.reset();
            
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
            
            // 对 weighted-quick-union 计时
            timer = new Stopwatch();
            for (Object a : list) {
                int[] arr = (int[])a;
                if (!wqu.connected(arr[0], arr[1]))
                    wqu.union(arr[0], arr[1]);
            }
            wquTime = timer.elapsedTime();
            
            // 对 compressed-weighted-quick-union 计时
            timer = new Stopwatch();
            for (Object a : list) {
                int[] arr = (int[])a;
                if (!cwqu.connected(arr[0], arr[1]))
                    cwqu.union(arr[0], arr[1]);
            }
            cwquTime = timer.elapsedTime();
            
            StdOut.printf("【规模 : %d  连接数 : %d】\t"
                    + "\tQU: %.0f毫秒  "
                    + "\tQF : %.0f毫秒 "
                    + "\tWQU : %.0f毫秒 "
                    + "\tCWQU : %.0f毫秒\n", 
                    i, list.size(),
                    quTime * 1000, 
                    qfTime * 1000,
                    wquTime * 1000, 
                    cwquTime * 1000
                    );
            
            // 防御
            if (!qf.allConnected() || 
                !qu.allConnected() || 
                !wqu.allConnected() ||
                !cwqu.allConnected())
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
    /*
     * Weighted-Quick-Union
     */
    static class WQU {
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
    }
    /*
     * Compressed-Weighted-Quick-Union
     */
    static class CWQU {
        private int[] id;
        private int[] size;
        private int count;
        CWQU(int N) {
            id = new int[N];
            size = new int[N];
            count = N;
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        public void reset() {
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = id.length;
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
    }
    public static void main(String[] args) {
        test(15, 100);
    }
    // output
    /*
     * 
     *   冠军组选手们 WQU 和 CWQU 
     * 
     *  【规模 : 100  连接数 : 325】               WQU : 0毫秒       CWQU : 0毫秒
        【规模 : 200  连接数 : 646】               WQU : 0毫秒       CWQU : 1毫秒
        【规模 : 400  连接数 : 1764】              WQU : 1毫秒       CWQU : 0毫秒
        【规模 : 800  连接数 : 3220】              WQU : 1毫秒       CWQU : 1毫秒
        【规模 : 1600  连接数 : 5541】             WQU : 2毫秒       CWQU : 4毫秒
        【规模 : 3200  连接数 : 13481】            WQU : 2毫秒       CWQU : 2毫秒
        【规模 : 6400  连接数 : 25489】            WQU : 2毫秒       CWQU : 3毫秒
        【规模 : 12800  连接数 : 58629】           WQU : 35毫秒      CWQU : 6毫秒
        【规模 : 25600  连接数 : 135213】          WQU : 13毫秒      CWQU : 11毫秒
        【规模 : 51200  连接数 : 286329】          WQU : 75毫秒      CWQU : 37毫秒
        【规模 : 102400  连接数 : 561291】         WQU : 76毫秒      CWQU : 107毫秒
        【规模 : 204800  连接数 : 1195357】        WQU : 70毫秒      CWQU : 98毫秒
        【规模 : 409600  连接数 : 2637715】        WQU : 273毫秒     CWQU : 319毫秒
        【规模 : 819200  连接数 : 7831431】        WQU : 1162毫秒    CWQU : 1053毫秒
        【规模 : 1638400  连接数 : 13251744】      WQU : 2353毫秒    CWQU : 2830毫秒
        【规模 : 3276800  连接数 : 23679296】      WQU : 5899毫秒    CWQU : 5470毫秒
        
        持久组选手们 QU 和 QF 
        
        【规模 : 100  连接数 : 300】           QU: 0毫秒         QF : 1毫秒        WQU : 1毫秒   CWQU : 0毫秒
        【规模 : 200  连接数 : 871】           QU: 0毫秒         QF : 1毫秒        WQU : 0毫秒   CWQU : 1毫秒
        【规模 : 400  连接数 : 1240】          QU: 3毫秒         QF : 1毫秒        WQU : 0毫秒   CWQU : 0毫秒
        【规模 : 800  连接数 : 2982】          QU: 1毫秒         QF : 5毫秒        WQU : 1毫秒   CWQU : 1毫秒
        【规模 : 1600  连接数 : 5977】         QU: 4毫秒         QF : 3毫秒        WQU : 1毫秒   CWQU : 1毫秒
        【规模 : 3200  连接数 : 14163】        QU: 21毫秒        QF : 9毫秒        WQU : 2毫秒   CWQU : 1毫秒
        【规模 : 6400  连接数 : 26335】        QU: 113毫秒       QF : 28毫秒       WQU : 1毫秒   CWQU : 4毫秒
        【规模 : 12800  连接数 : 62713】       QU: 712毫秒       QF : 167毫秒      WQU : 3毫秒   CWQU : 2毫秒
        【规模 : 25600  连接数 : 133040】      QU: 3164毫秒      QF : 490毫秒      WQU : 5毫秒   CWQU : 4毫秒
        【规模 : 51200  连接数 : 268873】      QU: 19613毫秒     QF : 2312毫秒     WQU : 13毫秒  CWQU : 10毫秒
        【规模 : 102400  连接数 : 584263】     QU: 135047毫秒    QF : 8579毫秒     WQU : 25毫秒  CWQU : 23毫秒
        【规模 : 204800  连接数 : 1196360】    QU: 783346毫秒    QF : 37245毫秒    WQU : 98毫秒  CWQU : 126毫秒
         ....
        
     */
}

package 第一章_Union_Find_算法;

import edu.princeton.cs.algs4.*;

public class Test {
    static class DepthWeightedQuickUnion {
        private int[] id;
        private int[] depth;
        DepthWeightedQuickUnion(int N) {
            depth = new int[N];
            id = new int[N];
            for (int i = 0; i < N; i++) 
                id[i] = i;
        }
        int find(int p) {
            while (p != id[p]) 
                p = id[p];
            return p;
        }
        int maxTreeDepth() {
            int dep = 0;
            for (int i = 0; i < depth.length; i++) 
                if (depth[i] > dep) 
                    dep = depth[i];
            return dep;
        }
        void update(int p) {
            while (id[p] != p) {
                depth[id[p]]++;
                p = id[p];
            }
        }
        void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                StdOut.printf("%d %d 已连通\n",p, q);
                return;
            }
            if      (depth[pRoot] < depth[qRoot]) id[pRoot] = qRoot;
            else if (depth[pRoot] > depth[qRoot]) id[qRoot] = pRoot;
            else    {  id[pRoot] = qRoot; update(pRoot); } // 合并高度相等的树，更新高度
            StdOut.printf("连接 %d %d\n", p, q);
            StdOut.println(this);
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("--------------------------\n");
            sb.append("索引\t");
            int i = 0;
            while (i < id.length) sb.append(String.format("%3d", i++));
            sb.append("\n\t"); i = 0;
            while (i < id.length) sb.append(String.format("%3d", id[i++]));
            sb.append("\n树高\t"); i = 0;
            while (i < id.length) sb.append(String.format("%3d", depth[i++]));
            sb.append("\n \t最大树高 " + maxTreeDepth()); 
            sb.append("\n--------------------------\n");
            return sb.toString();
        }
    }
    public static void theWorstCondition() {
        int N = 20;
        DepthWeightedQuickUnion dcqu = new DepthWeightedQuickUnion(N);
        dcqu.union(1, 2);
        dcqu.union(3, 4);
        dcqu.union(2, 4); // 构造高度2
        
        dcqu.union(5, 6);
        dcqu.union(7, 8);
        dcqu.union(6, 8); // 构造高度2
        
        dcqu.union(4, 8); // 构造高度3
        
        dcqu.union(10, 11);
        dcqu.union(12, 13);
        dcqu.union(11, 13); // 构造高度2
        
        dcqu.union(14, 15);
        dcqu.union(16, 17);
        dcqu.union(15, 17); // 构造高度2
        
        dcqu.union(13, 17); // 构造高度3
        
        dcqu.union(8, 17); // 构造高度4
        
        StdOut.printf("树高为 : %d\n", dcqu.maxTreeDepth());
    }
    public static void main(String[] args) {
        int N = 20;
        Stopwatch timer = new Stopwatch();
        Text_Generator gen = new Text_RandomPairGenerator(N);
        DepthWeightedQuickUnion dcqu = new DepthWeightedQuickUnion(N);
        for (int i = 0; i < N; i++) {
            int[] pair = gen.nextPair();
            dcqu.union(pair[0], pair[1]);
        }
        StdOut.printf("执行完毕，耗时为 %.3f 秒\n", timer.elapsedTime());
        StdOut.printf("树高为 : %d\n", dcqu.maxTreeDepth());
    }
    // output
    /*
     *  连接 12 10
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               0  1  2  3  4  5  6  7  8  9 10 11 10 13 14 15 16 17 18 19
        树高    0  0  0  0  0  0  0  0  0  0  1  0  0  0  0  0  0  0  0  0
            最大树高 1
        --------------------------
        
        连接 6 9
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               0  1  2  3  4  5  9  7  8  9 10 11 10 13 14 15 16 17 18 19
        树高    0  0  0  0  0  0  0  0  0  1  1  0  0  0  0  0  0  0  0  0
            最大树高 1
        --------------------------
        
        连接 4 10
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               0  1  2  3 10  5  9  7  8  9 10 11 10 13 14 15 16 17 18 19
        树高    0  0  0  0  0  0  0  0  0  1  1  0  0  0  0  0  0  0  0  0
            最大树高 1
        --------------------------
        
        连接 2 7
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               0  1  7  3 10  5  9  7  8  9 10 11 10 13 14 15 16 17 18 19
        树高    0  0  0  0  0  0  0  1  0  1  1  0  0  0  0  0  0  0  0  0
            最大树高 1
        --------------------------
        
        连接 16 12
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               0  1  7  3 10  5  9  7  8  9 10 11 10 13 14 15 10 17 18 19
        树高    0  0  0  0  0  0  0  1  0  1  1  0  0  0  0  0  0  0  0  0
            最大树高 1
        --------------------------
        
        连接 11 16
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               0  1  7  3 10  5  9  7  8  9 10 10 10 13 14 15 10 17 18 19
        树高    0  0  0  0  0  0  0  1  0  1  1  0  0  0  0  0  0  0  0  0
            最大树高 1
        --------------------------
        
        连接 5 4
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               0  1  7  3 10 10  9  7  8  9 10 10 10 13 14 15 10 17 18 19
        树高    0  0  0  0  0  0  0  1  0  1  1  0  0  0  0  0  0  0  0  0
            最大树高 1
        --------------------------
        
        连接 11 6
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               0  1  7  3 10 10  9  7  8  9  9 10 10 13 14 15 10 17 18 19
        树高    0  0  0  0  0  0  0  1  0  2  1  0  0  0  0  0  0  0  0  0
            最大树高 2
        --------------------------
        
        4 6 已连通
        连接 0 2
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               7  1  7  3 10 10  9  7  8  9  9 10 10 13 14 15 10 17 18 19
        树高    0  0  0  0  0  0  0  1  0  2  1  0  0  0  0  0  0  0  0  0
            最大树高 2
        --------------------------
        
        连接 13 3
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               7  1  7  3 10 10  9  7  8  9  9 10 10  3 14 15 10 17 18 19
        树高    0  0  0  1  0  0  0  1  0  2  1  0  0  0  0  0  0  0  0  0
            最大树高 2
        --------------------------
        
        连接 17 7
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               7  1  7  3 10 10  9  7  8  9  9 10 10  3 14 15 10  7 18 19
        树高    0  0  0  1  0  0  0  1  0  2  1  0  0  0  0  0  0  0  0  0
            最大树高 2
        --------------------------
        
        连接 16 0
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               7  1  7  3 10 10  9  9  8  9  9 10 10  3 14 15 10  7 18 19
        树高    0  0  0  1  0  0  0  1  0  2  1  0  0  0  0  0  0  0  0  0
            最大树高 2
        --------------------------
        
        0 10 已连通
        4 11 已连通
        连接 16 19
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               7  1  7  3 10 10  9  9  8  9  9 10 10  3 14 15 10  7 18  9
        树高    0  0  0  1  0  0  0  1  0  2  1  0  0  0  0  0  0  0  0  0
            最大树高 2
        --------------------------
        
        连接 18 7
        --------------------------
        索引    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
               7  1  7  3 10 10  9  9  8  9  9 10 10  3 14 15 10  7  9  9
        树高    0  0  0  1  0  0  0  1  0  2  1  0  0  0  0  0  0  0  0  0
            最大树高 2
        --------------------------
        
        10 18 已连通
        5 2 已连通
        7 5 已连通
        执行完毕，耗时为 0.076 秒
        树高为 : 2

     */
}

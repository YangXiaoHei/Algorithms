package Ch_1_5_Case_Study_Union_Find;

import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class Practise_1_5_16 {
    interface UF {
        int find(int p);
        void union(int p, int q);
    }

    static class QuickFind implements UF {
        private int[] id;
        private int accessTimes;
        private int totalTimes;
        private int i = 1;
        QuickFind(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
         
        }
        public int find(int p) { return id[p]; }
        public void union(int p, int q) {
            accessTimes = 0;
            int pID = find(p);
            int qID = find(q);
            accessTimes += 2;
            if (pID != qID) {
                for (int i = 0; i < id.length; i++) {
                    accessTimes++;
                    if (id[i] == pID) {
                        accessTimes++;
                        id[i] = qID;
                    }
                }
            }
            totalTimes += accessTimes;
            
            StdDraw.setPenColor(Color.gray);
            StdDraw.point(i, accessTimes);
            StdDraw.setPenColor(Color.red);
            StdDraw.point(i, totalTimes / i);
            i++;
        }
    }
    static class QuickUnion implements UF {
        private int[] id;
        private int accessTimes;
        private int totalTimes;
        private int i = 1;
        QuickUnion(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }
        public int find(int p) {
            while (true) {
                accessTimes++;
                if (p != id[p]) {
                    p = id[p];
                    accessTimes++;
                } else
                    break;
            }
            return p;
        }
        public void union(int p, int q) {
            accessTimes = 0;
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                id[pRoot] = qRoot;
                accessTimes++;
            } 
            totalTimes += accessTimes;
            
            StdDraw.setPenColor(Color.gray);
            StdDraw.point(i, accessTimes);
            StdDraw.setPenColor(Color.red);
            StdDraw.point(i, totalTimes / i);
            i++;
        }
    }
    static class WeightedQuickUnion implements UF {
        private int[] id;
        private int[] size;
        private int accessTimes;
        private int totalTimes;
        private int i = 1;
        WeightedQuickUnion(int N) {
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p) {
            while (true) {
                accessTimes++;
                if (p != id[p]) {
                    accessTimes++;
                    p = id[p];
                } else
                    break;
            }
            return p;
        }
        public void union(int p, int q) {
            accessTimes = 0;
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                accessTimes += 2;
                if (size[pRoot] < size[qRoot]) {
                    id[pRoot] = qRoot;
                    size[qRoot] += size[pRoot];
                } else {
                    id[qRoot] = pRoot;
                    size[pRoot] += size[qRoot];
                }
                accessTimes += 4;
            }
            totalTimes += accessTimes;
            
            StdDraw.setPenColor(Color.gray);
            StdDraw.point(i, accessTimes);
            StdDraw.setPenColor(Color.red);
            StdDraw.point(i, totalTimes / i);
            i++;
        }
    }
    static class CompressedWeightedQuickUnion implements UF {
        private int[] id;
        private int[] size;
        private int accessTimes;
        private int totalTimes;
        private int i = 1;
        CompressedWeightedQuickUnion(int N) {
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p) {
            int root = p;
            while (true) {
                accessTimes++;
                if (root != id[root]) {
                    accessTimes++;
                    root = id[root];
                } else
                    break;
            }
            
            while (true) {
                accessTimes++;
                if (id[p] != p) {
                    int parent = id[p];
                    id[p] = root;
                    size[parent] -= size[p];
                    p = parent;
                    
                    accessTimes += 5;
                } else 
                    break;
            }
            return root;
        }
        public void union(int p, int q) {
            accessTimes = 0;
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                accessTimes += 2;
                if (size[pRoot] < size[qRoot]) {
                    id[pRoot] = qRoot;
                    size[qRoot] += size[pRoot];
                } else {
                    id[qRoot] = pRoot;
                    size[pRoot] += size[qRoot];
                }
                accessTimes += 4;
            }
            totalTimes += accessTimes;
            
            StdDraw.setPenColor(Color.gray);
            StdDraw.point(i, accessTimes);
            StdDraw.setPenColor(Color.red);
            StdDraw.point(i, totalTimes / i);
            i++;
        }
    }
    
    public static void draw(UF uf, int N, int pairCount) {
        StdDraw.setPenRadius(.003);
        StdDraw.setXscale(0, pairCount);
        StdDraw.setYscale(-N * 2, N * 2);
        Text_Generator gen = new Text_RandomPairGenerator(N);
        for (int i = 0; i < pairCount; i++) {
            int[] pair = gen.nextPair();
            uf.union(pair[0], pair[1]);
        }
    }
    
    public static void main(String[] args) {
        int N = 1000, pairCount = 1000;
        draw(new QuickFind(N), N, pairCount);
        draw(new QuickUnion(N), N, pairCount);
        draw(new WeightedQuickUnion(N), N, pairCount);
        draw(new CompressedWeightedQuickUnion(N), N, pairCount);
    }
    // output execute to see drawing
}

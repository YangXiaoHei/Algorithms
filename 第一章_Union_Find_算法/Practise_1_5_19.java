package 第一章_Union_Find_算法;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import edu.princeton.cs.algs4.*;
import static 第一章_Union_Find_算法.Practise_1_5_18.*;

public class Practise_1_5_19 {
    static class DepthWeightedQuickUnion {
        private int[] id;
        private int[] depth;
        DepthWeightedQuickUnion(int N) {
            id = new int[N];
            depth = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }
        int[] id() { return id; }
        boolean connected(int p, int q) { return find(p) == find(q); }
        int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;
        }
        void update(int p) {
            while (p != id[p]) {
                depth[id[p]]++;
                p = id[p];
            }    
        }
        void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if      (depth[pRoot] < depth[qRoot]) { id[pRoot] = qRoot; }
            else if (depth[pRoot] > depth[qRoot]) { id[qRoot] = pRoot; }
            else    { id[pRoot] = qRoot; update(pRoot); }
        }
    }
    public static void draw(int N) throws Exception {
        N = (int)Math.sqrt(N);
        StdDraw.setXscale(-2,  N);
        StdDraw.setYscale(-2, N);
        StdDraw.setPenRadius(.005);
        StdDraw.setPenColor(Color.BLACK);
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                StdDraw.point(j, i);
            }
        }
        DepthWeightedQuickUnion dwqu = new DepthWeightedQuickUnion(N * N);
        RandomBag<Connection> rb = Practise_1_5_18.randomGridGenerator(N);
        for (Connection c : rb) {
            if (dwqu.connected(c.p, c.q)) {
                StdOut.printf("{%d %d} 已连通\n", c.p, c.q);
                continue;
            }
            dwqu.union(c.p, c.q);
            StdDraw.setPenColor(Color.red);
            StdDraw.setPenRadius(0.003);
            //p 
            int x0 = c.p / N;
            int y0 = c.p % N;
            //q
            int x1 = c.q / N;
            int y1 = c.q % N;
            StdDraw.line(x0, y0, x1, y1);
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
    public static void main(String[] args) throws Exception {
        draw(1000);  
    }
    // output : execute to see drawing
}

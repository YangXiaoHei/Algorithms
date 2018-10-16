package Ch_4_1_Undirected_Graphs;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_14 {
    static class Graph {
        private __Bag<Integer> adjs[];
        private EG eg;
        private int E;
        private int V;
        Graph(int V) {
            eg = new EG(V);
            this.V = V;
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++) {
                adjs[i] = new __Bag<Integer>();
            }
        }
        public void genRandom(int count) {
            int i = 0;
            while (i++ < count)
                addEdge(eg.next());
        }
        Iterable<Integer> adj(int v) {
           return adjs[v];
        }
        public int V() { return V; }
        public int E() { return E; }
        public boolean hasEdge(int v, int w) {
            boolean isV = adjs[v].size() < adjs[w].size();
            if (isV) {
                for (int k : adj(v))
                    if (k == w)
                        return true;
            } else {
                for (int k : adj(w))
                    if (k == v)
                        return true;
            }
            return false;
        }
        void addEdge(Pair p) {
            addEdge(p.v, p.w);
        }
        void addEdge(int v, int w) {
            if (hasEdge(v, w))
                return;
            if (v == w)
                return;
            adjs[v].add(w);
            adjs[w].add(v);
            E++;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) {
                sb.append(i + ": " + adjs[i].toString() + "\n");
            }
            return sb.toString();
        }
    }
    public static int findRoot(int v, int[] ids) {
        while (v != ids[v])
            v = ids[v];
        return v;
    }
    public static int[] DFS1(Graph G, int s) {
        int[] edgeTo = new int[G.V()];
        for (int i = 0; i < G.V(); i++) 
            edgeTo[i] = i;
        boolean marked[] = new boolean[G.V()];
        __Stack<Integer> S = new __Stack<>();
        marked[s] = true;
        S.push(s);
        while (!S.isEmpty()) {
            s = S.pop();
            for (int w : G.adj(s)) {
                if (!marked[w]) {
                    edgeTo[w] = s;
                    marked[w] = true;
                    S.push(w);
                }
            }
        }
        return edgeTo;
    }
    public static int[] DFS2(Graph G, int s) {
        int[] edgeTo = new int[G.V()];
        for (int i = 0; i < G.V(); i++) 
            edgeTo[i] = i;
        boolean marked[] = new boolean[G.V()];
        __Queue<Integer> q = new __Queue<>();
        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            s = q.dequeue();
            for (int w : G.adj(s)) {
                if (!marked[w]) {
                    edgeTo[w] = s;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
        return edgeTo;
    }
    public static void analyseEdgeArray(int from, int[] edgeTo) {
        StdOut.println("---------------------------\n");
        __Stack<Integer> S = new __Stack<>();
        for (int i = 0; i < edgeTo.length; i++) {
            if (findRoot(i, edgeTo) != from) {
                StdOut.printf("%d 不能找到去往 %d 的路径\n", 0, i);
            } else {
                for (int v = i; v != from; v = edgeTo[v]) 
                    S.push(v);
                S.push(from);
                StringBuilder sb = new StringBuilder();
                while (!S.isEmpty())
                    sb.append(String.format("%d -> ", S.pop()));
                sb.delete(sb.length() - 4, sb.length());
                StdOut.printf("from %d to %d : %s\n", from, i, sb.toString());
            }
        }
        StdOut.println("\n---------------------------\n");
    }
    public static void main(String[] args) {
        Graph g = new Graph(13);
        g.genRandom(25);
        StdOut.println(g);
        
        int from = 0;
        int[] edgeTo1 = DFS1(g, from);
        int[] edgeTo2 = DFS2(g, from);
        
        analyseEdgeArray(from, edgeTo1);
        analyseEdgeArray(from, edgeTo2);
    }
    /*
     * output 
     * 
     *  0: 5 11 10 1 7 
        1: 9 0 
        2: 7 8 12 
        3: 12 9 
        4: 5 
        5: 0 6 4 
        6: 11 5 12 
        7: 2 9 12 0 
        8: 2 
        9: 1 7 3 
        10: 0 
        11: 6 0 
        12: 6 3 2 7 
        
        ---------------------------
        // 使用栈
        from 0 to 0 : 0
        from 0 to 1 : 0 -> 1
        from 0 to 2 : 0 -> 7 -> 2
        from 0 to 3 : 0 -> 7 -> 12 -> 3
        from 0 to 4 : 0 -> 5 -> 4
        from 0 to 5 : 0 -> 5
        from 0 to 6 : 0 -> 7 -> 12 -> 6 👈
        from 0 to 7 : 0 -> 7
        from 0 to 8 : 0 -> 7 -> 2 -> 8
        from 0 to 9 : 0 -> 7 -> 9
        from 0 to 10 : 0 -> 10
        from 0 to 11 : 0 -> 11
        from 0 to 12 : 0 -> 7 -> 12
        
        ---------------------------
        
        ---------------------------
        // 使用队列
        from 0 to 0 : 0
        from 0 to 1 : 0 -> 1
        from 0 to 2 : 0 -> 7 -> 2
        from 0 to 3 : 0 -> 1 -> 9 -> 3
        from 0 to 4 : 0 -> 5 -> 4
        from 0 to 5 : 0 -> 5
        from 0 to 6 : 0 -> 5 -> 6  👈
        from 0 to 7 : 0 -> 7
        from 0 to 8 : 0 -> 7 -> 2 -> 8
        from 0 to 9 : 0 -> 1 -> 9
        from 0 to 10 : 0 -> 10
        from 0 to 11 : 0 -> 11
        from 0 to 12 : 0 -> 7 -> 12
        
        ---------------------------
     */
}

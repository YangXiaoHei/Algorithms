package Ch_4_1_Undirected_Graphs;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_08 {
    static class Graph {
        private __Bag<Integer> adjs[];
        private int E;
        private int V;
        Graph(int V) {
            this.V = V;
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++) {
                adjs[i] = new __Bag<Integer>();
            }
        }
        Iterable<Integer> adj(int v) {
           return adjs[v];
        }
        public int V() { return V; }
        public int E() { return E; }
        void addEdge(int v, int w) {
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
    static class UF {
        private int ids[];
        private int count[];
        private int N;
        public UF(int N) {
            ids = new int[N];
            this.N = N;
            for (int i = 0; i < N; i++) {
                ids[i] = i;
            }
            count = new int[N];
            for (int i = 0; i < N; i++) {
                count[i] = 1;
            }
        }
        public int find(int v) {
            while (v != ids[v])
                v = ids[v];
            return v;
        }
        public int count(int v) {
            return count[v];
        }
        public int maxDepth() {
            int maxDepth = 0;
            int curDepth = 0;
            int v;
            for (int i = 0; i < N; i++) {
                v = i;
                curDepth = 0;
                while (v != ids[v]) {
                    v = ids[v];
                    curDepth++;
                }
                if (curDepth > maxDepth)
                    maxDepth = curDepth;
            }
            return maxDepth;
        }
        public boolean connected(int v, int w) {
            return find(v) == find(w);
        }
        public void union(int v, int w) {
            int p = find(v);
            int q = find(w);
            if (q == p) return;
            for (int i = 0; i < N; i++) 
                if (ids[i] == p) {
                    ids[i] = q;
                    count[q]++;
                }
        }
        public static String toString(int[] arr) {
            StringBuilder sb = new StringBuilder();
            sb.append("---------------------\n");
            for (int i = 0; i < arr.length; i++) {
                sb.append(String.format("%-4d", i));
            }
            sb.append("\n");
            for (int i = 0; i < arr.length; i++) {
                sb.append(String.format("%-4d", arr[i]));
            }
            sb.append("\n---------------------\n");
            return sb.toString();
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("MaxDepth = %d", maxDepth()));
            for (int i = 0; i < N; i++) {
                sb.append(String.format("%-4d", i));
            }
            sb.append("\n");
            for (int i = 0; i < N; i++) {
                sb.append(String.format("%-4d", ids[i]));
            }
            return sb.toString();
        }
    }
    
    static class Search {
        private UF uf;
        private int s;
        public Search(Graph G, int s) {
            uf = new UF(G.V());
            for (int i = 0; i < G.V(); i++) {
                for (int w : G.adj(i))
                    uf.union(i, w);
            }
            this.s = s;
        }
        public boolean marked(int v) {
            return uf.connected(s, v);
        }
        public int count() {
            return uf.count(uf.find(s));
        }
    }
    
    public static void main(String[] args) {
        Graph G = new Graph(13);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 6);
        G.addEdge(0, 5);
        G.addEdge(3, 5);
        G.addEdge(3, 4);
        G.addEdge(5, 4);
        G.addEdge(6, 4);
        G.addEdge(7, 8);
        G.addEdge(9, 10);
        G.addEdge(9, 11);
        G.addEdge(9, 12);
        G.addEdge(11, 12);
        int n = 5;
        Search S = new Search(G, n);
        for (int i = 0; i < G.V(); i++) {
            if (S.marked(i))
                StdOut.printf("%d connect %d\n", n, i);
        }
        StdOut.println(S.count());    
    }
    /*
     *  output
     *  
     *  5 connect 0
        5 connect 1
        5 connect 2
        5 connect 3
        5 connect 4
        5 connect 5
        5 connect 6
        7

     */
}

package Ch_4_1_Undirected_Graphs;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_10 {
    static class Graph {
        private __Bag<Integer> adjs[];
        private int adjMarkedCount[];
        private boolean marked[];
        private int E;
        private int V;
        Graph(int V) {
            this.V = V;
            marked = new boolean[V];
            adjMarkedCount = new int[V];
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++) {
                adjs[i] = new __Bag<Integer>();
            }
        }
        Iterable<Integer> adj(int v) {
           return adjs[v];
        }
        public void mark(int v) { 
            marked[v] = true;
            adjMarkedCount[v]++;
        }
        public boolean marked(int v) {  return marked[v]; }
        public boolean allMarked(int v) { return adjMarkedCount[v] == adjs[v].size(); }
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
        public static void DFS(Graph G, int s) {
            __Stack<Integer> S = new __Stack<>();
            G.mark(s);
            S.push(s);
            StdOut.println(s);
            while (!S.isEmpty()) {
                s = S.peek();
                boolean allMarked = true;
                for (int w : G.adj(s)) {
                    if (!G.marked(w)) {
                        allMarked = false;
                        G.mark(w);
                        S.push(w);
                        StdOut.println(w);
                        break;
                    }
                }
                if (allMarked)
                    S.pop();
            }
        }
        public static int DFSFindNode(Graph G, int s) {
            __Stack<Integer> S = new __Stack<>();
            G.mark(s);
            S.push(s);
            while (!S.isEmpty()) {
                s = S.peek();
                boolean allMarked = true;
                for (int w : G.adj(s)) {
                    if (!G.marked(w)) {
                        G.mark(w);
                        S.push(w);
                        allMarked = false;
                        break;
                    }
                }
                if (allMarked)
                    return s;
            }
            throw new RuntimeException("should not reach here");
        }
    }
    public static void main(String[] args) {
        /*
         * 在使用深搜遍历图的过程中，若发现某顶点的相邻所有顶点都被标记过
         * 说明即使不用途径该点，也可以通过其他路径到达这些邻居，
         * 因此删除这样的点不会影响图的连通性
         */
        Graph g = new Graph(13);
        g.addEdge(8, 4);
        g.addEdge(2, 3);
        g.addEdge(1, 11);
        g.addEdge(0, 6);
        g.addEdge(3, 6);
        g.addEdge(10, 3);
        g.addEdge(7, 11);
        g.addEdge(7, 8);
        g.addEdge(11, 8);
        g.addEdge(2, 0);
        g.addEdge(6, 2);
        g.addEdge(5, 2);
        g.addEdge(5, 10);
        g.addEdge(3, 10);
        g.addEdge(8, 1);
        g.addEdge(4, 1);
        StdOut.println(g);
        StdOut.println(g.DFSFindNode(g, 0));
    }
    /*
     * output
     * 
     * 10
     */
}

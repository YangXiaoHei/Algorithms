package Ch_4_1_Undirected_Graphs;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_13 {
    /*
     * 换一种图的实现方式
     * 下面采用邻接矩阵实现图结构
     */
    static class Graph {
        private boolean[][] edges;
        private int[] vertexs;
        private boolean marked[];
        int E;
        int V;
        Graph(int V) {
            this.V = V;
            vertexs = new int[V];
            edges = new boolean[V][];
            marked = new boolean[V];
            for (int i = 0; i < V; i++) {
                edges[i] = new boolean[V];
            }
        }
        int V() { return V; }
        int E() { return E; }
        boolean hasEdge(int v, int w) {
            return edges[v][w] || edges[w][v];
        }
        void addEdge(int v, int w) {
            if (hasEdge(v, w))
                return;
            if (v == w)
                return;
            edges[v][w] = true;
            edges[w][v] = true;
            E++;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("vertex: ");
            for (int i = 0; i < V(); i++)
                sb.append(i + " ");
            sb.append("\n");
            sb.append("edge: \n");
            sb.append(String.format("%-4s", " "));
            for (int i = 0; i < V(); i++) 
                sb.append(String.format("%-4d", i));
            sb.append("\n");
            for (int i = 0; i < V(); i++) {
                sb.append(String.format("%-4d", i));
                for (int j = 0; j < V(); j++) 
                    sb.append(String.format("%-4s", edges[i][j] ? "X" : "0"));
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
        Iterable<Integer> adj(int v) {
            return new Iterable<Integer>() {
               public Iterator<Integer> iterator() {
                   return new Iterator<Integer>() {
                       int cur = 0;
                       public boolean hasNext() {
                           while (cur < V && !edges[v][cur]) cur++;
                           return cur < V;
                       }
                       public Integer next() {
                           return cur++;
                       }
                   };
               }
            };
        }
    }
    static class BFS {
        int[] dis;
        boolean[] marked;
        public BFS(Graph G, int s) {
            dis = new int[G.V()];
            marked = new boolean[G.V()];
            __Queue<Integer> q = new __Queue<>();
            marked[s] = true;
            q.enqueue(s);
            while (!q.isEmpty()) {
                int v = q.dequeue();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        marked[w] = true;
                        q.enqueue(w);
                        dis[w] = dis[v] + 1;
                    }
                }
            }
        }
        public int disTo(int v) {
            return dis[v];
        }
    }
    public static void main(String[] args) {
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
        BFS bfs = new BFS(g, 0);
        StdOut.println("---------");
        for (int i = 0; i < g.V(); i++) {
            int dis = bfs.disTo(i);
            if (dis == 0) {
                StdOut.printf("%d cannot find path to %d\n", 0, i);
            } else {
                StdOut.printf("%d -> %d dis: %d\n", 0, i, dis);
            }
        }
    }
    /*
     * output
     * 
     *  ---------
        0 cannot find path to 0
        0 cannot find path to 1
        0 -> 2 dis: 1
        0 -> 3 dis: 2
        0 cannot find path to 4
        0 -> 5 dis: 2
        0 -> 6 dis: 1
        0 cannot find path to 7
        0 cannot find path to 8
        0 cannot find path to 9
        0 -> 10 dis: 3
        0 cannot find path to 11
        0 cannot find path to 12
     */
}

package Ch_4_1_Undirected_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class ___Graph_BFS_DFS {
    static class Graph {
        private __Bag<Integer>[] bag;
        private int V;
        private int E;
        public Graph(int V) {
            this.V = V;
            bag = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++)
                bag[i] = new __Bag<Integer>();
        }
        public void addEdge(int v, int w) {
            bag[v].add(w);
            bag[w].add(v);
            E++;
        }
        public Iterable<Integer> adj(int v) {
            return bag[v];
        }
        public int V() { return V; }
        public int E() { return E; }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) {
                sb.append(i + ": ");
                for (int w : bag[i])
                    sb.append(String.format("%-4d", w));
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
    }
    public static void DFS(Graph G, int v) {
        boolean marked[] = new boolean[G.V()];
        _DFS(G, v, marked);
    }
    public static void _DFS(Graph G, int v, boolean[] marked) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                _DFS(G, w, marked);
    }
    public static void BFS(Graph G, int v) {
        boolean marked[] = new boolean[G.V()];
        _BFS(G, v, marked);
    }
    public static void _BFS(Graph G, int s, boolean[] marked) {
        __Queue<Integer> q = new __Queue<>();
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
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
        StdOut.println(G);
    }
}

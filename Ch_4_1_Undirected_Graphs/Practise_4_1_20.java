package Ch_4_1_Undirected_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_20 {
    static class Graph {
        public int V;
        public int E;
        private EG eg;
        public __Bag<Integer>[] adjs;
        private Pair[] tmpPairs;
        public Graph(int V) {
            this.V = V;
            eg = new EG(V);
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++)
                adjs[i] = new __Bag<Integer>();
        }
        public int V() { return V; }
        public int E() { return E; }
        public void addEdge(int v, int w) {
            if (v == w || hasEdge(v, w))
                return;
            adjs[v].add(w);
            adjs[w].add(v);
            E++;
        }
        boolean hasEdge(int v, int w) {
            return adjs[v].contains(w);
        }
        void genRandom(int edgeCount) {
            int i = 0, n = edgeCount;
            tmpPairs = new Pair[edgeCount];
            while (edgeCount-- > 0) {
                tmpPairs[i] = eg.next();
                addEdge(tmpPairs[i].v, tmpPairs[i].w);
                i++;
            }
            for (int j = 0; j < n; j++)
                StdOut.println(tmpPairs[j]);
            StdOut.println("-------------------");
        }
        public Iterable<Integer> adjs(int v) {
            return adjs[v];
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) 
                sb.append(i + ": " + adjs[i].toString() + "\n");
            return sb.toString();
        }
    }
    static class TwoColor {
        private boolean[] marked;
        private boolean isTwoColorable;
        private boolean colors[];
        private Graph g;
        TwoColor(Graph g) {
            colors = new boolean[g.V()];
            marked = new boolean[g.V()];
            isTwoColorable = true;
            this.g = g;
            for (int i = 0; i < g.V(); i++)
                if (!marked[i])
                    DFS(i);
        }
        void DFS(int v) {
            marked[v] = true;
            for (int w : g.adjs(v))
                if (!marked[w]) {
                    colors[w] = !colors[v];
                    DFS(w);
                } else if (colors[w] == colors[v])
                    isTwoColorable = false;
        }
        boolean isTwoColorable() {
            return isTwoColorable;
        }
    }
    public static void main(String[] args) {
        Graph g = new Graph(4);
//        g.addEdge(8, 4);
//        g.addEdge(2, 3);
//        g.addEdge(1, 11);
//        g.addEdge(0, 6);
//        g.addEdge(3, 6);
//        g.addEdge(10, 3);
//        g.addEdge(7, 11);
//        g.addEdge(7, 8);
//        g.addEdge(11, 8);
//        g.addEdge(2, 0);
//        g.addEdge(6, 2);
//        g.addEdge(5, 2);
//        g.addEdge(5, 10);
//        g.addEdge(5, 0);
//        g.addEdge(8, 1);
//        g.addEdge(4, 1);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        StdOut.println(g);
        
        TwoColor t = new TwoColor(g);
        StdOut.println(t.isTwoColorable());
        
    }
}

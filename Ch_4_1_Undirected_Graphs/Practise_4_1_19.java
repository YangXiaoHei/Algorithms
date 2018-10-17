package Ch_4_1_Undirected_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_19 {
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
    static class Cycle {
        private Graph g;
        private boolean hasCycle;
        private boolean marked[];
        Cycle(Graph g) {
            this.g = g;
            marked = new boolean[g.V()];
           for (int i = 0; i < g.V(); i++)
               if (!marked[i])
                   DFS(i, i);
        }
        private void DFS(int v, int w) {
            marked[v] = true;
            StdOut.println(String.format("v = %d w = %d", v, w));
            for (int s : g.adjs(v))
                if (!marked[s]) {
                    DFS(s, v);
                    StdOut.printf("current %d\n", s);
                }
                else if (s != w) hasCycle = true;
        }
    }
    public static void main(String[] args) {
        /*
         * 见 README.md
         */
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
//        g.addEdge(2, 3);
        g.addEdge(3, 1);
        StdOut.println(g);
        
        Cycle c = new Cycle(g);
        StdOut.println(c.hasCycle);
    }
}

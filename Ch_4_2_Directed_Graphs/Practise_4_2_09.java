package Ch_4_2_Directed_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_2_09 {
    static class Digraph {
        private __Bag<Integer> adjs[];
        private int E;
        private int V;
        private EG eg;
        public Digraph(int V) {
            this.eg = new EG(V);
            this.V = V;
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++)
                adjs[i] = new __Bag<>();
        }
        public void genOneWayRandom(int count) {
            while (count-- > 0) {
                Pair p = eg.next();
                addEdge(p.v, p.w);
            }
        }
        public Iterable<Integer> adj(int v) {
            return adjs[v];
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++)
                sb.append(String.format("%d : %s\n", i, adjs[i]));
            sb.append("\n");
            return sb.toString();
        }
        public void addEdge(int v, int w) {
            if (v == w || hasEdge(v, w))
                return;
            adjs[v].add(w);
            E++;
        }
        public boolean hasEdge(int v, int w) {
            return adjs[v].contains(w);
        }
    }
    static class CycleFinder {
        private boolean marked[];
        private Digraph g;
        private boolean onStack[];
        private boolean hasCycle;
        public CycleFinder(Digraph g) {
            marked = new boolean[g.V];
            onStack = new boolean[g.V];
            this.g = g;
            for (int i = 0; i < g.V; i++)
                if (!marked[i])
                    dfs(i);
        }
        private void dfs(int v) {
            marked[v] = true;
            onStack[v] = true;
            for (int w : g.adj(v)) {
                if (hasCycle) return;
                if (!marked[w])
                    dfs(w);
                else if (onStack[w]) 
                    hasCycle = true;
            }
            onStack[v] = false;
        }
        
    }
    static class TopoSort {
        private __Stack<Integer> topo;
        private Digraph g;
        private boolean marked[];
        public TopoSort(Digraph g) {
            this.g = g;
            marked = new boolean[g.V];
            topo = new __Stack<>();
            for (int i = 0; i < g.V; i++)
                if (!marked[i])
                    dfs(i);
        }
        private void dfs(int v) {
            marked[v] = true;
            for (int w : g.adj(v)) 
                if (!marked[w]) 
                    dfs(w);
            topo.push(v);
        }
        public int[] sort() {
            int[] sort = new int[topo.size()];
            int i = 0;
            for (int w : topo)
                sort[i++] = w;
            return sort;
        }
        public boolean isTopSort(int[] a) {
            for (int i = 0; i < a.length; i++) 
                for (int j = i + 1; j < a.length; j++) 
                    if (g.adjs[a[j]].contains(a[i]))
                        return false;
            return true;
        }
    }
    public static void main(String[] args) {
        Digraph g = new Digraph(13);
//        dg.genOneWayRandom(13);
          g.addEdge(0, 1);
          g.addEdge(0, 5);
          g.addEdge(0, 6);
          g.addEdge(2, 0);
          g.addEdge(2, 3);
          g.addEdge(3, 5);
          g.addEdge(5, 4);
          g.addEdge(6, 4);
          g.addEdge(6, 9);
          g.addEdge(7, 6);
          g.addEdge(8, 7);
          g.addEdge(9, 11);
          g.addEdge(9, 12);
          g.addEdge(9, 10);
          g.addEdge(11, 12);
        TopoSort ts = new TopoSort(g);
        for (int w : ts.sort())
            StdOut.print(w + " ");
        StdOut.println();
        
        int[] sequence1 = { 8, 7, 2, 3, 0, 6, 9, 10, 11, 12, 1, 5, 4 };
        int[] sequence2 = { 2, 3, 8, 7, 0, 6, 9, 10, 11, 12, 1, 5, 4 };
        int[] sequence3 = { 3, 2, 8, 7, 0, 6, 9, 10, 11, 12, 1, 5, 4 };
        StdOut.println(ts.isTopSort(sequence1));
        StdOut.println(ts.isTopSort(sequence2));
        StdOut.println(ts.isTopSort(sequence3));
    }
    // output
    /*
     * 8 7 2 3 0 6 9 10 11 12 5 4 1 
        true
        true
        false

     */
}

package Ch_4_2_Directed_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_2_13 {
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
    static class DirectedDFS {
        private boolean marked[];
        private Digraph g;
        public DirectedDFS(Digraph g, int v) {
            marked = new boolean[g.V];
            this.g = g;
            dfs(v);
        }
        private void dfs(int v) {
            marked[v] = true;
            for (int w : g.adj(v))
                if (!marked[w])
                    dfs(w);
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < marked.length; i++)
                sb.append(String.format("%-3c", marked[i] ? 'T' : ' '));
            sb.append("\n");
            return sb.toString();
        }
    }
    static class TransitiveClosure {
        private DirectedDFS[] all;
        public TransitiveClosure(Digraph g) {
            all = new DirectedDFS[g.V];
            for (int i = 0; i < g.V; i++)
                all[i] = new DirectedDFS(g, i);
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-3c", ' '));
            for (int i = 0; i < all.length; i++)
                sb.append(String.format("%-3d", i));
            sb.append("\n");
            for (int i = 0; i < all.length; i++) 
                sb.append(String.format("%-3d%s", i, all[i]));
            return sb.toString();
        }
        public boolean reachable(int v, int w) {
            return all[v].marked[w];
        }
    }
    public static void main(String[] args) {
        Digraph dg = new Digraph(10);
        dg.addEdge(3, 7);
        dg.addEdge(1, 4);
        dg.addEdge(7, 8);
        dg.addEdge(0, 5);
        dg.addEdge(5, 2);
        dg.addEdge(3, 8);
        dg.addEdge(2, 9);
        dg.addEdge(0, 6);
        dg.addEdge(4, 9);
        dg.addEdge(2, 6);
        dg.addEdge(6, 4);
        StdOut.println(dg);
        
        TransitiveClosure tc = new TransitiveClosure(dg);
        StdOut.println(tc);
    }
    //output
    /*
     *  0 : 5 6 
        1 : 4 
        2 : 9 6 
        3 : 7 8 
        4 : 9 
        5 : 2 
        6 : 4 
        7 : 8 
        8 : 
        9 : 
        
        
           0  1  2  3  4  5  6  7  8  9  
        0  T     T     T  T  T        T  
        1     T        T              T  
        2        T     T     T        T  
        3           T           T  T     
        4              T              T  
        5        T     T  T  T        T  
        6              T     T        T  
        7                       T  T     
        8                          T     
        9                             T  

     */
}

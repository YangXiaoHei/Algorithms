package Ch_4_2_Directed_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_2_03 {
    static class Digraph {
        private __Bag<Integer> adjs[];
        private int E;
        private int V;
        public Digraph(Digraph dg) {
            this.V = dg.V;
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++)
                adjs[i] = new __Bag<Integer>();
            for (int i = 0; i < V; i++)
                for (int w : dg.adjs(i))
                    addEdge(i, w);
        }
        public Digraph(int V) {
            this.V = V;
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++)
                adjs[i] = new __Bag<Integer>();
        }
        public boolean hasEdge(int v, int w) {
            return adjs[v].contains(w);
        }
        public void addEdge(int v, int w) {
            if (v == w || hasEdge(v, w))
                return;
            adjs[v].add(w);
            E++;
        }
        
        public Iterable<Integer> adjs(int v) {
            return adjs[v];
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++)
                sb.append(String.format("%d : %s\n", i, adjs[i]));
            sb.append("\n");
            return sb.toString();
        }
        public void genRandom(int count) {
            EG eg = new EG(V);
            while (count-- > 0) {
                Pair p = eg.next();
                addEdge(p.v, p.w);
            }
        }
    }
    public static void main(String[] args) {
        Digraph g = new Digraph(10);
        g.genRandom(15);
        StdOut.println(g);
        StdOut.println("--------------");
        
        Digraph copyG = new Digraph(g);
        StdOut.println(copyG);
    }
    /*
     *  output
     *  
     *  0 : 5 8 
        1 : 
        2 : 3 
        3 : 
        4 : 7 
        5 : 7 4 
        6 : 1 
        7 : 6 8 5 
        8 : 0 5 
        9 : 7 
        
        
        --------------
        0 : 5 8 
        1 : 
        2 : 3 
        3 : 
        4 : 7 
        5 : 7 4 
        6 : 1 
        7 : 6 8 5 
        8 : 0 5 
        9 : 7 

     */
}

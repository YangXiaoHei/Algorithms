package Ch_4_2_Directed_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_2_07 {
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
    static class Degress {
        private boolean isMap;
        private Digraph g;
        private boolean marked[];
        private __Queue<Integer> sources;
        private __Queue<Integer> sinks;
        static class Wrapper {
            int outdegree;
            int indegree;
        }
        private Wrapper[] ws;
        public Degress(Digraph g) {
            this.g = g;
            marked = new boolean[g.V];
            sinks = new __Queue<>();
            sources = new __Queue<>();
            ws = new Wrapper[g.V];
            for (int i = 0; i < g.V; i++)
                ws[i] = new Wrapper();
            
            for (int i = 0; i < g.V; i++)
                if (!marked[i])
                    dfs(i);
            
            isMap = true;
            for (int j = 0; j < g.V; j++) {
                if (ws[j].indegree == 0)
                    sources.enqueue(j);
                if (ws[j].outdegree == 0)
                    sinks.enqueue(j);
                
                if (ws[j].outdegree != 1)
                    isMap = false;
            }
        }
        public void dfs(int v) {
            marked[v] = true;
            for (int w : g.adj(v)) {
                ws[w].indegree++;
                ws[v].outdegree++;
                if (!marked[w])
                    dfs(w);
            } 
        }
        public int indegree(int v) {
            return ws[v].indegree;
        }
        public int outdegree(int v) {
            return ws[v].outdegree;
        }
        public boolean isMap() {
            return isMap;
        }
        public Iterable<Integer> sources() {
            return sources;
        }
        public Iterable<Integer> sinks() {
            return sinks;
        }
       
    }
    public static void main(String[] args) {
        Digraph g = new Digraph(10);
        g.genOneWayRandom(15);
        StdOut.println(g);
        
        Degress d = new Degress(g);
        StdOut.println("-------------- indegree ---------------");
        for (int i = 0; i < g.V; i++)
            StdOut.printf("%d : %d\n", i, d.indegree(i));
        StdOut.println("-------------- outdegree ---------------");
        for (int i = 0; i < g.V; i++)
            StdOut.printf("%d : %d\n", i, d.outdegree(i));
        StdOut.println("-------------- sources ---------------");
        StdOut.println(d.sources());
        StdOut.println("-------------- sinks ---------------");
        StdOut.println(d.sinks());
        StdOut.println("-------------- isMap ---------------");
        StdOut.println(d.isMap());
    }
    // output
    /*
     *  0 : 1 8 
        1 : 2 0 
        2 : 5 
        3 : 1 0 
        4 : 7 
        5 : 0 
        6 : 
        7 : 1 6 3 
        8 : 4 
        9 : 
        
        
        -------------- indegree ---------------
        0 : 3
        1 : 3
        2 : 1
        3 : 1
        4 : 1
        5 : 1
        6 : 1
        7 : 1
        8 : 1
        9 : 0
        -------------- outdegree ---------------
        0 : 2
        1 : 2
        2 : 1
        3 : 2
        4 : 1
        5 : 1
        6 : 0
        7 : 3
        8 : 1
        9 : 0
        -------------- sources ---------------
        |   9 |           <<<<<<<<<< size : 1 head : 0 tail : 0 cap : 1 >>>>>>>>>>
        -------------- sinks ---------------
        |   6 |   9 |           <<<<<<<<<< size : 2 head : 0 tail : 0 cap : 2 >>>>>>>>>>
        -------------- isMap ---------------
        false
     */
}

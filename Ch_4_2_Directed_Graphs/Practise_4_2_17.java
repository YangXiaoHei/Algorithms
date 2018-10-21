package Ch_4_2_Directed_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_2_17 {
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
        public int V() { return V; }  
        public Digraph reverse() {
            Digraph g = new Digraph(V);
            for (int i = 0; i < V; i++)
                for (int w : adj(i))
                    g.addEdge(w, i);
            return g;
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
    static class DepthFirstOrder {
        private boolean[] marked;
        private __Queue<Integer> pre;
        private __Queue<Integer> post;
        private __Stack<Integer> reversePost;
        public DepthFirstOrder(Digraph g) {
            pre = new __Queue<>();
            post = new __Queue<>();
            reversePost = new __Stack<>();
            marked = new boolean[g.V()];
            for (int i = 0; i < g.V(); i++)
                if (!marked[i])
                    dfs(g, i);
        }
        private void dfs(Digraph g, int v) {
            marked[v] = true;
            pre.enqueue(v);
            for (int w : g.adj(v)) 
                if  (!marked[w])
                    dfs(g, w);
            post.enqueue(v);
            reversePost.push(v);
        }
        public Iterable<Integer> pre() { return pre; }
        public Iterable<Integer> post() { return post; }
        public Iterable<Integer> reversePost() { return reversePost; }
    }
    public static void main(String[] args) {
        Digraph dg = new Digraph(4);
        dg.genOneWayRandom(4);
        StdOut.println(dg);
        StdOut.println(dg.reverse());
        
        DepthFirstOrder df = new DepthFirstOrder(dg.reverse());
        DepthFirstOrder df1 = new DepthFirstOrder(dg);
        StdOut.printf("逆后序 : %s\n", df.reversePost());
        StdOut.printf("后序 : %s\n", df1.post());
    }
    // output
    /*
     *  0 : 3 
        1 : 3 
        2 : 
        3 : 
        
        
        0 : 
        1 : 
        2 : 
        3 : 0 1 
        
        
        逆后序 : 3 2 1 0 
        后序 : 3 0 1 2 
     */
}

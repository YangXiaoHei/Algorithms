package Ch_4_2_Directed_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class __DiGraph {
    private __Bag<Integer> adjs[];
    private int E;
    private int V;
    __DiGraph(int V) {
        this.V = V;
        adjs = (__Bag<Integer>[])new __Bag[V];
        for (int i = 0; i < V; i++)
            adjs[i] = new __Bag<Integer>();
    }
    public int E() { return E; }
    public int V() { return V; }
    public Iterable<Integer> cycle() {
        DirectedCycle dc = new DirectedCycle(this);
        if (dc.hasCycle())
            return dc.cycle();
        else
            return null;
    }
    public void genRandom(int count) {
        EG eg = new EG(V);
        while (count-- > 0) {
            Pair p = eg.next();
            addEdge(p.v, p.w);
        }
    }
    public void genOneWayRandom(int count) {
        EG eg = new EG(V);
        while (count-- > 0) {
            Pair p = eg.next();
            if (adjs[p.v].contains(p.w) || adjs[p.w].contains(p.v))
                continue;
            addEdge(p.v, p.w);
        }
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
    static class DirectedCycle {
        private boolean[] marked;
        private int[] edgeTo;
        private __Stack<Integer> cycle;
        private boolean[] onStack;
        public DirectedCycle(__DiGraph G) {
            onStack = new boolean[G.V()];
            edgeTo = new int[G.V()];
            marked = new boolean[G.V()];
            for (int v = 0; v < G.V(); v++)
                if (!marked[v]) {
                    DFS(G, v);
                    StdOut.println("------" + v);
                }
        }
        public void DFS(__DiGraph g, int v) {
            onStack[v] = true;
            marked[v] = true;
            StdOut.println("**********" + v);
            for (int w : g.adj(v)) {
                if (hasCycle()) return;
                if (!marked[w]) {
                    edgeTo[w] = v;
                    DFS(g, w);
                } else if (onStack[w]) {
                    cycle = new __Stack<Integer>();
                    for (int x = v; x != w; x = edgeTo[x])
                        cycle.push(x);
                    
                    cycle.push(w);
                    cycle.push(v);
                }
            }
            StdOut.printf("将 %d 清理出栈\n", v);
            onStack[v] = false;
        }
        public boolean hasCycle() {
            return cycle != null;
        }
        public Iterable<Integer> cycle() {
            return cycle;
        }
    }
    /*
     *  0 : 8 
        1 : 6 7 
        2 : 4 9 
        3 : 
        4 : 3 6 
        5 : 9 1 2 
        6 : 0 5 
        7 : 2 3 
        8 : 3 5 
        9 : 1 4 8 6 
     */
    public static void main(String[] args) {
        __DiGraph g = new __DiGraph(10);
//        g.genOneWayRandom(30);
        g.addEdge(0, 8);
        g.addEdge(1, 6);
        g.addEdge(1, 7);
        g.addEdge(2, 4);
        g.addEdge(2, 9);
        g.addEdge(4, 3);
        g.addEdge(4, 6);
        g.addEdge(5, 9);
        g.addEdge(5, 1);
        g.addEdge(5, 2);
        g.addEdge(6, 0);
        g.addEdge(6, 5);
        g.addEdge(7, 2);
        g.addEdge(7, 3);
        g.addEdge(8, 3);
        g.addEdge(8, 5);
        g.addEdge(9, 1);
        g.addEdge(9, 4);
        g.addEdge(9, 8);
        g.addEdge(9, 6);
        
        StdOut.println(g);
        
        DirectedCycle dc = new DirectedCycle(g);
        if (dc.hasCycle())
            for (int w : dc.cycle)
                StdOut.println(w);
    }
    /*
     *  0 : 8 
        1 : 6 7 
        2 : 4 9 
        3 : 
        4 : 3 6 
        5 : 9 1 2 
        6 : 0 5 
        7 : 2 3 
        8 : 3 5 
        9 : 1 4 8 6 
        
        **********0
        **********8
        **********3
        将 3 清理出栈
        **********5
        **********9
        **********1
        **********6
        将 8 清理出栈
        将 0 清理出栈
        ------0
        **********2
        ------2
        **********4
        ------4
        **********7
        ------7
        6
        0
        8
        5
        9
        1
        6
     */
}

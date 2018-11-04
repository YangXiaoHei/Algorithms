package Review;

import edu.princeton.cs.algs4.StdOut;

public class TarjanSCC {

    private boolean[] marked;        // marked[v] = has v been visited?
    private int[] id;                // id[v] = id of strong component containing v
    private int[] low;               // low[v] = low number of v
    private int pre;                 // preorder number counter
    private int count;               // number of strongly-connected components
    private _Stack<Integer> stack;

    public TarjanSCC(Digraph G) {
        marked = new boolean[G.V()];
        stack = new _Stack<Integer>();
        id = new int[G.V()]; 
        low = new int[G.V()];
        for (int v = 0; v < G.V(); v++) 
            if (!marked[v]) 
                dfs(G, v);
        assert check(G);
    }

    private void dfs(Digraph G, int v) { 
        marked[v] = true;
        low[v] = pre++;
        int min = low[v];
        stack.push(v);
        StdOut.printf("%d 入栈，min = %d 打印栈: %s\n", v, min, stack);
        for (int w : G.adj(v)) {
            if (!marked[w]) 
                dfs(G, w);
            StdOut.printf("邻边 w = %d\n", w);
            // min = MIN(low[w], min)
            if (low[w] < min) {
                StdOut.printf("min 修改为 %d\n", low[w]);
                min = low[w];
            } 
        }
        
        // low[v] = MIN(low[v], min)
        if (min < low[v]) {
            StdOut.printf("跳出遍历邻边的循环，此时 low[%d] 从 %d 修改为 %d\n",v, low[v], min);
            low[v] = min;
            return;
        } 
        
        int w;
        do {
            w = stack.pop();
            StdOut.printf("pop:%d\n", w);
            id[w] = count;
            low[w] = G.V();
            StdOut.printf("low[%d] 修改为 %d\n", w, G.V());
        } while (w != v);
        count++;
    }
    public int count() {
        return count;
    }
    public boolean stronglyConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }
    public int id(int v) {
        validateVertex(v);
        return id[v];
    }
    private boolean check(Digraph G) {
        TransitiveClosure tc = new TransitiveClosure(G);
        for (int v = 0; v < G.V(); v++) {
            for (int w = 0; w < G.V(); w++) {
                if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
                    return false;
            }
        }
        return true;
    }
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    public static void main(String[] args) {
        Digraph g = DigraphGenerator.normal(8, 10);
//        Digraph g = new Digraph("{ 4 5 } { 5 7 } { 7 0 } { 7 8 } { 3 8 } { 7 1 } { 8 0 } "
//                              + "{ 2 1 } { 6 7 } { 5 7 } { 0 6 } { 3 7 } { 5 2 } { 3 6 } "
//                              + "{ 6 8 } { 1 3 } { 1 7 } { 9 2 } { 3 2 } { 8 6 } { 4 1 } "
//                              + "{ 4 0 } { 9 9 } { 3 0 } { 2 4 } { 5 0 } { 5 7 } { 3 7 } "
//                              + "{ 6 0 } { 7 8 }");
//        Digraph g = new Digraph("{ 6 5 } { 3 6 } { 0 6 } { 6 0 } { 2 6 } "
//                              + "{ 4 3 } { 7 2 } { 7 2 } { 3 5 } { 5 4 } ");
        
        // 4 连通分量: { 5 3 } { 4 1 } { 6 5 } { 0 7 } { 3 0 } { 1 6 } { 3 5 } { 3 6 } { 6 4 } { 4 6 } 
        // 3 连通分量: { 5 4 } { 0 6 } { 3 5 } { 6 7 } { 2 4 } { 5 1 } { 1 0 } { 3 6 } { 6 5 } { 4 3 } 
        // 4 连通分量: { 7 1 } { 0 7 } { 0 0 } { 6 4 } { 1 6 } { 7 6 } { 3 0 } { 1 7 } { 6 0 } { 0 3 } 
        StdOut.println(g);
        TarjanSCC scc = new TarjanSCC(g);
        StdOut.print("强连通分量的个数: " + scc.count());
    }
}

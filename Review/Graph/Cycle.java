package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class Cycle {
    private Graph g;
    private int edgeTo[];
    private boolean marked[];
    private _Stack<Integer> cycle;
    public Cycle(Graph g) {
        this.g = g;
    }
    /* 检测环 */
    public boolean hasCycle() {
        if (cycle != null) return true;
        if (hasSelfLoop()) return true;
        if (hasParallelEdge()) return true;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(-1, 0);
        return cycle != null;
    }
    private void dfs(int u, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) return;
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(v, w);
            } else if (u != w) {  /* 回头瞅一眼，这不是劳资刚来的路吧？啊西！果然是个环 */
                cycle = new _Stack<>();
                for (int i = v; i != w; i = edgeTo[i]) 
                    cycle.push(i);
                cycle.push(w);
                cycle.push(v);
                return;
            }
        }
    }
    public Iterable<Integer> cycle() {
        if (!hasCycle())
            throw new RuntimeException("no cycle graph");
        return cycle;
    }
    
    /* 检测平行边 */
    private boolean hasParallelEdge() {
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            for (int w : g.adj(i)) {
                /*
                 * 如果有平行边，那么遍历邻接点时会
                 * 标记同一个顶点两次
                 */
                if (marked[w]) {
                    cycle = new _Stack<>();
                    cycle.push(i);
                    cycle.push(w);
                    cycle.push(i);
                    return true;
                }
                marked[w] = true;
            }
            for (int w : g.adj(i))
                marked[w] = false;
        }
        return false;
    }
    /* 检测自环 */
    private boolean hasSelfLoop() {
        for (int i = 0; i < g.V(); i++) {
            for (int w : g.adj(i)) {
                if (w == i) {
                    cycle = new _Stack<>();
                    cycle.push(w);
                    cycle.push(w);
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
//        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        StdOut.println(g);
        Cycle c = new Cycle(g);
        if (c.hasCycle())
            for (int w : c.cycle())
                StdOut.print(w + " ");
    }
}

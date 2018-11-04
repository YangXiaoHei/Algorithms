package Review;

import edu.princeton.cs.algs4.StdOut;

public class TopologicalX {
    private _Queue<Integer> order;
    private int[] rank;
    public TopologicalX(Digraph g) {
        int count = 0;
        rank = new int[g.V()];
        _Queue<Integer> queue = new _Queue<>();
        int[] indegree = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            indegree[i] = g.indegree(i);
            if (indegree[i] == 0)
                queue.enqueue(i);
        }
        
        order = new _Queue<>();
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            order.enqueue(v);
            rank[v] = count++;
            for (int w : g.adj(v))
                if (--indegree[w] == 0)
                    queue.enqueue(w);
        }
        if (order.size() != g.V()) 
            order = null;
    }
    public boolean hasOrder() { return order != null; }
    public Iterable<Integer> order() {
        if (!hasOrder())
            throw new RuntimeException("order not exist!");
        return order;
    }
    public static void main(String[] args) {
        Digraph g = DigraphGenerator.DAG(10, 20);
        StdOut.println(g);
        
        Topological tp = new Topological(g);
        StdOut.print("拓扑排序: ");
        if (tp.hasOrder())
            for (int w : tp.order())
                StdOut.print(w + " ");
        StdOut.println();
        
        TopologicalX tpx = new TopologicalX(g);
        StdOut.print("拓扑排序-优化: ");
        if (tpx.hasOrder())
            for (int w : tpx.order())
                StdOut.print(w + " ");
    }
}

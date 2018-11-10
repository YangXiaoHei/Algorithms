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
        /*
         * 收集所有入度为 0 的顶点
         */
        for (int i = 0; i < g.V(); i++) {
            indegree[i] = g.indegree(i);
            if (indegree[i] == 0)
                queue.enqueue(i);
        }
        /*
         * 逐个删除入度为 0 的点，并遍历其邻接点将其入度减 1
         * 如果发现新的入度减为 1 的点，再将其加入队列
         * 如此循环直到队列为空
         */
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

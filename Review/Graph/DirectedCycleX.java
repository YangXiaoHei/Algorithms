package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class DirectedCycleX {
    private _Stack<Integer> cycle;
    public DirectedCycleX(Digraph g) {
        _Queue<Integer> q = new _Queue<>();
        /*
         * 把所有入度为 0 的点收集起来
         */
        int[] indegree = new int[g.V()];
        for (int i = 0; i < g.V(); i++)
            indegree[i] = g.indegree(i);
        for (int i = 0; i < g.V(); i++)
            if (g.indegree(i) == 0)
                q.enqueue(i);
        
        /*
         * 删掉入度为 0 的点，必然会导致它所有邻接点的入度减 1
         * 若邻接点入度减为 0，则再以此开始将其邻接点入度减 1
         * 直到连锁反应停止
         */
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) 
                if (--indegree[w] == 0)
                    q.enqueue(w);
        }
        
        int root = -1;
        int[] edgeTo = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            
            /* 忽略所有入度为 0 的点，
             * 上面只是用 indegree 数组记录
             * 并没有真的删除那些顶点 */
            if (indegree[i] == 0)
                continue;
            
            /*
             * 没有 continue 掉的顶点 i 至少被一条边所指
             */
            
            /* 连接 i -> w，并始终用最新的 i -> w 替换旧的 i -> w
             * 保证只有一条边指向 w，这样最后回退时才不会有歧义 */
            root = i;
            for (int w : g.adj(root)) 
                if (indegree[w] > 0)
                    edgeTo[w] = root;
        }
     
        /* 如果没有环 */
        if (root == -1) return;
        
        /*
         * 回退到链表中出现环的第一个顶点
         */
        boolean[] visited = new boolean[g.V()];
        while (!visited[root]) {
            visited[root] = true;
            root = edgeTo[root];
        }
        
        /*
         * 收集环中的所有顶点
         */
        cycle = new _Stack<>();
        int i = root;
        do {
            cycle.push(i);
            i = edgeTo[i];
        } while (i != root);
        cycle.push(root);
    }   
    public boolean hasCycle() { return cycle != null; }
    public Iterable<Integer> cycle() { return cycle; }
    public static void main(String[] args) {
        Digraph g = DigraphGenerator.simple(10, 15);
        StdOut.println(g);
        
        DirectedCycleX cx = new DirectedCycleX(g);
        if (cx.hasCycle()) {
            StdOut.print("DirectedCycleX : ");
            for (int w : cx.cycle())
                StdOut.print(w + " ");
        } else {
            StdOut.print("DirectedCycleX not find cycle!");
        }
        StdOut.println();

        DirectedCycle c = new DirectedCycle(g);
        if (c.hasCycle()) {
            StdOut.print("DirectedCycle : ");
            for (int w : c.cycle())
                StdOut.print(w + " ");
        } else {
            StdOut.print("DirectedCycle not find cycle!");
        }
        StdOut.println();
    }
}

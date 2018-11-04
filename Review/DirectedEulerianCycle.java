package Review;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class DirectedEulerianCycle {
    private _Stack<Integer> cycle;
    public DirectedEulerianCycle(Digraph g) {
        /*
         * 对于欧拉环中的每个顶点来说，入度必然等于出度
         * 若有不相等的，则一定不可能构成欧拉环
         */
        for (int i = 0; i < g.V(); i++)
            if (g.outdegree(i) != g.indegree(i))
                return;
        
        cycle = new _Stack<>();
        
        @SuppressWarnings("unchecked")
        Iterator<Integer> adjs[] = (Iterator<Integer>[])new Iterator[g.V()];
        for (int i = 0; i < g.V(); i++)
            adjs[i] = g.adj(i).iterator();
        
        int nonisolate = 0;
        for (int i = 0; i < g.V(); i++) {
            if (g.outdegree(i) > 0) {
                nonisolate = i;
                break;
            }
        }
        /*
         * 为什么这里不采用和无向图找欧拉环相同的方式？
         * 不定义静态内部类 Edge ？思考一下
         */
        _Stack<Integer> route = new _Stack<>();
        route.push(nonisolate);
        while (!route.isEmpty()) {
            int v = route.pop();
            while (adjs[v].hasNext()) {
                route.push(v);
                v = adjs[v].next();
            }
            cycle.push(v);
        }
        
        /*
         * 是否利用了图中所有的边？如果没有，那么肯定不构成欧拉环
         */
        if (cycle.size() != g.E() + 1)
            cycle = null;
    }
    public boolean hasCycle() { return cycle != null; }
    public Iterable<Integer> cycle() { return cycle; }
    public static void main(String[] args) {
        Digraph g = DigraphGenerator.eulerianCycle(10, 20);
        StdOut.println(g);
        DirectedEulerianCycle cycle = new DirectedEulerianCycle(g);
        if (cycle.hasCycle())
            for (int w : cycle.cycle())
                StdOut.print(w + "  ");
    }
}

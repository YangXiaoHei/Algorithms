package Review.Graph;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class DirectedEulerianPath {
    private _Stack<Integer> path;
    /*
     * 欧拉环也属于欧拉路径，因此这份代码对于欧拉环同样生效
     */
    public DirectedEulerianPath(Digraph g) {
        /*
         * 寻找一个潜在的欧拉路径的起点
         */
        int s = -1;
        for (int i = 0; i < g.V(); i++) {
            if (g.outdegree(i) > 0) {
                s = i;
                break;
            }
        }
        /*
         * 回想构造欧拉路径的方法：
         * 从一个点发出一条边，用一跟线穿过所有顶点，同时绝与已有路径重复
         * 因此如果有欧拉路径，肯定有一个 “起始顶点”，出度大于入度，比如
         * 出度为 1，入度为 0
         */
        int deficit = 0;
        for (int i = 0; i < g.V(); i++) {
            if (g.outdegree(i) > g.indegree(i)) {
                deficit += g.outdegree(i) - g.indegree(i);
                s = i;
            }
        }
        
        if (deficit > 1) return;
        if (s == -1) s = 0;  /* 没有边 */
        
        path = new _Stack<>();
        
        @SuppressWarnings("unchecked")
        Iterator<Integer> adjs[] = (Iterator<Integer>[])new Iterator[g.V()];
        for (int i = 0; i < g.V(); i++)
            adjs[i] = g.adj(i).iterator();
        
        _Stack<Integer> route = new _Stack<>();
        route.push(s);
        while (!route.isEmpty()) {
            int v = route.pop();
            while (adjs[v].hasNext()) {
                route.push(v);
                v = adjs[v].next();
            }
            path.push(v);
        }
        /*
         * 是否利用了图中所有的边？如果没有，那么肯定不构成欧拉路径
         */
        if (path.size() != g.E() + 1)
            path = null;
    }
    public boolean hasPath() { return path != null; }
    public Iterable<Integer> path() { return path; }
    public static void main(String[] args) {
        Digraph g = DigraphGenerator.eulerianPath(10, 20);
        StdOut.println(g);
        DirectedEulerianPath path = new DirectedEulerianPath(g);
        if (path.hasPath())
            for (int w : path.path())
                StdOut.print(w + "  ");
    }
}

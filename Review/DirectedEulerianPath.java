package Review;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class DirectedEulerianPath {
    private _Stack<Integer> path;
    public DirectedEulerianPath(Digraph g) {
        int s = -1;
        for (int i = 0; i < g.V(); i++) {
            if (g.outdegree(i) > 0) {
                s = i;
                break;
            }
        }
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

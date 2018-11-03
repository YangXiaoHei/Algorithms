package Review;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class DirectedEulerianCycle {
    private _Stack<Integer> cycle;
    public DirectedEulerianCycle(Digraph g) {
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

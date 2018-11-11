package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class EulerianPath {
    private _Stack<Integer> path;
    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;
        public Edge(int v, int w) { this.v = v; this.w = w; }
        public String toString() { return String.format("{ %d %d }", v, w); }
        public int other(int t) {
            if (t == v) return w;
            if (t == w) return v;
            throw new IllegalArgumentException("must equal to " + v + " or " + w);
        }
    }
    public EulerianPath(Graph g) {
        int s = -1;
        int oddDegreeVertices = 0;
        for (int i = 0; i < g.V(); i++) {
            if (g.degree(i) % 2 != 0) {
                oddDegreeVertices++;
                s = i;
            }
        }
        if (oddDegreeVertices > 2) return;
        if (s == -1) s = 0;
        
        @SuppressWarnings("unchecked")
        _Queue<Edge>[] adjs = new _Queue[g.V()];
        for (int i = 0; i < adjs.length; i++)
            adjs[i] = new _Queue<>();
        
        int selfLoops = 0;
        for (int i = 0; i < g.V(); i++) {
            selfLoops = 0;
            for (int w : g.adj(i)) {
                if (w == i) {
                    if (selfLoops % 2 == 0) {
                        Edge e = new Edge(w, w);
                        adjs[i].enqueue(e);
                        adjs[w].enqueue(e);
                    }
                    selfLoops++;
                } else if (w > i) {
                    Edge e = new Edge(i, w);
                    adjs[i].enqueue(e);
                    adjs[w].enqueue(e);
                }
            }
        }
        
        path = new _Stack<>();
        _Stack<Integer> route = new _Stack<>();
        route.push(s);
        
        while (!route.isEmpty()) {
            int v = route.pop();
            while (!adjs[v].isEmpty()) {
                Edge e = adjs[v].dequeue();
                if (e.isUsed) continue;
                e.isUsed = true;
                route.push(v);
                v = e.other(v);
            }
            path.push(v);
        }
        
        if (path.size() != g.E() + 1)
            path = null;
    }
    public boolean hasPath() { return path != null; }
    public Iterable<Integer> path() { 
        if (!hasPath())
            throw new RuntimeException("no path");
        return path;
    }
    public static void main(String[] args) {
        Graph g = GraphGenerator.eulerianPath(10, 20);
        StdOut.println(g);
        EulerianPath p = new EulerianPath(g);
        if (p.hasPath())
            for (int w : p.path())
                StdOut.print(w + " ");
    }
}

package code;

import edu.princeton.cs.algs4.StdOut;

public class EulerianCycle {
    private _Stack<Integer> cycle = new _Stack<Integer>();  // Eulerian cycle; null if no such cycle

    // an undirected edge, with a field to indicate whether the edge has already been used
    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;
        private static int counter = 0;
        private final int id = counter++;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }
        
        public String toString() { return String.format("{%d %d}%d ", v, w, id); }

        // returns the other vertex of the edge
        public int other(int vertex) {
            if      (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    /**
     * Computes an Eulerian cycle in the specified graph, if one exists.
     * 
     * @param G the graph
     */
    public EulerianCycle(Graph G) {

        // must have at least one edge
        if (G.E() == 0) return;

        // necessary condition: all vertices have even degree
        // (this test is needed or it might find an Eulerian path instead of cycle)
        for (int v = 0; v < G.V(); v++) 
            if (G.degree(v) % 2 != 0)
                return;

        // create local view of adjacency lists, to iterate one vertex at a time
        // the helper Edge data type is used to avoid exploring both copies of an edge v-w
        _Queue<Edge>[] adj = (_Queue<Edge>[]) new _Queue[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = new _Queue<Edge>();

        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (int w : G.adj(v)) {
                // careful with self loops
                if (v == w) {
                    if (selfLoops % 2 == 0) {
                        Edge e = new Edge(v, w);
                        adj[v].enqueue(e);
                        adj[w].enqueue(e);
                    }
                    selfLoops++;
                }
                else if (v < w) {
                    Edge e = new Edge(v, w);
                    adj[v].enqueue(e);
                    adj[w].enqueue(e);
                }
            }
        }
        
        StdOut.println("----- Queue -----");
        for (int i = 0; i < adj.length; i++)
            StdOut.printf("%3d : %s\n", i, adj[i]);
        StdOut.println("-----------------");

        // initialize stack with any non-isolated vertex
        int s = nonIsolatedVertex(G);
        _Stack<Integer> stack = new _Stack<Integer>();
        stack.push(s);

        // greedily search through edges in iterative DFS style
        cycle = new _Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].dequeue();
                StdOut.printf("v = %d edge = %s\n", v, edge);
                if (edge.isUsed) continue;
                edge.isUsed = true;
                stack.push(v);
                StdOut.printf("push %d to stack\n", v);
                v = edge.other(v);
            }
            StdOut.printf("----- Stack v = %d-----\n", v);
            StdOut.println(stack);
            StdOut.println("-----------------");
            
            // push vertex with no more leaving edges to cycle
            cycle.push(v);
        }

        // check if all edges are used
        if (cycle.size() != G.E() + 1)
            cycle = null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    // returns any non-isolated vertex; -1 if no such vertex
    private static int nonIsolatedVertex(Graph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > 0)
                return v;
        return -1;
    }
    public static void main(String[] args) {
        Graph g = GraphGenerator.eulerianCycle(10, 10);
        StdOut.print(g);
        EulerianCycle e = new EulerianCycle(g);
        if (e.hasEulerianCycle())
            for (int w : e.cycle())
                StdOut.print(w + " ");
    }
}

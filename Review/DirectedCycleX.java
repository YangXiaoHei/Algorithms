package Review;

import java.util.regex.*;

import edu.princeton.cs.algs4.StdOut;

public class DirectedCycleX {
    private _Stack<Integer> cycle;     // the directed cycle; null if digraph is acyclic

    public DirectedCycleX(Digraph G) {

        // indegrees of remaining vertices
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) 
            indegree[v] = G.indegree(v);

        // initialize queue to contain all vertices with indegree = 0
        _Queue<Integer> queue = new _Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            if (indegree[v] == 0) 
                queue.enqueue(v);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            StdOut.printf("//////////// %d\n", v);
            for (int w : G.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) 
                    queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        int[] edgeTo = new int[G.V()];
        int root = -1;  // any vertex with indegree >= -1
        for (int v = 0; v < G.V(); v++) {
            if (indegree[v] == 0) 
                continue;
            
            root = v;
            for (int w : G.adj(v)) {
                if (indegree[w] > 0) {
                    edgeTo[w] = v;
                }
            }
        }
        
        StdOut.println("root -- " + root);
        
        StdOut.println("**********************");
        for (int i = 0; i < edgeTo.length; i++)
            StdOut.printf("%-5d", i);
        StdOut.println();
        for (int i = 0; i < edgeTo.length; i++)
            StdOut.printf("%-5d", edgeTo[i]);
        StdOut.println("\n**********************");

        if (root != -1) {

            // find any vertex on cycle
            boolean[] visited = new boolean[G.V()];
            while (!visited[root]) {
                visited[root] = true;
                root = edgeTo[root];
            }

            // extract cycle
            cycle = new _Stack<Integer>();
            int v = root;
            do {
                cycle.push(v);
                v = edgeTo[v];
            } while (v != root);
            cycle.push(root);
        }
    }

    public _Stack<Integer> cycle() {
        return cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public static void main(String[] args) {
//      Digraph g = new Digraph("{ 3 0 }{ 2 1 }{ 3 7 }{ 8 7 }{ 9 4 }{ 7 3 }{ 3 2 }{ 2 7 }{ 6 6 }{ 0 8 }{ 0 2 }{ 4 6 }{ 2 4 }{ 2 1 }{ 5 6 }{ 6 7 }{ 2 1 }{ 3 9 }{ 6 4 }{ 4 3 }");
        Digraph g = new Digraph("{ 9 8 }{ 2 7 }{ 9 5 }{ 1 9 }{ 5 4 }{ 9 5 }{ 6 3 }{ 1 7 }{ 9 4 }{ 2 0 }{ 5 1 }{ 8 1 }{ 3 8 }{ 6 1 }{ 0 6 }");
//        String s;
//        Digraph g = new Digraph((s = DigraphGenerator.genCycleDigraphWithVertexCount(10, 15, 4)));
//        StdOut.println(s);
        
        StdOut.println(g);
        DirectedCycleX c = new DirectedCycleX(g);
        if (c.hasCycle())
            for (int w : c.cycle())
                StdOut.print(w + " ");
    }
}

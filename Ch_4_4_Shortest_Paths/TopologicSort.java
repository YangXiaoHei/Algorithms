package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.StdOut;

public class TopologicSort {
    private __Stack<Integer> reversePost;
    private boolean marked[];
    private EdgeWeightedDigraph g;
    public TopologicSort(EdgeWeightedDigraph g, int v) {
        reversePost = new __Stack<>();
        marked = new boolean[g.V()];
        this.g = g;
        
        EdgeWeightedDigraphCycle finder = new EdgeWeightedDigraphCycle(g);
        if (finder.hasCycle()) {
            StringBuilder sb = new StringBuilder();
            for (DirectedEdge e : finder.cycle())
                sb.append(e + "  ");
            throw new RuntimeException("toposort not exist in cyclic graph! \n" + sb.toString());
        }
        
        dfs(v);
    }
    private void dfs(int v) {
        marked[v] = true;
        for (DirectedEdge e : g.adj(v))
            if (!marked[e.to()]) 
                dfs(e.to());
        reversePost.push(v);
    }
    public Iterable<Integer> sortOrder() {
        return reversePost;
    }
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(10);
        g.genRandom(10);
        StdOut.println(g);
        
        TopologicSort sort = new TopologicSort(g, 0);
        for (int w : sort.sortOrder())
            StdOut.print(w + "  ");
    }
}

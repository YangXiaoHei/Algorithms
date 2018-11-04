package Review;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Topological {
    private Iterable<Integer> order;
    private int[] rank;
    private Digraph g;
    public Topological(Digraph g) {
        this.g = g;
        DirectedCycleX d = new DirectedCycleX(g);
        if (!d.hasCycle()) {
            rank = new int[g.V()];
            DepthFirstOrder o = new DepthFirstOrder(g);
            order = o.postReverseOrder();
            int i = 0;
            for (int w : order)
                rank[w] = i++;
        }
    }
    public Iterable<Integer> order() { 
        if (!hasOrder())
            throw new RuntimeException("cycle exist, no sort!");
        return order; 
    }
    public boolean hasOrder() { return order != null; }
    public int rank(int v) {
        checkVertex(v);
        return rank[v];
    }
    private void checkVertex(int v) {
        if (v < 0 || v >= g.V())
            throw new IllegalArgumentException(v + " is invalid");
    }
    public static void main(String[] args) {
        Digraph g = DigraphGenerator.DAG(10, 20);
        StdOut.println(g);
        Topological tp = new Topological(g);
        if (tp.hasOrder())
            for (int w : tp.order())
                StdOut.print(w + " ");
    }
}

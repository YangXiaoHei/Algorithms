package Review;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TransitiveClosure {
    private DepthFirstDirectedPath[] dc;
    public TransitiveClosure(Digraph g) {
        dc = new DepthFirstDirectedPath[g.V()];
        for (int i = 0; i < g.V(); i++)
            dc[i] = new DepthFirstDirectedPath(g, i);
    }
    public boolean reachable(int v, int w) { return dc[v].hasPathTo(w); }
    public Iterable<Integer> pathBetween(int v, int w) { 
        if (!reachable(v, w))
            throw new RuntimeException("no path between " + v + " and " + w);
        return dc[v].pathTo(w);
    }
    public static void main(String[] args) {
        Digraph g = new Digraph(new In("/Users/bot/Desktop/algs4-data/mediumDG.txt"));
        TransitiveClosure t = new TransitiveClosure(g);
        for (int i = 0; i < g.V(); i++) {
            for (int j = 0; j < g.V(); j++)
               StdOut.printf("%-2c", t.reachable(i, j) ? 'T' : ' ');
            StdOut.println();
        }
    }
}

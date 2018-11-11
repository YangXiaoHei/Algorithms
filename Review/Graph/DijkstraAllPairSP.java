package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class DijkstraAllPairSP {
    private DijkstraSP sp[];
    public DijkstraAllPairSP(EdgeWeightedDigraph g) {
        sp = new DijkstraSP[g.V()];
        for (int i = 0; i < g.V(); i++)
            sp[i] = new DijkstraSP(g, i);
    }
    public boolean hasPath(int v, int w) { return sp[v].hasPathTo(w); }
    public Iterable<DirectedEdge> pathBetween(int v, int w) {
        if (!sp[v].hasPathTo(w))
            throw new RuntimeException("no path!");
        return sp[v].pathTo(w);
    }
    public double dis(int v, int w) { return sp[v].disTo(w); }
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(10, 30);
        StdOut.println(g);
        
        DijkstraAllPairSP dj = new DijkstraAllPairSP(g);
        for (int i = 0; i < g.V(); i++)
            for (int j = 0; j < g.V(); j++) {
                if (dj.hasPath(i, j)) {
                    StdOut.printf("%d to %d [%.2f]: ", i, j, dj.dis(i, j));
                    for (DirectedEdge e : dj.pathBetween(i, j))
                        StdOut.printf("%s ", e);
                    StdOut.println();
                }
            }
    }
}

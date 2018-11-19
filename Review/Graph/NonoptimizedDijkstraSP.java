package Review.Graph;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class NonoptimizedDijkstraSP {
    private Edge edgeTo[];
    private double disTo[];
    private ArrayList<Integer> arr;
    public NonoptimizedDijkstraSP(EdgeWeightedGraph g, int s) {
        edgeTo = new Edge[g.V()];
        disTo = new double[g.V()];
        for (int i = 0; i < g.V(); i++)
            disTo[i] = Double.POSITIVE_INFINITY;
        disTo[s] = 0.0;
        arr = new ArrayList<>();
        arr.add(s);
        while (!arr.isEmpty()) {
            relax(g, findMin());
            
//            for (int i = 0; i < g.V(); i++) {
//                StdOut.printf("%-15.0f", disTo[i]);
//            }
//            StdOut.println();
//            for (int i = 0; i < g.V(); i++) {
//                StdOut.printf("%-15s", edgeTo[i]);
//            }
//            StdOut.println();
//            for (int i = 0; i < arr.size(); i++) {
//                StdOut.printf("%-15d", arr.get(i));
//            }
//            StdOut.println("\n---------------------------------");
        }
    }
    private void relax(EdgeWeightedGraph g, int v) {
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
//            StdOut.println("HansonTest ---- " + v);
            
            if (disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
                if (!arr.contains(w))
                arr.add(w);
            }
        }
    }
    private int findMin() {
        double min = Double.POSITIVE_INFINITY;
        int index = -1, v = -1;
        for (int i = 0; i < arr.size(); i++) {
            v = arr.get(i);
            if (disTo[v] < min) {
                min = disTo[v];
                index = i;
            }
        }
        v = arr.get(index);
        arr.remove(index);
        return v;   
    }
    public double disTo(int v) { return disTo[v]; }
    public boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) 
            throw new RuntimeException("no path!");
        _Stack<Edge> S = new _Stack<>();
        for (Edge e = edgeTo[v]; e != null; v = e.other(v), e = edgeTo[v])
            S.push(e);
        return S;
    }
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(20, 80);
        StdOut.println(g);
        
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(g, 0);
        for (int i = 0; i < g.V(); i++) {
            if (sp.hasPathTo(i)) {
                StdOut.printf("from %d to %d dis : %.2f ",0, i, sp.disTo(i));
                for (Edge e : sp.pathTo(i))
                    StdOut.printf("%s", e);
                StdOut.println();
            }
        }
        
        StdOut.println("------------------");
        
        NonoptimizedDijkstraSP nsp = new NonoptimizedDijkstraSP(g, 0);
        for (int i = 0; i < g.V(); i++) {
            if (nsp.hasPathTo(i)) {
                StdOut.printf("from %d to %d dis : %.2f ",0, i, nsp.disTo(i));
                for (Edge e : nsp.pathTo(i))
                    StdOut.printf("%s", e);
                StdOut.println();
            }
        }
    }
}

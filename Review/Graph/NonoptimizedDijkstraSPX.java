package Review.Graph;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class NonoptimizedDijkstraSPX {
    private double disTo[];
    private Edge edgeTo[];
    private EdgeWeightedGraph g;
    private ArrayList<Integer> arr;
    public NonoptimizedDijkstraSPX(EdgeWeightedGraph g, int s) {
        this.g = g;
        edgeTo = new Edge[g.V()];
        disTo = new double[g.V()];
        disTo[s] = 0.0; 
        for (Edge e : g.adj(s)) {
            int w = e.other(s);
            /*
             * 将源结点的邻接点初始化为 c(s, w)
             * ⚠️注意起点可能包含自环，因此如果只是简单的
             * 作赋值而不判断与当前 disTo[w] 的大小关系
             * 那么本来 disTo[s] 应该是 0, edgeTo[s] 应该是 null
             * 现在变为 edgeTo[s] 不为 null
             * 那么如果进行 pathTo(0) 的查询，就会陷入自环的死循环
             */
            if (disTo[w] > e.weight()) {
                disTo[w] = e.weight();
                edgeTo[w] = e;
            }
        }
        for (int i = 0; i < g.V(); i++) 
            if (i != s && disTo[i] <= 0) 
                disTo[i] = Double.POSITIVE_INFINITY;
        
        arr = new ArrayList<>();
        int minV = -1;
        while (arr.size() < g.V()) {
            /*
             * ⚠️对于非连通图
             * arr.size() 将永远小于 g.V()
             */
            if ((minV = findMinDistanceSoFar()) < 0) 
                break;
            
            arr.add(minV);
            relax(g, minV);
        }
    }
    private int findMinDistanceSoFar() {
        double min = Double.POSITIVE_INFINITY;
        int minV = -1;
        for (int i = 0; i < g.V(); i++) {
            if (arr.contains(i))
                continue;
            if (disTo[i] < min) {
                min = disTo[i];
                minV = i;
            }
        }
        return minV;
    }
    private void relax(EdgeWeightedGraph g, int v) {
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }
    public boolean hasPathTo(int v) { return disTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v))
            throw new RuntimeException("no path!");
        _Stack<Edge> s = new _Stack<>();
        for (Edge e = edgeTo[v]; e != null; v = e.other(v), e = edgeTo[v])  {
            s.push(e);
        }
        return s;
    }
    public double disTo(int v) { return disTo[v]; }
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(8, 20);
        StdOut.println(g);
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(g, 0);
        NonoptimizedDijkstraSPX nsp = new NonoptimizedDijkstraSPX(g, 0);
        
        for (int i = 0; i < g.V(); i++) {
            if (sp.hasPathTo(i)) {
                StdOut.printf("from %d to %d dis : %.2f ",0, i, sp.disTo(i));
                for (Edge e : sp.pathTo(i))
                    StdOut.printf("%s", e);
                StdOut.println();
            }
          }
          
          StdOut.println("------------------");
          
          for (int i = 0; i < g.V(); i++) {
              if (nsp.hasPathTo(i)) {
                  StdOut.printf("from %d to %d dis : %.2f ",0, i, nsp.disTo(i));
                  for (Edge e : nsp.pathTo(i)) 
                      StdOut.printf("%s", e);
                  StdOut.println();
              }
          }
        
        for (int j = 0; j < g.V(); j++) 
            if (sp.disTo(j) != nsp.disTo(j)) 
               throw new RuntimeException("not equal!");
    }
}

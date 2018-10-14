package Ch_4_1_Undirected_Graphs;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class ___Graph_multiImplementation {
    public interface Graph {
        public abstract String toString();
        public abstract int V();
        public abstract int E();
        public void addEdge(int v, int w);
        public Iterable<Integer> adj(int v);
    }
    /*
     * 获取图中某个顶点的度数
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v))
            degree++;
        return degree;
    }
    /*
     * 获取图中最大度数
     */
    public static int maxDegree(Graph G) {
        int max = 0, tmp = 0;
        for (int i = 0; i < G.V(); i++)
            if (max < (tmp = degree(G, i)))
                max = tmp;
        return max;
    }
    public static double avgDegree(Graph G) {
        /* 一条边能同时贡献两个度数，因此乘 2 */
        return 2.0 * G.E() / G.V();
    }
    public static int numberOfSelfLoops(Graph G) {
        int n = 0;
        for (int i = 0; i < G.V(); i++) {
            for (int w : G.adj(i))
                if (w == i)
                    n++;
        }
        /* 每条边都被记过两次 ⚠️ 该句存疑 */
        return n / 2;
    }
    
    static class AdjacencyMatrixImp implements Graph {
        public int[] vertex;
        public boolean[][] edge;
        public AdjacencyMatrixImp(int V) {
            vertex = new int[V];
            edge = new boolean[V][];
            for (int i = 0; i < V; i++) {
                edge[i] = new boolean[V];
                for (int j = 0; j < V; j++) 
                    edge[i][j] = false;
            }
        }
        public int V() { return vertex.length; }
        public int E() {
            int ne = 0;
            for (int i = 0; i < V(); i++) 
                for (int j = 0; j < V(); j++) 
                    if (edge[i][j])
                        ne++;
            return ne;
        }
        public void addEdge(int v, int w) {
            if (v >= vertex.length || w >= vertex.length)
                throw new RuntimeException("invalid vertex");
            edge[v][w] = edge[w][v] = true;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("vertex: ");
            for (int i = 0; i < V(); i++)
                sb.append(i + " ");
            sb.append("\n");
            sb.append("edge: \n");
            sb.append(String.format("%-4s", " "));
            for (int i = 0; i < V(); i++) 
                sb.append(String.format("%-4d", i));
            sb.append("\n");
            for (int i = 0; i < V(); i++) {
                sb.append(String.format("%-4d", i));
                for (int j = 0; j < V(); j++) {
                    sb.append(edge[i][j] ?
                            String.format("%-4s", "X") :
                            String.format("%-4s", "0"));
                }
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
        public Iterable<Integer> adj(int v) {
            return new Iterable<Integer>() {
                public Iterator<Integer> iterator() {
                    return new Iterator<Integer>() {
                        int cursor = -1;
                        public boolean hasNext() {
                            while (++cursor < V())
                                if (edge[v][cursor])
                                    return true;
                            return false;
                        }
                        public Integer next() {
                            if (cursor < 0)
                                throw new RuntimeException("you must invoke hasNext first!");
                            return cursor;
                        }
                    };
                }
            };
        }
    }
    public static void main(String[] args) {
        AdjacencyMatrixImp G = new AdjacencyMatrixImp(13);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 6);
        G.addEdge(0, 5);
        G.addEdge(3, 5);
        G.addEdge(3, 4);
        G.addEdge(5, 4);
        G.addEdge(7, 8);
        G.addEdge(9, 10);
        G.addEdge(9, 11);
        G.addEdge(9, 12);
        G.addEdge(11, 12);
        StdOut.println(G);
        
        for (int w : G.adj(11))
            StdOut.println(w);
        
    }
}

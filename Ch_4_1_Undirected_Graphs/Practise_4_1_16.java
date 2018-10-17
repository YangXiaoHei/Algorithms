package Ch_4_1_Undirected_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_16 {
    static class Graph {
        private __Bag<Integer> adjs[];
        private EG eg;
        private int E;
        private int V;
        private Pair[] tmpPairs;
        Graph(int V) {
            this.V = V;
            eg = new EG(V);
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++)
                adjs[i] = new __Bag<Integer>();
        }
        void genRandom(int edgeCount) {
            int i = 0, n = edgeCount;
            tmpPairs = new Pair[edgeCount];
            while (edgeCount-- > 0) {
                tmpPairs[i] = eg.next();
                addEdge(tmpPairs[i].v, tmpPairs[i].w);
                i++;
            }
            for (int j = 0; j < n; j++)
                StdOut.println(tmpPairs[j]);
            StdOut.println("-------------------");
        }
        boolean hasEdge(int v, int w) {
            return adjs[v].contains(w);
        }
        int V() { return V; }
        int E() { return E; }
        void addEdge(int v, int w) {
            if (v == w) 
                return;
            if (hasEdge(v, w))
                return;
            adjs[v].add(w);
            adjs[w].add(v);
            E++;
        }
        Iterable<Integer> adjs(int v) {
            return adjs[v];
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) 
                sb.append(i + ": " + adjs[i].toString() + "\n");
            return sb.toString();
        }
    }
    static class GraphProperties {
        private Graph g;
        private boolean marked[];
        private int edgeTo[][];
        private int disTo[][];
        private int currentMarkedCount;
        GraphProperties(Graph g) {
            this.g = g;
            marked = new boolean[g.V()];
            edgeTo = new int[g.V()][];
            disTo = new int[g.V()][];
            for (int i = 0; i < g.V(); i++) {
                edgeTo[i] = new int[g.V()];
                disTo[i] = new int[g.V()];
            }
            for (int i = 0; i < g.V(); i++) 
                if (!DFS(i))
                    throw new RuntimeException("not connected graph! sometimes happen, try again!");
        }
        void clearAllMarked() {
            for (int i = 0; i < marked.length; i++)
                marked[i] = false;
            currentMarkedCount = 0;
        }
        boolean DFS(int v) {
            clearAllMarked();
            __Queue<Integer> q = new __Queue<>();
            marked[v] = true;
            currentMarkedCount++;
            q.enqueue(v);
            while (!q.isEmpty()) {
                int w = q.dequeue();
                for (int k : g.adjs(w)) {
                    if (!marked[k]) {
                        edgeTo[v][k] = w;
                        marked[k] = true;
                        q.enqueue(k);
                        disTo[v][k] = disTo[v][w] + 1;
                        currentMarkedCount++;
                    }
                }
            }
            return currentMarkedCount == g.V();
        }
        /*
         * v 的离心率: 从 v 到达所有点的最短路径中最长的一条
         */
        int eccentricity(int v, boolean logon) {
            int max = -1, furthestVertex = -1;
            for (int i = 0; i < disTo[v].length; i++) {
                if (disTo[v][i] > max) {
                    max = disTo[v][i];
                    furthestVertex = i;
                }
            }
            if (logon) {
                __Stack<Integer> S = new __Stack<>();
                for (int w = furthestVertex; w != v; w = edgeTo[v][w]) 
                    S.push(w);
                S.push(v);
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%d -> %d 是所有最短路径中最远的一条: ", v, furthestVertex));
                while (!S.isEmpty())
                    sb.append(String.format("%d -> ", S.pop()));
                sb.delete(sb.length() - 4, sb.length());
                StdOut.println(sb.toString());
            }
            return max;
        }
        /*
         * 直径
         */
        int diameter() {
            int max = -1;
            int tmp = 0;
            for (int i = 0; i < g.V(); i++)
                if ((tmp = eccentricity(i, true)) > max)
                    max = tmp;
            return max;
        }
//        /*
//         * 半径
//         */
        int radius() {
            int min = Integer.MAX_VALUE;
            int tmp = 0;
            for (int i = 0; i < g.V(); i++)
                if ((tmp = eccentricity(i, true)) < min)
                    min = tmp;
            return min;
        }
//        /*
//         * G 的某个中点
//         */
        int center() {
            for (int i = 0; i < g.V(); i++) 
                if (eccentricity(i, true) == radius())
                    return i;
            throw new RuntimeException("should not reach here!");
        }
    }
    public static void main(String[] args) {
        Graph g = new Graph(13);
        g.genRandom(24);
        StdOut.println(g);
        GraphProperties gp = new GraphProperties(g);
        StdOut.printf("\n直径 : %d\n\n", gp.diameter());
        StdOut.printf("\n半径 : %d\n\n", gp.radius());
        StdOut.printf("\n中点 : %d\n", gp.center());
    }
}

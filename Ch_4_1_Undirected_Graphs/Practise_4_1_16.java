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
                for (int j = 0; j < g.V(); j++) {
                    edgeTo[i][j] = -1;
                    disTo[i][j] = 0;
                }
            }
            for (int i = 0; i < g.V(); i++) 
                if (!BFS(i))
                    throw new RuntimeException("not connected graph! sometimes happen, try again!");
        }
        public static void printArray(int[][] a) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    StdOut.printf("%-3d", a[i][j]);
                }
                StdOut.println();
            }
        }
        int girth(boolean logon) {
            int min = Integer.MAX_VALUE;
            int from = -1, to = -1;
            for (int i = 0; i < g.V(); i++) {
                for (int j = 0; j < g.V(); j++) {
                    if (i == j) continue;
                    // 由 i 发起的 bfs 到达 j
                    // 由 j 发起的 bfs 到达 i
                    if (disTo[i][j] + disTo[j][i] < min) {
                        min = disTo[i][j] + disTo[j][i];
                        from = i; to = j;
                    }
                }
            }
            if (logon) {
                __Stack<Integer> S = new __Stack<>();
                // edgeTo[from] 记录由 from 发起的 bfs 到所有其他点的最短路径
                for (int w = to; w != from; w = edgeTo[from][w]) 
                    S.push(w);
                S.push(from);
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("from %d to %d : ", from, to));
                while (!S.isEmpty())
                    sb.append(String.format("%d -> ", S.pop()));
                sb.delete(sb.length() - 4, sb.length());
                StdOut.println(sb.toString());
                
                S.clear();
                
                for (int w = from; w != to; w = edgeTo[to][w]) 
                    S.push(w);
                S.push(to);
                sb = new StringBuilder();
                sb.append(String.format("from %d to %d : ", to, from));
                while (!S.isEmpty())
                    sb.append(String.format("%d -> ", S.pop()));
                sb.delete(sb.length() - 4, sb.length());
                StdOut.println(sb.toString());
            }
            return min;
        }
        void clearAllMarked() {
            for (int i = 0; i < marked.length; i++)
                marked[i] = false;
            currentMarkedCount = 0;
        }
        boolean BFS(int v) {
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
        Graph g = new Graph(100);
        g.genRandom(1000);
//        g.addEdge(0, 1);
//        g.addEdge(1, 2);
//        g.addEdge(2, 3);
//        g.addEdge(3, 4);
//        g.addEdge(4, 5);
//        g.addEdge(5, 0);
//        g.addEdge(6, 0);
//        g.addEdge(6, 1);
//        g.addEdge(6, 2);
//        g.addEdge(6, 3);
//        g.addEdge(6, 4);
//        g.addEdge(6, 5);
        StdOut.println(g);
        GraphProperties gp = new GraphProperties(g);
        StdOut.printf("\n直径 : %d\n\n", gp.diameter());
        StdOut.printf("\n半径 : %d\n\n", gp.radius());
        StdOut.printf("\n中点 : %d\n\n", gp.center());
    }
    /*
     *  12 9
        10 8
        11 7
        3 8
        9 3
        1 0
        11 1
        3 8
        9 3
        8 3
        6 3
        4 12
        3 11
        8 12
        9 5
        4 5
        10 5
        0 2
        11 1
        12 11
        9 2
        9 7
        5 6
        4 1
        -------------------
        0: 1 2 
        1: 0 11 4 
        2: 0 9 
        3: 8 9 6 11 
        4: 12 5 1 
        5: 9 4 10 6 
        6: 3 5 
        7: 11 9 
        8: 10 3 12 
        9: 12 3 5 2 7 
        10: 8 5 
        11: 7 1 3 12 
        12: 9 4 8 11 
        
        0 -> 6 是所有最短路径中最远的一条: 0 -> 1 -> 11 -> 3 -> 6
        1 -> 6 是所有最短路径中最远的一条: 1 -> 11 -> 3 -> 6
        2 -> 4 是所有最短路径中最远的一条: 2 -> 0 -> 1 -> 4
        3 -> 0 是所有最短路径中最远的一条: 3 -> 9 -> 2 -> 0
        4 -> 2 是所有最短路径中最远的一条: 4 -> 12 -> 9 -> 2
        5 -> 0 是所有最短路径中最远的一条: 5 -> 9 -> 2 -> 0
        6 -> 0 是所有最短路径中最远的一条: 6 -> 3 -> 9 -> 2 -> 0
        7 -> 0 是所有最短路径中最远的一条: 7 -> 11 -> 1 -> 0
        8 -> 0 是所有最短路径中最远的一条: 8 -> 3 -> 9 -> 2 -> 0
        9 -> 1 是所有最短路径中最远的一条: 9 -> 12 -> 4 -> 1
        10 -> 0 是所有最短路径中最远的一条: 10 -> 5 -> 9 -> 2 -> 0
        11 -> 2 是所有最短路径中最远的一条: 11 -> 7 -> 9 -> 2
        12 -> 0 是所有最短路径中最远的一条: 12 -> 9 -> 2 -> 0
        
        直径 : 4
        
        0 -> 6 是所有最短路径中最远的一条: 0 -> 1 -> 11 -> 3 -> 6
        1 -> 6 是所有最短路径中最远的一条: 1 -> 11 -> 3 -> 6
        2 -> 4 是所有最短路径中最远的一条: 2 -> 0 -> 1 -> 4
        3 -> 0 是所有最短路径中最远的一条: 3 -> 9 -> 2 -> 0
        4 -> 2 是所有最短路径中最远的一条: 4 -> 12 -> 9 -> 2
        5 -> 0 是所有最短路径中最远的一条: 5 -> 9 -> 2 -> 0
        6 -> 0 是所有最短路径中最远的一条: 6 -> 3 -> 9 -> 2 -> 0
        7 -> 0 是所有最短路径中最远的一条: 7 -> 11 -> 1 -> 0
        8 -> 0 是所有最短路径中最远的一条: 8 -> 3 -> 9 -> 2 -> 0
        9 -> 1 是所有最短路径中最远的一条: 9 -> 12 -> 4 -> 1
        10 -> 0 是所有最短路径中最远的一条: 10 -> 5 -> 9 -> 2 -> 0
        11 -> 2 是所有最短路径中最远的一条: 11 -> 7 -> 9 -> 2
        12 -> 0 是所有最短路径中最远的一条: 12 -> 9 -> 2 -> 0
        
        半径 : 3
        
        0 -> 6 是所有最短路径中最远的一条: 0 -> 1 -> 11 -> 3 -> 6
        0 -> 6 是所有最短路径中最远的一条: 0 -> 1 -> 11 -> 3 -> 6
        1 -> 6 是所有最短路径中最远的一条: 1 -> 11 -> 3 -> 6
        2 -> 4 是所有最短路径中最远的一条: 2 -> 0 -> 1 -> 4
        3 -> 0 是所有最短路径中最远的一条: 3 -> 9 -> 2 -> 0
        4 -> 2 是所有最短路径中最远的一条: 4 -> 12 -> 9 -> 2
        5 -> 0 是所有最短路径中最远的一条: 5 -> 9 -> 2 -> 0
        6 -> 0 是所有最短路径中最远的一条: 6 -> 3 -> 9 -> 2 -> 0
        7 -> 0 是所有最短路径中最远的一条: 7 -> 11 -> 1 -> 0
        8 -> 0 是所有最短路径中最远的一条: 8 -> 3 -> 9 -> 2 -> 0
        9 -> 1 是所有最短路径中最远的一条: 9 -> 12 -> 4 -> 1
        10 -> 0 是所有最短路径中最远的一条: 10 -> 5 -> 9 -> 2 -> 0
        11 -> 2 是所有最短路径中最远的一条: 11 -> 7 -> 9 -> 2
        12 -> 0 是所有最短路径中最远的一条: 12 -> 9 -> 2 -> 0
        1 -> 6 是所有最短路径中最远的一条: 1 -> 11 -> 3 -> 6
        0 -> 6 是所有最短路径中最远的一条: 0 -> 1 -> 11 -> 3 -> 6
        1 -> 6 是所有最短路径中最远的一条: 1 -> 11 -> 3 -> 6
        2 -> 4 是所有最短路径中最远的一条: 2 -> 0 -> 1 -> 4
        3 -> 0 是所有最短路径中最远的一条: 3 -> 9 -> 2 -> 0
        4 -> 2 是所有最短路径中最远的一条: 4 -> 12 -> 9 -> 2
        5 -> 0 是所有最短路径中最远的一条: 5 -> 9 -> 2 -> 0
        6 -> 0 是所有最短路径中最远的一条: 6 -> 3 -> 9 -> 2 -> 0
        7 -> 0 是所有最短路径中最远的一条: 7 -> 11 -> 1 -> 0
        8 -> 0 是所有最短路径中最远的一条: 8 -> 3 -> 9 -> 2 -> 0
        9 -> 1 是所有最短路径中最远的一条: 9 -> 12 -> 4 -> 1
        10 -> 0 是所有最短路径中最远的一条: 10 -> 5 -> 9 -> 2 -> 0
        11 -> 2 是所有最短路径中最远的一条: 11 -> 7 -> 9 -> 2
        12 -> 0 是所有最短路径中最远的一条: 12 -> 9 -> 2 -> 0
        
        中点 : 1

     */
}

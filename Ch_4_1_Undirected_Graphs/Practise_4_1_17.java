package Ch_4_1_Undirected_Graphs;

import Ch_4_1_Undirected_Graphs.Practise_4_1_16.Graph;
import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_17 {
    static class Graph {
        public int V;
        public int E;
        private EG eg;
        public __Bag<Integer>[] adjs;
        private Pair[] tmpPairs;
        public Graph(int V) {
            this.V = V;
            eg = new EG(V);
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++)
                adjs[i] = new __Bag<Integer>();
        }
        public int V() { return V; }
        public int E() { return E; }
        public void addEdge(int v, int w) {
            if (v == w || hasEdge(v, w))
                return;
            adjs[v].add(w);
            E++;
        }
        boolean hasEdge(int v, int w) {
            /* 因为我不想让生成的图中含有 1 -> 2 2 -> 1 这样的边 */
            return adjs[v].contains(w) || adjs[w].contains(v);
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
        public Iterable<Integer> adjs(int v) {
            return adjs[v];
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) 
                sb.append(i + ": " + adjs[i].toString() + "\n");
            return sb.toString();
        }
    }
    /*
     * 无向图请看上题 4.1.16 中的实现，本题尝试实现找出有向图的最小环
     */
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
    }
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 0);
        GraphProperties gp = new GraphProperties(g);
        StdOut.printf("最小环距离为 : %d", gp.girth(true));
    }
    /*
     *  from 0 to 1 : 0 -> 1
        from 1 to 0 : 1 -> 2 -> 3 -> 4 -> 5 -> 0
        最小环距离为 : 6
     */
}

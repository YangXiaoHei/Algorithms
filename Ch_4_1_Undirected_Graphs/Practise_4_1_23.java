package Ch_4_1_Undirected_Graphs;

import edu.princeton.cs.algs4.StdOut;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class Practise_4_1_23 {
    static class Graph {
        private __Bag<Integer> adjs[];
        private int E;
        private int V;
        Graph(int V) {
            this.V = V;
            adjs = (__Bag<Integer>[])new __Bag[V];
            for (int i = 0; i < V; i++)
                adjs[i] = new __Bag<Integer>();
        }
        boolean hasEdge(int v, int w) {
            /* 无向图只需要作一个判断 */
            return adjs[v].contains(w); 
        }
        void addEdge(int v, int w) {
            if (v == w || hasEdge(v, w)) 
                return;
            adjs[v].add(w);
            adjs[w].add(v);
            E++;
        }
        Iterable<Integer> adj(int v) {
            return adjs[v];
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) {
                sb.append(String.format("%d : %s\n", i, adjs[i]));
            }
            return sb.toString();
        }
    }
    static class SymbolGraph {
        private HashMap<String, Integer> ST;
        private String[] name;
        private Graph G;
        private int disTo[];
        private int edgeTo[];
        private boolean marked[];
        private int BaconKevinfromIndex;
        private int[] connectedRegion;
        private int id;
        private int[] idCount;
        private int BaconKevinId;
        SymbolGraph(String filename, String delim) {
            File file = new File(filename);
            BufferedReader buffer = null;
            String line;
            ArrayList<String[]> allLines = new ArrayList<>();
            ST = new HashMap<>();
            int count = 0;
            try {
                buffer = new BufferedReader(new FileReader(file));
                while ((line = buffer.readLine()) != null) {
                    String[] arr = line.split("/");
                    allLines.add(arr);
                    /* 电影 or 名字 -> 索引 */
                    for (int i = 0; i < arr.length; i++)
                        if (!ST.containsKey(arr[i]))
                            ST.put(arr[i], count++);
                }
            } catch(Exception e) { }
            
            /* 索引 -> 电影 or 名字 */
            name = new String[ST.size()];
            for (Entry<String, Integer> entry : ST.entrySet()) 
                name[entry.getValue()] = entry.getKey();
            
            /* 图结构初始化 */
            G = new Graph(ST.size());
            for (int i = 0; i < allLines.size(); i++) {
                String[] names = allLines.get(i);
                String movie = names[0];
                for (int j = 1;  j < names.length; j++)
                    G.addEdge(ST.get(movie).intValue(), ST.get(names[j]).intValue());
            }
            
            /* 初始化用于 BFS 的数组 */
            connectedRegion = new int[ST.size()];
            edgeTo = new int[ST.size()];
            marked = new boolean[ST.size()];
            disTo = new int[ST.size()];
            for (int i = 0; i < edgeTo.length; i++)
                edgeTo[i] = i;
            BaconKevinfromIndex = ST.get("Bacon, Kevin").intValue();
            
            /* 连通分量 */
            for (int i = 0; i < ST.size(); i++) {
                if (!marked[i]) {
                    BFS(i);
                    id++;
                }
            }
            
            idCount = new int[id];
            for (int i = 0; i < connectedRegion.length; i++)
                idCount[connectedRegion[i]]++;
            for (int i = 0; i < id; i++)
                StdOut.printf("[%d] : %d\n", i, idCount[i]);
        }
        public int connectedRegionCount() {
            return id;
        }
        public int BaconKevinId() { return  BaconKevinId; }
        public int lessThan10() {
            int n = 0;
            for (int i = 0; i < idCount.length; i++)
                if (idCount[i] < 10)
                    n++;
            return n;
        }
        private void BFS(int from) {
            __Queue<Integer> Q = new __Queue<>();
            if (from == BaconKevinfromIndex)
                BaconKevinId = id;
            marked[from] = true;
            Q.enqueue(from);
            while (!Q.isEmpty()) {
                from = Q.dequeue();
                for (int w : G.adj(from)) {
                    if (!marked[w]) {
                        connectedRegion[w] = id;
                        if (w == BaconKevinfromIndex)
                            BaconKevinId = id;
                        marked[w] = true;
                        Q.enqueue(w);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        String path = "/Users/bot/Desktop/algs4-data/movies.txt";
        SymbolGraph SG = new SymbolGraph(path, "/");
        StdOut.printf("连通分量的数目 : %d\n", SG.connectedRegionCount());
        StdOut.printf("连通分量中顶点个数小于 10 的数目 : %d\n", SG.lessThan10());
        StdOut.printf("BaconKevinId = %d\n", SG.BaconKevinId());
    }
    /*
     * output
     * 
     *  [0] : 118806
        [1] : 20
        [2] : 28
        [3] : 45
        [4] : 26
        [5] : 22
        [6] : 22
        [7] : 66
        [8] : 18
        [9] : 12
        [10] : 11
        [11] : 11
        [12] : 10
        [13] : 1
        [14] : 43
        [15] : 25
        [16] : 26
        [17] : 13
        [18] : 17
        [19] : 17
        [20] : 10
        [21] : 5
        [22] : 8
        [23] : 19
        [24] : 15
        [25] : 7
        [26] : 6
        [27] : 28
        [28] : 9
        [29] : 10
        [30] : 24
        [31] : 16
        [32] : 33
        连通分量的数目 : 33
        连通分量中顶点个数小于 10 的数目 : 6
        BaconKevinId = 0
     */
}

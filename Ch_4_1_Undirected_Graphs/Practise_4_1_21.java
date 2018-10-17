package Ch_4_1_Undirected_Graphs;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_4_1_21 {
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
        public int findRoot(int v, int edgeTo[]) {
            while (v != edgeTo[v])
                v = edgeTo[v];
            return v;
        }
        public int V() { return V; }
        public int E() { return E; }
        public void addEdge(int v, int w) {
            if (v == w || hasEdge(v, w))
                return;
            adjs[v].add(w);
            adjs[w].add(v);
            E++;
        }
        boolean hasEdge(int v, int w) {
            return adjs[v].contains(w);
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
    /* 从 BaconKevin 到 $s 的距离 */
    public static int[] BaconKevin(SymbolGraph SG, String s, boolean printPath) {
        boolean[] marked = new boolean[SG.G.V()];
        int edgeTo[] = new int[SG.G.V()];
        int disTo[] = new int[SG.G.V()];
        for (int i = 0; i < SG.G.V(); i++)
            edgeTo[i] = i;
        
        if (s == null) s = "Kidman, Nicole";
        
        /* BFS */
        __Queue<Integer> Q = new __Queue<>();
        int from = SG.index("Bacon, Kevin"), to = SG.index(s);
        int v = from;
        marked[v] = true;
        Q.enqueue(v);
        while (!Q.isEmpty()) {
            v = Q.dequeue();
            for (int w : SG.G.adjs(v))
                if (!marked[w]) {
                    edgeTo[w] = v;
                    disTo[w] = disTo[v] + 1;
                    marked[w] = true;
                    Q.enqueue(w);
                }
        }
        
        /* ⚠️⚠️⚠️⚠️ 排除掉不与 Bacon, Kevin 连通的演员 ⚠️⚠️⚠️⚠️⚠️ */
        for (int i = 0; i < disTo.length; i++) {
            if (disTo[i] == 0) {
                if (SG.G.findRoot(i, edgeTo) != from)
                    disTo[i] = -1;
            }
        }
        
        if (printPath) {
            
            /* 收集路径 */
            __Stack<String> S = new __Stack<>();
            for (int i = to; i != from; i = edgeTo[i])  
                S.push(SG.name(i));
            S.push(SG.name(from));
            
            /* 打印结果 */
            StdOut.println(s);
            while (!S.isEmpty()) 
                StdOut.println("  " + S.pop());
        }
        return disTo;
    }
    static class SymbolGraph {
        private HashMap<String, Integer> ST;
        private String[] name;
        private Graph G;
        SymbolGraph(String filename, String delim) {
            ST = new HashMap<String, Integer>();
            ArrayList<String[]> allLines = new ArrayList<>();
            File file = new File(filename);
            BufferedReader reader = null;
            int count = 0;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] arr = line.split("/");
                    allLines.add(arr);
                    for (int i = 0; i < arr.length; i++) {
                        /* 电影 or 名字 -> 索引 */
                        if (!ST.containsKey(arr[i]))
                            ST.put(arr[i], count++);
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) { }
                }
            }
            
            /*  索引 -> 电影 or 名字 */
            name = new String[ST.size()];
            for (Entry<String, Integer> ent :  ST.entrySet()) 
                name[ent.getValue().intValue()] = ent.getKey();
            
            /* 初始化图结构 */
            G = new Graph(ST.size());
            for (int i = 0; i < allLines.size(); i++) {
                String[] arr = allLines.get(i);
                String movie = arr[0];
                for (int j = 1; j < arr.length; j++)
                    G.addEdge(ST.get(movie), ST.get(arr[j]));
            }
        }
        public Graph G() { return G; }
        public String name(int v) { return name[v]; }
        public int index(String s) { return ST.get(s); }
        public boolean contains(String s) { return ST.containsKey(s); } 
    }
    public static void main(String[] args) {
        
        /* 4.1.21 */
        String path = "/Users/bot/Desktop/algs4-data/movies.txt";
        SymbolGraph SG = new SymbolGraph(path, "/");
        BaconKevin(SG, "Kidman, Nicole", true);
        BaconKevin(SG, "Grant, Cary", true);
        
        /* 4.1.22 画图的 API 不想用了...直接看数字吧 - -| */
        int[] disTo = BaconKevin(SG, null, false);
        int count[] = new int[100];
        for (int i = 0; i < disTo.length; i++) 
            if (disTo[i] >= 0 && disTo[i] % 2 == 0)  /* 演员和演员之间一定会有一个电影 */
                count[disTo[i] / 2]++; 
            
        for (int i = 0; i < count.length; i++) 
            if (count[i] != 0) 
                StdOut.printf("距 KevinBacon 距离为 %d 的演员有 %d 名\n", i, count[i]);
    }
    /*
     * output
     * 
     *    Kidman, Nicole
          Bacon, Kevin
          Animal House (1978)
          Sutherland, Donald (I)
          Cold Mountain (2003)
          Kidman, Nicole
        Grant, Cary
          Bacon, Kevin
          JFK (1991)
          Matthau, Walter
          Charade (1963)
          Grant, Cary
        距 KevinBacon 距离为 0 的演员有 1 名
        距 KevinBacon 距离为 1 的演员有 1324 名
        距 KevinBacon 距离为 2 的演员有 70717 名
        距 KevinBacon 距离为 3 的演员有 40862 名
        距 KevinBacon 距离为 4 的演员有 1591 名
        距 KevinBacon 距离为 5 的演员有 125 名
     */
}

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
    public static void BaconKevin(SymbolGraph SG, String s) {
        boolean[] marked = new boolean[SG.G.V()];
        int edgeTo[] = new int[SG.G.V()];
        for (int i = 0; i < SG.G.V(); i++)
            edgeTo[i] = i;
        
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
                    marked[w] = true;
                    Q.enqueue(w);
                }
        }
        
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
    static class SymbolGraph {
        private HashMap<String, Integer> ST;
        private String[] name;
        private String[] movie;
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
            G = new Graph(ST.size());
            name = new String[ST.size()];
            for (Entry<String, Integer> ent :  ST.entrySet()) 
                name[ent.getValue().intValue()] = ent.getKey();
            
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
        String path = "/Users/bot/Desktop/algs4-data/movies.txt";
        SymbolGraph SG = new SymbolGraph(path, "/");
        BaconKevin(SG, "Kidman, Nicole");
        BaconKevin(SG, "Grant, Cary");
    }
    /*
     * output
     * 
     *  Kidman, Nicole
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
     */
}

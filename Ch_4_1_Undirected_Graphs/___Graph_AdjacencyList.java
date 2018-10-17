package Ch_4_1_Undirected_Graphs;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class ___Graph_AdjacencyList {
    static class AdjacencyList {
        public int V;
        public int E;
        private EG eg;
        public __Bag<Integer>[] adjs;
        private Pair[] tmpPairs;
        public AdjacencyList(int V) {
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
        public Iterable<Integer> adj(int v) {
            return adjs[v];
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) 
                sb.append(i + ": " + adjs[i].toString() + "\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        AdjacencyList G = new AdjacencyList(13);
        G.genRandom(13);
        StdOut.println(G);
    }
    /*
     * output
     * 
     *  0: 1 2 6 5 
        1: 0 
        2: 0 
        3: 5 4 
        4: 3 5 6 
        5: 0 3 4 
        6: 0 4 
        7: 8 
        8: 7 
        9: 10 11 12 
        10: 9 
        11: 9 12 
        12: 9 11 
        
        9
        12
     */
}

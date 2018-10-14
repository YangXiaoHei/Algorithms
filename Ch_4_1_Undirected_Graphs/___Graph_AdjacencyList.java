package Ch_4_1_Undirected_Graphs;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class ___Graph_AdjacencyList {
    static class Bag<T> implements Iterable<T> {
        private class Node {
            Node next;
            T item;
            Node (T item, Node next) {
                this.item = item;
                this.next = next;
            }
        }
        private Node head = null;
        private Node tail = null;
        public void add(T item) {
            Node n = new Node(item, null);
            if (head == null)
                head = n;
            if (tail == null)
                tail = n;
            else {
                tail.next = n;
                tail = tail.next;
            }
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (T i : this)
                sb.append(i + " ");
            return sb.toString();
            
        }
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                Node cur = head;
                public boolean hasNext() {
                    return cur != null;
                }
                public T next() {
                    T item = cur.item;
                    cur = cur.next;
                    return item;
                }
            };
        }
    }
    static class AdjacencyList {
        public int V;
        public int E;
        public Bag<Integer>[] adj;
        public AdjacencyList(int V) {
            this.V = V;
            adj = (Bag<Integer>[])new Bag[V];
            for (int i = 0; i < V; i++)
                adj[i] = new Bag<Integer>();
        }
        public int V() { return V; }
        public int E() { return E; }
        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }
        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) {
                sb.append(i + ": " + adj[i].toString() + "\n");
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        AdjacencyList G = new AdjacencyList(13);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 6);
        G.addEdge(0, 5);
        G.addEdge(3, 5);
        G.addEdge(3, 4);
        G.addEdge(5, 4);
        G.addEdge(6, 4);
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

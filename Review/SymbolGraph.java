package code;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class SymbolGraph {
    private Graph g;
    private HashMap<String, Integer> st;
    private String[] names;
    public SymbolGraph(String fileName, String delimiter) {
        st = new HashMap<>();
        In in = new In(fileName);
        while (!in.isEmpty()) {
            String a[] = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++)
                if (!st.containsKey(a[i]))
                    st.put(a[i], st.size());
        }
        names = new String[st.size()];
        for (String key : st.keySet())
            names[st.get(key)] = key;
        
        g = new Graph(st.size());
        in = new In(fileName);
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++)
                g.addEdge(v, st.get(a[i]));
        }
    }
    public boolean contains(String name) { return st.containsKey(name); }
    public int indexOf(String name) { return st.get(name); }
    public String name(int i) { return names[i]; }
    public Graph graph() { return g; }
    public int moviesCountOf(String actor) {
        if (!contains(actor))
            throw new IllegalArgumentException("actor " + actor + "not exist in graph");
        return g.adjSize(st.get(actor));
    }
    public Iterable<String> moviesOf(String actor) {
        if (!contains(actor))
            throw new IllegalArgumentException("actor " + actor + "not exist in graph");
        _Queue<String> q = new _Queue<>();
        for (int w : g.adj(indexOf(actor)))
            q.enqueue(name(w));
        return q;
    }
    public static void main(String[] args) {
        SymbolGraph g = new SymbolGraph("/Users/bot/Desktop/algs4-data/movies.txt", "/");
        StdOut.println("Brown, Bryan (I)");
        for (String movie : g.moviesOf("Brown, Bryan (I)"))
            StdOut.println("    " + movie);
        StdOut.println("Ruiz, Anthony");
        for (String movie : g.moviesOf("Ruiz, Anthony"))
            StdOut.println("    " + movie);
        StdOut.println("Christian, Robert");
        for (String movie : g.moviesOf("Christian, Robert"))
            StdOut.println("    " + movie);
    }
}

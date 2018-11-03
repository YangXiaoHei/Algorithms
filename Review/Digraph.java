package Review;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Review.DigraphGenerator.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Digraph {
    private int inDegree[];
    private _Bag<Integer> adjs[];
    private int E;
    private int V;
    @SuppressWarnings("unchecked")
    public Digraph(int V) {
        inDegree = new int[V];
        adjs = (_Bag<Integer>[])new _Bag[V];
        for (int i = 0; i < V; i++)
            adjs[i] = new _Bag<>();
        this.V = V;
    }
    public int V() { return V; }
    public int E() { return E; }
    public Iterable<Integer> adj(int v) { checkVertex(v); return adjs[v]; }
    public void addEdge(int v, int w) {
        checkVertex(v, w);
        adjs[v].add(w);
        inDegree[w]++;
        E++;
    }
    public boolean hasEdge(int v, int w) { return adjs[v].contains(w); }
    @SuppressWarnings("unchecked")
    public Digraph(In in) {
        int V = in.readInt();
        int E = in.readInt();
        if (V <= 0)
            throw new IllegalArgumentException("V : " + V + " <= 0");
        if (E < 1)
            throw new IllegalArgumentException(String.format("E : %d < 1", E));
        
        inDegree = new int[V];
        adjs = (_Bag<Integer>[])new _Bag[V];
        for (int i = 0; i < V; i++)
            adjs[i] = new _Bag<>();
        this.V = V;
        while (E-- > 0)
            addEdge(in.readInt(), in.readInt());
    }
    /*
     * mystr example : { 3 3 } { 2 4 } { 3 3 } { 3 1 } { 8 2 } 
     */
    public Digraph(String mystr) {
        int V = 0;
        _Queue<Edge> q = new _Queue<>();
        Pattern p = Pattern.compile("\\{[^}]+\\}");
        Matcher m = p.matcher(mystr);
        while (m.find()) {
            Pattern pp = Pattern.compile("\\d+");
            Matcher mm = pp.matcher(m.group());
            int[] twoVertices = new int[2];
            int i = 0;
            while (mm.find()) 
                twoVertices[i++] = Integer.parseInt(mm.group());
            if (twoVertices[0] > V)
                V = twoVertices[0];
            if (twoVertices[1] > V)
                V = twoVertices[1];
            q.enqueue(new Edge(twoVertices[0], twoVertices[1]));
        }
//        StdOut.println(q);
        V++;
        
        inDegree = new int[V];
        adjs = (_Bag<Integer>[])new _Bag[V];
        for (int i = 0; i < V; i++)
            adjs[i] = new _Bag<>();
        this.V = V;
        for (Edge e : q)
            addEdge(e.v, e.w);
    }
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int i = 0; i < V; i++)
            for (int w : adjs[i])
                reverse.addEdge(w, i);
        return reverse;
    }
    public int outdegree(int v) { checkVertex(v); return adjs[v].size(); }
    public int indegree(int v) { checkVertex(v); return inDegree[v]; }
    private void checkVertex(int ...s) {
        for (int i = 0; i < s.length; i++)
        if (s[i] < 0 || s[i] >= V)
            throw new IllegalArgumentException("vertex" + s[i] + " must between " + 0 + " and " + V);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++)
            sb.append(String.format("%d : %s", i, adjs[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        Digraph g = new Digraph(new In("/Users/bot/Desktop/algs4-data/mediumDG.txt"));
        StdOut.println(g);
    }
}

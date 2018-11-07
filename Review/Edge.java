package Review;

public class Edge implements Comparable<Edge> {
    public final int v;
    public final int w;
    public final double weight;
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public int compareTo(Edge that) {
        return weight < that.weight ? -1 : weight > that.weight ? 1 : 0;
    }
    public boolean isSelfLoop() { return v == w; }
    public int either() { return v; }  
    public int other(int t) { 
        if (t == w) return v;
        if (t == v) return w;
        throw new IllegalArgumentException(String.format("v must be value either %d or %d\n", v, w));
    }
    public String toString() { return String.format("{ %d %d %.0f }", v, w, weight); }
}

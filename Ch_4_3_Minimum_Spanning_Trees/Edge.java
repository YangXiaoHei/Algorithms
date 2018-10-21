package Ch_4_3_Minimum_Spanning_Trees;

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public double weight() { return weight; }
    public int either() { return v; }
    public int other(int ver) { 
        if (ver == w)
            return v;
        if (ver == v)
            return w;
       throw new RuntimeException("should not reach here!");
    } 
    public int compareTo(Edge that) {
        return weight < that.weight ? -1 : weight > that.weight ? 1 : 0;
    }
    public String toString() {
        return String.format("{%d-%d %.2f}", v, w, weight);
    }
}

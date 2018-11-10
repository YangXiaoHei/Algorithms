package Review;

public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;
    public DirectedEdge(int v, int w, double weight) { this.v = v; this.w = w; this.weight = weight; }
    public String toString() {
        return String.format("{ %d -> %d %.0f }", v, w, weight);
    }
    public double weight() { return weight; }
    public int from() { return v; }
    public int to() { return w; }
}

package Ch_4_4_Shortest_Paths;

public class DirectedEdge {
    private final int from;
    private final int to;
    private final double weight;
    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    public double weight() { return weight; }
    public int from() { return from; }
    public int to() { return to; }
    public String toString() {
        return String.format("%d->%d %.3f", from, to, weight);
    }
}

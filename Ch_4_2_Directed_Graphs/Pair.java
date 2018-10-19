package Ch_4_2_Directed_Graphs;

public class Pair {
    int v, w;
    public Pair(int v, int w) {
        this.v = v;
        this.w = w;
    }
    public String toString() {
        return String.format("%d %d", v, w);
    }
}

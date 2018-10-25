package Ch_4_3_Minimum_Spanning_Trees;

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

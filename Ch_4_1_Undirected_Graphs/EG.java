package Ch_4_1_Undirected_Graphs;
import edu.princeton.cs.algs4.*;

public class EG {
    int V;
    public EG(int V) {
        this.V = V;
    }
    public Pair next() {
        return new Pair(StdRandom.uniform(0, V),
                StdRandom.uniform(0, V));
    }
    public static void main(String[] args) {
        EG gen = new EG(13);
        for (int i = 0; i < 10; i++)
            StdOut.println(gen.next());
    }
}

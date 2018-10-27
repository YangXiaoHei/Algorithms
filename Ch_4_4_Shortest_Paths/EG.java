package Ch_4_4_Shortest_Paths;
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
    public DirectedEdge nextW() {
        return new DirectedEdge(StdRandom.uniform(0, V),
                                StdRandom.uniform(0, V), 
                                StdRandom.uniform(1, 20) * 1.0);
    }
    public static void main(String[] args) {
        EG gen = new EG(13);
        for (int i = 0; i < 10; i++)
            StdOut.println(gen.next());
    }
}

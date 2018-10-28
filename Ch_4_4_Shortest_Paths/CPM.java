package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CPM {
    public static void main(String[] args) {
        int N = StdIn.readInt(); StdIn.readLine();
        /* 每个顶点需要有起始和结束，再加一个开始和结束
         * 所以是 2N+2 */
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(2 * N + 2);
        int s = 2 * N + 1, t = 2 * N + 2;
        for (int i = 0; i < N; i++) {
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            g.addEdge(new DirectedEdge(i, i + N, duration));
            g.addEdge(new DirectedEdge(s, i, 0.0));
            g.addEdge(new DirectedEdge(i + N, t, 0.0));
            for (int j = 1; j < a.length; j++) {
                int succ = Integer.parseInt(a[j]);
                g.addEdge(new DirectedEdge(i + N, succ, 0.0));
            }
        }
        AcyclicLP lp = new AcyclicLP(g, s);
        StdOut.println("Start times: ");
        for (int i = 0; i < N; i++) 
            StdOut.printf("%4d: %5.1f\n", i, lp.disTo(i));
        StdOut.printf("Finish time: %5.1f\n", lp.disTo(t));
    }
}

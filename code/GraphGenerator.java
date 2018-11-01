package code;

import edu.princeton.cs.algs4.*;
import Tool.ArrayGenerator;

public class GraphGenerator {
    public static Graph eulerianCycle(int V, int E) {
        if (E <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one edge");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one vertex");
        Graph G = new Graph(V);
//        int[] vertices = new int[E];
//        for (int i = 0; i < E; i++)
//            vertices[i] = StdRandom.uniform(V);
//        int[] vertices = ArrayGenerator.parseInts("3    2    0    1    3    9    6    1    4    8 ");
//        int[] vertices = ArrayGenerator.parseInts("9    3    7    2    1    7    5    0    5    2  ");
        int[] vertices = ArrayGenerator.parseInts("7    5    3    7    9    6    9    7    3    6  ");
        
        for (int i = 0; i < E - 1; i++) 
            G.addEdge(vertices[i], vertices[i + 1]);
        
        G.addEdge(vertices[E - 1], vertices[0]);
        StdOut.println("-----------------------");
        for (int i = 0; i < vertices.length; i++)
            StdOut.printf("%-5d", i);
        StdOut.println();
        for (int j = 0; j < vertices.length; j++)
            StdOut.printf("%-5d", vertices[j]);
        StdOut.println("\n-----------------------");
        return G;
    }
    public static Graph eulerianPath(int V, int E) {
        if (E < 0)
            throw new IllegalArgumentException("");
        if (V <= 0)
            throw new IllegalArgumentException("");
        Graph g = new Graph(V);
        int[] vertices = new int[E + 1];
        for (int i = 0; i < E + 1; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E; i++)
            g.addEdge(vertices[i], vertices[i + 1]);
        return g;
    }
    public static void main(String[] args) {
        StdOut.println(GraphGenerator.eulerianCycle(10, 10));
        StdOut.println(GraphGenerator.eulerianPath(5, 10));
    }
}

package Review;

import edu.princeton.cs.algs4.StdOut;

public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph("/Users/bot/Desktop/algs4-data/movies.txt", "/");
        Graph g = sg.graph();
        BreadthFirstPath bfs = new BreadthFirstPath(g, sg.indexOf("Bacon, Kevin"));
        
        String name = "Boutsikaris, Dennis";
        if (!sg.contains(name)) {
            StdOut.println("not in database");
            return;
        }
        
        int to = sg.indexOf(name);
        StdOut.println(name);
        if (bfs.hasPathTo(to)) {
            for (int w : bfs.pathTo(to)) {
                StdOut.println("   " + sg.name(w));
            }
        } else {
            StdOut.println("not connected!");
        }
    }
}

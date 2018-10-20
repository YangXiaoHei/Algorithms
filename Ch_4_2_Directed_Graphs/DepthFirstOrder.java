package Ch_4_2_Directed_Graphs;

import edu.princeton.cs.algs4.StdOut;

public class DepthFirstOrder {
    private boolean[] marked;
    private __Queue<Integer> pre;
    private __Queue<Integer> post;
    private __Stack<Integer> reversePost;
    public DepthFirstOrder(__DiGraph g) {
        pre = new __Queue<>();
        post = new __Queue<>();
        reversePost = new __Stack<>();
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++)
            if (!marked[i])
                dfs(g, i);
    }
    private void dfs(__DiGraph g, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (int w : g.adj(v)) 
            if  (!marked[w])
                dfs(g, w);
        post.enqueue(v);
        reversePost.push(v);
    }
    public Iterable<Integer> pre() { return pre; }
    public Iterable<Integer> post() { return post; }
    public Iterable<Integer> reversePost() { return reversePost; }
    
    public static void main(String[] args) {
        __DiGraph g = new __DiGraph(13);
        g.genOneWayRandom(15);
//        g.addEdge(0, 1);
//        g.addEdge(0, 5);
//        g.addEdge(0, 6);
//        g.addEdge(2, 0);
//        g.addEdge(2, 3);
//        g.addEdge(3, 5);
//        g.addEdge(5, 4);
//        g.addEdge(6, 4);
//        g.addEdge(6, 9);
//        g.addEdge(7, 6);
//        g.addEdge(8, 7);
//        g.addEdge(9, 11);
//        g.addEdge(9, 12);
//        g.addEdge(9, 10);
//        g.addEdge(11, 12);
        StdOut.print(g);
        Iterable<Integer> cycle = null;
        if (( cycle = g.cycle()) == null) {
            StdOut.println("无环");
        } else {
            StdOut.println("有环");
            for (int w : cycle)
                StdOut.print(w + "  ");
            StdOut.println();
        }
        
        DepthFirstOrder dfo = new DepthFirstOrder(g);
//        StdOut.println("\n---------- pre ----------\n");
//        for (int w : dfo.pre())
//            StdOut.print(w + "  ");
//        StdOut.println("\n---------- post ----------\n");
//        for (int w : dfo.post())
//            StdOut.print(w + "  ");
        StdOut.println("\n---------- reversePost ----------\n");
        for (int w : dfo.reversePost())
            StdOut.println(w);
    }
    /*
     *  0 : 1 
        1 : 7 8 4 
        2 : 
        3 : 7 
        4 : 7 6 2 
        5 : 
        6 : 2 
        7 : 
        8 : 3 5 
        9 : 5 1 
        
        **********0
        **********1
        **********7
        将 7 清理出栈
        **********8
        **********3
        将 3 清理出栈
        **********5
        将 5 清理出栈
        将 8 清理出栈
        **********4
        **********6
        **********2
        将 2 清理出栈
        将 6 清理出栈
        将 4 清理出栈
        将 1 清理出栈
        将 0 清理出栈
        ------0
        **********9
        将 9 清理出栈
        ------9
        无环
        
        ---------- pre ----------
        
        0  1  7  8  3  5  4  6  2  9  
        ---------- post ----------
        
        7  3  5  8  2  6  4  1  0  9  
        ---------- reversePost ----------
        
        9  0  1  4  6  2  8  5  3  7  
     */
}

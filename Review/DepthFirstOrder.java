package Review;

import edu.princeton.cs.algs4.StdOut;

public class DepthFirstOrder {
    private _Queue<Integer> pre;
    private _Queue<Integer> post;
    private _Stack<Integer> postReverse;
    private Digraph g;
    private boolean marked[];
    public DepthFirstOrder(Digraph g, int s) {
        this.g = g;
        pre = new _Queue<>();
        post = new _Queue<>();
        postReverse = new _Stack<>();
        marked = new boolean[g.V()];
        dfs(s);
    }
    private void dfs(int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (int w : g.adj(v)) 
            if (!marked[w]) 
                dfs(w);
        post.enqueue(v);
        postReverse.push(v);
    }
    public Iterable<Integer> postOrder() { return post; }
    public Iterable<Integer> preOrder() { return pre; }
    public Iterable<Integer> postReverseOrder() { return postReverse; }
    public static void main(String[] args) {
        Digraph g = DigraphGenerator.simple(10, 10);
        StdOut.println(g);
        DepthFirstOrder dfo = new DepthFirstOrder(g, 0);
        StdOut.print("前序: ");
        for (int w : dfo.preOrder())
            StdOut.print(w + " ");
        StdOut.println();
        
        StdOut.print("后序: ");
        for (int w : dfo.postOrder())
            StdOut.print(w + " ");
        StdOut.println();
        
        StdOut.print("逆后序: ");
        for (int w : dfo.postReverseOrder())
            StdOut.print(w + " ");
    }
}

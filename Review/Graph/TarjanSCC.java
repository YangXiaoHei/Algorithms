package Review.Graph;

import edu.princeton.cs.algs4.StdOut;

public class TarjanSCC {
    private boolean[] marked;
    private int[] low;
    private Digraph g;
    private int pre;
    private int count;
    private int[] id;
    private _Stack<Integer> S;
    public TarjanSCC(Digraph g) {
        this.g = g;
        id = new int[g.V()];
        marked = new boolean[g.V()];
        low = new int[g.V()];
        S = new _Stack<>();
        for (int i = 0; i < g.V(); i++)
            if (!marked[i])
                dfs(i);
    }
    public int count() { return count; }
    public boolean areStronglyConnected(int v, int w) { checkVertex(v, w); return id[v] == id[w]; }
    public int id(int v) { checkVertex(v); return id[v]; }
    private void checkVertex(int ...v) {
        for (int i = 0; i < v.length; i++)
            if (v[i] < 0 || v[i] >= g.V())
                throw new IllegalArgumentException(String.format("vertex must between %d and %d", 0, g.V() - 1));
    }
    private void dfs(int v) {
        marked[v] = true;
        int min = pre++;  /* 时间戳递增 */
        low[v] = min;
        /*
         * 用栈来保留依次访问的节点，便于回溯时弹出作为
         * 一组强连通分量中的节点
         */
        S.push(v);
        for (int w : g.adj(v)) {
            if (!marked[w])
                dfs(w);
            /*
             * 遍历邻边时发现一个已标记的顶点，其实并不能说明
             * 发现了环，比如
             *     a
             *     .
             *  b . . c
             *  
             * 邻接表为
             *   a : b
             *   a : c
             *   b : c
             *   
             *  a -> c，然后 c 返回，a -> b, b -> c 发现 c 已经被标记
             *  然而此时并不构成一个环，b -> c 这条边称为横叉边，
             *  而只有后向边能构成环
             *  
             *  遇到已标记顶点时采取的措施是，判断该已标记顶点
             *  是否有后向边，随着递归的深入，递增的时间戳本来不可能
             *  大于 low[w]，若出现该情况，说明邻接点必然有回向边，
             *  那么将该层的时间戳置为搜索树中辈分最大的祖宗顶点的时间戳
             */
            if (low[w] < min)
                min = low[w];
        }
        /*
         *  访问了一圈邻接点，时间戳居然变小了，说明此刻正在回溯
         */
        if (min < low[v]) {
            low[v] = min;
            return;
        }
        /*
         * 执行到这里，说明回溯到了 min == low[v] 的位置
         * 该位置是环的 “起点”，一个强连通分量的分界线
         */
        int w;
        do {
            w = S.pop();
            /*
             * 这一句代码很重要，这样赋值是为了
             * 避免强连通分量 A 的顶点在访问强连通分量 B
             * 被标记的顶点时，错把 B 中被访问的顶点纳入
             * A 中的错误，因为这样赋值后，
             * 
             * if (low[w] < min)
             *   min = low[w];
             * 
             *  上面的判断条件永远不会满足
             *
             */
            low[w] = g.V(); 
            id[w] = count;
        } while (w != v);
        count++;
    }
    public static void main(String[] args) {
        Digraph g = new Digraph("{0 7} { 1 6} {3  6} {3 5} { 3 0} {4 6} { 4 1} {5 3} { 6 4} { 6 5}");
        StdOut.println(g);
        TarjanSCC scc = new TarjanSCC(g);
        StdOut.println(scc.count());
    }
}

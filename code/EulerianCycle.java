package code;

import edu.princeton.cs.algs4.StdOut;

public class EulerianCycle {
    private Graph g;
    /*
     * 之所以要定义该 Edge 数据结构，因为下面需要以边为单位（而非以顶点为单位）
     * 进行 dfs ，目的是 "一笔画"。之所以要以边为单位进行 dfs 是因为
     * 一笔画可能需要穿越已经被访问的顶点
     */
    private static class Edge {
        private final int v;
        private final int w;
        private static int counter;
        private final int id = counter++;
        private boolean isUsed;
        public Edge(int v, int w) { this.v = v; this.w = w; }
        public int other(int t) { 
            if (t == v) return w;
            if (t == w) return v;
            throw new IllegalArgumentException("v must between " + v + " and " + w);
        }
        public String toString() { return String.format("{%d %d}-%d ", v, w, id); }
    }
    private _Stack<Integer> cycle;
    public EulerianCycle(Graph g) {
       this.g = g;
       cycle = new _Stack<>();
       
       /*
        * 必须对顶点进行度数的检查，否则欧拉路径会被识别成欧拉环
        * 比如欧拉路径 1 - 2 - 3 - 4 会成功被添加到 cycle 路径中
        * 而我们需要的是欧拉环比如 1 - 2 - 3 - 4 - 1
        */
       for (int i = 0; i < g.V(); i++)
           if (g.degree(i) % 2 != 0)
               return;
       
       /*
        * 构造一个以 Edge 对象为邻接表以便能以边为单位进行 dfs
        */
       @SuppressWarnings("unchecked")
       _Queue<Edge> adjs[] = (_Queue<Edge>[])new _Queue[g.V()];
       for (int i = 0; i < g.V(); i++)
           adjs[i] = new _Queue<>();
       int selfLoop = 0;
       for (int i = 0; i < g.V(); i++) {
           for (int w : g.adj(i)) {
               if (w == i) {
                   if (selfLoop % 2 == 0) {
                       Edge e = new Edge(i, i);
                       adjs[i].enqueue(e);
                       adjs[i].enqueue(e);
                   }
                   selfLoop++;
               } else if (w > i) {
                   Edge e = new Edge(w, i);
                   adjs[w].enqueue(e);
                   adjs[i].enqueue(e);
               }
           }
       }
              
       StdOut.println("--------- Queue -------");
       for (int i = 0; i < adjs.length; i++)
           StdOut.printf("%-2d : %s\n",i, adjs[i]);
       StdOut.println("---------------------");
       
       int nonisolate = 0;
       for (int i = 0; i < g.V(); i++) {
           if (g.degree(i) != 0) {
               nonisolate = i;
               break;
           }
       }
       StdOut.printf("nonisolate vertex = %d\n\n", nonisolate);
               
       /*
        * 以边为单位进行 dfs，但是记录沿途经历的顶点
        */
       _Stack<Integer> S = new _Stack<>();
       S.push(nonisolate);
       while (!S.isEmpty()) {
           int from = S.pop();
           /*
            * 第一次跳出循环时添加进来的
            * 是一笔画的最后一个顶点，但沿途可能有
            * 没有走的分支，所以一步步后退，若有先前
            * 忽略的分支，那么就进去
            */
           while (!adjs[from].isEmpty()) {
               Edge e = adjs[from].dequeue();
               /*
                *  走过的边直接把它删掉，要注意
                *  无向边需要删掉两个方向，因为
                *  添加时是同一个引用，所以直接
                *  用 isUsed 来判断
                */
               if (e.isUsed) continue;
               e.isUsed = true;
               S.push(from);
               from = e.other(from);
           }
           /*
            * 放进 cycle 的顺序依次是一笔画经历的
            * 倒数第一个顶点，倒数第二个，倒数第三个
            * ...
            */
           StdOut.printf("cycle constructed by %d\n", from);
           cycle.push(from);
       }
       
       /*
        * 把一笔画摊开
        * 
        * 1 - 2 - 1 - 3 - 4 - 5 - 4 - 7 - 9
        * 
        * 因此连接 V 顶点至少需要 E - 1 条边
        */
       if (cycle.size() != g.E() + 1) {
           cycle = null;
           StdOut.printf("cannot go through all vertices by a nonduplicate path");
       }
    }
    public Iterable<Integer> cycle() {
        if (!hasEulerianCycle())
            throw new RuntimeException("eulerianCycle not exist");
        return cycle;
    }
    public boolean hasEulerianCycle() {
        return cycle != null;
    }
    public static void main(String[] args) {
        Graph g = GraphGenerator.eulerianCycle(10, 10);
        StdOut.print(g);
        EulerianCycle c = new EulerianCycle(g);
        if (c.hasEulerianCycle())
            for (int w : c.cycle())
                StdOut.println(w);
    }
}

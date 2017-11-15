package 第一章_Union_Find_算法;

public class Practise_1_5_15 {
    public static void main(String[] args) {
        /*
         * 对于加权的 quick-union， 我们来研究下，什么叫做最坏的情况
         * 
         * 要明白什么情况是最坏的，可以先寻找一个最好的情况
         * 首先观察一下 union(int p, int q) 操作 
         * 
         * void union(int p, int q) {
         *     int pRoot = find(p);
         *     int qRoot = find(q);
         *     if (pRoot == qRoot) return;
         *     if (size[pRoot] < size[qRoot]) {
         *         id[pRoot] = qRoot;
         *         size[qRoot] += size[pRoot];
         *     } else {
         *         id[qRoot] = pRoot;
         *         size[pRoot] += size[qRoot];
         *     }
         * }
         * 对于上述 union 操作来说，除去 find(int) 两步操作外，其余操作均为常数级，
         * 我们来观察 find 操作
         * 
         * int find(int p) {
         *     while (p != id[p])
         *          p = id[p];
         *     return p;
         * }
         * 
         * 可以发现，find 操作是一个从当前结点，向上追溯至根结点的过程，它的执行路径正比于当前结点的深度
         * 因此我们可以得出，在最好的情况下，find 操作理应仅仅在 while 仅执行一次后就停止，此时当前结点
         * 的父亲即为根结点，因此 find 操作可以在常数时间内完成，整个 union 操作不过常数时间
         * 我们用 0 ~ 9 这十个数字构造一个最好的情况
         * 
         * union(0, 1)
         * union(0, 2)
         * union(0, 3)
         * union(0, 4)
         * union(0, 5)
         * union(0, 6)
         * union(0, 7)
         * union(0, 8)
         * union(0, 9)
         * 
         * 明白了加权 quick-union 的时间消耗主要在 find 操作的寻根上，我们可以知道，要构造出一个最坏情况
         * 应该让每次子树合并形成的新树深度最大，因此每次合并的子树规模应该相同，由次可以构造出
         * 
         * 结点数 N = 2^0  每层结点数 :     1 
         * 结点数 N = 2^1  每层结点数 :    1 1
         * 结点数 N = 2^2  每层结点数 :   1 2 1
         * 结点数 N = 2^3  每层结点数 :  1 3 3 1
         * 结点数 N = 2^4  每层结点数 : 1 4 6 4 1
         * ...
         * 结点数 N = 2^n  每层结点数 : 1 C(n,1) C(n, 2) ... C(n, 2), C(n, 1), 1
         * 
         * 由杨辉三角性质可以得到，每层的结点数为 C(n, m) n 是二项式系数
         * PS : 二项式展开式为 (a + b)^n = C(n, 0)a^nb^0 + C(n, 1)a^n-1b^1 + ...C(n, n)a^0b^n
         * 
         * 树结点的平均深度为 : 
         * 
         *  Σ[(k + 1) * C(n, k)] / 2^n
         * 
         * n 代表杨辉三角的第几层，n的取值范围是 [0,∞)
         * k 代表杨辉三角第 n 层的第 k 个，k的取值范围是 [0,∞) 
         * 
         * 意思是每个节点乘高度的和，除以总结点个数
         * 由杨辉三角性质可以得到，第 n 层的和为 2^n
         * 用 k + 1 表示结点的高度
         * 
         * 
         */
    }
}

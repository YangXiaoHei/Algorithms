package Ch_3_2_Binary_Search_Trees;

import edu.princeton.cs.algs4.*;

public class Practise_3_2_06 {
    static class BST <K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right;
            int size;
            Node (K kk, V vv, int sizee) { k = kk; v = vv; size = sizee; }
        }
        private Node root;
        public void put(K k, V v) { root = put(root, k, v); }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        public int height() { return height(root); }
        private int height(Node n) { 
            if (n == null) return -1;
            return 1 + Math.max(height(n.left), height(n.right));
        }
        private Node put(Node n, K k, V v) {
            if (n == null) return new Node(k, v, 1);
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n.left = put(n.left, k, v);
            else if (cmp > 0) n.right = put(n.right, k, v);
            else    n.v = v;
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendString(sb, root);
            return sb.toString();
        }
        private void appendString(StringBuilder sb, Node n) {
            if (n == null) return;
            appendString(sb, n.left);
            sb.append(String.format("{ %3s  %3s  size = %3d }\n", n.k, n.v, n.size));
            appendString(sb, n.right);
        }
    }
    static class BST2<K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right;
            int size, height;
            Node (K kk, V vv) { k = kk; v = vv; size = 1; height = 0; }
            public String toString() { return String.format("{ %3s %s size = %3d  height = %3d }", k, v, size, height); }
        }
        private Node root;
        public void put(K k, V v) { root = put(root, k, v); }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        public int height() { return height(root); }
        public int height(Node n) { return n == null ? -1 : n.height; }
        private Node put(Node n, K k, V v) {
            if (n == null) return new Node(k, v);
            int cmp = k.compareTo(n.k);
            if      (cmp < 0)  n.left = put(n.left, k, v);
            else if (cmp > 0) n.right = put(n.right, k, v);
            else    n.v = v;
            n.size = 1 + size(n.left) + size(n.right);
            n.height = 1 + Math.max(height(n.left), height(n.right));
            return n;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendString(sb, root);
            return sb.toString();
        }
        private void appendString(StringBuilder sb, Node n) {
            if (n == null) return;
            appendString(sb, n.left);
            sb.append(String.format("{ %3s  %3s  size = %3d }\n", n.k, n.v, n.size));
            appendString(sb, n.right);
        }
    }
    /*
     * 递归实现查询树高
     * 
     * 所需时间为线性级别，因为需要访问所有结点
     * 所需空间即递归调用函数所占空间，树有多深，递归调用就有多深
     * 
     */
    public static void imp1Test() {
        BST<Integer, Integer> bst = new BST<>();
        bst.put(1, 0);
        bst.put(2, 0);
        bst.put(3, 0);
        bst.put(4, 0);
        bst.put(5, 0);
        bst.put(6, 0);
        bst.put(9, 0);
        bst.put(7, 0);
        bst.put(8, 0);
        bst.put(10, 0);
        StdOut.println(bst);
        StdOut.println(bst.height());
    }
    /*
     * 在每个结点中保存一个变量来记录树高
     * 
     * 每插入一个结点，都会随着递归逐层返回更新该路径上的每个结点的树高
     * 因此查询时间为常数，只需要取出结点中记录的树高即可
     * 而空间为线性级别，因为在 N 个结点中都需要保留一个高度变量
     * 
     */
    public static void imp2Test() {
        BST2<Integer, Integer> bst = new BST2<>();
        bst.put(1, 0);
        bst.put(2, 0);
        bst.put(3, 0);
        bst.put(4, 0);
        bst.put(5, 0);
        bst.put(6, 0);
        bst.put(9, 0);
        bst.put(7, 0);
        bst.put(8, 0);
        bst.put(10, 0);
        StdOut.println(bst);
        StdOut.println(bst.height());
    }
    public static void main(String[] args) {
        imp2Test();
        
    }
}

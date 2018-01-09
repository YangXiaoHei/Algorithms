package 第三章_二叉查找树;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_2_07 {
    static class BST <K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right;
            int size, height;
            Node (K kk, V vv) {
                k = kk; v = vv;
                size = 1; height = 0;
            }
        }
        private Node root;
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        public int height() { return height(root); }
        private int height(Node n) { return n == null ? -1 : n.height; }
        public void put(K k, V v) { root = put(root, k, v); }
        private Node put(Node n, K k, V v) {
            if (n == null) return new Node(k, v);
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n.left = put(n.left, k, v);
            else if (cmp > 0) n.right = put(n.right, k, v);
            else    n.v = v;
            n.size = 1 + size(n.left) + size(n.right);
            n.height = 1 + Math.max(height(n.left), height(n.right));
            return n;
        }
        public int avgCompares() { return internalPathLength() / size() + 1; }
        public int internalPathLength() {
            return internalPathLength(root, 0);
        }
        private int internalPathLength(Node d, int depth) {
            if (d == null) return 0;
            int curLevel = depth;
            depth += internalPathLength(d.left, curLevel + 1);
            depth += internalPathLength(d.right, curLevel + 1);
            return depth;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendString(sb, root);
            return sb.toString();
        }
        private void appendString(StringBuilder sb, Node n) {
            if (n == null) return;
            appendString(sb, n.left);
            sb.append(String.format("{ %3s  %3s  size = %3d height = %3d }\n", n.k, n.v, n.size, n.height));
            appendString(sb, n.right);
        }
    }
    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(4, 0);
        bst.put(2, 0);
        bst.put(6, 0);
        bst.put(1, 0);
        bst.put(3, 0);
        bst.put(5, 0);
        bst.put(7, 0);
        StdOut.println(bst);
        StdOut.println(bst.internalPathLength());
        StdOut.println(bst.avgCompares());
    }
}

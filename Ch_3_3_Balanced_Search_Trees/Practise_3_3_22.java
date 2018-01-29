package Ch_3_3_Balanced_Search_Trees;

import edu.princeton.cs.algs4.*;
import static Tool.ArrayGenerator.*;;

public class Practise_3_3_22 {
    static class BSTree<K extends Comparable<K>, V> {
        private class Node {
            int size; Node left, right;
            K key; V val;
            Node(K k, V v) { key = k; val = v; size = 1; }
            public String toString() { return String.format("%5s%5d\n", key, size); }
        }
        private Node root;
        public boolean isEmpty() { return root == null; } 
        public int height() { return height(root); }
        public int height(Node n) {
            if (n == null) return -1;
            return 1 + Math.max(height(n.left), height(n.right));
        }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        // 假设不会插入重复键
        public void put(K k, V v) {
            root = put(root, k, v);
        }
        private Node put(Node n, K k, V v) {
            if (n == null) return new Node(k, v);
            int cmp = k.compareTo(n.key);
            if      (cmp < 0) n.left = put(n.left, k, v);
            else if (cmp > 0) n.right = put(n.right, k, v);
            else    n.val = v;
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public String toString() {
            if (isEmpty()) return "Empty Tree";
            StringBuilder sb = new StringBuilder();
            Queue<Node> q = new Queue<>();
            q.enqueue(root); Node n = null;
            while (!q.isEmpty()) {
                sb.append(n = q.dequeue());
                if (n.left != null)  q.enqueue(n.left);
                if (n.right != null) q.enqueue(n.right);
            }
            return sb.toString();
        }
    }
    static class RBTree<K extends Comparable<K>, V> {
        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private class Node {
            K key; V val;
            int size; Node left, right;
            boolean color;
            Node (K k, V v) { key = k; val = v; size = 1; color = RED; }
            public String toString() { return String.format("%5s%5s%5d\n", key, color ? "红" : "黑", size); }
        }
        private Node root;
        public boolean isEmpty() { return root == null; } 
        public int height() { return height(root); }
        public int height(Node n) {
            if (n == null) return -1;
            return 1 + Math.max(height(n.left), height(n.right));
        }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        private boolean isRed(Node h) { return h != null && h.color; }
        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            x.size = h.size;
            h.size = 1 + size(h.left) + size(h.right);
            return x;
        }
        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            x.size = h.size;
            h.size = 1 + size(h.left) + size(h.right);
            return x;
        }
        private void flipColors(Node h) {
            h.color = !h.color;
            h.left.color = !h.left.color;
            h.right.color = !h.right.color;
        }
        public void put(K k, V v) {
            root = put(root, k, v);
            root.color = BLACK;
        }
        private Node put(Node n, K k, V v) {
            if (n == null) return new Node(k, v);
            int cmp = k.compareTo(n.key);
            if      (cmp < 0) n.left = put(n.left, k, v);
            else if (cmp > 0) n.right = put(n.right, k, v);
            else    n.val = v;
            if (!isRed(n.left) && isRed(n.right))    n = rotateLeft(n);
            if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
            if (isRed(n.left) && isRed(n.right))     flipColors(n);
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public String toString() {
            if (isEmpty()) return "Empty Tree";
            StringBuilder sb = new StringBuilder();
            Queue<Node> q = new Queue<>();
            q.enqueue(root); Node n = null;
            while (!q.isEmpty()) {
                sb.append(n = q.dequeue());
                if (n.left != null)  q.enqueue(n.left);
                if (n.right != null) q.enqueue(n.right);
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        while (true) {
            Integer[] a = IntegersNoDupli(10, 0, 20);
            RBTree<Integer, Integer> rb = new RBTree<>();
            for (Integer ss : a) rb.put(ss, 1);
            BSTree<Integer, Integer> bs = new BSTree<>();
            for (Integer ss : a) bs.put(ss, 1);
            if (bs.height() < rb.height()) {
                print(a);
                StdOut.printf("红黑树高度 : %d\n", rb.height());
                StdOut.printf("二叉查找树高度 : %d\n", bs.height());
                break;
            }
        }
    }
    // output
    /*
     *  0      1      2      3      4      5      6      7      8      9      
        8      5      15     4      0      12     13     11     18     16     
        红黑树高度 : 4
        二叉查找树高度 : 3

     */
}

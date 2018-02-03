package Ch_3_3_Balanced_Search_Trees;

import java.util.NoSuchElementException;
import java.util.LinkedList;
import edu.princeton.cs.algs4.*;
import static Tool.ArrayGenerator.*;

public class Practise_3_3_24 {
    static class RBTree <K extends Comparable<K>, V> {
        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private class Node {
            int size, height;
            Node left, right, parent;
            K key; V val;
            boolean color;
            Node (K k, V v) { key = k; val = v; size = 1; color = RED; height = 0; }
            public String toString() {
                return String.format("{%5s%5s%4d%4d%5s}\n", key, color ? "红" : "黑", height, size, (parent != null ? parent.key : null));
            }
        }
        private Node root;
        public boolean isEmpty() { return root == null; }
        public int size() { return size(root); }
        private int size(Node h) { return h == null ? 0 : h.size; }
        public int ipl() {
            return ipl(root, 0);
        }
        private int ipl(Node n, int depth) {
            if (n == null) return 0;
            int cur = depth;
            depth += ipl(n.left, cur + 1);
            depth += ipl(n.right, cur + 1);
            return depth;
        }
        public Queue<Node> collectAllLeaf() {
            Queue<Node> q = new Queue<>();
            collectAllLeaf(root, q);
            return q;
        }
        private void collectAllLeaf(Node h, Queue<Node> q) {
            if (h == null) return;
            collectAllLeaf(h.left, q);
            if (h.left == null || h.right == null) q.enqueue(h);
            collectAllLeaf(h.right, q);
        }
        public int depthFromRootTo(Node h) {
            int depth = 0;
            while (h != null) {
                ++depth;
                h = h.parent;
            }
            return depth;
        }
        public int countEmptyLink(Queue<Node> nodesContainEmptyLink) {
            int count = 0;
            for (Node n : nodesContainEmptyLink) {
                if (n.left == null && n.right == null)
                    count += 2;
                else if (n.left == null || n.right == null)
                    count++;
                else    continue;
            }
            return count;
        }
        public double avgOfAllEmptyLinkDepth() {
            int sum = 0;
            Queue<Node> nodes = collectAllLeaf();
            for (Node n : nodes) {
                int depth = depthFromRootTo(n);
                if (n.left == null && n.right == null)
                    sum += (depth + 1) * 2;
                else if (n.left == null || n.right == null)
                    sum += (depth + 1);
            }
            return sum * 1.0 / countEmptyLink(nodes);
        }
        public void printAllEmptyLinkDepth() {
            Queue<Node> leaves = collectAllLeaf();
            for (Node n : leaves) {
                int depth = depthFromRootTo(n);
                StdOut.printf("叶子%3s  空链接深度为 : %d\n", n.key, ++depth);
            }
        }
        public int avgCompares() { return ipl() / size(); }
        public int height() { return height(root); }
        private int height(Node h) { return h == null ? -1 : h.height; }
        private boolean isRed(Node h) { return h != null && h.color; }
        private void flipColors(Node h) {
            h.color = !h.color;
            h.left.color = !h.left.color;
            h.right.color = !h.right.color;
        }
        private Node rotateRight(Node h) {
            Node x = h.left;
            
            h.left = x.right;
            if (x.right != null)
                x.right.parent = h; // 设置父链接
            
            x.right = h;
            h.parent = x.right; // 设置父链接
            
            x.size = h.size;
            h.size = 1 + size(h.left) + size(h.right);
            x.color = h.color;
            h.color = RED;
            h.height = 1 + Math.max(height(h.left), height(h.right));
            x.height = 1 + Math.max(height(x.left), height(x.right));
            return x;
        }
        private Node rotateLeft(Node h) {
            Node x = h.right;
            
            h.right = x.left;
            if (x.left != null)
                x.left.parent = h;
            
            x.left = h;
            h.parent = x;
            
            x.size = h.size;
            h.size = 1 + size(h.left) + size(h.right);
            x.color = h.color;
            h.color = RED;
            h.height = 1 + Math.max(height(h.left), height(h.right));
            x.height = 1 + Math.max(height(x.left), height(x.right));
            return x;
        }
        private Node balance(Node h) {
            if (isRed(h.right))                      h = rotateLeft(h);
            if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
            if (isRed(h.left) && isRed(h.right))     flipColors(h);
            h.size = 1 + size(h.left) + size(h.right);
            h.height = 1 + Math.max(height(h.left), height(h.right));
            return h;
        }
        private Node moveRedRight(Node h) {
            flipColors(h);
            if (isRed(h.left.left)) {
                h = rotateRight(h);
                flipColors(h);
            }
            return h;
        }
        private Node moveRedLeft(Node h) {
            flipColors(h);
            if (isRed(h.right.left)) {
                h.right = rotateLeft(h.right);
                h = rotateRight(h);
                flipColors(h);
            }
            return h;
        }
        public void put(K k, V v) {
            if (k == null) throw new IllegalArgumentException();
            if (v == null) {
                delete(k);
                return;
            }
            root = put(root, k, v);
            root.color = BLACK;
            root.parent = null;
        }
        private Node put(Node n, K k, V v) {
            if (n == null) return new Node(k, v);
            int cmp = k.compareTo(n.key);
            if      (cmp < 0) n.left = put(n.left, k, v);
            else if (cmp > 0) n.right = put(n.right, k, v);
            else    n.val = v;
            if (!isRed(n.left) && isRed(n.right)) n = rotateLeft(n);
            if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
            if (isRed(n.left) && isRed(n.right)) flipColors(n);
            n.height = 1 + Math.max(height(n.left), height(n.right));
            n.size = 1 + size(n.left) + size(n.right);
            if (n.left != null)  n.left.parent = n;
            if (n.right != null) n.right.parent = n;
            return n;
        }
        private Node min(Node h) {
            while (h.left != null) h = h.left;
            return h;
        }
        public void deleteMin() {
            if (root == null) throw new NoSuchElementException();
            if (!isRed(root.left) && !isRed(root.right))
                root.color = RED;
            root = deleteMin(root);
            if (root != null) root.color = BLACK;
        }
        private Node deleteMin(Node h) {
            if (h.left == null) return null;
            if (!isRed(h.left) && !isRed(h.left.left)) 
                h = moveRedLeft(h);
            h.left = deleteMin(h.left);
            return balance(h);
        }
        public void deleteMax() {
            if (root == null) throw new NoSuchElementException();
            if (!isRed(root.left) && !isRed(root.right))
                root.color = RED;
            root = deleteMax(root);
            if (root != null) root.color = BLACK;
        }
        private Node deleteMax(Node h) {
            if (isRed(h.left)) h = rotateRight(h);
            if (h.right == null) return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            h.right = deleteMax(h.right);
            return balance(h);
        }
        public void delete(K k) {
            if (root == null) throw new NoSuchElementException();
            if (!isRed(root.left) && !isRed(root.right))
                root.color = RED;
            root = delete(root, k);
            if (root != null) root.color = BLACK;
        }
        private Node delete(Node n, K k) {
            if (k.compareTo(n.key) < 0) {
                if (!isRed(n.left) && !isRed(n.left.left))
                    n = moveRedLeft(n);
                n.left = delete(n.left, k);
            } else {
                if (isRed(n.left)) n = rotateRight(n);
                if (n.right == null && k.compareTo(n.key) == 0) return null;
                if (!isRed(n.right) && !isRed(n.right.left)) n = moveRedRight(n);
                if (k.compareTo(n.key) == 0) {
                    Node x = min(n.right);
                    n.key = x.key;
                    n.val = x.val;
                    n.right = deleteMin(n.right);
                } else {
                    n.right = delete(n.right, k);
                }
            }
            return balance(n);
        }
        public String toString() {
            if (root == null) return "Empty Tree";
            StringBuilder sb = new StringBuilder();
            Queue<Node> q = new Queue<>();
            Node h = null; q.enqueue(root);
            while (!q.isEmpty()) {
                sb.append(h = q.dequeue());
                if (h.left != null) q.enqueue(h.left);
                if (h.right != null) q.enqueue(h.right);
            }
            return sb.toString();
        }
    }
    public static void parentTest() {
        String[] a = Alphbets.parseAlphbets("S E A R C H X M P L");
        StdRandom.shuffle(a);
        RBTree<String, Integer> rb = new RBTree<>();
        for (String ss : a) rb.put(ss, 1);
        StdOut.println(rb);
        StdOut.printf("2logN = %d\n", 2 * (int)(Math.log(a.length) / Math.log(2)));
        rb.printAllEmptyLinkDepth();
        double dd = rb.avgOfAllEmptyLinkDepth();
        StdOut.printf("红黑树实际情况 : %f\n", dd);
    }
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        RBTree<Integer, Integer> tmp = null;
        while (true) {
            list.clear();
            Integer[] a = Integers(1, 10);
            for (Integer i : a) list.add(i);
            
            RBTree<Integer, Integer> rb = new RBTree<>();
            tmp = rb;
            for (Integer ss : a) rb.put(ss, 1);
            
            int d = 2 * (int)(Math.log(a.length) / Math.log(2));
            double dd = rb.avgOfAllEmptyLinkDepth();
            if (Math.abs(d - dd) < 1.4) {
                StdOut.printf("红黑树最坏情况 : %d\n", d);
                StdOut.printf("红黑树实际情况 : %f\n", dd);
                break;
            }  
        }
        tmp.printAllEmptyLinkDepth();
        StdOut.println(list);
    }
    // output
    /*
     *  红黑树最坏情况 : 6
        红黑树实际情况 : 4.636364
        叶子  1  空链接深度为 : 6
        叶子  2  空链接深度为 : 5
        叶子  4  空链接深度为 : 5
        叶子  6  空链接深度为 : 4
        叶子  8  空链接深度为 : 4
        叶子 10  空链接深度为 : 4
        [2, 3, 4, 10, 7, 5, 1, 9, 8, 6]
     */
}

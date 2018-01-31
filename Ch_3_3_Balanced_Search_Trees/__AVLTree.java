package Ch_3_3_Balanced_Search_Trees;

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;
import static Tool.ArrayGenerator.Alphbets.*;

public class __AVLTree <K extends Comparable<K>, V> {
    private class Node {
        K key; V val;
        int size, height;
        Node left, right;
        Node (K k, V v) { key = k; val = v; size = 1; height = 0; }
        public String toString() { return String.format("%5s%4d%4d\n", key, height, size); }
    }
    private Node root;
    public boolean isEmpty() { return root == null; }
    public int height() { return height(root); }
    private int height(Node n) { return n == null ? -1 : n.height; }
    public int size() { return size(root); }
    private int size(Node n) { return n == null ? 0 : n.size; }
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        h.height = 1 + Math.max(height(h.left), height(h.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        h.height = 1 + Math.max(height(h.left), height(h.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }
    private int balanceFactor(Node h) {
        return height(h.left) - height(h.right);
    }
    private Node balance(Node h) {
        if (balanceFactor(h) < -1) {
            if (balanceFactor(h.right) > 0) h.right = rotateRight(h.right);
            h = rotateLeft(h);
        } else if (balanceFactor(h) > 1) {
            if (balanceFactor(h.left) < 0) h.left = rotateLeft(h.left);
            h = rotateRight(h);
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
    }
    private Node put(Node n, K k, V v) {
        if (n == null) return new Node(k, v);
        int cmp = k.compareTo(n.key);
        if      (cmp < 0) n.left = put(n.left, k, v);
        else if (cmp > 0) n.right = put(n.right, k, v);
        else    { n.val = v; return n; }
        n.size = 1 + size(n.left) + size(n.right);
        n.height = 1 + Math.max(height(n.left), height(n.right));
        return balance(n);
    }
    public V get(K k) {
        if (k == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        Node n = get(root, k);
        return n == null ? null : n.val;
    }
    private Node get(Node n, K k) {
        while (n != null) {
            int cmp = k.compareTo(n.key);
            if (cmp == 0) return n;
            n = cmp < 0 ? n.left : n.right;
        }
        return null;
    }
    public boolean contain(K k) {
        return get(k) != null;
    }
    public K min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }
    private Node min(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }
    public K max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }
    private Node max(Node n) {
        while (n.right != null) n = n.right;
        return n;
    }
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMin(root);
    }
    private Node deleteMin(Node n) {
        if (n.left == null) return n.right;
        n.left = deleteMin(n.left);
        n.size = 1 + size(n.left) + size(n.right);
        n.height = 1 + Math.max(height(n.left), height(n.right));
        return balance(n);
    }
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMax(root);
    }
    private Node deleteMax(Node n) {
        if (n.right == null) return n.left;
        n.right = deleteMax(n.right);
        n.size = 1 + size(n.left) + size(n.right);
        n.height = 1 + Math.max(height(n.left), height(n.right));
        return balance(n);
    }
    public void delete(K k) {
        if (k == null) throw new IllegalArgumentException();
        if (isEmpty()) throw new NoSuchElementException();
        if (!contain(k)) throw new IllegalArgumentException();
        root = delete(root, k);
    }
    private Node delete(Node n, K k) {
        int cmp = k.compareTo(n.key);
        if      (cmp < 0) n.left = delete(n.left, k);
        else if (cmp > 0) n.right = delete(n.right, k);
        else    {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;
            Node x = n;
            n = min(x.right);
            n.right = deleteMin(x.right);
            n.left = x.left;
        }
        n.size = 1 + size(n.left) + size(n.right);
        n.height = 1 + Math.max(height(n.left), height(n.right));
        return balance(n);
    }
    public String travPre() {
        return travPre(root);
    }
    private void visitAlongLeftMostBranch(Node n, Stack<Node> S, StringBuilder sb) {
        while (n != null) {
            sb.append(n);
            if (n.right != null)
                S.push(n.right);
            n = n.left;
        }
    }
    private String travPre(Node n) {
        if (n == null) return "Empty Tree";
        Stack<Node> S = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (true) {
            visitAlongLeftMostBranch(n, S, sb);
            if (S.isEmpty()) break;
            n = S.pop();
        }
        return sb.toString();
    }
    public String travIn() { return travIn(root); }
    private void goAlongLeftMostBranch(Node n, Stack<Node> S) {
        while (n != null) {
            S.push(n);
            n = n.left;
        }
    }
    public String travIn(Node n) {
        if (n == null) return "Empty Tree";
        Stack<Node> S = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (true) {
            goAlongLeftMostBranch(n, S);
            if (S.isEmpty()) break;
            n = S.pop();
            sb.append(n);
            n = n.right;
        }
        return sb.toString();
    }
    public String travLevel() { return travLevel(root); }
    private String travLevel(Node n) {
        if (n == null) return "Empty Tree";
        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new Queue<>();
        q.enqueue(n);
        while (!q.isEmpty()) {
            sb.append(n = q.dequeue());
            if (n.left != null) q.enqueue(n.left);
            if (n.right != null) q.enqueue(n.right);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
      String[] alp = parseAlphbets("F  Z  W  T  L  U  V  A  C  X  B  S  O  D  G  Y  I  N  K  J  P  M  Q  E  R  H");
      __AVLTree<String, Integer> t = new __AVLTree<>();
      for (String s : alp) t.put(s, 1);
      StdOut.println(t.travLevel());
    }
}

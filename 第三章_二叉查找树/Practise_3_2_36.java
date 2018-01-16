package 第三章_二叉查找树;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;
import static 第二章_优先队列.Text_Alphabet.*;

public class Practise_3_2_36 {
    static class BST<K extends Comparable<K>, V> {
        private class Node {
            K k; V v; int size;
            Node left, right, parent;
            Node (K kk, V vv, Node pp) { k = kk; v = vv; parent = pp; size = 1; }
        }
        private Node root, hot;
        public boolean isEmpty() { return root == null; }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        public void updateSizeAbove(Node n) {
            while (n != null) {
                n.size = 1 + size(n.left) + size(n.right);
                n = n.parent;
            }
        }
        public void put(K k, V v) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) {
                root = new Node(k, v, null);
                return;
            }
            Node n = get(root, k);
            if (n != null) {
                n.v = v;
                return;
            }
            int cmp = k.compareTo(hot.k);
            if (cmp < 0) hot.left = new Node(k, v, hot);
            else         hot.right = new Node(k, v, hot);
            updateSizeAbove(hot);
        }
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node n = get(root, k);
            return n == null ? null : n.v;
        }
        private Node get(Node n, K k) {
            hot = null;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return n;
                hot = n;
                n = cmp < 0 ? n.left : n.right;
            }
            return null;
        }
        public K max() { return max(root).k; }
        public K min() { return min(root).k; }
        private Node max(Node n) {
            while (n.right != null) n = n.right;
            return n;
        }
        private Node min(Node n) {
            while (n.left != null) n = n.left;
            return n;
        }
        public void deleteMin() {
            root = deleteMin(root);
        }
        private Node deleteMin(Node n) {
            if (n.left == null) return n.right;
            n.left = deleteMin(n.left);
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public void deleteMax() {
            root = deleteMax(root);
        }
        private Node deleteMax(Node n) {
            if (n.right == null) return n.left;
            n.right = deleteMax(n.right);
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public void delete(K k) {
            root = delete(root, k);
        }
        private Node delete(Node n, K k) {
            if (n == null) return null;
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n.left = delete(n.left, k);
            else if (cmp > 0) n.right = delete(n.right, k);
            else {
                if (n.left == null) return n.right;
                if (n.right == null) return n.left;
                Node t = n;
                n = min(t.right);
                n.right = deleteMin(t.right);
                n.left = t.left;
            }
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public Iterable<K> keys() { return keys(min(), max()); }
        public Iterable<K> keys(K lo, K hi) {
            LinkedList<K> list = new LinkedList<K>();
            keys(root, list, lo, hi);
            return list;
        }
        private void goAlongLeftMostBranch(Stack<Node> S, Node n, K lo) {
            while (n != null) {
                S.push(n); 
                n = n.left;
            }
        }
        private void keys(Node n, LinkedList<K> list, K lo, K hi) {
            Stack<Node> S = new Stack<>();
            while (true) {
                goAlongLeftMostBranch(S, n, lo);
                if (S.isEmpty()) break;
                n = S.pop(); 
                if (lo.compareTo(n.k) <= 0 && 
                    n.k.compareTo(hi) <= 0) list.add(n.k);
                n = n.right;
            }
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(root, sb);
            return sb.toString();
        }
        private void toString(Node x, StringBuilder sb) {
            if (x == null) return;
            toString(x.left, sb);
            sb.append(String.format("{ %4s  %4s size = %4d }\n", x.k, x.v, x.size));
            toString(x.right, sb);
        }
    }
    public static void main(String[] args) {
        String[] alp = random(30);
        BST<String, Integer> bst = new BST<>();
        for (String s : alp) bst.put(s, 1);
        StdOut.println(bst);
        
        for (String key : bst.keys("B", "G"))
            StdOut.printf("{ %s  %s }\n", key, bst.get(key));
        
        StdOut.println("\n\n");
        
        for (String key : bst.keys())
            StdOut.printf("{ %s  %s }\n", key, bst.get(key));
    }
    // output
    /*
     *  {    B     1 size =    1 }
        {    C     1 size =    3 }
        {    D     1 size =    1 }
        {    G     1 size =   11 }
        {    H     1 size =    7 }
        {    I     1 size =    1 }
        {    J     1 size =    6 }
        {    L     1 size =    4 }
        {    M     1 size =    3 }
        {    N     1 size =    1 }
        {    P     1 size =    2 }
        {    R     1 size =   19 }
        {    S     1 size =    3 }
        {    T     1 size =    2 }
        {    U     1 size =    1 }
        {    V     1 size =    7 }
        {    W     1 size =    1 }
        {    X     1 size =    3 }
        {    Y     1 size =    1 }
        
        { B  1 }
        { C  1 }
        { D  1 }
        { G  1 }
        
        
        
        { B  1 }
        { C  1 }
        { D  1 }
        { G  1 }
        { H  1 }
        { I  1 }
        { J  1 }
        { L  1 }
        { M  1 }
        { N  1 }
        { P  1 }
        { R  1 }
        { S  1 }
        { T  1 }
        { U  1 }
        { V  1 }
        { W  1 }
        { X  1 }
        { Y  1 }
     */
}

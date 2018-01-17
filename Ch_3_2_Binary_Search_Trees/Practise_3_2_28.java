package Ch_3_2_Binary_Search_Trees;

import static Tool.ArrayGenerator.Alphbets.*;
import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_3_2_28 {
    static class BST<K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right, parent;
            int size;
            Node (K k, V v, Node pa) { this.k = k; this.v = v; size = 1; parent = pa; }
            Node insertLC(K k, V v) { return left = new Node(k, v, this); }
            Node insertRC(K k, V v) { return right = new Node(k, v, this); }
        }
        private Node root, hot, cache;
        public boolean isEmpty() { return root == null; }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        public void updateSizeAbove(Node n) {
            while (n != null) {
                n.size = 1 + size(n.left) + size(n.right);
                n = n.parent;
            }
        }
        public K randomKey() {
            if (isEmpty()) throw new NoSuchElementException();
            int h = (int)(Math.log(size()) / Math.log(2));
            int steps = StdRandom.uniform(h + 1);
            Node n = root;
            while (steps-- > 0) {
                boolean left = StdRandom.bernoulli();
                if (left) {
                    if      (n.left != null) n = n.left;
                    else if (n.right != null) n = n.right;
                    else    return n.k;
                } else {
                    if      (n.right != null) n = n.right;
                    else if (n.left != null) n = n.left;
                    else    return n.k;
                }
            }
            return n.k;
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
            if (cmp < 0)   cache = hot.insertLC(k, v);
            else           cache = hot.insertRC(k, v);
            updateSizeAbove(hot);
        }
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node n = get(root, k);
            return n == null ? null : n.v;
        }
        private Node get(Node n, K k) {
            if (cache != null && k.compareTo(cache.k) == 0) return cache;
            hot = null;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return cache = n;
                hot = n;
                n = cmp < 0 ? n.left : n.right;
            }
            return null;
        }
        public void delete(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            root = delete(root, k);
        }
        private Node delete(Node n, K k) {
            if (n == null) return null;
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n.left = delete(n.left, k);
            else if (cmp > 0) n.right = delete(n.right, k);
            else    {
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
        public void deleteMin()  {
            if (isEmpty()) throw new NoSuchElementException();
            root = deleteMin(root);
        }
        private Node deleteMin(Node n) {
            if (n.left == null) return n.right;
            n.left = deleteMin(n.left);
            return n;
        }
        public K min() {
            if (isEmpty()) throw new NoSuchElementException();
            return min(root).k;
        }
        private Node min(Node n) {
            while (n.left != null) n = n.left;
            return n;
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
        String[] strs = random(30);
        StdOut.println(Arrays.toString(strs));
        BST <String, Integer> bst = new BST<String, Integer>();
        for (String s : strs) 
            bst.put(s, StdRandom.uniform(100));
        StdOut.println(bst);
        for (int i = 0; i < 10; i++) {
            String randomKey = bst.randomKey();
            Integer value = bst.get(randomKey);
            StdOut.printf("{ %s  %s }\n", randomKey, value);
        }
    }
    // output
    /*
     *  [S, Y, D, F, P, I, G, O, S, F, V, M, N, I, K, H, D, L, B, W, I, G, Z, M, V, N, G, K, S, X]
        {    B     4 size =    1 }
        {    D    52 size =   12 }
        {    F    39 size =   10 }
        {    G    46 size =    2 }
        {    H    23 size =    1 }
        {    I    38 size =    8 }
        {    K    84 size =    2 }
        {    L    27 size =    1 }
        {    M    37 size =    4 }
        {    N    52 size =    1 }
        {    O     3 size =    5 }
        {    P    78 size =    9 }
        {    S    70 size =   18 }
        {    V    87 size =    3 }
        {    W    41 size =    2 }
        {    X    87 size =    1 }
        {    Y    44 size =    5 }
        {    Z    17 size =    1 }
        
        { S  70 }
        { B  4 }
        { D  52 }
        { Z  17 }
        { D  52 }
        { Y  44 }
        { F  39 }
        { B  4 }
        { S  70 }
        { S  70 }
     */
}

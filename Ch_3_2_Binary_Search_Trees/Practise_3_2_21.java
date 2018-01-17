package Ch_3_2_Binary_Search_Trees;

import static Tool.ArrayGenerator.Alphbets.*;
import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_3_2_21 {
    static class BST<K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right, parent;
            int size;
            Node (K kk, V vv, Node pa) { k = kk; v = vv; parent = pa; size = 1; }
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
        public K max() {
            if (isEmpty()) throw new NoSuchElementException();
            return max(root).k;
        }
        private Node max(Node n) {
            while (n.right != null) n = n.right;
            return n;
        }
        public K min() {
            if (isEmpty()) throw new NoSuchElementException();
            return min(root).k; 
        }
        public void deleteMin() {
            if (isEmpty()) throw new NoSuchElementException();
            root = deleteMin(root);
        }
        private Node deleteMin(Node n) {
            if (n.left == null) return n.right;
            n.left = deleteMin(n.left);
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        private Node min(Node n) {
            while (n.left != null) n = n.left;
            return n;
        }
        public void delete(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            root = delete(root, k);
        }
        private Node delete(Node n, K k) {
            if (n == null) throw new NoSuchElementException();
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
        public K floor(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node t = floor(root, k);
            return t == null ? null : t.k;
        }
        private Node floor(Node n, K k) {
            Node tmp = null;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return n;
                if (cmp < 0) n = n.left;
                else       { tmp = n; n = n.right; }
            }
            return tmp;
        }
        public K ceiling(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node t = ceiling(root, k);
            return t == null ? null : t.k;
        }
        private Node ceiling(Node n, K k) {
            Node tmp = null;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return n;
                if (cmp > 0) n = n.right;
                else       { tmp = n; n = n.left; }
            }
            return tmp;
        }
        public K select(int k) {
            if (k < 0 || k >= size()) throw new IllegalArgumentException();
            return select(root, k).k;
        }
        private Node select(Node n, int k) {
            while (n != null) {
                int ls = size(n.left);
                if      (k < ls) n = n.left;
                else if (k > ls) { n = n.right; k -= (ls + 1); }
                else    return n;
            }
            return null;
        }
        public int rank(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            return rank(root, k);
        }
        private int rank(Node n, K k) {
            int count = 0;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return size(n.left) + count;
                if (cmp < 0) n = n.left;
                else       { count += size(n.left) + 1; n = n.right; }
            }
            return count;
        }
        public K randomKey() {
            if (isEmpty()) throw new NoSuchElementException();
            int h = (int)(Math.log(size()) / Math.log(2));
            Node n = root;
            int steps = StdRandom.uniform(h + 1);
            while (steps-- > 0) {
                boolean left = StdRandom.bernoulli();
                if (left) {
                    if (n.left == null) return n.k;
                    n = n.left;
                } else {
                    if (n.right == null) return n.k;
                    n = n.right;
                }
            }
            return n.k;
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
        String[] alps = random(30);
        BST<String, Integer> bst = new BST<String, Integer>();
        for (int i = 0; i < alps.length; i++)
            bst.put(alps[i], i);
        StdOut.println(bst);
        
        /*
         * 抽取若干个随机键
         */
        for (int i = 0; i < 10; i++) {
            String key = bst.randomKey();
            StdOut.printf("{ %s  %s }\n", key, bst.get(key));
        }
    }
    // output
    /*
     *  {    A    18 size =    1 }
        {    C     9 size =    3 }
        {    D    10 size =    1 }
        {    G    15 size =    4 }
        {    H     5 size =    6 }
        {    I    26 size =    1 }
        {    J    29 size =   18 }
        {    L    25 size =    1 }
        {    M    27 size =    4 }
        {    O    28 size =    1 }
        {    P    12 size =    2 }
        {    Q     4 size =    8 }
        {    R    22 size =    3 }
        {    S    23 size =    1 }
        {    U    19 size =    2 }
        {    V    14 size =   11 }
        {    X    24 size =    1 }
        {    Z     2 size =    2 }
        
        { I  26 }
        { D  10 }
        { J  29 }
        { H  5 }
        { I  26 }
        { J  29 }
        { Z  2 }
        { I  26 }
        { Z  2 }
        { G  15 }

     */
}

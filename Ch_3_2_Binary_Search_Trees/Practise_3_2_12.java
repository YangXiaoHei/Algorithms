package Ch_3_2_Binary_Search_Trees;

import static Ch_2_4_Priority_Queues.Text_Alphabet.*;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_3_2_12 {
    static class BST <K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right;
            Node (K kk, V vv) { k = kk; v = vv; }
        }
        private Node root, hot;
        public boolean isEmpty() { return root == null; }
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            return get(root, k).v;
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
                root = new Node(k, v);
                return;
            }
            Node n = get(root, k);
            if (n != null) {
                n.v = v;
                return;
            }
            int cmp = k.compareTo(hot.k);
            if (cmp < 0)    hot.left = new Node(k, v);
            else            hot.right = new Node(k, v);
        }
        public void delete(K k) {
            if (isEmpty()) throw new NoSuchElementException();
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
            return n;
        }
        public void deleteMax() {
            if (isEmpty()) throw new NoSuchElementException();
            root = deleteMax(root);
        }
        private Node deleteMax(Node n) {
            if (n.right == null) return n.left;
            n.right = deleteMax(n.right);
            return n;
        }
        public void deleteMin() {
            if (isEmpty()) throw new NoSuchElementException();
            root = deleteMin(root);
        }
        private Node deleteMin(Node n) {
            if (n.left == null) return n.right;
            n.left = deleteMin(n.left);
            return n;
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
        private Node min(Node n) {
            while (n.left != null) n = n.left;
            return n;
        }
        public K floor(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node n = floor(root, k);
            return n == null ? null : n.k;
        }
        private Node floor(Node n, K k) {
            Node tmp = null;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return n;
                n = cmp < 0 ? n.left : (tmp = n.right);
            }
            return tmp;
        }
        public K ceiling(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node n = ceiling(root, k);
            return n == null ? null : n.k;
        }
        private Node ceiling(Node n, K k) {
            Node tmp = null;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return n;
                n = cmp > 0 ? n.right : (tmp = n.left);
            }
            return tmp;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(root, sb);
            return sb.toString();
        }
        private void toString(Node x, StringBuilder sb) {
            if (x == null) return;
            toString(x.left, sb);
            sb.append(String.format("{ %4s  %4s }\n", x.k, x.v));
            toString(x.right, sb);
        }
    }
    public static void main(String[] args) {
        String[] alps = random(30);
        BST<String, Integer> bst = new BST<String, Integer>();
        for (int i = 0; i < alps.length; i++)
            bst.put(alps[i], i);
        StdOut.println(bst);
        
        StdOut.printf("最小 : {%s  %s}\n", bst.min(), bst.get(bst.min()));
        StdOut.printf("最大 : {%s  %s}\n", bst.max(), bst.get(bst.max()));
        
        StdOut.println();
        
        StdOut.printf("floor : %s\n", bst.floor("E"));
        StdOut.printf("floor : %s\n", bst.floor("F"));
        StdOut.printf("floor : %s\n", bst.floor("G"));
        StdOut.printf("floor : %s\n", bst.floor("Y"));
        
        StdOut.println();
        
        StdOut.printf("ceiling : %s\n", bst.ceiling("A"));
        StdOut.printf("ceiling : %s\n", bst.ceiling("H"));
        StdOut.printf("ceiling : %s\n", bst.ceiling("I"));
        StdOut.printf("ceiling : %s\n", bst.ceiling("U"));
        
        StdOut.println();
//        
//        StdOut.printf("select : %s\n", bst.select(1));
//        StdOut.printf("select : %s\n", bst.select(8));
//        StdOut.printf("select : %s\n", bst.select(10));
//        StdOut.printf("select : %s\n", bst.select(13));
//        
//        StdOut.println();
//        
//        StdOut.printf("rank : %d\n", bst.rank("A"));
//        StdOut.printf("rank : %d\n", bst.rank("O"));
//        StdOut.printf("rank : %d\n", bst.rank("T"));
//        StdOut.printf("rank : %d\n", bst.rank("U"));
        
        StdOut.println();
        
        bst.delete("D");
        StdOut.println(bst);
        
        bst.delete("G");
        StdOut.println(bst);
        
        bst.delete("I");
        StdOut.println(bst);
        
        bst.delete("P");
        StdOut.println(bst);
        
        bst.delete("U");
        StdOut.println(bst);
        
        bst.deleteMax();
        StdOut.println(bst);
        
        bst.deleteMax();
        StdOut.println(bst);
        
        bst.deleteMin();
        StdOut.println(bst);
        
        bst.deleteMin();
        StdOut.println(bst);
    }
    // output
    /*
     *  {    A     2 }
        {    B     3 }
        {    C     7 }
        {    D    25 }
        {    G    27 }
        {    H    10 }
        {    I     5 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    P    14 }
        {    R    24 }
        {    S    22 }
        {    U    19 }
        {    V    17 }
        {    X    15 }
        {    Y    12 }
        
        最小 : {A  2}
        最大 : {Y  12}
        
        floor : G
        floor : G
        floor : G
        floor : Y
        
        ceiling : A
        ceiling : H
        ceiling : I
        ceiling : U
        
        
        {    A     2 }
        {    B     3 }
        {    C     7 }
        {    G    27 }
        {    H    10 }
        {    I     5 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    P    14 }
        {    R    24 }
        {    S    22 }
        {    U    19 }
        {    V    17 }
        {    X    15 }
        {    Y    12 }
        
        {    A     2 }
        {    B     3 }
        {    C     7 }
        {    H    10 }
        {    I     5 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    P    14 }
        {    R    24 }
        {    S    22 }
        {    U    19 }
        {    V    17 }
        {    X    15 }
        {    Y    12 }
        
        {    A     2 }
        {    B     3 }
        {    C     7 }
        {    H    10 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    P    14 }
        {    R    24 }
        {    S    22 }
        {    U    19 }
        {    V    17 }
        {    X    15 }
        {    Y    12 }
        
        {    A     2 }
        {    B     3 }
        {    C     7 }
        {    H    10 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    R    24 }
        {    S    22 }
        {    U    19 }
        {    V    17 }
        {    X    15 }
        {    Y    12 }
        
        {    A     2 }
        {    B     3 }
        {    C     7 }
        {    H    10 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    R    24 }
        {    S    22 }
        {    V    17 }
        {    X    15 }
        {    Y    12 }
        
        {    A     2 }
        {    B     3 }
        {    C     7 }
        {    H    10 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    R    24 }
        {    S    22 }
        {    V    17 }
        {    X    15 }
        
        {    A     2 }
        {    B     3 }
        {    C     7 }
        {    H    10 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    R    24 }
        {    S    22 }
        {    V    17 }
        
        {    B     3 }
        {    C     7 }
        {    H    10 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    R    24 }
        {    S    22 }
        {    V    17 }
        
        {    C     7 }
        {    H    10 }
        {    J    29 }
        {    K    23 }
        {    L    26 }
        {    M     8 }
        {    N    20 }
        {    R    24 }
        {    S    22 }
        {    V    17 }


     */
}

package Ch_3_2_Binary_Search_Trees;

import static Ch_2_4_Priority_Queues.Text_Alphabet.*;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_2_10 {
    static class BST <K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right;
            int size;
            Node(K kk, V vv) { k = kk; v = vv; size = 1; }
        }
        private Node root;
        public boolean isEmpty() { return root == null; }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        public void put(K k, V v) { root = put(root, k, v); }
        private Node put(Node n, K k, V v) {
            if (n == null) return new Node(k, v);
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n.left = put(n.left, k, v);
            else if (cmp > 0) n.right = put(n.right, k, v);
            else    n.v = v;
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException("键不能为null");
            Node t = get(root, k);
            return t == null ? null : t.v;
        }
        private Node get(Node n, K k) {
            if (n == null) return null;
            int cmp = k.compareTo(n.k);
            return cmp == 0 ? n : cmp < 0 ? get(n.left, k) : get(n.right, k);
        }
        public int height() { return height(root); }
        private int height(Node n) {
            if (n == null) return -1;
            return 1 + Math.max(height(n.left), height(n.right));
        }
        public int avgCompares() { return internalPathLength() / size(); }
        public int internalPathLength() { return internalPathLength(root, 0); }
        public int internalPathLength(Node n, int depth) {
            if (n == null) return 0;
            int curLevel = depth;
            depth += internalPathLength(n.left, curLevel + 1);
            depth += internalPathLength(n.right, curLevel + 1);
            return depth;
        }
        public void delete(K k) {
            if (k == null) throw new IllegalArgumentException("键不能为null");
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
        public K floor(K k) {
            if (k == null) throw new IllegalArgumentException("");
            Node t = floor(root, k);
            return t == null ? null : t.k;
        }
        private Node floor(Node n, K k) {
            if (n == null) return null;
            int cmp = k.compareTo(n.k);
            if (cmp == 0) return n;
            if (cmp < 0) return floor(n.left, k);
            Node t = floor(n.right, k);
            return t == null ? n : t;
        }
        public K ceiling(K k) {
            if (k == null) throw new IllegalArgumentException();
            Node t = ceiling(root, k);
            return t == null ? null : t.k;
        }
        private Node ceiling(Node n, K k) {
            if (n == null) return null;
            int cmp = k.compareTo(n.k);
            if (cmp == 0) return n;
            if (cmp > 0) return ceiling(n.right, k);
            Node t = ceiling(n.left, k);
            return t == null ? n : t;
        }
        public int rank(K k) { 
            return rank(root, k);
        }
        private int rank(Node n, K k) {
            if (n == null) return 0;
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) return rank(n.left, k);
            else if (cmp > 0) return 1 + size(n.left) + rank(n.right, k);
            else    return size(n.left);
        }
        public K select(int k) {
            if (k < 0 || k >= size()) throw new IllegalArgumentException();
            return select(root, k).k;
        }
        private Node select(Node n, int k) {
            int leftTreeSize = size(n.left);
            if      (k < leftTreeSize) return select(n.left, k);
            else if (k > leftTreeSize) return select(n.right, k - leftTreeSize - 1);
            else    return n;
        }
        public K max() { return isEmpty() ? null : max(root).k; }
        private Node max(Node n) {
            if (n.right == null) return n;
            return max(n.right);
        }
        public K min() { return isEmpty() ? null : min(root).k; }
        private Node min(Node n) {
            if (n.left == null) return n;
            return min(n.left);
        }
        public void deleteMin() { root = deleteMin(root); }
        private Node deleteMin(Node n) {
            if (n.left == null) return n.right;
            n.left = deleteMin(n.left);
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public void deleteMax() { root = deleteMax(root); }
        private Node deleteMax(Node n) {
            if (n.right == null) return n.left;
            n.right = deleteMin(n.right);
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        public Iterable<K> keys(K lo, K hi) {
            LinkedList<K> list = new LinkedList<K>();
            keys(root, list, lo, hi);
            return list;
        }
        private void keys(Node n, LinkedList<K> list, K lo, K hi) {
            if (n == null) return;
            int cmplo = lo.compareTo(n.k);
            int cmphi = n.k.compareTo(hi);
            if (cmplo < 0) keys(n.left, list, lo, hi);
            if (cmplo <= 0 && cmphi <= 0) list.add(n.k);
            if (cmphi < 0) keys(n.right, list, lo, hi);
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(root, sb);
            sb.append(String.format("树高 : %d\n"
                    + "树规模 : %d\n"
                    + "内部路径长度 : %d\n"
                    + "随机命中查找平均所需比较次数 : %d\n", 
                    height(), size(), internalPathLength(), avgCompares()));
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
        
        StdOut.printf("select : %s\n", bst.select(1));
        StdOut.printf("select : %s\n", bst.select(8));
        StdOut.printf("select : %s\n", bst.select(10));
        StdOut.printf("select : %s\n", bst.select(13));
        
        StdOut.println();
        
        StdOut.printf("rank : %d\n", bst.rank("A"));
        StdOut.printf("rank : %d\n", bst.rank("O"));
        StdOut.printf("rank : %d\n", bst.rank("T"));
        StdOut.printf("rank : %d\n", bst.rank("U"));
        
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
        
        for (String key : bst.keys("D", "Y")) {
            StdOut.printf("{ %s  %s }\n", key, bst.get(key));
        }
    }
    // output
    /*
     *  {    A     4 size =    1 }
        {    D     1 size =    8 }
        {    E    27 size =    1 }
        {    F    29 size =    2 }
        {    I    18 size =    6 }
        {    J    11 size =    3 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =   16 }
        {    P    13 size =    1 }
        {    R    25 size =    3 }
        {    T    15 size =    1 }
        {    U    28 size =    7 }
        {    V    12 size =    2 }
        {    Y    20 size =    1 }
        {    Z    10 size =    3 }
        树高 : 5
        树规模 : 16
        内部路径长度 : 42
        随机命中查找平均所需比较次数 : 2
        
        最小 : {A  4}
        最大 : {Z  10}
        
        floor : E
        floor : F
        floor : F
        floor : Y
        
        ceiling : A
        ceiling : I
        ceiling : I
        ceiling : U
        
        select : D
        select : O
        select : R
        select : V
        
        rank : 0
        rank : 8
        rank : 11
        rank : 12
        
        {    A     4 size =    1 }
        {    E    27 size =    7 }
        {    F    29 size =    1 }
        {    I    18 size =    5 }
        {    J    11 size =    3 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =   15 }
        {    P    13 size =    1 }
        {    R    25 size =    3 }
        {    T    15 size =    1 }
        {    U    28 size =    7 }
        {    V    12 size =    2 }
        {    Y    20 size =    1 }
        {    Z    10 size =    3 }
        树高 : 5
        树规模 : 15
        内部路径长度 : 38
        随机命中查找平均所需比较次数 : 2
        
        {    A     4 size =    1 }
        {    E    27 size =    7 }
        {    F    29 size =    1 }
        {    I    18 size =    5 }
        {    J    11 size =    3 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =   15 }
        {    P    13 size =    1 }
        {    R    25 size =    3 }
        {    T    15 size =    1 }
        {    U    28 size =    7 }
        {    V    12 size =    2 }
        {    Y    20 size =    1 }
        {    Z    10 size =    3 }
        树高 : 5
        树规模 : 15
        内部路径长度 : 38
        随机命中查找平均所需比较次数 : 2
        
        {    A     4 size =    1 }
        {    E    27 size =    6 }
        {    F    29 size =    1 }
        {    J    11 size =    4 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =   14 }
        {    P    13 size =    1 }
        {    R    25 size =    3 }
        {    T    15 size =    1 }
        {    U    28 size =    7 }
        {    V    12 size =    2 }
        {    Y    20 size =    1 }
        {    Z    10 size =    3 }
        树高 : 4
        树规模 : 14
        内部路径长度 : 33
        随机命中查找平均所需比较次数 : 2
        
        {    A     4 size =    1 }
        {    E    27 size =    6 }
        {    F    29 size =    1 }
        {    J    11 size =    4 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =   13 }
        {    R    25 size =    2 }
        {    T    15 size =    1 }
        {    U    28 size =    6 }
        {    V    12 size =    2 }
        {    Y    20 size =    1 }
        {    Z    10 size =    3 }
        树高 : 4
        树规模 : 13
        内部路径长度 : 30
        随机命中查找平均所需比较次数 : 2
        
        {    A     4 size =    1 }
        {    E    27 size =    6 }
        {    F    29 size =    1 }
        {    J    11 size =    4 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =   12 }
        {    R    25 size =    2 }
        {    T    15 size =    1 }
        {    V    12 size =    5 }
        {    Y    20 size =    1 }
        {    Z    10 size =    2 }
        树高 : 4
        树规模 : 12
        内部路径长度 : 26
        随机命中查找平均所需比较次数 : 2
        
        {    A     4 size =    1 }
        {    E    27 size =    6 }
        {    F    29 size =    1 }
        {    J    11 size =    4 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =   11 }
        {    T    15 size =    1 }
        {    V    12 size =    4 }
        {    Y    20 size =    1 }
        {    Z    10 size =    2 }
        树高 : 4
        树规模 : 11
        内部路径长度 : 23
        随机命中查找平均所需比较次数 : 2
        
        {    A     4 size =    1 }
        {    E    27 size =    6 }
        {    F    29 size =    1 }
        {    J    11 size =    4 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =   10 }
        {    V    12 size =    3 }
        {    Y    20 size =    1 }
        {    Z    10 size =    2 }
        树高 : 4
        树规模 : 10
        内部路径长度 : 21
        随机命中查找平均所需比较次数 : 2
        
        {    E    27 size =    5 }
        {    F    29 size =    1 }
        {    J    11 size =    4 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =    9 }
        {    V    12 size =    3 }
        {    Y    20 size =    1 }
        {    Z    10 size =    2 }
        树高 : 4
        树规模 : 9
        内部路径长度 : 19
        随机命中查找平均所需比较次数 : 2
        
        {    F    29 size =    1 }
        {    J    11 size =    4 }
        {    M    16 size =    2 }
        {    N    22 size =    1 }
        {    O     2 size =    8 }
        {    V    12 size =    3 }
        {    Y    20 size =    1 }
        {    Z    10 size =    2 }
        树高 : 3
        树规模 : 8
        内部路径长度 : 14
        随机命中查找平均所需比较次数 : 1
        
        { F  29 }
        { J  11 }
        { M  16 }
        { N  22 }
        { O  2 }
        { V  12 }
        { Y  20 }
     */
}

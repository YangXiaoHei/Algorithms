package Ch_3_2_Binary_Search_Trees;

import static Tool.ArrayGenerator.Alphbets.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_3_2_13 {
    static class BST<K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right, parent;
            int size;
            Node(K kk, V vv) { k = kk; v = vv; size = 1; }
            Node(K kk, V vv, Node pa) { k = kk; v = vv; size = 1; parent = pa; }
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
        public int height() { return height(root); }
        private int height(Node n) {
            if (n == null) return -1;
            return 1 + Math.max(height(n.left), height(n.right));
        }
        public int avgCompares() { return ipl() / size(); }
        public int ipl() { return ipl(root, 0); }
        private int ipl(Node n, int depth) {
            if (n == null) return 0;
            int cur = depth;
            depth += ipl(n.left, cur + 1);
            depth += ipl(n.right, cur + 1);
            return depth;
        }
        /*
         * 非递归 min
         */
        public K min() { 
            if (isEmpty()) return null;
            return min(root).k;
        }
        private Node min(Node n) {
            while (n.left != null) n = n.left;
            return n;
        }
        /*
         * 非递归 max
         */
        public K max() {
            if (isEmpty()) return null;
            return max(root).k;
        }
        private Node max(Node n) {
            while (n.right != null) n = n.right;
            return n;
        }
        /*
         * 非递归的 floor
         */
        public K floor(K k) {
            if (k == null) throw new IllegalArgumentException();
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
        /*
         * 非递归的 select
         */
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
        /*
         * 非递归的 rank
         */
        public int rank(K k) { 
            if (isEmpty() || k == null) throw new IllegalArgumentException();
            return rank(root, k); 
        }
        private int rank(Node n, K k) {
            int count = 0;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return count + size(n.left);
                if (cmp <  0) n = n.left;
                else        { count += size(n.left) + 1; n = n.right;  }
            }
            return count;
        }
        /*
         * 非递归的ceiling
         */
        public K ceiling(K k) {
            if (k == null) throw new IllegalArgumentException();
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
        /*
         * 非递归 get
         */
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) return null;
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
        /*
         * 非递归 put
         */
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
            if (cmp < 0) hot.left  = new Node(k,v, hot);
            else         hot.right = new Node(k,v, hot);
            updateSizeAbove(hot);
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(root, sb);
            sb.append(String.format("树高 : %d\n"
                    + "树规模 : %d\n"
                    + "内部路径长度 : %d\n"
                    + "随机命中查找平均所需比较次数 : %d\n", 
                    height(), size(), ipl(), avgCompares()));
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
    }
    // output
    /*
     *  {    B    28 size =    1 }
        {    G    15 size =   10 }
        {    H     4 size =    1 }
        {    I     9 size =    8 }
        {    J    23 size =    4 }
        {    K    26 size =    1 }
        {    M    29 size =    2 }
        {    O    17 size =    3 }
        {    P     7 size =    5 }
        {    Q    11 size =    6 }
        {    R     0 size =   16 }
        {    T    22 size =    2 }
        {    U    24 size =    1 }
        {    W     3 size =    5 }
        {    X    18 size =    2 }
        {    Y    14 size =    1 }
        树高 : 8
        树规模 : 16
        内部路径长度 : 52
        随机命中查找平均所需比较次数 : 3
        
        最小 : {B  28}
        最大 : {Y  14}
        
        floor : B
        floor : B
        floor : G
        floor : Y
        
        ceiling : B
        ceiling : H
        ceiling : I
        ceiling : U
        
        select : G
        select : P
        select : R
        select : W
        
        rank : 0
        rank : 7
        rank : 11
        rank : 12

     */
}

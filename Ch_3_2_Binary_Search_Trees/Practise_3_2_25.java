package Ch_3_2_Binary_Search_Trees;

import static Ch_2_4_Priority_Queues.__Alphabet.*;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class Practise_3_2_25 {
    public static int[] indexs(int N) {
        int[] indexs = new int[N];
        indexs(indexs, 0, 0, N - 1);
        return indexs;
    }
    public static int indexs(int[] is, int i, int lo, int hi) {
        if (lo > hi) return i;
        int mid = (lo + hi) >> 1;
        is[i++] = mid;
        i = indexs(is, i, lo, mid - 1);
        i = indexs(is, i, mid + 1, hi);
        return i;
    }
    public static int binarySearch(Comparable[] a, Comparable key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            StdOut.printf(" %3s  ", a[mid]);
            int cmp = key.compareTo(a[mid]);
            if      (cmp < 0)    hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else    return mid;
        }
        return -1;
    }
    static class BST<K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right, parent;
            int size;
            Node (K k, V v, Node pa) { this.k = k; this.v = v; size = 1; parent = pa; }
        }
        public boolean traceLog;
        private Node root, hot;
        public V get(K k) {
            Node n = get(root, k);
            return n == null ? null : n.v;
        }
        private Node get(Node n, K k) {
            hot = null;
            while (n != null) {
                if (traceLog)
                    StdOut.printf(" %3s  ", n.k);
                int cmp = k.compareTo(n.k);
                if (cmp == 0) return n;
                hot = n;
                n = cmp < 0 ? n.left : n.right;
            }
            return null;
        }
        public void put(K k, V v) {
            if (root == null) {
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
        public int size(Node n) { return n == null ? 0 : n.size; }
        public void updateSizeAbove(Node n) {
            while (n != null) {
                n.size = 1 + size(n.left) + size(n.right);
                n = n.parent;
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
            sb.append(String.format("{ %4s  %4s   size = %4d}\n", x.k, x.v, x.size));
            toString(x.right, sb);
        }
    }
    public static void main(String[] args) {
        int N = 20;
        String[] alps = randomNoDupli(N);
        Arrays.sort(alps);
        StdOut.println(Arrays.toString(alps));
        int[] indexs = indexs(N);
        BST<String, Integer> bst = new BST<String, Integer>();
        for (int i = 0; i < indexs.length; i++)
            bst.put(alps[indexs[i]], i);
        StdOut.println(bst);
        
        bst.traceLog = true;
        StdOut.println("二叉树访问顺序 : ");
        bst.get("H");
        StdOut.println("\n二分查找访问顺序 : ");
        binarySearch(alps, "H");
    }
    // output
    /*
     * [A, B, C, D, E, G, J, K, L, N, O, P, Q, R, S, T, V, X, Y, Z]
        {    A     3   size =    1}
        {    B     2   size =    4}
        {    C     4   size =    2}
        {    D     5   size =    1}
        {    E     1   size =    9}
        {    G     7   size =    1}
        {    J     6   size =    4}
        {    K     8   size =    2}
        {    L     9   size =    1}
        {    N     0   size =   20}
        {    O    12   size =    1}
        {    P    11   size =    4}
        {    Q    13   size =    2}
        {    R    14   size =    1}
        {    S    10   size =   10}
        {    T    16   size =    2}
        {    V    17   size =    1}
        {    X    15   size =    5}
        {    Y    18   size =    2}
        {    Z    19   size =    1}
        
        二叉树访问顺序 : 
           N     E     J     G  
        二分查找访问顺序 : 
           N     E     J     G  
     */
}

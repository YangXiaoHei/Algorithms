package 第三章_二叉查找树;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Text_BST_Review <K extends Comparable<K>, V>{
    private class Node {
        K key; V value; 
        Node left, right;
        int size;
        Node (K k, V v, int sz) { key = k; value = v; size = sz; }
    }
    private Node root;
    public boolean isEmpty() { return size() == 0; }
    /*
     * 获取二叉查找树的总规模
     */
    public int size() { return size(root); }
    /*
     * 获取以某个结点为根结点的树的规模
     */
    private int size(Node x) { return x == null ? 0 : x.size; }
    /*
     * 存储键值对
     */
    public void put(K k, V v) { root = put(root, k, v); }
    private Node put(Node x, K k, V v) {
        if (x == null) return new Node(k, v, 1);
        int cmp = k.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, k, v);
        else if (cmp > 0) x.right = put(x.right, k, v);
        else    x.value = v;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    /*
     * 获取与键关联的值
     */
    public V get(K k) { 
        if (k == null) throw new IllegalArgumentException("");
        Node t = get(root, k);
        return t == null ? null : t.value;
    }
    /*
     * 在以 x 为根结点的树中查找键 k
     */
    private Node get(Node x, K k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        return cmp == 0 ? x : cmp < 0 ? get(x.left, k) : get(x.right, k);
    }
    /*
     * 删除树中的最小键
     */
    public void deleteMin() { root = deleteMin(root); }
    /*
     * 删除以 x 为根结点的树中的最小键
     */
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    /*
     * 删除树中的最小键
     */
    public void deleteMax() { root = deleteMax(root); }
    /*
     * 删除以 x 为根结点的树中的最小键
     */
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    /*
     * 删除树中的键 k
     */
    public void delete(K k) {
        if (k == null) throw new IllegalArgumentException("");
        root = delete(root, k);
    }
    /*
     * 在以 x 为根结点的树中删除 k
     */
    private Node delete(Node x, K k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if      (cmp < 0) x.left = delete(x.left, k);
        else if (cmp > 0) x.right = delete(x.right, k);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    public K floor(K k) { 
        Node t = floor(root, k);
        return t == null ? null : t.key;
    }
    private Node floor(Node x, K k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, k);
        Node t = floor(x.right, k);
        return t == null ? x : t;
    }
    public K ceiling(K k) {
        Node t = ceiling(root, k);
        return t == null ? null : t.key;
    }
    private Node ceiling(Node x, K k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, k);
        Node t = ceiling(x.left, k);
        return t == null ? x : t;
    }
    /*
     * 获取树中排名为 k 的键
     */
    public K select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        return select(root, k).key;
    }
    /*
     * 寻找以 x 为根结点的树中排名为 k 的键
     */
    private Node select(Node x, int k) {
        int leftTreeSize = size(x.left);
        if      (k < leftTreeSize) return select(x.left, k);
        else if (k > leftTreeSize) return select(x.right, k - leftTreeSize - 1);
        else    return x;
    }
    /*
     * 获取树中 k 的排名
     */
    public int rank(K k) {
        if (k == null ) throw new IllegalArgumentException();
        return rank(root, k);
    }
    /*
     * 获取 k 在以 x 为根结点的树中的排名
     */
    private int rank(Node x, K k) {
        if (x == null) return 0;
        int cmp = k.compareTo(x.key);
        if      (cmp < 0) return rank(x.left, k);
        else if (cmp > 0) return 1 + size(x.left) + rank(x.right, k);
        else    return size(x.left);
    }
    /*
     * 获取最大的键
     */
    public K max() {  return isEmpty() ? null : max(root).key; }
    private Node max(Node x) { return x.right == null ? x : max(x.right); }
    /*
     * 获取最小的键
     */
    public K min() { return isEmpty() ? null : min(root).key; }
    public Node min(Node x) { return x.left == null ? x : min(x.left); }
    public Iterable<K> keys() { return keys(min(), max()); }
    public Iterable<K> keys(K lo, K hi) {
        LinkedList<K> list = new LinkedList<K>();
        keys(root, list, lo, hi);
        return list;
    }
    private void keys(Node x, LinkedList<K> list, K lo, K hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = x.key.compareTo(hi);
        if (cmplo < 0) keys(x.left, list, lo, hi);
        if (cmplo <= 0 && cmphi <= 0) list.add(x.key);
        if (cmphi < 0) keys(x.right, list, lo, hi);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString();
    }
    private void toString(Node x, StringBuilder sb) {
        if (x == null) return;
        toString(x.left, sb);
        sb.append(String.format("{ %4s  %4s size = %4d }\n", x.key, x.value, x.size));
        toString(x.right, sb);
    }
    public static void main(String[] args) {

        Text_BST_Review<String, Integer> bst = new Text_BST_Review<String, Integer>();
        bst.put("S", 0);
        bst.put("E", 1);
        bst.put("A", 2);
        bst.put("R", 3);
        bst.put("C", 4);
        bst.put("H", 5);
        bst.put("E", 6);
        bst.put("X", 7);
        bst.put("A", 8);
        bst.put("M", 9);
        bst.put("P", 10);
        bst.put("L", 11);
        bst.put("E", 12);
        StdOut.println(bst);
        StdOut.println(bst.get("E"));
        StdOut.println(bst.get("P"));
        StdOut.println(bst.get("M"));
        StdOut.println(bst.get("C"));
        StdOut.printf("max = %s\n", bst.max());
        StdOut.printf("min = %s\n", bst.min());
        StdOut.printf("ceiling(C) = %s\n", bst.ceiling("B"));
        StdOut.printf("ceiling(C) = %s\n", bst.ceiling("F"));
        StdOut.printf("ceiling(C) = %s\n", bst.ceiling("G"));
        StdOut.printf("floor(C) = %s\n", bst.floor("J"));
        StdOut.printf("floor(C) = %s\n", bst.floor("Q"));
        StdOut.printf("floor(C) = %s\n", bst.floor("U"));
//        StdOut.printf("select(10) = %s\n", bst.select(10));
        StdOut.printf("select(9) = %s\n", bst.select(9));
        StdOut.printf("select(5) = %s\n", bst.select(5));
        StdOut.printf("select(2) = %s\n", bst.select(2));
        StdOut.printf("select(6) = %s\n", bst.select(6));
        StdOut.printf("rank(Y) = %d\n", bst.rank("Y"));
        StdOut.printf("rank(X) = %d\n", bst.rank("X"));
        StdOut.printf("rank(A) = %d\n", bst.rank("A"));
        StdOut.printf("rank(G) = %d\n", bst.rank("G"));
        StdOut.printf("rank(I) = %d\n", bst.rank("I"));
        StdOut.printf("rank(J) = %d\n", bst.rank("J"));
        bst.delete("E");
        StdOut.println(bst);
        bst.delete("M");
        StdOut.println(bst);
        bst.delete("L");
        bst.delete("P");
        StdOut.println(bst);
        for (String key : bst.keys("C", "S")) 
            StdOut.printf("{ %s  %s }\n", key, bst.get(key));
        StdOut.println("\n\n");
        for (String key : bst.keys())
            StdOut.printf("{ %s  %s }\n", key, bst.get(key));
    }
    // output
    /*
     *  {    A     8 size =    2 }
        {    C     4 size =    1 }
        {    E    12 size =    8 }
        {    H     5 size =    4 }
        {    L    11 size =    1 }
        {    M     9 size =    3 }
        {    P    10 size =    1 }
        {    R     3 size =    5 }
        {    S     0 size =   10 }
        {    X     7 size =    1 }
        
        12
        10
        9
        4
        max = X
        min = A
        ceiling(C) = C
        ceiling(C) = H
        ceiling(C) = H
        floor(C) = H
        floor(C) = P
        floor(C) = S
        select(9) = X
        select(5) = M
        select(2) = E
        select(6) = P
        rank(Y) = 10
        rank(X) = 9
        rank(A) = 0
        rank(G) = 3
        rank(I) = 4
        rank(J) = 4
        {    A     8 size =    2 }
        {    C     4 size =    1 }
        {    H     5 size =    7 }
        {    L    11 size =    1 }
        {    M     9 size =    3 }
        {    P    10 size =    1 }
        {    R     3 size =    4 }
        {    S     0 size =    9 }
        {    X     7 size =    1 }
        
        {    A     8 size =    2 }
        {    C     4 size =    1 }
        {    H     5 size =    6 }
        {    L    11 size =    1 }
        {    P    10 size =    2 }
        {    R     3 size =    3 }
        {    S     0 size =    8 }
        {    X     7 size =    1 }
        
        {    A     8 size =    2 }
        {    C     4 size =    1 }
        {    H     5 size =    4 }
        {    R     3 size =    1 }
        {    S     0 size =    6 }
        {    X     7 size =    1 }
        
        { C  4 }
        { H  5 }
        { R  3 }
        { S  0 }
        
        
        
        { A  8 }
        { C  4 }
        { H  5 }
        { R  3 }
        { S  0 }
        { X  7 }
     */
    
}

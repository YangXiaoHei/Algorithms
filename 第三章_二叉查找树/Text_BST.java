package 第三章_二叉查找树;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Text_BST <K extends Comparable<K>, V> {
    private class Node {
        Node left, right;
        K key; V value;
        int size;
        Node(K k, V v, int sz) { key = k; value = v; size = sz; }
    }
    private Node root;
    public void put(K k, V v) {
        if (k == null) throw new IllegalArgumentException("符号表中不能插入 null 键");
        root = put(root, k, v);
    }
    public boolean isEmpty() { return root == null; }
    private int size(Node n) { return n == null ? 0 : n.size; }
    /*
     * 存储键值对
     */
    private Node put(Node n, K k, V v) {
        if (n == null) return new Node(k, v, 1);
        int cmp = k.compareTo(n.key);
        if      (cmp < 0) n.left = put(n.left, k, v);
        else if (cmp > 0) n.right = put(n.right, k, v);
        else    n.value = v;
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }
    /*
     * 取出与键关联的值
     */
    public V get(K k) {
        if (isEmpty()) throw new NoSuchElementException("符号表为空");
        return get(root, k);
    }
    private V get(Node n, K k) {
        if (n == null) return null;
        int cmp = k.compareTo(n.key);
        return cmp == 0 ? n.value : cmp < 0 ? get(n.left, k) : get(n.right, k);
    }
    /*
     * 获取小于等于键 K 的键
     */
    public K floor(K k) {
        if (k == null) throw new IllegalArgumentException("符号表中没有 null 键");
        Node x = floor(root, k);
        return x == null ? null : x.key;
    }
    public Node floor(Node n, K k) {
        if (n == null) return null;
        int cmp = k.compareTo(n.key);
        if (cmp == 0) return n;
        if (cmp < 0) return floor(n.left, k);
        Node t = floor(n.right, k);
        return t == null ? n : t;
    }
    /*
     * 获取大于等于键 K 的键
     */
    public K ceiling(K k) {
        if (k == null) throw new IllegalArgumentException("符号表中没有 null 键");
        Node x = ceiling(root, k);
        return x == null ? null : x.key;
    }
    public Node ceiling(Node n, K k) {
        if (n == null) return null;
        int cmp = k.compareTo(n.key);
        if (cmp == 0) return n;
        if (cmp > 0) return ceiling(n.right, k);
        Node t = ceiling(n.left, k);
        return t == null ? n : t;
    }
    /*
     * 获取排名为 k 的键
     */
    public K select(int k) { 
        if (k < 0 || k >= size(root)) throw new IllegalArgumentException("");
        return select(root, k).key;
    }
    private Node select(Node n, int k) {
        if (n == null) return null;
        int leftTreeSize = size(n.left);
        if      (k < leftTreeSize) return select(n.left, k);
        else if (k > leftTreeSize) return select(n.right, k - leftTreeSize - 1);
        else    return n;
    }
    /*
     * 排名
     */
    public int rank(K k) {
        if (k == null) throw new IllegalArgumentException("符号表中不能有 null 键");
        return rank(root, k);
    }
    private int rank(Node n, K k) {
        if (n == null) return 0;
        int cmp = k.compareTo(n.key);
        if      (cmp < 0) return rank(n.left, k);
        else if (cmp > 0) return 1 + size(n.left) + rank(n.right, k);
        else    return size(n.left);
    }
    /*
     * 删除最小键
     */
    public void deleteMin() { root = deleteMin(root); }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    /*
     * 删除最大键
     */
    public void deleteMax() { root = deleteMax(root); }
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right) + 1;
        return x;
    } 
    /*
     * 删除给定结点
     */
    public void delete(K k) { root = delete(root, k); }
    private Node delete(Node n, K k) {
        if (n == null) return null;
        int cmp = k.compareTo(n.key);
        if      (cmp < 0) n.left = delete(n.left, k);
        else if (cmp > 0) n.right = delete(n.right, k);
        else {
            if (n.right == null) return n.left;
            if (n.left == null) return n.right;
            Node t = n;
            n = min(t.right);
            n.right = deleteMin(t.right);
            n.left = t.left;
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }
    /*
     * 获取可供遍历的键
     */
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
    /*
     * 先序遍历
     */
    public void travPre(Visit<K, V> visit) { travPre(root, visit); }
    private void travPre(Node n, Visit<K, V> visit) {
        if (n == null) return;
        visit.visit(n.key, n.value, n.size);
        travPre(n.left, visit);
        travPre(n.right, visit);
    }
    /*
     * 中序遍历
     */
    public void travIn(Visit<K, V> visit) { travIn(root, visit); }
    private void travIn(Node n, Visit<K, V> visit) {
        if (n == null) return;
        travIn(n.left, visit);
        visit.visit(n.key, n.value, n.size);
        travIn(n.right, visit);
    }
    /*
     * 后序遍历
     */
    public void travPost(Visit<K, V> visit) { travPost(root, visit); }
    private void travPost(Node n, Visit<K, V> visit) {
        if (n == null) return;
        travIn(n.left, visit);
        travIn(n.right, visit);
        visit.visit(n.key, n.value, n.size);
    }
    /*
     * 获取最小的键
     */
    public K min() { return isEmpty() ? null : min(root).key; }
    private Node min(Node n) { return n.left == null ? n : min(n.left); }
    /*
     * 获取最大的键
     */
    public K max() { return isEmpty() ? null : max(root).key; }
    private Node max(Node n) { return n.right == null ? n : max(n.right); }
    public interface Visit<Key, Value> {
        void visit(Key k, Value v, int size);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        sb.append(String.format("尺寸 = %d\n", size(root)));
        return sb.toString();
    }
    public void toString(Node n, StringBuilder sb) {
        if (n == null) return;
        toString(n.left, sb);
        sb.append(String.format("{ %3s %3s \tsize = %3d }\n", n.key, n.value, n.size));
        toString(n.right, sb);
    }
    public static void main(String[] args) {
        Text_BST<String, Integer> bst = new Text_BST<String, Integer>();
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
     *  {   A   8   size =   2 }
        {   C   4   size =   1 }
        {   E  12   size =   8 }
        {   H   5   size =   4 }
        {   L  11   size =   1 }
        {   M   9   size =   3 }
        {   P  10   size =   1 }
        {   R   3   size =   5 }
        {   S   0   size =  10 }
        {   X   7   size =   1 }
        尺寸 = 10
        
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
        {   A   8   size =   2 }
        {   C   4   size =   1 }
        {   H   5   size =   7 }
        {   L  11   size =   1 }
        {   M   9   size =   3 }
        {   P  10   size =   1 }
        {   R   3   size =   4 }
        {   S   0   size =   9 }
        {   X   7   size =   1 }
        尺寸 = 9
        
        {   A   8   size =   2 }
        {   C   4   size =   1 }
        {   H   5   size =   6 }
        {   L  11   size =   1 }
        {   P  10   size =   2 }
        {   R   3   size =   3 }
        {   S   0   size =   8 }
        {   X   7   size =   1 }
        尺寸 = 8
        
        {   A   8   size =   2 }
        {   C   4   size =   1 }
        {   H   5   size =   4 }
        {   R   3   size =   1 }
        {   S   0   size =   6 }
        {   X   7   size =   1 }
        尺寸 = 6
        
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

package Ch_1_5_Case_Study_Union_Find;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_5_20 {
   static class List {
       private class Node {
           private Node parent;
           private Node next;
           private int value;
           private int size = 1;
           void setParent(Node n) { parent = n; }
           int value() { return value; }
           int size() { return size; }
           int addSize(int sz) { return size += sz; }
           Node() {}
           Node(int value, Node next) {  
               this.value = value;
               this.parent = this;
               this.next = next;
           }
           Node insertAfter(int p) { return next = new Node(p, next); }
           public String toString() {
               return String.format("{ %d %d }", value, parent.value);
           }
       }
       private Node header = new Node();
       boolean isEmpty() { return header == null; }
       Node findNode(int p) {
           Node cur = header.next;
           while (cur != null && cur.value != p) 
               cur = cur.next;
           if (cur == null)
               throw new RuntimeException("out of list's bounds");
           while (cur.parent.value != cur.value) 
               cur = cur.parent;
           return cur;
       }
       Node insertAsLast(int p) {
           Node cur = header;
           while (cur.next != null)
               cur = cur.next;
           return cur.insertAfter(p);
       }
       public String toString() {
           if (isEmpty()) return "empty list";
           StringBuilder sb = new StringBuilder();
           Node cur = header.next;
           while (cur.next != null) {
               sb.append(cur + "\n");
               cur = cur.next;
           }
           sb.append(cur);
           return sb.toString();
       }
   }
   static class ListWeightedQuickUnion {
       private List id = new List();
       int newSite(int p) { return id.insertAsLast(p).value(); }
       int find(int p) { return id.findNode(p).value(); }
       List.Node findNode(int p) { return id.findNode(p); }
       boolean connected(int p, int q) { return find(p) == find(q); }
       void union(int p, int q) {
           List.Node pRoot = findNode(p);
           List.Node qRoot = findNode(q);
           if (pRoot == qRoot) return;
           if (pRoot.size() < qRoot.size()) {
               pRoot.setParent(qRoot);
               qRoot.addSize(pRoot.size());
           } else {
               qRoot.setParent(pRoot);
               pRoot.addSize(pRoot.size);
           }
       }
       public String toString() { return id.toString(); }
   }
    public static void main(String[] args) {
        int N = 30, pairCount = 40;
        ListWeightedQuickUnion lwqu = new ListWeightedQuickUnion();
        for (int i = 0; i < N; i++)
            lwqu.newSite(i);
        Text_Generator gen = new Text_RandomPairGenerator(N);
        for (int i = 0; i < pairCount; i++) {
            int[] pair = gen.nextPair();
            if (lwqu.connected(pair[0], pair[1])) {
                StdOut.printf("%d %d 已连通\n", pair[0], pair[1]);
                continue;
            } else {
                lwqu.union(pair[0], pair[1]);
            }
        }
        StdOut.println("\n\n连接结果如下 : \n" + lwqu);
    }
    // output
    /*
     *  18 16 已连通
        26 21 已连通
        11 5 已连通
        28 7 已连通
        25 2 已连通
        21 1 已连通
        4 8 已连通
        8 5 已连通
        5 12 已连通
        2 28 已连通
        15 8 已连通
        17 3 已连通
        
        
        连接结果如下 : 
        { 0 7 }
        { 1 7 }
        { 2 16 }
        { 3 7 }
        { 4 1 }
        { 5 6 }
        { 6 7 }
        { 7 7 }
        { 8 16 }
        { 9 0 }
        { 10 6 }
        { 11 6 }
        { 12 0 }
        { 13 13 }
        { 14 0 }
        { 15 10 }
        { 16 7 }
        { 17 0 }
        { 18 16 }
        { 19 0 }
        { 20 7 }
        { 21 7 }
        { 22 0 }
        { 23 0 }
        { 24 7 }
        { 25 7 }
        { 26 7 }
        { 27 0 }
        { 28 7 }
        { 29 7 }
     */
}

package Ch_3_2_Binary_Search_Trees;

import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class Practise_3_2_33 {
    static class Node {
        Integer k; int size;
        Node l, r, parent;
        Node (Integer kk, int sz) { k = kk;  size = sz; }
        Node ilc(Integer k, int size) { return l = new Node(k, size); }
        Node irc(Integer k, int size) { return r = new Node(k, size); }
        public static int size(Node n) { return n == null ? 0 : n.size; }
        static void travInOrder(Node n) {
            if (n == null) return;
            travInOrder(n.l);
            StdOut.println(n.k);
            travInOrder(n.r);
        }
    }
    public static Integer select(Node n, int k) {
        while (n != null) {
            int ls = Node.size(n.l);
            if      (k < ls) n = n.l;
            else if (k > ls) { k -= (1 + Node.size(n.l)); n = n.r; }
            else    return n.k;
        }
        throw new RuntimeException("fatal error");
    }
    public static int rank(Node n, Integer k) {
        int count = 0;
        while (n != null) {
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n = n.l;
            else if (cmp > 0) { count += (1 + Node.size(n.l)); n = n.r; }
            else    return count += Node.size(n.l);
        }
        throw new RuntimeException("fatal error");
    }
    public static Iterable<Integer> keys(Node n) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        keys(list, n);
        return list;
    }
    public static void keys(LinkedList<Integer> list, Node n) {
        if (n == null) return;
        keys(list, n.l);
        list.add(n.k);
        keys(list, n.r);
    }
    public static boolean rankSelectCheck(Node n) {
        for (int i = 0; i < Node.size(n); i++) 
            if (i != rank(n, select(n, i))) return false;
        for (Integer key : keys(n)) 
            if (key.compareTo(select(n, rank(n, key))) != 0) return false;
        return true;
    }
    public static Node createTree() {
                                                                           Node r = new Node(8, 15);    // h = 1


                                      r.ilc(4, 7);                                                                             r.irc(12, 7);   // h = 2
    
    
    
                 r.l.ilc(2, 3);                          r.l.irc(6, 3);                                r.r.ilc(10, 3);                              r.r.irc(14, 3); // h = 3
    
    
    
    r.l.l.ilc(1, 1);     r.l.l.irc(3, 1);     r.l.r.ilc(5, 1);     r.l.r.irc(7, 1);        r.r.l.ilc(9, 1);     r.r.l.irc(11, 1);       r.r.r.ilc(13, 1);     r.r.r.irc(15, 1);  // h = 4
    return r;
    }
    public static void main(String[] args) {
        Node root = createTree();
        StdOut.println(rankSelectCheck(root));
    }
    // output
    /*
     * true

     */
}

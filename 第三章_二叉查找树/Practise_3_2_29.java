package 第三章_二叉查找树;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_2_29 {
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
        static boolean isBinaryTree(Node n) {
            if (n == null) return true;
            if (n.size != 1 + size(n.l) + size(n.r)) return false;
            return isBinaryTree(n.l) && isBinaryTree(n.r);
        }
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
       StdOut.println(Node.isBinaryTree(root));
    }
}

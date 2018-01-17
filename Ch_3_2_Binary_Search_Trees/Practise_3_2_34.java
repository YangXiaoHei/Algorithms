package Ch_3_2_Binary_Search_Trees;

import static Ch_2_4_Priority_Queues.Text_Alphabet.*;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_3_2_34 {
    static class ThreadedST <K extends Comparable<K>, V> {
        private class Node {
            K k; V v; int size;
            Node left, right, parent;
            Node pred, succ;
            Node (K kk, V vv, Node pp) { k = kk; v = vv; parent = pp; size = 1; }
            Node insertLC(K k, V v) { return left = new Node(k, v, this); }
            Node insertRC(K k, V v) { return right = new Node(k, v, this); }
            Node searchSucc() {
                Node t = this;
                if (right != null) {
                    t = right;
                    while (t.left != null) t = t.left;
                } else {
                    while (t.parent != null && t.parent.right == t) t = t.parent;
                    t = t.parent;
                }
                return t;
            }
            Node searchPred() {
                Node t = this;
                if (left != null) {
                    t = left;
                    while (t.right != null) t = t.right;
                } else {
                    while (t.parent != null && t.parent.left == t) t = t.parent;
                    t = t.parent;
                }
                return t;
            }
        }
        private Node root, hot;
        public boolean isEmpty() { return root == null; }
        public V get(K k) {
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
            if (cmp < 0) {
                Node cur = hot.insertLC(k, v);
                
                // 设置直接后继
                cur.succ = hot;
                Node hotPred = hot.pred;
                hot.pred = cur;
                
                // 设置直接前驱
                cur.pred = hotPred;
                if (hotPred != null) hotPred.succ = cur;
            } else {
                Node cur = hot.insertRC(k, v);
                
                // 设置直接前驱
                cur.pred = hot;
                Node hotSucc = hot.succ;
                hot.succ = cur;
                
                // 设置直接后继
                cur.succ = hotSucc;
                if (hotSucc != null) hotSucc.pred = cur;
            }  
        }
        public void delete(K k) {
            root = delete(root, k);
        }
        private Node delete(Node n, K k) {
            if (n == null) return null;
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n.left = delete(n.left, k);
            else if (cmp > 0) n.right = delete(n.right, k);
            else {
                if (n.left == null) {
                    if (n.pred != null) n.pred.succ = n.succ;
                    if (n.succ != null) n.succ.pred = n.pred;
                    return n.right;
                }
                if (n.right == null)  {
                    if (n.pred != null) n.pred.succ = n.succ;
                    if (n.succ != null) n.succ.pred = n.pred;
                    return n.left;
                }
                Node t = n;
                n = min(t.right);
                n.right = deleteMin_(t.right);
                n.left = t.left;
                
                t.pred.succ = t.succ;
                t.succ.pred = t.pred;
            }
            return n;
        }
        public K max() { return max(root).k; }
        public K min() { return min(root).k; }
        public Node min(Node n) {
            while (n.left != null) n = n.left;
            return n;
        }
        public Node max(Node n) {
            while (n.right != null) n = n.right;
            return n;
        }
        public void deleteMin() {
            root = deleteMin(root);
        }
        private Node deleteMin_(Node n) {
            if (n.left == null)  return n.right;
            n.left = deleteMin(n.left);
            return n;
        }
        private Node deleteMin(Node n) {
            if (n.left == null) {
                if (n.succ != null) n.succ.pred = n.pred;
                if (n.pred != null) n.pred.succ = n.succ;
                return n.right;
            }
            n.left = deleteMin(n.left);
            return n;
        }
        public void deleteMax() { root = deleteMax(root); }
        private Node deleteMax(Node n) {
            while (n.right == null) {
                if (n.succ != null) n.succ.pred = n.pred;
                if (n.pred != null) n.pred.succ = n.succ;
                return n.left;
            }
            n.right = deleteMax(n.right);
            return n;
        }
        private String ascendString() {
            StringBuilder sb = new StringBuilder();
            Node min = min(root);
            for (Node t = min; t != null; t = t.succ)
                sb.append(String.format("%4s", t.k));
            return "succ : " + sb.toString();
        }
        private String descendString() {
            StringBuilder sb = new StringBuilder();
            Node max = max(root);
            for (Node t = max; t != null; t = t.pred)
                sb.append(String.format("%4s", t.k));
            return "pred : " + sb.toString();
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(root, sb);
            return "中序遍历 : " + sb.toString();
        }
        private void toString(Node x, StringBuilder sb) {
            if (x == null) return;
            toString(x.left, sb);
            sb.append(String.format("%4s", x.k));
            toString(x.right, sb);
        }
    }
    public static void main(String[] args) {
        String[] alps = allRandom();
        ThreadedST<String, Integer> bst = new ThreadedST<String, Integer>();
        for (int i = 0; i < alps.length; i++)
            bst.put(alps[i], i);
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n删除 G\n");
        bst.delete("G");
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n删除 C\n");
        bst.delete("C");
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n删除 I\n");
        bst.delete("I");
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n删除 H\n");
        bst.delete("H");
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n删除 Q\n");
        bst.delete("Q");
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n删除 Z\n");
        bst.delete("Z");
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n删除 A\n");
        bst.delete("A");
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n== 删除最小值 == " + bst.min());
        bst.deleteMin();
        StdOut.println("== 删除最小值 == " + bst.min());
        bst.deleteMin();
        StdOut.println("== 删除最小值 == " + bst.min() + "\n");
        bst.deleteMin();
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
        
        StdOut.println("\n== 删除最大值 == " + bst.max());
        bst.deleteMax();
        StdOut.println("== 删除最大值 == " + bst.max());
        bst.deleteMax();
        StdOut.println("== 删除最大值 == " + bst.max() + "\n");
        bst.deleteMax();
        StdOut.println(bst);
        StdOut.println(bst.ascendString());
        StdOut.println(bst.descendString());
    }
    // output
    /*
     *  中序遍历 :    A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        succ :    A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        pred :    Z   Y   X   W   V   U   T   S   R   Q   P   O   N   M   L   K   J   I   H   G   F   E   D   C   B   A
        
        删除 G
        
        中序遍历 :    A   B   C   D   E   F   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        succ :    A   B   C   D   E   F   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        pred :    Z   Y   X   W   V   U   T   S   R   Q   P   O   N   M   L   K   J   I   H   F   E   D   C   B   A
        
        删除 C
        
        中序遍历 :    A   B   D   E   F   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        succ :    A   B   D   E   F   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        pred :    Z   Y   X   W   V   U   T   S   R   Q   P   O   N   M   L   K   J   I   H   F   E   D   B   A
        
        删除 I
        
        中序遍历 :    A   B   D   E   F   H   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        succ :    A   B   D   E   F   H   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        pred :    Z   Y   X   W   V   U   T   S   R   Q   P   O   N   M   L   K   J   H   F   E   D   B   A
        
        删除 H
        
        中序遍历 :    A   B   D   E   F   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        succ :    A   B   D   E   F   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z
        pred :    Z   Y   X   W   V   U   T   S   R   Q   P   O   N   M   L   K   J   F   E   D   B   A
        
        删除 Q
        
        中序遍历 :    A   B   D   E   F   J   K   L   M   N   O   P   R   S   T   U   V   W   X   Y   Z
        succ :    A   B   D   E   F   J   K   L   M   N   O   P   R   S   T   U   V   W   X   Y   Z
        pred :    Z   Y   X   W   V   U   T   S   R   P   O   N   M   L   K   J   F   E   D   B   A
        
        删除 Z
        
        中序遍历 :    A   B   D   E   F   J   K   L   M   N   O   P   R   S   T   U   V   W   X   Y
        succ :    A   B   D   E   F   J   K   L   M   N   O   P   R   S   T   U   V   W   X   Y
        pred :    Y   X   W   V   U   T   S   R   P   O   N   M   L   K   J   F   E   D   B   A
        
        删除 A
        
        中序遍历 :    B   D   E   F   J   K   L   M   N   O   P   R   S   T   U   V   W   X   Y
        succ :    B   D   E   F   J   K   L   M   N   O   P   R   S   T   U   V   W   X   Y
        pred :    Y   X   W   V   U   T   S   R   P   O   N   M   L   K   J   F   E   D   B
        
        == 删除最小值 == B
        == 删除最小值 == D
        == 删除最小值 == E
        
        中序遍历 :    F   J   K   L   M   N   O   P   R   S   T   U   V   W   X   Y
        succ :    F   J   K   L   M   N   O   P   R   S   T   U   V   W   X   Y
        pred :    Y   X   W   V   U   T   S   R   P   O   N   M   L   K   J   F
        
        == 删除最大值 == Y
        == 删除最大值 == X
        == 删除最大值 == W
        
        中序遍历 :    F   J   K   L   M   N   O   P   R   S   T   U   V
        succ :    F   J   K   L   M   N   O   P   R   S   T   U   V
        pred :    V   U   T   S   R   P   O   N   M   L   K   J   F
     */
}

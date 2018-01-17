package Ch_3_3_Balanced_Search_Trees;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class __RBTree<K extends Comparable<K>, V> {
    static class Queue<T> {
        private class Node {
            T item;
            Node prev, next;
            Node () {}
            Node (T it, Node pre, Node nex) { item = it; prev = pre; next = nex; }
            Node insertBefore(T item) {
                Node n = new Node(item, prev, this);
                if (prev != null) prev.next = n;
                prev = n;
                return n;
            }
            T delete() {
                T ite = item;
                if (prev != null) prev.next = next;
                if (next != null) next.prev = prev;
                return ite;
            }
        }
        private Node header = new Node(), tailer = new Node(); 
        private int size;
        {
            header.next = tailer;
            tailer.prev = header;
        }
        public boolean isEmpty() { return size == 0; }
        public void enqueue(T item) {
            ++size;
            tailer.insertBefore(item);
        }
        public T dequeue() {
            if (isEmpty()) throw new NoSuchElementException();
            --size;
            return header.next.delete();
        }
        
    }
    static class Stack<T> {
        private class Node {
            T item;
            Node next;
            Node (T it, Node n) { item = it; next = n; }
        }
        private Node top = null;
        public boolean isEmpty() { return top == null; }
        public T top() {
            if (isEmpty()) return null;
            return top.item;
        }
        public void push(T item) {
            top = new Node(item, top);
        }
        public T pop() {
            if (isEmpty()) throw new NoSuchElementException();
            T del = top.item;
            top = top.next;
            return del;
        }
    }
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        K k; V v; int size;
        Node left, right, parent;
        boolean color;
        Node (K kk, V vv, boolean c) { k = kk; v = vv; size = 1; color = c; }
        public String toString() { return String.format("%-4s 父 : %-4s\n", k, parent == null ? null : parent.k); }
    }
    private Node root;
    private final boolean isRed(Node n) { return n == null ? false : n.color; }
    public int size() { return size(root); }
    private final int size(Node n) { return n == null ? 0 : n.size; }
    private final Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    private final Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    private final void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    public void put(K k, V v) {
        if (k == null) throw new IllegalArgumentException();
        if (v == null) {
            // 删除
            return;
        }
        root = put(root, k, v);
        root.parent = null;
        root.color = BLACK;
    }
    private Node put(Node n, K k, V v) {
        if (n == null) return new Node(k, v, RED);
        int cmp = k.compareTo(n.k);
        if      (cmp < 0) n.left = put(n.left, k, v);
        else if (cmp > 0) n.right = put(n.right, k, v);
        else    n.v = v;
        if (isRed(n.right) && !isRed(n.left))    n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
        if (isRed(n.left) && isRed(n.right))     flipColors(n);
        n.size = 1 + size(n.left) + size(n.right);
        if (n.left != null) n.left.parent = n;
        if (n.right != null) n.right.parent = n;
        return n;
    }
    /*
     * 先序遍历迭代版
     */
    public String travPre_I() {
        StringBuilder sb = new StringBuilder();
        travPre_I(root, sb);
        return "==========  先序遍历  ===========\n" + sb.toString();
    }
    private void visitAlongLeftMostBranch(Stack<Node> S, Node n, StringBuilder sb) {
        while (n != null) {
            sb.append(n);
            if (n.right != null) S.push(n.right);
            n = n.left;
        }
    }
    private void travPre_I(Node n, StringBuilder sb) {
        Stack<Node> S = new Stack<>();
        while (true) {
            visitAlongLeftMostBranch(S, n, sb);
            if (S.isEmpty()) break;
            n = S.pop();
        }
    }
    /*
     * 中序遍历迭代版
     */
    public String travIn_R() {
        StringBuilder sb = new StringBuilder();
        travIn_R(root, sb);
        return  "==========  中序遍历  ===========\n" + sb.toString();
    }
    private void travIn_R(Node n, StringBuilder sb) {
        if (n == null) return;
        travIn_R(n.left, sb);
        sb.append(n);
        travIn_R(n.right, sb);
    }
    public String travIn_I() {
        StringBuilder sb = new StringBuilder();
        travIn_I(root, sb);
        return  "==========  中序遍历  ===========\n" + sb.toString();
    }
    private void goAlongLeftMostBranch(Stack<Node> S, Node n) {
        while (n != null) {
            S.push(n);
            n = n.left;
        }
    }
    private void travIn_I(Node n, StringBuilder sb) {
        Stack<Node> S = new Stack<>();
        while (true) {
            goAlongLeftMostBranch(S, n);
            if (S.isEmpty()) break;
            n = S.pop(); sb.append(n);
            n = n.right;
        }
    }
    /*
     * 后序遍历迭代版
     */
    public String travPost_I() {
        StringBuilder sb = new StringBuilder();
        travPost_I(root, sb);
        return  "==========  后序遍历  ===========\n" + sb.toString();
    }
    private void goToHLVFL(Stack<Node> S) {
        Node n = null;
        while ((n = S.top()) != null) {
            if (n.left != null) {
                if (n.right != null) S.push(n.right);
                S.push(n.left);
            } else
                S.push(n.right);
        }
        S.pop();
    }
    private void travPost_I(Node n, StringBuilder sb) {
        Stack<Node> S = new Stack<>();
        S.push(n);
        while (!S.isEmpty()) {
            if (S.top() != n.parent) 
                goToHLVFL(S);
            n = S.pop(); sb.append(n);
        }
    }
    /*
     * 层级遍历
     */
    public String travLevel() {
        StringBuilder sb = new StringBuilder();
        travLevel(root, sb);
        return  "==========  层级遍历  ===========\n" + sb.toString();
    }
    private void travLevel(Node n, StringBuilder sb) {
        Queue<Node> queue = new Queue<>();
        queue.enqueue(n);
        while (!queue.isEmpty()) {
            sb.append(n = queue.dequeue());
            if (n.left  != null) queue.enqueue(n.left);
            if (n.right != null) queue.enqueue(n.right);
        }
    }
    public static void main(String[] args) {
        __RBTree<String, Integer> rbt = new __RBTree<>();
        rbt.put("S", 2);
        rbt.put("E", 2);
        rbt.put("A", 2);
        rbt.put("R", 2);
        rbt.put("C", 2);
        rbt.put("H", 2);
        rbt.put("X", 2);
        rbt.put("M", 2);
        rbt.put("P", 2);
        rbt.put("L", 2);
        StdOut.println(rbt.size());
        StdOut.println(rbt.travPre_I());
        StdOut.println(rbt.travIn_I());
        StdOut.println(rbt.travPost_I());
        StdOut.println(rbt.travLevel());
    }
}

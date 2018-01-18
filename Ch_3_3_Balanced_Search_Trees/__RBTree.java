package Ch_3_3_Balanced_Search_Trees;

import edu.princeton.cs.algs4.*;
import java.util.*;
import static Tool.ArrayGenerator.Alphbets.*;

/*
 * 红黑树
 */
public class __RBTree<K extends Comparable<K>, V> {
    /*
     * 用于层次遍历的辅助队列
     */
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
    /*
     * 用于迭代版各种遍历的辅助栈
     */
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
        public String toString() { 
            return String.format("%s %-4s 高度 : %d sz : %d \n", 
                    color ? "红" : "黑", // 结点颜色
                    k,  // 结点键值
//                    parent == null ? null : parent.k, // 父结点键值
                    height(this), // 该结点高度
                    size
//                    parent != null &&  // 该结点是父结点的左孩子或者右孩子
//                    parent.left == this ? "左" : parent != null ? "右" : ""
                        ); 
        }
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
    public boolean isEmpty() { return root == null; }
    public int height() { return height(root); }
    private int height(Node n) {
        if (n == null) return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }
    
    
    /*
     * 删除最大值
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMax(Node h) { 
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left)) 
            h = moveRedRight(h);

        h.right = deleteMax(h.right);
        return balance(h);
    }
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    
    
    
    
    /*
     *  删除最小值
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }
    
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    private Node balance(Node h) {
        
        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }
    
    /*
     * 最小值
     */
    public K min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).k;
    }
    private Node min(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }
    public K max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).k;
    }
    private Node max(Node n) {
        while (n.right != null) n = n.right;
        return n;
    }

    /*
     * 删除结点操作
     */
    public void delete(K key) { 
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
//        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, K key) { 
        
        if (key.compareTo(h.k) < 0)  {
            
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            
            h.left = delete(h.left, key);
            
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            
            if (key.compareTo(h.k) == 0 && (h.right == null))
                return null;
            
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            
            if (key.compareTo(h.k) == 0) {
                Node x = min(h.right);
                h.k = x.k;
                h.v = x.v;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
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
//        if (n.left != null) n.left.parent = n;
//        if (n.right != null) n.right.parent = n;
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
    public Iterable<K> keys() { return keys(min(), max()); }
    public Iterable<K> keys(K lo, K hi) {
        LinkedList<K> list = new LinkedList<>();
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
    public static void main(String[] args) {
        __RBTree<String, Integer> rbt = new __RBTree<>();
        rbt.put("A", 2);
        rbt.put("C", 2);
        rbt.put("E", 2);
        rbt.put("H", 2);
        rbt.put("L", 2);
        rbt.put("M", 2);
        rbt.put("P", 2);
        rbt.put("R", 2);
        rbt.put("S", 2);
        rbt.put("X", 2);
        rbt.delete("H");
        rbt.delete("R");
        rbt.delete("P");
        rbt.delete("L");
        rbt.delete("X");
        rbt.delete("C");
        rbt.delete("S");
//        String[] keys = objectToString(((LinkedList<String>)rbt.keys()).toArray());
//        String random = keys[StdRandom.uniform(keys.length)];
//        StdOut.printf("删除 %s \n", random);
//        rbt.delete(random);
        StdOut.println(rbt.travLevel());
    }
}

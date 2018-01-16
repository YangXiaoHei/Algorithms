package 第三章_二叉查找树;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import static 第二章_优先队列.Text_Alphabet.*;

public class Text_BST_traversal {
    /*
     * 用于二叉树迭代版遍历的辅助栈
     */
    static class Stack<T> {
        private class Node {
            T item;
            Node next;
            Node (T i, Node n) { item = i; next = n; }
        }
        private Node top = null;
        public boolean isEmpty() { return top == null; }
        public void push(T item) { top = new Node(item ,top); }
        public T top() { return top.item; }
        public T pop() {
            if (isEmpty()) throw new NoSuchElementException();
            T popped = top.item;
            top = top.next;
            return popped;
        }
    }
    /*
     * 用于二叉树层级遍历的辅助队列
     */
    static class Queue<T> {
        private class Node {
            T item;
            Node prev, next;
            Node () {}
            Node (T ite, Node pre, Node nex) { item = ite; prev = pre; next = nex; }
            Node insertBefore(T item) {
                Node n = new Node(item, prev, this);
                if (prev != null) prev.next = n;
                prev = n;
                return n;
            }
            T delete() {
                T del = item;
                if (next != null) next.prev = prev;
                if (prev != null) prev.next = next;
                return del;
            }
        }
        private int size;
        private Node header = new Node();
        private Node tailer = new Node();
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
     * 二叉查找树
     */
    static class BST<K extends Comparable<K>, V> {
        // 二叉树内部结点
        private class Node {
            K k; V v; // 键，值
            int size; // 以该结点为子树的规模
            Node left, right, parent; // 左右子结点，父结点
            Node (K kk, V vv) { k = kk; v = vv; size = 1; }
            Node succ() { // 该结点在中序遍历顺序中的直接后继
                Node t = this;
                if (right != null) { // 如果有右孩子，那么直接后继就是该右子树中的最小
                    t = right;
                    while (t.left != null) t = t.left;
                } else { // 如果没有右孩子
                    // 如果当前结点是其父亲的右孩子，上溯
                    while (t.parent != null && t.parent.right == t) t = t.parent;
                    t = t.parent; // 再上溯一步
                }
                return t;
            }
            public String toString() { return String.format("%4s", k); }
        }
        private Node root;
        public boolean isEmpty() { return root == null; }
        private void updateSize(Node n) { n.size = 1 + size(n.left) + size(n.right); }
        private void updateParent(Node n) { // 让当前结点的左右孩子将父亲引用连上
            if (n.left != null) n.left.parent = n;
            if (n.right != null) n.right.parent = n;
        }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node n = get(root, k);
            return n == null ? null : n.v;
        }
        private Node get(Node n, K k) {
            if (n == null) return null;
            int cmp = k.compareTo(n.k);
            return cmp == 0 ? n : cmp < 0 ? get(n.left, k) : get(n.right, k);
        }
        public void put(K k, V v) {
            if (k == null) throw new IllegalArgumentException();
            if (v == null) {
                delete(k);
                return;
            }
            root = put(root, k, v);
        }
        private Node put(Node n, K k, V v) {
            if (n == null) return new Node(k, v); // 为上一层调用返回一个新结点以供连接
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n.left = put(n.left, k, v); // 深入左子树
            else if (cmp > 0) n.right = put(n.right, k, v); // 深入右子树
            else    n.v = v; // 重复键，那么更新值
            updateSize(n); updateParent(n);
            return n;
        }
        public void delete(K k) {
            if (isEmpty()) throw new NoSuchElementException();
            root = delete(root, k);
        }
        private Node delete(Node n, K k) {
            if (n == null) return null;
            int cmp = k.compareTo(n.k);
            if      (cmp < 0) n.left = delete(n.left, k);
            else if (cmp > 0) n.right = delete(n.right, k);
            else    {
                if (n.left == null) return n.right;
                if (n.right == null) return n.left;
                Node t = n;
                n = min(t.right); // t 的直接后继
                n.right = deleteMin(n.right); 
                n.left = t.left;
            }
            updateSize(n); updateParent(n);
            return n;
        }
        public void deleteMin() {
            if (isEmpty()) throw new NoSuchElementException();
            root = deleteMin(root);
        }
        private Node deleteMin(Node n) {
            if (n.left == null) return n.right;
            n.left = deleteMin(n.left);
            updateSize(n); updateParent(n);
            return n;
        }
        public K min() { 
            if (isEmpty()) throw new NoSuchElementException();
            return min(root).k;
        }
        public K max() {
            if (isEmpty()) throw new NoSuchElementException();
            return max(root).k;
        }
        private Node max(Node n) {
            while (n.right != null) n = n.right;
            return n;
        }
        private Node min(Node n) {
            while (n.left != null) n = n.left;
            return n;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(root, sb);
            return sb.toString();
        }
        private void toString(Node x, StringBuilder sb) {
            if (x == null) return;
            toString(x.left, sb);
            sb.append(String.format("{ %4s  父:%-5s   size:%4d}\n", x.k, x.parent, x.size));
            toString(x.right, sb);
        }
        /************************************************************************
         * 二叉树遍历方法
         * 
         * 以下遍历方法，均会返回一个按照结点遍历顺序拼接而成的字符串
         * 
         * 先序遍历
         * public String travPre_R()     // 递归版
         * public String travPre_I1()    // 迭代版
         * 
         * 中序遍历
         * public String travIn_R()     // 递归版
         * public String travIn_I1()    // 迭代版1
         * public String travIn_I2()    // 迭代版2
         * public String travIn_I3()    // 迭代版3
         * 
         * 后序遍历
         * public String travPost_R()   // 递归版
         * public String travPost_I()   // 迭代版
         ************************************************************************/
        /*************************************************************************
         * 🍎️先序遍历
         ************************************************************************/
        // 递归版
        public String travPre_R() {
           StringBuilder sb = new StringBuilder();
           travPre_R(root, sb);
           return sb.toString();
        }
        private void travPre_R(Node n, StringBuilder sb) {
            if (n == null) return;
            sb.append(n);
            travPre_R(n.left, sb);
            travPre_R(n.right, sb);
        }
        // 迭代版2
        public String travPre_I() {
            StringBuilder sb = new StringBuilder();
            travPre_I(root, sb);
            return sb.toString();
        }
        private void visitAlongLeftMostBranch(Node n, Stack<Node> S, StringBuilder sb) {
            while (n != null) {
                sb.append(n);
                if (n.right != null) S.push(n.right);
                n = n.left;
            }
        }
        private void travPre_I(Node n, StringBuilder sb) {
            Stack<Node> S = new Stack<>();
            while (true) {
                visitAlongLeftMostBranch(n, S, sb);
                if (S.isEmpty()) break;
                n = S.pop();
            }
        }
        /*************************************************************************
         * 🍐️中序遍历
         ************************************************************************/
        // 递归版
        public String travIn_R() {
            StringBuilder sb = new StringBuilder();
            travIn_R(root, sb);
            return sb.toString();
        }
        private void travIn_R(Node n, StringBuilder sb) {
            if (n == null) return;
            travIn_R(n.left, sb);
            sb.append(n);
            travIn_R(n.right, sb);
        }
        // 迭代版1
        public String travIn_I1() {
            StringBuilder sb = new StringBuilder();
            travIn_I1(root, sb);
            return sb.toString();
        }
        private void goAlongLeftMostBranch(Node n, Stack<Node> S) {
            while (n != null) {
                S.push(n);
                n = n.left;
            }
        }
        private void travIn_I1(Node n, StringBuilder sb) {
            Stack<Node> S = new Stack<>();
            while (true) {
                goAlongLeftMostBranch(n, S);
                if (S.isEmpty()) break;
                n = S.pop();
                sb.append(n);
                n = n.right;
            }
        }
        // 迭代版2
        public String travIn_I2() {
            StringBuilder sb = new StringBuilder();
            travIn_I2(root, sb);
            return sb.toString();
        }
        private void travIn_I2(Node n, StringBuilder sb) {
            Stack<Node> S = new Stack<Node>();
            while (true) {
                if (n != null) {
                    S.push(n);
                    n = n.left;
                } else if (!S.isEmpty()) {
                    n = S.pop();
                    sb.append(n);
                    n = n.right;
                } else break;
            }
        }
        // 迭代版3
        public String travIn_I3() {
            StringBuilder sb = new StringBuilder();
            travIn_I3(root, sb);
            return sb.toString();
        }
        private void travIn_I3(Node n, StringBuilder sb) {
            boolean backtrace = false;
            while (true) {
                if (n.left != null && !backtrace) {
                    n = n.left;
                } else {
                    sb.append(n);
                    if (n.right != null) {
                        n = n.right;
                        backtrace = false;
                    } else {
                        if ((n = n.succ()) == null) break;
                        backtrace = true;
                    }
                }
            }
        }
        /*************************************************************************
         * 🍉后序遍历
         ************************************************************************/
        // 递归版
        public String travPost_R() {
            StringBuilder sb = new StringBuilder();
            travPost_R(root, sb);
            return sb.toString();
        }
        private void travPost_R(Node n, StringBuilder sb) {
            if (n == null) return;
            travPost_R(n.left, sb);
            travPost_R(n.right, sb);
            sb.append(n);
        }
        // 迭代版
        public String travPost_I() {
            StringBuilder sb = new StringBuilder();
            travPost_I(root, sb);
            return sb.toString();
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
        /*************************************************************************
         * 🍍层级遍历
         ************************************************************************/
        public String travLevel() {
            StringBuilder sb = new StringBuilder();
            travLevel(root, sb);
            return sb.toString();
        }
        private void travLevel(Node n, StringBuilder sb) {
            Queue<Node> queue = new Queue<>();
            queue.enqueue(n);
            while (!queue.isEmpty()) {
                n = queue.dequeue();
                sb.append(n);
                if (n.left != null) queue.enqueue(n.left);
                if (n.right != null) queue.enqueue(n.right);
            }
        }
    }
    public static void main(String[] args) {
        String[] s = random(20);
        BST<String,Integer> bst = new BST<>();
        for (String ss : s) bst.put(ss, 1);
        StdOut.println(bst);
        
        StdOut.printf("先序遍历 - 递归版  : %s\n", bst.travPre_R());
        StdOut.printf("先序遍历 - 迭代版  : %s\n\n", bst.travPre_I());
        
        StdOut.printf("中序遍历 - 递归版  : %s\n", bst.travIn_R());
        StdOut.printf("中序遍历 - 迭代版1 : %s\n", bst.travIn_I1());
        StdOut.printf("中序遍历 - 迭代版2 : %s\n", bst.travIn_I2());
        StdOut.printf("中序遍历 - 迭代版3 : %s\n\n", bst.travIn_I3());
        
        StdOut.printf("后序遍历 - 递归版  : %s\n", bst.travPost_R());
        StdOut.printf("后序遍历 - 迭代版  : %s\n\n", bst.travPost_I());
        
        StdOut.printf("层级遍历          : %s\n\n", bst.travLevel());
    }
    // output
    /*
     *  {    A  父:   B    size:   1}
        {    B  父:   C    size:   2}
        {    C  父:   I    size:   4}
        {    E  父:   C    size:   1}
        {    I  父:null    size:  13}
        {    L  父:   O    size:   1}
        {    O  父:   Z    size:   7}
        {    P  父:   S    size:   2}
        {    Q  父:   P    size:   1}
        {    S  父:   O    size:   5}
        {    T  父:   X    size:   1}
        {    X  父:   S    size:   2}
        {    Z  父:   I    size:   8}
        
        先序遍历 - 递归版  :    I   C   B   A   E   Z   O   L   S   P   Q   X   T
        先序遍历 - 迭代版  :    I   C   B   A   E   Z   O   L   S   P   Q   X   T
        
        中序遍历 - 递归版  :    A   B   C   E   I   L   O   P   Q   S   T   X   Z
        中序遍历 - 迭代版1 :    A   B   C   E   I   L   O   P   Q   S   T   X   Z
        中序遍历 - 迭代版2 :    A   B   C   E   I   L   O   P   Q   S   T   X   Z
        中序遍历 - 迭代版3 :    A   B   C   E   I   L   O   P   Q   S   T   X   Z
        
        后序遍历 - 递归版  :    A   B   E   C   L   Q   P   T   X   S   O   Z   I
        后序遍历 - 迭代版  :    A   B   E   C   L   Q   P   T   X   S   O   Z   I
        
        层级遍历          :    I   C   Z   B   E   O   A   L   S   P   X   Q   T
     */
    
    
}

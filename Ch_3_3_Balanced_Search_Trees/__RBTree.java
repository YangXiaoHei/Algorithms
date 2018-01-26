package Ch_3_3_Balanced_Search_Trees;

import edu.princeton.cs.algs4.*;
import java.util.*;
import static Tool.ArrayGenerator.Alphbets.*;

public class __RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        K key; V val; int size;
        Node left, right;
        boolean color;
        Node (K k, V v) { key = k; val = v; size = 1; color = RED; }
        public String toString() { return String.format("%4s%4s%4d%4d\n", key, color ? "红" : "黑", height(this), size(this)); }
    }
    private Node root;
    public boolean isEmpty() { return root == null; }
    public int size() { return size(root); }
    private int size(Node n) { return n == null ? 0 : n.size; }
    public int height() { return height(root); }
    private int height(Node n) { 
        if (n == null) return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }
    /*
     * 平均比较次数
     */
    public int avgCompares() { return ipl() / size(); }
    public int ipl() { return ipl(root, 0); }
    /*
     * 内部路径长度
     */
    private int ipl(Node n, int depth) {
        if (n == null) return 0;
        int curLevel = depth;
        depth += ipl(n.left, curLevel + 1);
        depth += ipl(n.right, curLevel + 1);
        return depth;
    }
    /*
     * 插入键值对
     */
    public void put(K k, V v) {
        if (k == null) throw new IllegalArgumentException();
        if (v == null) {
            delete(k);
            return;
        }
        root = put(root, k, v);
        root.color = BLACK;
    }
    private Node put(Node n, K k, V v) {
        if (n == null) return new Node(k, v);
        int cmp = k.compareTo(n.key);
        if      (cmp < 0) n.left = put(n.left, k, v);
        else if (cmp > 0) n.right = put(n.right, k, v);
        else    n.val = v;
        if (isRed(n.right) && !isRed(n.left)) n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
        if (isRed(n.left) && isRed(n.right)) flipColors(n);
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }
    /*
     * 获取与键关联的值
     */
    public V get(K k) {
        if (k == null) throw new IllegalArgumentException();
        if (isEmpty()) throw new NoSuchElementException();
        Node n = get(root, k);
        return n == null ? null : n.val;
    }
    private Node get(Node n, K k) {
        while (n != null) {
            int cmp = k.compareTo(n.key);
            if (cmp == 0) return n;
            n = cmp < 0 ? n.left : n.right;
        }
        return null;
    }
    /*
     * 是否包含
     */
    public boolean contain(K k) {
        if (k == null) throw new IllegalArgumentException();
        if (isEmpty()) return false;
        return get(root, k) != null;
    }
    /*
     * 最小值
     */
    public K min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }
    private Node min(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }
    /*
     * 最大值
     */
    public K max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }
    private Node max(Node n) {
        while (n.right != null) n = n.right;
        return n;
    }
    /*
     * 删除最小值
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMin(Node n) {
        if (n.left == null) return null;
        if (!isRed(n.left) && !isRed(n.left.left))
            n = moveRedLeft(n);
        n.left = deleteMin(n.left);
        return balance(n);
    }
    /*
     * 删除最大值
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMax(Node n) {
        if (isRed(n.left))
            n = rotateRight(n);
        if (n.right == null) return null;
        if (!isRed(n.right) && !isRed(n.right.left))
            n = moveRedRight(n);
        n.right = deleteMax(n.right);
        return balance(n);
    }
    /*
     * 删除结点
     */
    public void delete(K k) {
        if (k == null) throw new IllegalArgumentException();
        if (!contain(k)) throw new IllegalArgumentException();
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, k);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node delete(Node n, K k) {
        if (k.compareTo(n.key) < 0) {
            if (!isRed(n.left) && !isRed(n.left.left))
                n = moveRedLeft(n);
            n.left = delete(n.left, k);
        } else {
            if (isRed(n.left)) n = rotateRight(n);
            if (k.compareTo(n.key) == 0 && n.right == null) return null;
            if (!isRed(n.right) && !isRed(n.right.left)) n = moveRedRight(n);
            if (k.compareTo(n.key) == 0) {
                Node succ = min(n.right);
                n.key = succ.key;
                n.val = succ.val;
                n.right = deleteMin(n.right);
            } else {
                n.right = delete(n.right, k);
            }
        }
        return balance(n);
    }
    /**********************************************************
     * helper class
     **********************************************************/
    static class Queue<T> implements Iterable<T> {
        private class Node {
            T item; Node prev, next;
            Node () {}
            Node (T it, Node pre, Node nex) { item = it; prev = pre; next = nex; }
            Node insertBefore(T item) {
                Node n = new Node(item, prev, this);
                if (prev != null) prev.next = n;
                return prev = n;
            }
            T delete() {
                T del = item;
                if (prev != null) prev.next = next;
                if (next != null) next.prev = prev;
                return del;
            }
        }
        private Node header = new Node(), tailer = new Node();
        private int size;
        {
            header.next = tailer;
            tailer.prev = header;
        }
        public boolean isEmpty() { return size == 0; }
        public void enqueue(T item) { ++size; tailer.insertBefore(item); }
        public T dequeue() {
            if (isEmpty())  throw new NoSuchElementException();
            --size; return header.next.delete();
        }
        @SuppressWarnings("unchecked")
        public T[] toArray() {
            T[] objs = (T[])new Object[size]; int i = 0;
            for (Node n = header.next; n != tailer; n = n.next)
                objs[i++] = n.item;
            return objs;
        }
        public Queue<T> copy() {
            Queue<T> q = new Queue<>();
            for (Node n = header.next; n != tailer; n = n.next)
                q.enqueue(n.item);
            return q;
        }
        public Iterator<T> iterator() {
            return new Iterator<T>() {
               private Queue<T> copy = copy();
               public boolean hasNext() { return !copy.isEmpty(); }
               public T next() { return copy.dequeue(); }
            };
        }
    }
    // 层次遍历
    public String travLevel() {
        if (isEmpty()) return "Empty Tree";
        StringBuilder sb = new StringBuilder();
        travLevel(root, sb);
        return sb.toString();
    }
    private void travLevel(Node n, StringBuilder sb) {
        Queue<Node> queue = new Queue<>();
        queue.enqueue(n);
        while (!queue.isEmpty()) {
            sb.append(n = queue.dequeue());
            if (n.left != null)  queue.enqueue(n.left);
            if (n.right != null) queue.enqueue(n.right);
        }
    }
    // 可遍历键集
    public Iterable<K> keys() { return keys(min(), max()); }
    public Iterable<K> keys(K lo, K hi) {
        Queue<K> q = new Queue<>();
        keys(root, q, lo, hi);
        return q;
    }
    private void keys(Node n, Queue<K> list, K lo, K hi) {
        if (n == null) return;
        int cmplo = lo.compareTo(n.key);
        int cmphi = n.key.compareTo(hi);
        if (cmplo < 0) keys(n.left, list, lo, hi);
        if (cmplo <= 0 && cmphi <= 0) list.enqueue(n.key);
        if (cmphi < 0) keys(n.right, list, lo, hi);
    }
    /**********************************************************
     * helper function
     **********************************************************/
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    private boolean isRed(Node n) { return n == null ? false : n.color; }
    private Node balance(Node h) {
        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);
        h.size = 1 + size(h.left) + size(h.right);
        return h;
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
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    public static void main(String[] args) {
        String[] s = allRandom();
        print(s);
        
        // 构造一棵红黑树
        __RBTree<String, Integer> rbtree = new __RBTree<>();
        for (String key : s) rbtree.put(key, 1);
        StdOut.printf("平均比较次数 : %d\n", rbtree.avgCompares());
        StdOut.println(rbtree.travLevel());
        
       
        // 逐个删除结点
        while (!rbtree.isEmpty()) {
            String[] keys = objectToString(((Queue<String>)rbtree.keys()).toArray());
            String rk = keys[StdRandom.uniform(keys.length)];
            StdOut.printf("删除 %4s\n", rk);
            rbtree.delete(rk);
            StdOut.println(rbtree.travLevel());
        }
    }
    // output
    /*
     *    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
          J  I  B  T  M  C  N  R  V  Q  H  Z  S  X  U  F  W  G  O  A  Y  L  K  E  D  P
        
           M   黑   5  26
           G   黑   4  12
           T   黑   4  13
           E   黑   3   6
           K   黑   2   5
           R   黑   3   6
           X   黑   2   6
           C   红   2   4
           F   黑   0   1
           I   红   1   3
           L   黑   0   1
           O   红   2   4
           S   黑   0   1
           V   红   1   3
           Z   黑   1   2
           B   黑   1   2
           D   黑   0   1
           H   黑   0   1
           J   黑   0   1
           N   黑   0   1
           Q   黑   1   2
           U   黑   0   1
           W   黑   0   1
           Y   红   0   1
           A   红   0   1
           P   红   0   1
        
        删除    B
           M   黑   5  25
           G   黑   3  11
           T   黑   4  13
           E   黑   2   5
           K   黑   2   5
           R   黑   3   6
           X   黑   2   6
           C   红   1   3
           F   黑   0   1
           I   红   1   3
           L   黑   0   1
           O   红   2   4
           S   黑   0   1
           V   红   1   3
           Z   黑   1   2
           A   黑   0   1
           D   黑   0   1
           H   黑   0   1
           J   黑   0   1
           N   黑   0   1
           Q   黑   1   2
           U   黑   0   1
           W   黑   0   1
           Y   红   0   1
           P   红   0   1
        
        删除    F
           M   黑   5  24
           G   黑   3  10
           T   黑   4  13
           C   黑   2   4
           K   黑   2   5
           R   黑   3   6
           X   黑   2   6
           A   黑   0   1
           E   黑   1   2
           I   红   1   3
           L   黑   0   1
           O   红   2   4
           S   黑   0   1
           V   红   1   3
           Z   黑   1   2
           D   红   0   1
           H   黑   0   1
           J   黑   0   1
           N   黑   0   1
           Q   黑   1   2
           U   黑   0   1
           W   黑   0   1
           Y   红   0   1
           P   红   0   1
        
        删除    J
           M   黑   5  23
           G   黑   3   9
           T   黑   4  13
           C   黑   2   4
           K   黑   2   4
           R   黑   3   6
           X   黑   2   6
           A   黑   0   1
           E   黑   1   2
           I   黑   1   2
           L   黑   0   1
           O   红   2   4
           S   黑   0   1
           V   红   1   3
           Z   黑   1   2
           D   红   0   1
           H   红   0   1
           N   黑   0   1
           Q   黑   1   2
           U   黑   0   1
           W   黑   0   1
           Y   红   0   1
           P   红   0   1
        
        删除    M
           N   黑   4  22
           G   黑   3   9
           T   黑   3  12
           C   黑   2   4
           K   黑   2   4
           R   黑   2   5
           X   黑   2   6
           A   黑   0   1
           E   黑   1   2
           I   黑   1   2
           L   黑   0   1
           P   红   1   3
           S   黑   0   1
           V   红   1   3
           Z   黑   1   2
           D   红   0   1
           H   红   0   1
           O   黑   0   1
           Q   黑   0   1
           U   黑   0   1
           W   黑   0   1
           Y   红   0   1
        
        删除    Z
           N   黑   4  21
           G   黑   3   9
           T   黑   3  11
           C   黑   2   4
           K   黑   2   4
           R   黑   2   5
           X   黑   2   5
           A   黑   0   1
           E   黑   1   2
           I   黑   1   2
           L   黑   0   1
           P   红   1   3
           S   黑   0   1
           V   红   1   3
           Y   黑   0   1
           D   红   0   1
           H   红   0   1
           O   黑   0   1
           Q   黑   0   1
           U   黑   0   1
           W   黑   0   1
        
        删除    V
           N   黑   4  20
           G   黑   3   9
           T   黑   3  10
           C   黑   2   4
           K   黑   2   4
           R   黑   2   5
           X   黑   2   4
           A   黑   0   1
           E   黑   1   2
           I   黑   1   2
           L   黑   0   1
           P   红   1   3
           S   黑   0   1
           W   黑   1   2
           Y   黑   0   1
           D   红   0   1
           H   红   0   1
           O   黑   0   1
           Q   黑   0   1
           U   红   0   1
        
        删除    W
           N   黑   4  19
           G   黑   3   9
           R   黑   3   9
           C   黑   2   4
           K   黑   2   4
           P   黑   1   3
           X   黑   2   5
           A   黑   0   1
           E   黑   1   2
           I   黑   1   2
           L   黑   0   1
           O   黑   0   1
           Q   黑   0   1
           T   红   1   3
           Y   黑   0   1
           D   红   0   1
           H   红   0   1
           S   黑   0   1
           U   黑   0   1
        
        删除    D
           N   黑   4  18
           G   黑   3   8
           R   黑   3   9
           C   黑   1   3
           K   黑   2   4
           P   黑   1   3
           X   黑   2   5
           A   黑   0   1
           E   黑   0   1
           I   黑   1   2
           L   黑   0   1
           O   黑   0   1
           Q   黑   0   1
           T   红   1   3
           Y   黑   0   1
           H   红   0   1
           S   黑   0   1
           U   黑   0   1
        
        删除    R
           N   黑   4  17
           G   黑   3   8
           S   黑   3   8
           C   黑   1   3
           K   黑   2   4
           P   黑   1   3
           X   黑   2   4
           A   黑   0   1
           E   黑   0   1
           I   黑   1   2
           L   黑   0   1
           O   黑   0   1
           Q   黑   0   1
           U   黑   1   2
           Y   黑   0   1
           H   红   0   1
           T   红   0   1
        
        删除    K
           N   黑   4  16
           G   黑   2   7
           S   黑   3   8
           C   黑   1   3
           I   黑   1   3
           P   黑   1   3
           X   黑   2   4
           A   黑   0   1
           E   黑   0   1
           H   黑   0   1
           L   黑   0   1
           O   黑   0   1
           Q   黑   0   1
           U   黑   1   2
           Y   黑   0   1
           T   红   0   1
        
        删除    T
           N   黑   3  15
           G   黑   2   7
           S   黑   2   7
           C   黑   1   3
           I   黑   1   3
           P   黑   1   3
           X   黑   1   3
           A   黑   0   1
           E   黑   0   1
           H   黑   0   1
           L   黑   0   1
           O   黑   0   1
           Q   黑   0   1
           U   黑   0   1
           Y   黑   0   1
        
        删除    S
           N   黑   3  14
           G   红   2   7
           U   黑   2   6
           C   黑   1   3
           I   黑   1   3
           P   红   1   3
           Y   黑   1   2
           A   黑   0   1
           E   黑   0   1
           H   黑   0   1
           L   黑   0   1
           O   黑   0   1
           Q   黑   0   1
           X   红   0   1
        
        删除    H
           N   黑   3  13
           G   黑   2   6
           U   黑   2   6
           C   红   1   3
           L   黑   1   2
           P   红   1   3
           Y   黑   1   2
           A   黑   0   1
           E   黑   0   1
           I   红   0   1
           O   黑   0   1
           Q   黑   0   1
           X   红   0   1
        
        删除    P
           N   黑   3  12
           G   黑   2   6
           U   黑   2   5
           C   红   1   3
           L   黑   1   2
           Q   黑   1   2
           Y   黑   1   2
           A   黑   0   1
           E   黑   0   1
           I   红   0   1
           O   红   0   1
           X   红   0   1
        
        删除    G
           N   黑   3  11
           I   黑   2   5
           U   黑   2   5
           C   红   1   3
           L   黑   0   1
           Q   黑   1   2
           Y   黑   1   2
           A   黑   0   1
           E   黑   0   1
           O   红   0   1
           X   红   0   1
        
        删除    E
           N   黑   3  10
           I   黑   2   4
           U   黑   2   5
           C   黑   1   2
           L   黑   0   1
           Q   黑   1   2
           Y   黑   1   2
           A   红   0   1
           O   红   0   1
           X   红   0   1
        
        删除    U
           N   黑   3   9
           I   黑   2   4
           X   黑   2   4
           C   黑   1   2
           L   黑   0   1
           Q   黑   1   2
           Y   黑   0   1
           A   红   0   1
           O   红   0   1
        
        删除    O
           N   黑   3   8
           I   黑   2   4
           X   黑   1   3
           C   黑   1   2
           L   黑   0   1
           Q   黑   0   1
           Y   黑   0   1
           A   红   0   1
        
        删除    I
           N   黑   2   7
           C   黑   1   3
           X   黑   1   3
           A   黑   0   1
           L   黑   0   1
           Q   黑   0   1
           Y   黑   0   1
        
        删除    X
           N   黑   2   6
           C   红   1   3
           Y   黑   1   2
           A   黑   0   1
           L   黑   0   1
           Q   红   0   1
        
        删除    Q
           N   黑   2   5
           C   红   1   3
           Y   黑   0   1
           A   黑   0   1
           L   黑   0   1
        
        删除    A
           N   黑   2   4
           L   黑   1   2
           Y   黑   0   1
           C   红   0   1
        
        删除    L
           N   黑   1   3
           C   黑   0   1
           Y   黑   0   1
        
        删除    N
           Y   黑   1   2
           C   红   0   1
        
        删除    C
           Y   黑   0   1
        
        删除    Y
        Empty Tree
     */
}


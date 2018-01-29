package Ch_3_3_Balanced_Search_Trees;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import static Tool.ArrayGenerator.Alphbets.*;

public class Practise_3_3_21 {
    static class RBTree <K extends Comparable<K>, V> {
        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private class Node {
            K key; V val; int size;
            Node left, right;
            boolean color;
            Node(K k, V v) { key = k; val = v; size = 1; color = RED; }
            public String toString() { return String.format("%5s %5s %3d\n", key, color ? "红" : "黑", size); }
        }
        private Node root;
        /***************************************************
         *  Helper Function
         **************************************************/
        private int size(Node h) { return h == null ? 0 : h.size; }
        private boolean isRed(Node h) { return h != null && h.color; }
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
        private Node moveRedLeft(Node h) {
            flipColors(h);
            if (isRed(h.right.left)) {  // 是否形成了 4- 结点
                h.right = rotateRight(h.right);
                h = rotateLeft(h);
                flipColors(h);
            }
            return h;
        }
        private Node moveRedRight(Node h) {
            flipColors(h);
            if (isRed(h.left.left)) { // 是否形成了 4- 结点
                h = rotateRight(h);
                flipColors(h);
            }
            return h;
        }
        private Node balance(Node h) {
            if (isRed(h.right))                      h = rotateLeft(h);
            if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
            if (isRed(h.left) && isRed(h.right))     flipColors(h);
            h.size = 1 + size(h.left) + size(h.right);
            return h;
        }
        /***************************************************
         *  Public API
         **************************************************/
        // 树规模
        public int size() { return size(root); }
        // 树是否为空
        public boolean isEmpty() { return root == null; }
        // 获取与键关联的值
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) return null;
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
        // 插入键值对
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
            if (isRed(n.right) && !isRed(n.left))    n = rotateLeft(n);
            if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
            if (isRed(n.left) && isRed(n.right))     flipColors(n);
            n.size = 1 + size(n.left) + size(n.right);
            return n;
        }
        // 删除最小键
        public void deleteMin() {
            if (isEmpty()) throw new NoSuchElementException();
            if (!isRed(root.left) && !isRed(root.right))
                root.color = RED;
            root = deleteMin(root);
            if(root != null) root.color = BLACK;
        }
        private Node deleteMin(Node n) {
            if (n.left == null) return null;
            if (!isRed(n.left) && !isRed(n.left.left))
                n = moveRedLeft(n);
            n.left = deleteMin(n.left);
            return balance(n);
        }
        // 删除最大键
        public void deleteMax() {
            if (isEmpty()) throw new NoSuchElementException();
            if (!isRed(root.left) && !isRed(root.right))
                root.color = RED;
            root = deleteMax(root);
            if (root != null) root.color = BLACK;
        }
        private Node deleteMax(Node n) {
            if (isRed(n.left)) n = rotateRight(n);
            if (n.right == null) return null;
            if (!isRed(n.right) && !isRed(n.right.left))
                n = moveRedRight(n);
            n.right = deleteMax(n.right);
            return balance(n);
        }
        // 是否包含键
        public boolean contain(K k) { return get(k) != null; }
        // 删除键
        public void delete(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (!contain(k)) throw new IllegalArgumentException();
            if (!isRed(root.left) && !isRed(root.right))
                root.color = RED;
            root = delete(root, k);
            if (root != null) root.color = BLACK;
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
                    Node min = min(n.right);
                    n.key = min.key;
                    n.val = min.val;
                    n.right = deleteMin(n.right);
                } else {
                    n.right = delete(n.right, k);
                }
            }
            return balance(n);
        }
        // 获取最小键
        public K min() { 
            if (isEmpty()) throw new NoSuchElementException();
            return min(root).key;
        }
        private Node min(Node n) {
            while (n.left != null) n = n.left;
            return n;
        }
        // 获取最大键
        public K max() {
            if (isEmpty()) throw new NoSuchElementException();
            return max(root).key;
        }
        private Node max(Node n) {
            while (n.right != null) n = n.right;
            return n;
        }
        // 小于 K 的最大键
        public K floor(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node n = floor(root, k);
            return n == null ? null : n.key;
        }
        private Node floor(Node n, K k) {
            Node tmp = null;
            while (n != null) {
                int cmp = k.compareTo(n.key);
                if (cmp == 0) return n;
                if (cmp < 0) n = n.left;
                else         { tmp = n; n = n.right; }
            }
            return tmp;
        }
        // 大于 k 的最小键
        public K ceiling(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            Node n = ceiling(root, k);
            return n == null ? null : n.key;
        }
        private Node ceiling(Node n, K k) {
            Node tmp = null;
            while (n != null) {
                int cmp = k.compareTo(n.key);
                if (cmp == 0) return n;
                if (cmp > 0) n = n.right;
                else         { tmp = n; n = n.left; }
            }
            return tmp;
        }
        // 选择次序为 i 的键
        public K select(int i) {
            if (i < 0 || i >= size()) throw new IllegalArgumentException();
            return select(root, i).key;
        }
        private Node select(Node n, int i) {
            while (n != null) {
                int ls = size(n.left);
                if      (i < ls) n = n.left;
                else if (i > ls) { i-= (ls + 1); n = n.right; }
                else    return n;
            }
            return null;
        }
        // 排名
        public int rank(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) throw new NoSuchElementException();
            if (!contain(k)) throw new NoSuchElementException();
            return rank(root, k);
        }
        private int rank(Node n, K k) {
            int count = 0;
            while (true) {
                int cmp = k.compareTo(n.key);
                if (cmp == 0) return count + size(n.left);
                if (cmp < 0) n = n.left;
                else         { count += (size(n.left) + 1); n = n.right; }
            }
        }
        private class Queue<T> {
            private class Node {
                T item;
                Node prev, next;
                Node () {}
                Node (T i, Node p, Node n) {
                    item = i;
                    prev = p;
                    next = n;
                }
                Node insertBefore(T item) {
                    Node n = new Node(item, prev, this);
                    if (prev != null) prev.next = n;
                    prev = n;
                    return n;
                }
                T delete() {
                    T del = item;
                    if (prev != null) prev.next = next;
                    if (next != null) next.prev = prev;
                    return del;
                }
            }
            private int size;
            private Node header = new Node(), tailer = new Node();
            {
                header.next = tailer;
                tailer.prev = header;
            }
            public boolean isEmpty() { return size == 0; }
            public void enqueue(T item) { ++size; tailer.insertBefore(item); }
            public T dequeue() {
                if (isEmpty()) throw new NoSuchElementException();
                --size; return header.next.delete();
            }
        }
        // 层级遍历
        public String travLevel() {
            if (isEmpty()) return "Empty Tree";
            StringBuilder sb = new StringBuilder();
            Queue<Node> q = new Queue<>();
            q.enqueue(root); Node n = null;
            while (!q.isEmpty()) {
                sb.append(n = q.dequeue());
                if (n.left != null)  q.enqueue(n.left);
                if (n.right != null) q.enqueue(n.right);
            }
            return sb.toString();
        } 
    }
    public static void main(String[] args) {
        String[] s = parseAlphbets("P G W C Y R F J A N U O L I E S K X D Q");
        print(s);
        RBTree<String, Integer> rb = new RBTree<>();
        for (String ss : s) rb.put(ss, 1);
        StdOut.println(rb.travLevel());
        
        /*
         * 最大键
         */
        StdOut.printf("最大键 : %5s\n", rb.max());
        
        /*
         * 最小键
         */
        StdOut.printf("最小键 : %5s\n", rb.min());
        
        /*
         * 排名
         */
        StdOut.printf("J 的排名 %5d\n", rb.rank("J"));
        
        /*
         * 选择
         */
        StdOut.printf("排名为 5 的键是 %5s\n\n", rb.select(5));
        
        /*
         * floor
         */
        StdOut.printf("小于 E 的最大键 : %5s\n", rb.floor("E"));
        StdOut.printf("小于 H 的最大键 : %5s\n", rb.floor("H"));
        StdOut.printf("小于 Z 的最大键 : %5s\n", rb.floor("Z"));
        StdOut.printf("小于 I 的最大键 : %5s\n", rb.floor("I"));
        StdOut.printf("小于 K 的最大键 : %5s\n", rb.floor("K"));
        StdOut.printf("小于 A 的最大键 : %5s\n", rb.floor("A"));
        
        /*
         * ceiling
         */
        StdOut.printf("大于 E 的最小键 : %5s\n", rb.ceiling("E"));
        StdOut.printf("大于 H 的最小键 : %5s\n", rb.ceiling("H"));
        StdOut.printf("大于 Z 的最小键 : %5s\n", rb.ceiling("Z"));
        StdOut.printf("大于 I 的最小键 : %5s\n", rb.ceiling("I"));
        StdOut.printf("大于 K 的最小键 : %5s\n", rb.ceiling("K"));
    }
}

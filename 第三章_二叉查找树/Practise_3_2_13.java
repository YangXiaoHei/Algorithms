package 第三章_二叉查找树;

import static 第二章_优先队列.Text_Alphabet.random;

import edu.princeton.cs.algs4.StdOut;
import 第三章_二叉查找树.Practise_3_2_10.BST;

public class Practise_3_2_13 {
    static class BST<K extends Comparable<K>, V> {
        private class Node {
            K k; V v; Node left, right, parent;
            int size;
            Node(K kk, V vv) { k = kk; v = vv; size = 1; }
            Node(K kk, V vv, Node pa) { k = kk; v = vv; size = 1; parent = pa; }
        }
        private Node root, hot;
        public boolean isEmpty() { return root == null; }
        public int size() { return size(root); }
        private int size(Node n) { return n == null ? 0 : n.size; }
        public void updateSizeAbove(Node n) {
            while (n != null) {
                n.size = 1 + size(n.left) + size(n.right);
                n = n.parent;
            }
        }
        public int height() { return height(root); }
        private int height(Node n) {
            if (n == null) return -1;
            return 1 + Math.max(height(n.left), height(n.right));
        }
        public int avgCompares() { return ipl() / size(); }
        public int ipl() { return ipl(root, 0); }
        private int ipl(Node n, int depth) {
            if (n == null) return 0;
            int cur = depth;
            depth += ipl(n.left, cur + 1);
            depth += ipl(n.right, cur + 1);
            return depth;
        }
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) return null;
            Node n = get(root, k);
            return n == null ? null : n.v;
        }
        private Node get(Node n, K k) {
            hot = null;
            while (n != null) {
                int cmp = k.compareTo(n.k);
                if (cmp == 0) break;
                hot = n;
                n = cmp < 0 ? n.left : n.right;
            }
            return n;
        }
        public void put(K k, V v) {
            if (k == null) throw new IllegalArgumentException();
            if (isEmpty()) {
                root = new Node(k, v);
                return;
            }
            Node n = get(root, k);
            if (n != null) {
                n.v = v;
                return;
            }
            int cmp = k.compareTo(hot.k);
            if (cmp < 0) hot.left  = new Node(k,v, hot);
            else         hot.right = new Node(k,v, hot);
            updateSizeAbove(hot);
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(root, sb);
            sb.append(String.format("树高 : %d\n"
                    + "树规模 : %d\n"
                    + "内部路径长度 : %d\n"
                    + "随机命中查找平均所需比较次数 : %d\n", 
                    height(), size(), ipl(), avgCompares()));
            return sb.toString();
        }
        private void toString(Node x, StringBuilder sb) {
            if (x == null) return;
            toString(x.left, sb);
            sb.append(String.format("{ %4s  %4s size = %4d }\n", x.k, x.v, x.size));
            toString(x.right, sb);
        }
    }
    public static void main(String[] args) {
        String[] alps = random(30);
        for (int i = 0; i < alps.length; i++)
            StdOut.println(alps[i]);
        BST<String, Integer> bst = new BST<String, Integer>();
        for (int i = 0; i < alps.length; i++)
            bst.put(alps[i], i);
        StdOut.println(bst);
    }
}

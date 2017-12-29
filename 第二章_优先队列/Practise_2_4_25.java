package 第二章_优先队列;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import static 第二章_初级排序算法.Text_Array.*;

public class Practise_2_4_25 {
    static class MinPQ<Key extends Comparable<Key>> {
        private class Node {
            Node parent, left, right;
            int height;
            Key key;
            Node (Key key) { this.key = key; }
        }
        private Node root;
        public boolean isEmpty() { return root == null; }
        public void insert(Key key) { root = insert(root, key); }
        public Key delMin() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            // 寻找末尾结点
            Node last = root;
            while (last.left != null || last.right != null) {
                int lh = last.left == null ? -1 : last.left.height;
                int rh = last.right == null ? -1 : last.right.height;
                last = lh > rh ? last.left : last.right;
            }
            // 末尾即根
            if (last == root) {
                Key max = root.key;
                root = null;
                return max;
            }
            Key min = root.key;
            root.key = last.key;
            // 删除末尾结点
            Node parent = last.parent;
            if (last.parent.left == last)
                parent.left = null;
            else
                parent.right = null;
            // 更新该路径上的结点高度
            while (parent != null) {
                int lh = parent.left == null ? -1 : parent.left.height;
                int rh = parent.right == null ? -1 : parent.right.height;
                parent.height = 1 + (lh > rh ? lh : rh);
                parent = parent.parent;
            }
            // 下沉
            Node cur = root;
            while (cur.left != null || cur.right != null) {
                if (cur.left == null) {
                    if (cur.key.compareTo(cur.right.key) <= 0) break;
                    Key k = cur.key;
                    cur.key = cur.right.key;
                    cur.right.key = k;
                    cur = cur.right;
                } else if (cur.right == null) {
                    if (cur.key.compareTo(cur.left.key) <= 0) break;
                    Key k = cur.key;
                    cur.key = cur.left.key;
                    cur.left.key = k;
                    cur = cur.left;
                } else {
                    Node m = cur.left.key.compareTo(cur.right.key) < 0 ? cur.left : cur.right;
                    if (cur.key.compareTo(m.key) <= 0) break;
                    Key t = cur.key;
                    cur.key = m.key;
                    m.key = t;
                    cur = m == cur.left ? cur.left : cur.right;
                }
            }
            return min;
        }
        private Node insert(Node n, Key key) {
            if (n == null) 
                return new Node(key);
            if (key.compareTo(n.key) < 0) {
                Key k = n.key;
                n.key = key;
                key = k;
            }
            int lh = (n.left == null) ? -1 : n.left.height;
            int rh = (n.right == null) ? -1 : n.right.height;
            if (lh <= rh) {
                n.left = insert(n.left, key);
                n.left.parent = n;
            } else {
                n.right = insert(n.right, key);
                n.right.parent = n;
            }
            lh = (n.left == null) ? -1 : n.left.height;
            rh = (n.right == null) ? -1 : n.right.height;
            n.height = 1 + ((lh > rh) ? lh : rh);
            return n;
        }
    }
    public static void main(String[] args) {
        Integer[] a = Integers(0, 20);
        MinPQ<Integer> pq = new MinPQ<Integer>();
        for (Integer i : a) pq.insert(i);
        while (!pq.isEmpty())
            StdOut.println(pq.delMin());
    }
    // output
    /*
     *  0
        1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        11
        12
        13
        14
        15
        16
        17
        18
        19
        20

     */
}

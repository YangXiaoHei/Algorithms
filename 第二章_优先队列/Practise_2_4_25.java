package 第二章_优先队列;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import static 第二章_初级排序算法.Text_Array.*;

public class Practise_2_4_25 {
    static class CubeSum implements Comparable<CubeSum> {
        private final int sum;
        private final int i;
        private final int j;
        public CubeSum(int i, int j) {
            this.sum = i * i * i + j * j * j;
            this.i = i;
            this.j = j;
        }
        public int compareTo(CubeSum that) {
            if (this.sum < that.sum) return -1;
            if (this.sum > that.sum) return +1;
            return 0;
        }
        public String toString() {
            return sum + " = " + i + "^3" + " + " + j + "^3";
        }
    }
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
        int N = 10;
        MinPQ<CubeSum> pq = new MinPQ<CubeSum>();
        for (int i = 0; i <= N; i++) {
            pq.insert(new CubeSum(i, i));
        }

        while (!pq.isEmpty()) {
            CubeSum s = pq.delMin();
            StdOut.println(s);
            if (s.j < N)
                pq.insert(new CubeSum(s.i, s.j + 1));
        }
    }
    // output
    /*
     *  0 = 0^3 + 0^3
        1 = 0^3 + 1^3
        2 = 1^3 + 1^3
        8 = 0^3 + 2^3
        9 = 1^3 + 2^3
        16 = 2^3 + 2^3
        27 = 0^3 + 3^3
        28 = 1^3 + 3^3
        35 = 2^3 + 3^3
        54 = 3^3 + 3^3
        64 = 0^3 + 4^3
        65 = 1^3 + 4^3
        72 = 2^3 + 4^3
        91 = 3^3 + 4^3
        125 = 0^3 + 5^3
        126 = 1^3 + 5^3
        128 = 4^3 + 4^3
        133 = 2^3 + 5^3
        152 = 3^3 + 5^3
        189 = 4^3 + 5^3
        216 = 0^3 + 6^3
        217 = 1^3 + 6^3
        224 = 2^3 + 6^3
        243 = 3^3 + 6^3
        250 = 5^3 + 5^3
        280 = 4^3 + 6^3
        341 = 5^3 + 6^3
        343 = 0^3 + 7^3
        344 = 1^3 + 7^3
        351 = 2^3 + 7^3
        370 = 3^3 + 7^3
        407 = 4^3 + 7^3
        432 = 6^3 + 6^3
        468 = 5^3 + 7^3
        512 = 0^3 + 8^3
        513 = 1^3 + 8^3
        520 = 2^3 + 8^3
        539 = 3^3 + 8^3
        559 = 6^3 + 7^3
        576 = 4^3 + 8^3
        637 = 5^3 + 8^3
        686 = 7^3 + 7^3
        728 = 6^3 + 8^3
        729 = 0^3 + 9^3
        730 = 1^3 + 9^3
        737 = 2^3 + 9^3
        756 = 3^3 + 9^3
        793 = 4^3 + 9^3
        854 = 5^3 + 9^3
        855 = 7^3 + 8^3
        945 = 6^3 + 9^3
        1000 = 0^3 + 10^3
        1001 = 1^3 + 10^3
        1008 = 2^3 + 10^3
        1024 = 8^3 + 8^3
        1027 = 3^3 + 10^3
        1064 = 4^3 + 10^3
        1072 = 7^3 + 9^3
        1125 = 5^3 + 10^3
        1216 = 6^3 + 10^3
        1241 = 8^3 + 9^3
        1343 = 7^3 + 10^3
        1458 = 9^3 + 9^3
        1512 = 8^3 + 10^3
        1729 = 9^3 + 10^3
        2000 = 10^3 + 10^3

     */
}

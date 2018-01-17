package Ch_2_4_Priority_Queues;

import edu.princeton.cs.algs4.*;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.util.*;

public class Practise_2_4_24 {
    static class MaxPQ <Key extends Comparable<Key>> {
        class Node {
            Node left, right;
            Node parent;
            int height;
            Key key;
            Node (Key key) { this.key = key; }
        }
        Node root = null;
        public boolean isEmpty() { return root == null; }
        public void insert(Key key) { root = insert(root, key); }
        private final int maxHeight(Node n1, Node n2) { return getHeight(n1) > getHeight(n2) ? getHeight(n1) : getHeight(n2); }
        private final Node maxNode(Node n1, Node n2) { 
            if (n1 == null) return n2;
            if (n2 == null) return n1;
            return n1.key.compareTo(n2.key) > 0 ? n1 : n2;
        }
        private final int getHeight(Node n) { return n == null ? -1 : n.height; }
        private final boolean isLeft(Node n) { return n.parent.left == n; }
        private final boolean hasChild(Node n) { return n.left != null || n.right != null; }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Node last = getLast(), parent = last.parent;
            if (parent == null) {
                Key max = root.key;
                root.key = null;
                root = null;
                return max;
            }
            Key max = root.key;
            root.key = last.key;
            // 删掉末尾结点，同时更新该路径上所有结点的高度
            if (isLeft(last)) 
                last.parent.left = null;
            else 
                last.parent.right = null;
            while (parent != null) {
                parent.height = 1 + maxHeight(parent.left, parent.right);
                parent = parent.parent;
            }
            // 下沉
            Node cur = root;
            while (hasChild(cur)) {
                Node m = maxNode(cur.left, cur.right);
                if (cur.key.compareTo(m.key) >= 0) break;
                Key t = cur.key;
                cur.key = m.key;
                m.key = t;
                if (m == cur.right)
                    cur = cur.right;
                else
                    cur = cur.left;
            }
            return max;
        }
        private Node getLast() {
            if (root == null) return null;
            Node cur = root;
            while (hasChild(cur)) {
                if (getHeight(cur.left) > getHeight(cur.right)) 
                    cur = cur.left;
                else
                    cur = cur.right;
            }
            return cur;
        }
        private Node insert(Node n, Key key) {
            if (n == null) 
                return new Node(key);
            if (key.compareTo(n.key) > 0) {
                Key t = n.key;
                n.key = key;
                key = t;
            }
            int lh = getHeight(n.left);
            int rh = getHeight(n.right);
            if (lh <= rh) {
                n.left = insert(n.left, key);
                n.left.parent = n;
            } else {
                n.right = insert(n.right, key);
                n.right.parent = n;
            }
            n.height = 1 + maxHeight(n.left, n.right);
            return n;
        }
    }
    public static void main(String[] args) {
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        Integer[] ints = Integers(0, 30);
        for (Integer i : ints) pq.insert(i);
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
    // output
    /*
     *  30
        29
        28
        27
        26
        25
        24
        23
        22
        21
        20
        19
        18
        17
        16
        15
        14
        13
        12
        11
        10
        9
        8
        7
        6
        5
        4
        3
        2
        1
        0
     */
}

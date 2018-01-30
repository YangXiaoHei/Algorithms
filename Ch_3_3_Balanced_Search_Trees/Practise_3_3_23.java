package Ch_3_3_Balanced_Search_Trees;

import edu.princeton.cs.algs4.*;
import static Tool.ArrayGenerator.*;

public class Practise_3_3_23 {
    static class TwoThreeTree <K extends Comparable<K>, V> {
        private class Node {
            K key; V val;
            Node next;
            Node() {}
            Node(K k, V v) { key = k; val = v; }
        }
        private class List {
            int size;
            List left, middle, right;
            Node header = new Node();
            private boolean isEmpty() { return size == 0; }
            private boolean isFull() { return size == 2; }
            private List(Node n) {
                ++size;
                header.next = n;
            }
            private int getNextLinkWhenFull(K k, V v) {
                assert isFull();
                Node fir = header.next;
                Node sec = fir.next;
                assert fir.key.compareTo(sec.key) < 0;
                int cmp1 = k.compareTo(fir.key);
                int cmp2 = k.compareTo(sec.key);
                if (cmp1 < 0) return 0; // 左
                if (cmp1 == 0) {
                    fir.val = v;
                    return -1;
                }
                if (cmp2 < 0) return 1;  // 中
                if (cmp2 == 0) {
                    sec.val = v;
                    return -1;
                }
                return 2; // 右
            }
            private void insertNotFull(K k, V v) {
                assert !isFull() && !isEmpty();
                Node first = header.next;
                int cmp = k.compareTo(first.key);
                Node n = new Node(k, v);
                if (cmp < 0) {
                    ++size;
                    header.next = n;
                    n.next = first;
                } else if (cmp > 0) {
                    ++size;
                    first.next = n;
                } else {
                    first.val = n.val;
                }
            }
        }
        private List root;
        public boolean isEmpty() { return root == null; }
        public void put(K k, V v) {
            if (k == null || v == null) throw new IllegalArgumentException();
            root = put(root, k, v);
        }
        private List put(List l, K k, V v) {
            if (l == null) return new List(new Node(k, v));
            if (!l.isFull()) {
                l.insertNotFull(k, v);
                return l;
            } else {
                int link = l.getNextLinkWhenFull(k, v);
                if (link < 0) return l;
                switch (link) {
                    case 0 : l.left = put(l.left, k, v); break;
                    case 1 : l.middle = put(l.middle, k, v); break;
                    case 2 : l.right = put(l.right, k, v); break;
                }
            }
            return l;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Queue<List> q = new Queue<>();
            q.enqueue(root); List l = null;
            while (!q.isEmpty()) {
                l = q.dequeue();
                sb.append(String.format("{   %-5s %-5s}\n", l.header.next.key, 
                        (l.header.next.next != null ? l.header.next.next.key : null)));
                if (l.left != null)   q.enqueue(l.left);
                if (l.middle != null) q.enqueue(l.middle);
                if (l.right != null)  q.enqueue(l.right);
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        /*
         * 用某序列构造的一棵 2-3 树如下
         * 
         * https://github.com/YangXiaoHei/Algorithms_4/blob/master/FlowChart/Ch_3_3_Practise_3_3_23.png
         */
        TwoThreeTree<Integer, Integer> t = new TwoThreeTree<>();
        Integer[] a = IntegersNoDupli(20, 0, 30);
        print(a);
        for (Integer i : a) t.put(i, 0);
        StdOut.println(t);
    }
    // output
    /*
     * 
        0      1      2      3      4      5      6      7      8      9      10     11     12     13     14     15     16     17     18     19     
        0      28     8      27     10     19     21     30     22     5      14     13     9      16     2      26     29     11     6      24     
        {   0     28   }
        {   8     27   }
        {   29    30   }
        {   2     5    }
        {   10    19   }
        {   6     null }
        {   9     null }
        {   13    14   }
        {   21    22   }
        {   11    null }
        {   16    null }
        {   24    26   }

     */
}

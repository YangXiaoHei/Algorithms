package 第三章_符号表;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_3_1_03 {
    static class OrderedSequentialSearchST <K extends Comparable<K>, V> {
        private class Node {
            K key;
            V value;
            Node next;
            Node() {}
            Node(K key, V value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
            public String toString() { return String.format("{%s  %s}", key, value); }
        }
        private Node header = new Node();
        private K max = null, min = null;
        private int size;
        public boolean isEmpty() { return size == 0; }
        public int size() { return size ;}
        public void put(K key, V value) {
            if (key == null) return;
            if (value == null) {
                delete(key);
                return;
            }
            /*
             * 更新最大值
             */
            if      (max == null) max = key;
            else if (key.compareTo(max) > 0) max = key;
            /*
             * 更新最小值
             */
            if      (min == null) min = key;
            else if (key.compareTo(min) < 0) min = key;
            
            Node cur = header.next, pre = header;
            
            while (cur != null && cur.key.compareTo(key) < 0) {
                cur = cur.next; 
                pre = pre.next;
            }
            if (cur != null && cur.key.compareTo(key) == 0) {
                cur.value = value;
            } else {
                ++size;
                pre.next = new Node(key, value, pre.next);
            }
        }
        public V get(K key) {
            if (key == null || isEmpty()) return null;
            for (Node x = header.next; x != null; x = x.next) 
                if (x.key.compareTo(key) == 0) 
                    return x.value;
            return null;
        }
        public boolean contains(K key) {
            if (key == null || isEmpty()) return false;
            for (Node x = header.next; x != null; x = x.next) 
                if (x.key.compareTo(key) == 0) return true;
            return false;
        }
        public String toString() {
            if (isEmpty()) return "emptyST";
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("最大值 : %s  最小值 : %s  尺寸 : %d\n", max, min, size));
            for (Node x = header.next; x != null; x = x.next) 
                sb.append(x + "\n");
            return sb.toString();
        }
        public void delete(K key) {
            if (key == null) return;
            if (isEmpty())
                throw new NoSuchElementException("symbol table is empty!");
            Node cur = header.next, pre = header;
            while (cur != null && cur.key.compareTo(key) != 0) {
                cur = cur.next;
                pre = pre.next;
            }
            if (cur == null) return;
            --size;
            /*
             * 更新最小值
             */
            if (cur.key == min) min = cur.next == null ? null : cur.next.key;
            /*
             * 更新最大值
             */
            if (cur.key == max) max = pre == header ? null : pre.key;
            cur.key = null;
            pre.next = pre.next.next;
        }
        public K max() { return max ;}
        public K min() { return min; }
        public int rank(K key) {
            if (key == null || isEmpty()) return -1;
            int i = 0;
            Node x = header.next;
            while (x != null && x.key.compareTo(key) < 0) {
                x = x.next;
                ++i;
            }
            return i;
        }
        public K floor(K key) {
            if (isEmpty()) return null;
            Node cur = header.next, pre = header;
            while (cur != null && cur.key.compareTo(key) < 0) {
                cur = cur.next;
                pre = pre.next;
            }
            if (pre == header) return null;
            return pre.key;
        }
        public K ceiling(K key) {
            if (isEmpty()) return null;
            Node cur = header.next;
            while (cur != null && cur.key.compareTo(key) <= 0) 
                cur = cur.next;
            if (cur == null) return null;
            return cur.key;
        }
        public K select(int k) {
            if (isEmpty()) return null;
            Node x = header.next;
            while (k-- > 0 && x != null) 
                x = x.next;
            return x == null ? null : x.key;
        }
        public void deleteMin() { delete(min); }
        public void deleteMax() { delete(max); }
        public int size(K lo, K hi) {
            if (lo.compareTo(hi) > 0 || isEmpty()) return 0;
            return rank(hi) - rank(lo) + 1;
        }
        public Iterable<K> keys(K lo, K hi) {
            if (lo.compareTo(hi) > 0 || isEmpty()) return null;
            LinkedList<K> keys = new LinkedList<K>();
            Node x = header.next;
            while (x != null && x.key.compareTo(lo) < 0) 
                x = x.next;
            if (x == null) return null;
            Node cur = header.next, pre = header;
            while (cur != null && cur.key.compareTo(hi) < 0) {
                cur = cur.next;
                pre = pre.next;
            }
            while (x != pre.next) {
                keys.add(x.key);
                x = x.next;
            }
            return keys;
        }
        public Iterable<K> keys() {
            if (isEmpty()) return null;
            return keys(min, max);
        }
    }
    public static void main(String[] args) {
        OrderedSequentialSearchST<Integer, String> st = new OrderedSequentialSearchST<Integer, String>(); 
        st.put(14, "H");
        st.put(4, "C");
        st.put(34, "R");
        st.put(32, "Q");
        st.put(6, "D");
        st.put(2, "B");
        st.put(10, "F");
        st.put(22, "L");
        st.put(0, "A");
        st.put(16, "I");
        st.put(40, "U");
        st.put(18, "J");
        st.put(20, "K");
        st.put(12, "G");
        st.put(38, "T");
        st.put(24, "M");
        st.put(36, "S");
        st.put(26, "N");
        st.put(28, "O");
        st.put(30, "P");
        st.put(8, "E");
        StdOut.println("1⃣️" + st);
        st.deleteMax();
        st.deleteMax();
        st.deleteMax();  // 删除三次最大值
        st.deleteMin();
        st.deleteMin();  // 删除两次最小值
        StdOut.println("2⃣️" + st);
        st.delete(20);
        st.delete(18);
        st.delete(16);  // 连续删除三个元素
        st.delete(17);
        st.delete(15);  // 删除不存在的键值
        StdOut.println("3⃣️" + st);
        StdOut.println("4⃣️" + st.select(0) + "  " + st.get(st.select(0)));
        StdOut.println(st.select(3) + "  " + st.get(st.select(3))); // select 和 get 测试
        StdOut.println(st.select(5) + "  " + st.get(st.select(5)));
        StdOut.println(st.select(7) + "  " + st.get(st.select(7)));
        StdOut.println(st.select(9) + "  " + st.get(st.select(9)));
        StdOut.println("5⃣️" + "小于键值 28 的数量为 : " + st.rank(28)); // rank 测试
        StdOut.println("小于键值 30 的数量为 : " + st.rank(30));
        StdOut.println("小于键值 15 的数量为 : " + st.rank(15));
        StdOut.println("小于键值 5 的数量为 : " + st.rank(5));
        StdOut.println("小于键值 8 的数量为 : " + st.rank(8));
        StdOut.println("小于键值 10 的数量为 : " + st.rank(10));
        StdOut.println("小于键值 4 的数量为 : " + st.rank(4));
        // 迭代器测试
        StdOut.println("6⃣️");
        for (Integer key : st.keys())
            StdOut.printf("key = %s value = %s\n", key, st.get(key));
        StdOut.println("\n\n");
        for (Integer key : st.keys(5, 31))
            StdOut.printf("key = %s value = %s\n", key, st.get(key));
        StdOut.println("\n\n");
        for (Integer key : st.keys(11, 29))
            StdOut.printf("key = %s value = %s\n", key, st.get(key));
        StdOut.println("\n\n");
        st.delete(14);
        st.delete(26);
        for (Integer key : st.keys(5, 18))
            StdOut.printf("key = %s value = %s\n", key, st.get(key));
        StdOut.println("\n\n");
    }
    // output
    /*
     *  1⃣️最大值 : 40  最小值 : 0  尺寸 : 21
        {0  A}
        {2  B}
        {4  C}
        {6  D}
        {8  E}
        {10  F}
        {12  G}
        {14  H}
        {16  I}
        {18  J}
        {20  K}
        {22  L}
        {24  M}
        {26  N}
        {28  O}
        {30  P}
        {32  Q}
        {34  R}
        {36  S}
        {38  T}
        {40  U}
        
        2⃣️最大值 : 34  最小值 : 4  尺寸 : 16
        {4  C}
        {6  D}
        {8  E}
        {10  F}
        {12  G}
        {14  H}
        {16  I}
        {18  J}
        {20  K}
        {22  L}
        {24  M}
        {26  N}
        {28  O}
        {30  P}
        {32  Q}
        {34  R}
        
        3⃣️最大值 : 34  最小值 : 4  尺寸 : 13
        {4  C}
        {6  D}
        {8  E}
        {10  F}
        {12  G}
        {14  H}
        {22  L}
        {24  M}
        {26  N}
        {28  O}
        {30  P}
        {32  Q}
        {34  R}
        
        4⃣️4  C
        10  F
        14  H
        24  M
        28  O
        5⃣️小于键值 28 的数量为 : 9
        小于键值 30 的数量为 : 10
        小于键值 15 的数量为 : 6
        小于键值 5 的数量为 : 1
        小于键值 8 的数量为 : 2
        小于键值 10 的数量为 : 3
        小于键值 4 的数量为 : 0
        6⃣️
        key = 4 value = C
        key = 6 value = D
        key = 8 value = E
        key = 10 value = F
        key = 12 value = G
        key = 14 value = H
        key = 22 value = L
        key = 24 value = M
        key = 26 value = N
        key = 28 value = O
        key = 30 value = P
        key = 32 value = Q
        
        
        
        key = 6 value = D
        key = 8 value = E
        key = 10 value = F
        key = 12 value = G
        key = 14 value = H
        key = 22 value = L
        key = 24 value = M
        key = 26 value = N
        key = 28 value = O
        key = 30 value = P
        
        
        
        key = 12 value = G
        key = 14 value = H
        key = 22 value = L
        key = 24 value = M
        key = 26 value = N
        key = 28 value = O
        
        
        
        key = 6 value = D
        key = 8 value = E
        key = 10 value = F
        key = 12 value = G




     */
}

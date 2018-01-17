package Ch_3_1_Symbol_Tables;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_05 {
    static class SequentialSearchST<K, V> {
        private class Node {
            K key; V value; Node next;
            Node() {}
            Node(K k, V v, Node n) { key = k; value = v; next = n; }
            public String toString() { return String.format("{%s  %s}", key, value); }
        }
        private Node header = new Node();
        private int size;
        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }
        public void put(K k, V v) {
            if (k == null) return;
            if (v == null) {
                delete(k);
                return;
            }
            Node x;
            for (x = header.next; 
                 x != null && !x.key.equals(k); 
                 x = x.next);
            if (x != null)
                x.value = v;
            else {
                ++size;
                header.next = new Node(k, v, header.next);
            }
        }
        public V get(K k) {
            if (k == null) return null;
            Node x;
            for (x = header.next;
                 x != null && !x.key.equals(k);
                 x = x.next);
            return x == null ? null : x.value;
        }
        public int rank(K k) {
            if (k == null) return -1;
            Node x; int i = 0;
            for (x = header.next;
                 x != null && !x.key.equals(k);
                 x = x.next, ++i);
            return x == null ? -1 : i;
        }
        public int size(K lo, K hi) {
            int r1 = rank(lo), r2 = rank(hi);
            if (r1 < 0 || r2 < 0) return 0;
            return r1 > r2 ? 0 : r2 - r1 + 1;
        }
        public Iterable<K> keys(K lo, K hi) {
            int r1 = rank(lo), r2 = rank(hi);
            if (r1 < 0 || r2 < 0 || r1 > r2) return null;
            LinkedList<K> list = new LinkedList<K>();
            Node x = header.next, y = header.next;
            while (!x.key.equals(lo)) x = x.next;
            while (!y.key.equals(hi)) y = y.next;
            while (x != y.next) {
                list.add(x.key);
                x = x.next;
            }
            return list;
        }
        public void delete(K k) {
            if (k == null) return;
            Node cur, pre; 
            for (cur = header.next, pre = header;
                 cur != null && !cur.key.equals(k);
                 cur = cur.next, pre = pre.next);
            if (cur == null) return;
            --size;
            pre.next = pre.next.next;
        }
        public String toString() {
            if (isEmpty()) return "emptyST";
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("尺寸 : %d\n", size));
            for (Node x = header.next; x != null; x = x.next) 
                sb.append(x + "\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        SequentialSearchST<Integer, String> st = new SequentialSearchST<Integer, String>(); 
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
        StdOut.println(st);
        StdOut.printf("size(24, 22) = %d\n", st.size(24, 22));
        StdOut.println("delete(24) delete(14) delete(8)\n");
        st.delete(24);
        st.delete(14);
        st.delete(8);
        StdOut.println(st);
        StdOut.println("遍历 key = 36 ~ key = 6");
        for (Integer key : st.keys(36, 6))
            StdOut.println(key + " : " + st.get(key));
    }
    // output
    /*
     *  尺寸 : 21
        {8  E}
        {30  P}
        {28  O}
        {26  N}
        {36  S}
        {24  M}
        {38  T}
        {12  G}
        {20  K}
        {18  J}
        {40  U}
        {16  I}
        {0  A}
        {22  L}
        {10  F}
        {2  B}
        {6  D}
        {32  Q}
        {34  R}
        {4  C}
        {14  H}
        
        size(24, 22) = 9
        delete(24) delete(14) delete(8)
        
        尺寸 : 18
        {30  P}
        {28  O}
        {26  N}
        {36  S}
        {38  T}
        {12  G}
        {20  K}
        {18  J}
        {40  U}
        {16  I}
        {0  A}
        {22  L}
        {10  F}
        {2  B}
        {6  D}
        {32  Q}
        {34  R}
        {4  C}
        
        遍历 key = 36 ~ key = 6
        36 : S
        38 : T
        12 : G
        20 : K
        18 : J
        40 : U
        16 : I
        0 : A
        22 : L
        10 : F
        2 : B
        6 : D
     */
}

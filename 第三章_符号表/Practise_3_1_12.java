package 第三章_符号表;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_12 {
    static class BinarySearchST <K extends Comparable<K>, V> {
        private class Entry<Key, Value> {
            Key key; Value value;
            public Entry(Key k, Value v) { key = k; value = v; }
            public String toString() { return String.format("{ %s %s }", key, value); }
        }
        @SuppressWarnings("unchecked")
        private Entry<K, V>[] entrys = (Entry<K, V>[])new Entry[2];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Entry<K, V>[] es = (Entry<K, V>[])new Entry[newSize];
            for (int i = 0; i < size; i++)
                es[i] = entrys[i];
            entrys = es;
        }
        public BinarySearchST (Entry<K, V>[] entries) {
            this.entrys = entries;
            merge(this.entrys);
        }
        public BinarySearchST () {}
        private void merge(Entry<K, V>[] entries) {
            Entry<K, V>[] aux = entries.clone();
            merge(aux, entries, 0, entries.length - 1);
        }
        private void merge(Entry<K, V>[] src, Entry<K, V>[] dst, int lo, int hi) {
            if (hi - lo + 1 < 8) {
                for (int i = lo; i <= hi; i++) {
                    Entry<K, V> t = dst[i]; int j;
                    for (j = i - 1; j >= lo && t.key.compareTo(dst[j].key) < 0; j--)
                        dst[j + 1] = dst[j];
                    dst[j + 1] = t;
                }
                return;
            }
            int mid = (lo + hi) >> 1;
            merge(dst, src, lo, mid);
            merge(dst, src, mid + 1, hi);
            if (src[mid].key.compareTo(src[mid + 1].key) < 0) {
                System.arraycopy(src, lo, dst, lo, hi - lo + 1);
                return;
            }
            mergeSort(src, dst, lo, mid, hi);
        }
        private void mergeSort(Entry<K, V>[] src, Entry<K, V>[] dst, int lo, int mid, int hi) {
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)
                if      (i > mid)                               dst[k] = src[j++];
                else if (j > hi)                                dst[k] = src[i++];
                else if (src[j].key.compareTo(src[i].key) < 0)  dst[k] = src[j++];
                else                                            dst[k] = src[i++];
        }
        private int rank(K k) {
            int lo = 0, hi = size - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int rel = entrys[mid].key.compareTo(k);
                if      (rel > 0) hi = mid - 1;
                else if (rel < 0) lo = mid + 1;
                else    return mid;
            }
            return lo;
        }
        public void put(K k, V v) {
            if (k == null) return;
            if (v == null) {
                delete(k);
                return;
            }
            if (size == entrys.length)
                resize(size << 1);
            int lo = rank(k);
            if (lo < size && entrys[lo].key.compareTo(k)  == 0) {
                entrys[lo].value = v;
                return;
            }
            int i;
            for (i = size; i > lo; i--) 
                entrys[i] = entrys[i - 1];
            entrys[i] = new Entry<K, V>(k, v);
            ++size;
        }
        public V get(K k) {
            if (k == null) return null;
            int lo = rank(k);
            if (lo < size && entrys[lo].key.compareTo(k) == 0)
                return entrys[lo].value;
            return null;
        }
        public void delete(K k) {
            if (k == null) return;
            int lo = rank(k);
            if (lo >= size || entrys[lo].key.compareTo(k) != 0) return;
            for (int i = lo + 1; i < size; i++)
                entrys[i - 1] = entrys[i];
            entrys[size - 1] = null;
            --size;
            if (size > 0 && size == entrys.length >> 2)
                resize(entrys.length >> 1);
        }
        public K min() { return entrys[0].key; }
        public K max() { return entrys[size - 1].key; }
        public K floor(K k) {
            if (k == null) return null;
            int lo = rank(k);
            return --lo < 0 ? null : entrys[lo].key;
        }
        public K ceiling(K k) {
            if (k == null) return null;
            int lo = rank(k);
            return lo == size ? null : entrys[lo].key;
        }
        public int size(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return 0;
            return rank(hi) - rank(lo);
        }
        public Iterable<K> keys(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return null;
            if (lo.compareTo(max()) > 0 || hi.compareTo(min()) < 0) return null;
            int loc = rank(lo), hic = rank(hi);
            hic = entrys[hic].key.compareTo(hi) != 0 ? hic - 1 : hic;
            LinkedList<K> list = new LinkedList<K>();
            while (loc <= hic)
                list.add(entrys[loc++].key);
            return list;
        }
        public void deleteMax() { delete(max()); }
        public void deleteMin() { delete(min()); }
        public K select(int k) {
            if (k < 0 || k >= size)
                throw new IllegalArgumentException("越界啦啦啦啦啦");
            return entrys[k].key;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(String.format("{%s  %s}\n", entrys[i].key, entrys[i].value));
            }
            return sb.toString();
        }
        public Iterable<K> keys() { return keys(min(), max()); }
    }
    public static void main(String[] args) {
        BinarySearchST<Integer, String> st = new BinarySearchST<Integer, String>();
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
     * 1⃣️{0  A}
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
        
        2⃣️{4  C}
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
        
        3⃣️{4  C}
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
        key = 34 value = R
        
        
        
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

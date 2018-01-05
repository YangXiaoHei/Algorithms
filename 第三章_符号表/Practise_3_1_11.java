package 第三章_符号表;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_11 {
    static class BinarySearchST <K extends Comparable<K>, V> {
        @SuppressWarnings("unchecked")
        private K keys[] = (K[])new Comparable[1];
        @SuppressWarnings("unchecked")
        private V values[] = (V[])new Object[1];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            K[] keys = (K[])new Comparable[newSize];
            V[] values = (V[])new Object[newSize];
            for (int i = 0; i < size;i ++) {
                keys[i] = this.keys[i];
                values[i] = this.values[i];
            }
            this.keys = keys;
            this.values = values;
        }
        public int size() { return size; }
        public boolean isEmpty() { return size == 0; }
        private int rank(K key) {
            int lo = 0, hi = size - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int rel = keys[mid].compareTo(key);
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
            if (size == keys.length)
                resize(size << 1);
            int lo = rank(k);
            if (lo < size && keys[lo].compareTo(k) == 0) {
                values[lo] = v;
                return;
            }
            int i;
            for (i = size; i > lo; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            ++size;
            keys[i] = k;
            values[i] = v;
        }
        public V get(K k) {
            if (k == null || isEmpty()) return null;
            int lo = rank(k);
            if (lo < size && keys[lo].compareTo(k) == 0) 
                return values[lo];
            return null;
        }
        public void delete(K k) {
            if (k == null) return;
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return;
            for (int i = lo + 1; i < size; i++) {
                keys[i - 1] = keys[i];
                values[i - 1] = values[i];
            }
            --size;
            keys[size + 1] = null;
            values[size + 1] = null;
            if (size > 0 && size == keys.length >> 2)
                resize(keys.length >> 1);
        }
        public K floor(K k) {
            if (k == null) return null;
            int lo = rank(k);
            return --lo < 0 ? null : keys[lo];
        }
        public K ceiling(K k) {
            if (k == null) return null;
            int lo = rank(k);
            return lo == size ? keys[lo - 1] : keys[lo];
        }
        public K max() { return keys[size - 1]; }
        public K min() { return keys[0]; }
        public void deleteMax() { delete(max()); }
        public void deleteMin() { delete(min()); }
        public K select(int i) { 
            if (i < 0 || i >= size) 
                throw new IllegalArgumentException("数组越界");
            return keys[i];
        }
        public int size(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return 0;
            return rank(hi) - rank(lo);
        }
        public Iterable<K> keys(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return null;
            if (lo.compareTo(max()) > 0 || hi.compareTo(min()) < 0) return null; 
            LinkedList<K> list = new LinkedList<K>();
            int loc = rank(lo), hic = rank(hi);
            hic = hic == size ? hic - 1 : hic;
            while (loc <= hic) 
                list.add(keys[loc++]);
            return list;
        }
        public Iterable<K> keys() { return keys(min(), max()); }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(String.format("{%s  %s}\n", keys[i], values[i]));
            }
            return sb.toString();
        }
    }
    public static void test() {
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
    public static void main(String[] args) {
        test();
        /*
         * E A S Y Q U E S T I O N
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         */
    }
}

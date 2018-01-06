package 第三章_符号表;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_15 {
    static class BinarySearchST <K extends Comparable<K>, V> {
        @SuppressWarnings("unchecked")
        private K[] keys = (K[])new Comparable[2];
        @SuppressWarnings("unchecked")
        private V[] values = (V[])new Object[2];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            K[] keys = (K[])new Comparable[newSize];
            V[] values = (V[])new Object[newSize];
            for (int i = 0; i < size; i++) {
                keys[i] = this.keys[i];
                values[i] = this.values[i];
            }
            this.keys = keys;
            this.values = values;
        }
        private int rank(K k) {
            if (k == null) return -1;
            int lo = 0, hi = size - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int cmp = k.compareTo(keys[mid]);
                if      (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else    return mid;
            }
            return lo;
        }
        public void put(K k, V v) {
            if (k == null) return;
            if (v == null) {
                // 删除
                return;
            }
            int lo = rank(k);
            if (lo < size && keys[lo].compareTo(k) == 0) {
                values[lo] = v;
                return;
            }
            if (size == keys.length)
                resize(size << 1);
            for (int i = size; i > lo; i++) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            keys[lo] = k;
            values[lo] = v;
        }
        public V get(K k) {
            if (k == null) return null;
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return null;
            return values[lo];
        }
       
    }
    public static void main(String[] args) {
        
    }
}

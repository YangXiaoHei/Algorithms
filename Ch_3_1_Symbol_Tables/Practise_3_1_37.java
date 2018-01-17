package Ch_3_1_Symbol_Tables;

import java.util.*;
import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;

public class Practise_3_1_37 {
    static class ST<K extends Comparable<K>, V> {
        @SuppressWarnings("unchecked")
        private K[] keys = (K[])new Comparable[1];
        @SuppressWarnings("unchecked")
        private V[] values = (V[])new Object[1];
        private int size;
        public boolean isEmpty() { return size == 0; }
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
        public int rank(K k) {
            if (k == null) 
                throw new NoSuchElementException("符号表中不会有 null 键!");
            int lo = 0, hi = size - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int cmp = keys[mid].compareTo(k);
                if      (cmp > 0) lo = mid + 1;
                else if (cmp < 0) hi = mid - 1;
                else    return mid;
            }
            return lo;
        }
        public double putTime = 0;
        public void put(K k, V v) {
            Stopwatch timer = new Stopwatch();
            if (k == null) { putTime += timer.elapsedTime(); return; }
            if (v == null) {
                delete(k);
                putTime += timer.elapsedTime();
                return;
            }
            int lo = rank(k);
            if (lo < size && keys[lo].compareTo(k) == 0) {
                values[lo] = v;
                putTime += timer.elapsedTime();
                return;
            }
            if (size == keys.length)
                resize(size << 1);
            for (int i = size; i > lo; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            ++size;
            keys[lo] = k;
            values[lo] = v;
            putTime += timer.elapsedTime();
        }
        public double getTime = 0;
        public V get(K k) {
            Stopwatch timer = new Stopwatch();
            if (k == null)  { getTime += timer.elapsedTime(); return null; }
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) { getTime += timer.elapsedTime(); return null; }
            { getTime += timer.elapsedTime(); return values[lo]; }
        }
        public void delete(K k) {
            if (k == null)
                throw new IllegalArgumentException("符号表中没有 null 键");
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return;
            for (int i = lo + 1; i < size; i++) {
                keys[i - 1] = keys[i];
                values[i - 1] = values[i];
            }
            --size;
            keys[size] = null;
            values[size] = null;
            if (size > 0 && size == keys.length >> 2)
                resize(keys.length >> 1);
        }
    }
    public static void main(String[] args) {
        int M = 10;
        int maxN = 1;
        for (int i = 0; i < M; i++)
            maxN <<= 1;
        maxN -= 1;
        Integer[] a = Integers(1000000, 0, maxN);
        ST<Integer, Integer> st = new ST<Integer, Integer>();
        for (Integer i : a) 
            st.put(i, st.get(i) == null ? 1 : st.get(i) + 1);
        Integer max = maxN + 1;
        st.put(max, 0);
        for (Integer i : a) {
            if (st.get(i) > st.get(max))
                max = i;
        }
        Integer maxCount = st.get(max);
        StdOut.printf("M = %d, put / get = %.3f\n",M, st.putTime / st.getTime);
    }
    // output
    /*
     * M = 10, put / get = 0.345

     */
}

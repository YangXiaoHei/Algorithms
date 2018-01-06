package 第三章_符号表;

import edu.princeton.cs.algs4.*;
import java.util.*;

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
        public double put(K k, V v) {
            Stopwatch timer = new Stopwatch();
            if (k == null) return timer.elapsedTime();
            if (v == null) {
                delete(k);
                return timer.elapsedTime();
            }
            int lo = rank(k);
            if (lo < size && keys[lo].compareTo(k) == 0) {
                values[lo] = v;
                return timer.elapsedTime();
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
            return timer.elapsedTime();
        }
        public double get(K k) {
            Stopwatch timer = new Stopwatch();
            if (k == null) return timer.elapsedTime();
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return timer.elapsedTime();
            V rel = values[lo];
            return timer.elapsedTime();
        }
        public void delete(K k) {
            if (k == null) return;
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return;
            for (int i = lo + 1; i < size; i++) {
                keys[i - 1] = keys[i];
                values[i - 1] = values[i];
            }
            keys[size] = null;
            values[size] = null;
            --size;
            if (size > 0 && size == keys.length >> 2)
                resize(keys.length >> 1);
        }
        public K select(int k) {
            if (k < 0 || k >= size) return null;
            return keys[k];
        }
        public int size(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return 0;
            return rank(hi) - rank(lo);
        }
        public K max() { return size == 0 ? null : keys[size - 1]; }
        public K min() { return size == 0 ? null : keys[0]; }
        public void deleteMax() { delete(max()); }
        public void deleteMin() { delete(min()); }
        public Iterable<K> keys(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return null;
            if (lo.compareTo(max()) > 0 || hi.compareTo(min()) < 0) return null;
            LinkedList<K> list = new LinkedList<K>();
            int loc = rank(lo), hic = rank(hi);
            if (keys[hic].compareTo(hi) != 0) --hic;
            while (loc <= hic) 
                list.add(keys[loc++]);
            return list;
        }
        public Iterable<K> keys() { return keys(min(), max()); }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) 
                sb.append(String.format("{%s  %s}\n",
                        keys[i], values[i]));
            return sb.toString();
        }
    }
    public static boolean[] op(int N, int successTimes) {
        boolean[] ops = new boolean[N];
        Arrays.fill(ops, false);
        for (int i = 0; i < successTimes; i++)
            ops[i] = true;
        for (int i = 0; i < N; i++) {
            int r = i + StdRandom.uniform(N - i);
            boolean t = ops[r];
            ops[r] = ops[i];
            ops[i] = t;
        }
        return ops;
    }
    public static void main(String[] args) {
        BinarySearchST<Integer, String> st = new BinarySearchST<Integer, String>();
        int putCount = 10000;
        int getCount = putCount * 1000;
        double putTime = 0, getTime = 0;
        boolean[] ops = op(putCount + getCount, putCount);
        for (int i = 0; i <  ops.length; i++)
            if (ops[i])
                putTime += st.put(StdRandom.uniform(1, 100000000), "A");
            else
                getTime += st.get(StdRandom.uniform(1, 100000000));
        StdOut.printf("put 操作耗时 : %.3f get 操作耗时 : %.3f put / Total = %.3f\n",
                putTime, getTime, putTime / (getTime + putTime));
    }
    // output
    /*
     * put 操作耗时 : 0.055 get 操作耗时 : 1.359 put / Total = 0.039
     * 
     */
}

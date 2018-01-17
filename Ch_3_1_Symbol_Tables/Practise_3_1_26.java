package Ch_3_1_Symbol_Tables;

import java.util.LinkedList;
import edu.princeton.cs.algs4.*;

public class Practise_3_1_26 {
    static class ST <K extends Comparable<K>, V> {
        @SuppressWarnings("unchecked")
        private K[] keys = (K[])new Comparable[2];
        @SuppressWarnings("unchecked")
        private V[] values = (V[])new Object[2];
        private int size;
        private class Cache { K k; V v; int index; }
        private Cache cache = new Cache();
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
        public boolean isEmpty() { return size == 0; } 
        public boolean contains(K k) {
            if (k == null) return false;
            if (cache.k != null && k.compareTo(cache.k) == 0) return true;
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return false;
            return true;
        }
        public int rank(K k) {
            if (k == null) return -1;
            if (cache.k != null && k.compareTo(cache.k) == 0) return cache.index;
            int lo = 0, hi = size - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int cmp = (k.compareTo(keys[mid]));
                if      (cmp > 0)   lo = mid + 1;
                else if (cmp < 0)   hi = mid - 1;
                else    { cache.k = keys[mid]; 
                          cache.v = values[mid]; 
                          cache.index = mid;  // 更新缓存
                          return mid; }
            }
            return lo;
        }
        public void put(K k, V v) {
            if (k == null) return;
            if (v == null) {
                delete(k);
                return;
            }
            /*
             * 命中缓存，就直接使用缓存
             */
            if (cache.k != null && cache.k.compareTo(k) == 0) {
                cache.v = v; 
                values[cache.index] = v;
                return;
            }
            /*
             * 如果 k 比符号表中所有键都大，那么直接插入到末尾
             */
            if (max() != null && k.compareTo(max()) > 0) {
                if (size == keys.length)
                    resize(size << 1);
                keys[size] = k;
                values[size] = v;
                cache.k = k; // 更新缓存
                cache.v = v;
                cache.index = size; 
                ++size;
                return;
            }
            int lo = rank(k); // 如果找到，那么缓存已经被更新
            if (lo < size && keys[lo].compareTo(k) == 0) {
                cache.v = v;
                values[lo] = v;
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
            cache.k = k; // 更新缓存
            cache.v = v;
            cache.index = lo; 
        }
        public void delete(K k) {
            if (k == null)  throw new RuntimeException("删除null");
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0)  throw new RuntimeException("删除不存在的键");
            
            if (cache.k != null && k.compareTo(cache.k) == 0) { //删除缓存
                cache.k = null;
                cache.v = null;
                cache.index = -1;
            }
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
        public V get(K k) {
            if (k == null) return null;
            if (cache.k != null && cache.k.compareTo(k) == 0) return cache.v;
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return null;
            V v = values[lo];
            cache.k = k; // 更新缓存
            cache.v = v;
            cache.index = lo;
            return v;
        }
        public void deleteMin() { delete(min()); }
        public void deleteMax() { delete(max()); }
        public K select(int k) {
            if (k < 0 || k >= size)
                throw new IllegalArgumentException("");
            cache.k = keys[k];
            cache.v = values[k];
            cache.index = k;
            return keys[k];
        }
        public K max() { return isEmpty() ? null : keys[size - 1]; }
        public K min() { return isEmpty() ? null : keys[0]; }
        public Iterable<K> keys(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return null;
            if (lo.compareTo(max()) > 0 || hi.compareTo(min()) < 0) return null;
            LinkedList<K> list = new LinkedList<K>();
            int loc = rank(lo), hic = rank(hi);
            if (hic >= size || keys[hic].compareTo(hi) != 0) --hic;
            while (loc <= hic)
                list.add(keys[loc++]);
            return list;
        }
        public Iterable<K> keys() { return keys(min(), max()); }
        public int size(K lo, K hi) {
            if (lo.compareTo(hi) > 0) return 0;
            return rank(lo) - rank(hi);
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(String.format("{%s  %s}\n", keys[i], values[i]));
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        String path = "/Users/bot/Desktop/algs4-data/tale.txt";
        String[] words = new In(path).readAll().split("\\s+");
        
        edu.princeton.cs.algs4.ST<String, Integer> st1 = new edu.princeton.cs.algs4.ST<String, Integer>();
        for (String s : words)
            st1.put(s, st1.get(s) == null ? 1 : st1.get(s) + 1);
        
        ST<String, Integer> st = new ST<String, Integer>();
        for (String s : words)
            st.put(s, st.get(s) == null ? 1 : st.get(s) + 1);
        
        String in = "the";
        StdOut.printf("%s 在双城记中出现的频率为 : %d\n",in, st.get(in));
        StdOut.printf("%s 在双城记中出现的频率为 : %d\n",in, st1.get(in));
    }
    // output
    /*
     *  the 在双城记中出现的频率为 : 7989
        the 在双城记中出现的频率为 : 7989

     */
    
}

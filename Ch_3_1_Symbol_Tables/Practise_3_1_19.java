package Ch_3_1_Symbol_Tables;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_19 {
    static class ST <K extends Comparable<K>, V> {
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
            int lo = 0, hi = size - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int cmp = k.compareTo(keys[mid]);
                if      (cmp > 0) lo = mid + 1;
                else if (cmp < 0) hi = mid - 1;
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
            int lo = rank(k);
            if (lo < size && keys[lo].compareTo(k) == 0) {
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
        }
        public V get(K k) {
            if (k == null) return null;
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return null;
            return values[lo]; 
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
            keys[size] = null;
            values[size] = null;
            if (size > 0 && size == keys.length >> 2)
                resize(size >> 1);
        }
        public K max() { return size == 0 ? null : keys[size - 1]; }
        public K min() { return size == 0 ? null : keys[0]; }
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
    }
    static class Queue<T> {
        @SuppressWarnings("unchecked")
        private T[] items = (T[])new Object[1];
        private int size;
        private int head;
        private int tail;
        public boolean isEmpty() { return size == 0; }
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            T[] items = (T[])new Object[newSize];
            int i = 0, cur = head;
            while (i < size) {
                items[i++] = this.items[cur++];
                cur = cur == this.items.length ? 0 : cur;
            }
            this.items = items;
            head = 0;
            tail = size;
        }
        public void enqueue(T item) {
            if (size == items.length)
                resize(size << 1);
            ++size;
            items[tail++] = item;
            tail = tail == items.length ? 0 : tail;
        }
        public T dequeue() {
            if (size == 0)
                throw new NoSuchElementException("queue underflow");
            --size;
            T del = items[head++];
            head = head == items.length ? 0 : head;
            if (size > 0 && size == items.length >> 2)
                resize(items.length >> 1);
            return del;
        }
    }
    
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        Queue<String> queue = new Queue<String>();
        
//        String path = "/Users/bot/Desktop/algs4-data/tale.txt";
//        String[] words = new In(path).readAll().split("\\s+");
        
        String[] words = {
           "you", "are", "nice", "how", "are", "you",
           "are", "you", "ok", "how", "old", "are", "you",
           "how", "how"
        };
        
        for (String s : words)
            st.put(s, st.get(s) == null ? 1 : st.get(s) + 1);
        
        String max = " ";
        st.put(max, 0);
        
        for (String key : st.keys())
            if (st.get(key) > st.get(max))
                max = key;
        
        for (String key : st.keys())
            if (st.get(key) == st.get(max))
                queue.enqueue(key);
        
        while (!queue.isEmpty())
            StdOut.println(queue.dequeue());
    }
    // output
    /*
     * are
        how
        you

     */
}

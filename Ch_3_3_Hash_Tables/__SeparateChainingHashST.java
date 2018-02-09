package Ch_3_3_Hash_Tables;

import static Tool.ArrayGenerator.Alphbets.*;

import edu.princeton.cs.algs4.StdOut;

public class __SeparateChainingHashST <K, V> {
    private static final int INIT_CAPACITY = 4;
    private __SequentialSearchST<K, V>[] st;
    private int size, capacity;
    @SuppressWarnings("unchecked")
    public __SeparateChainingHashST(int c) {
        st = new __SequentialSearchST[c];
        for (int i = 0; i < c; i++) 
            st[i] = new __SequentialSearchST<K, V>();
        capacity = c;
    }
    public __SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
    @SuppressWarnings("unchecked")
    private void resize(int c) {
        __SequentialSearchST<K, V>[] t = new __SequentialSearchST[c];
        for (int i = 0; i < capacity; i++) 
            for (K key : st[i].keys()) 
                t[i].put(key, st[i].get(key));
        capacity = c;
        st = t;
    }
    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % capacity;
    }
    public void put(K k, V v) {
        if (k == null) throw new IllegalArgumentException();
        if (v == null) {
            delete(k);
            return;
        }
        if (size >= 10 * capacity) 
            resize(size << 1);
        int i = hash(k);
        if (!st[i].contain(k)) ++size;
        st[i].put(k, v);
    }
    public V get(K k) {
        int i = hash(k);
        return st[i].get(k);
    }
    public void delete(K k) {
        if (k == null) throw new IllegalArgumentException();
        int i = hash(k);
        if (st[i].contain(k)) --size;
        st[i].delete(k);
        if (capacity > INIT_CAPACITY && size <= capacity << 1) 
            resize(capacity >> 1);
    }
    public static void main(String[] args) {
        __SeparateChainingHashST<String, Integer> st = 
                new __SeparateChainingHashST<>();
        st.put("yanghan", 24);
        st.put("lijie", 25);
        st.put("pengshasha", 26);
        st.put("zhanghaiyue", 28);
//        st.put("lili", 48);
        StdOut.println(st.get("yanghan"));
        StdOut.println(st.get("pengshasha"));
        StdOut.println(st.get("zhanghaiyue"));
    }
}

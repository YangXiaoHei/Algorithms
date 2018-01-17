package Ch_3_1_Symbol_Tables;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_22 {
    static class ArrayST <K, V> {
        private class Entry<Key, Value> {
           Key k;
           Value v;
           Entry(Key kk, Value vv) { k = kk; v = vv; }
           public String toString() { return String.format("{ %s %s }", k, v); }
        }
        @SuppressWarnings("unchecked")
        private Entry<K, V>[] entries = (Entry<K, V>[])new Entry[1];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Entry<K, V>[] entries = (Entry<K, V>[])new Entry[newSize];
            for (int i = 0; i < size; i++)
                entries[i] = this.entries[i];
            this.entries = entries;
        }
        public void delete(K k) {
            if (k == null) return;
            for (int i = 0; i < size; i++)
                if (k.equals(entries[i].k)) {
                    Entry<K, V> t = entries[i];
                    entries[i] = entries[size--];
                    entries[size] = t;
                    entries[size] = null;
                    if (size > 0 && size == entries.length >> 2)
                        resize(entries.length >> 1);
                }
        }
        public void put(K k, V v) {
            if (k == null) return;
            if (v == null) {
                delete(k);
                return;
            }
            for (int i = 0; i < size; i++)
                if (k.equals(entries[i].k)) {
                    entries[i].v = v;
                    return;
                }
            if (size == entries.length)
                resize(size << 1);
            entries[size++] = new Entry<K, V>(k, v);
        }
        public V get(K k) {
            if (k == null) return null;
            int i; Entry<K, V> e = null;
            for (i = 0; i < size; i++)
                if (k.equals(entries[i].k)) 
                    e = entries[i];
            if (e != null) {
                for (int j = i; j > 0; j--)
                    entries[j] = entries[j - 1];
                entries[0] = e;
            }
            return e.v;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++)
                sb.append(entries[i] + "\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();
        st.put("yanghan", 1);
        st.put("lijie", 2);
        st.put("zhanghaiyue", 3);
        st.put("zhangyafang", 4);
        st.put("liyun", 5);
        st.put("yangkaiyun", 6);
        st.put("wangjinyuan", 7);
        st.put("datou", 8);
        st.put("chenghao", 9);
        st.put("hahah", 10);
        st.put("heihei", 11);
        st.put("xixi", 12);
        StdOut.println(st);
        StdOut.println(st.get("xixi"));
        StdOut.println(st);
        StdOut.println(st.get("chenghao"));
        StdOut.println(st);
    }
    // output
    /*
     *  { yanghan 1 }
        { lijie 2 }
        { zhanghaiyue 3 }
        { zhangyafang 4 }
        { liyun 5 }
        { yangkaiyun 6 }
        { wangjinyuan 7 }
        { datou 8 }
        { chenghao 9 }
        { hahah 10 }
        { heihei 11 }
        { xixi 12 }
        
        12
        { xixi 12 }
        { yanghan 1 }
        { lijie 2 }
        { zhanghaiyue 3 }
        { zhangyafang 4 }
        { liyun 5 }
        { yangkaiyun 6 }
        { wangjinyuan 7 }
        { datou 8 }
        { chenghao 9 }
        { hahah 10 }
        { heihei 11 }
        
        9
        { chenghao 9 }
        { xixi 12 }
        { yanghan 1 }
        { lijie 2 }
        { zhanghaiyue 3 }
        { zhangyafang 4 }
        { liyun 5 }
        { yangkaiyun 6 }
        { wangjinyuan 7 }
        { datou 8 }
        { chenghao 9 }
        { hahah 10 }
     */
}

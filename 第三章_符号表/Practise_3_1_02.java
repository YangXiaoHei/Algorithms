package 第三章_符号表;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_3_1_02 {
    static class ArrayST<K, V> {
        private class Entry<Key, Value> {
            Key key; Value value;
            Entry(Key k, Value v) { key = k; value = v; }
            public String toString() {
                return String.format("{ %s  %s }", key, value);
            }
        }
        @SuppressWarnings("unchecked")
        private Entry<K, V> entrys[] = (Entry<K, V>[])new Entry[2];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Entry<K, V> entrys[] = (Entry<K, V>[])new Entry[newSize];
            for (int i = 0; i < size; i++)
                entrys[i] = this.entrys[i];
            this.entrys = entrys;
        }
        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }
        public void delete(K key) {
            if (key == null) return;
            for (int i = 0; i < size; i++) {
                if (entrys[i].key.equals(key)) {
                    Entry<K, V> t = entrys[size - 1];
                    entrys[size - 1] = entrys[i];
                    entrys[i] = t;
                    entrys[--size] = null;
                    if (size > 0 && size == entrys.length >> 2)
                        resize(entrys.length >> 1);
                }
            }
        }
        public void put(K key, V value) {
            if (key == null) return;
            if (value == null) {
                delete(key);
                return;
            }
            for (int i = 0; i < size; i++) {
                if (entrys[i].key.equals(key)) {
                    entrys[i].value = value;
                    return;
                }
            }
            if (size == entrys.length)
                resize(size << 1);
            entrys[size++] = new Entry<K, V>(key, value);
        }
        public V get(K key) {
            if (key == null) return null;
            for (int i = 0; i < size; i++)
                if (entrys[i].key.equals(key)) 
                    return entrys[i].value;
            return null;
        }
        public boolean contains(K key) {
            if (key == null) return false;
            for (int i = 0; i < size; i++)
                if (entrys[i].key.equals(key)) return true;
            return false;
        }
        public Iterable<K> keys() {
            LinkedList<K> list = new LinkedList<K>();
            for (int i = 0; i < size; i++)
                list.add(entrys[i].key);
            return list;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++)
                sb.append(entrys[i] + "\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        ArrayST <String, Integer> st = new ArrayST<String, Integer>();
        st.put("A", 100);
        st.put("B", 300);
        st.put("C", 200);
        st.put("D", 50);
        st.put("F", 600);
        st.put("G", 78);
        st.put("H", 90);
        st.put("J", 19);
        st.put("K", 20);
        st.put("L", 29);
        st.put("M", 38);
        st.put("N", 45);
        st.put("O", 61);
        st.put("P", 132);
        st.put("Q", 438);
        st.put("R", 400);
        st.put("S", 643);
        st.put("T", 500);
        StdOut.println(st);
        st.put("G", null);
        st.put("T", null);
        st.put("P", null);
        st.put("Q", null);
        StdOut.println(st);
        st.delete("A");
        st.delete("B");
        st.delete("C");
        st.delete("D");
        st.delete("F");
        st.delete("S");
        StdOut.println(st);
    }
    // output
    /*
     *  { A  100 }
        { B  300 }
        { C  200 }
        { D  50 }
        { F  600 }
        { G  78 }
        { H  90 }
        { J  19 }
        { K  20 }
        { L  29 }
        { M  38 }
        { N  45 }
        { O  61 }
        { P  132 }
        { Q  438 }
        { R  400 }
        { S  643 }
        { T  500 }
        
        { A  100 }
        { B  300 }
        { C  200 }
        { D  50 }
        { F  600 }
        { S  643 }
        { H  90 }
        { J  19 }
        { K  20 }
        { L  29 }
        { M  38 }
        { N  45 }
        { O  61 }
        { R  400 }
        
        { R  400 }
        { O  61 }
        { N  45 }
        { M  38 }
        { L  29 }
        { K  20 }
        { H  90 }
        { J  19 }
     */
}

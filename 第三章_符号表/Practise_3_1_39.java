package 第三章_符号表;

import java.awt.Color;

import edu.princeton.cs.algs4.*;

public class Practise_3_1_39 {
    static class SequentialSearchST<K, V> {
        private class Node {
            K k; V v; Node next;
            Node() {}
            Node(K kk, V vv, Node nextt) { k = kk; v = vv; next = nextt; }
            public String toString() { return String.format("{ %s %s }", k, v); }
        }
        public int putCount, getCount;
        private Node header = new Node();
        private int size;
        public void put(K k, V v) {
            putCount++;
            if (k == null) throw new IllegalArgumentException();
            if (v == null) {
                delete(k);
                return;
            }
            for (Node x = header.next; x != null; x = x.next) {
                if (isEqual(x.k, k)) {
                    x.v = v;
                    return;
                }
            }
            ++size;
            header.next = new Node(k, v, header.next);
        }
        public V get(K k) {
            getCount++;
            if (k == null) throw new IllegalArgumentException();
            for (Node x = header.next; x != null; x = x.next) {
                if (isEqual(x.k, k)) return x.v;
            }
            return null;
        }
        public void delete(K k) {
            if (k == null) throw new IllegalArgumentException();
            Node pre, cur;
            for (pre = header, cur = header.next;
                 cur != null && !isEqual(cur.k, (k));
                 cur = cur.next, pre = pre.next);
            if (cur == null) return;
            cur.k = null; cur.v = null;
            pre.next = pre.next.next;
        }
        public boolean isEqual(K a, K b) {
            return a.equals(b);
        }
    }
    static class BinarySearchST<K extends Comparable<K>, V> {
        @SuppressWarnings("unchecked")
        private K[] keys = (K[])new Comparable[1];
        @SuppressWarnings("unchecked")
        private V[] values = (V[])new Object[1];
        private int size;
        public int putCount, getCount;
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
            if (k == null) throw new IllegalArgumentException("符号表中没有 null 键");
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
        public V get(K k) {
            getCount++;
            if (k == null) throw new IllegalArgumentException("符号表中没有 null 键");
            int lo = rank(k);
            if (lo >= size || keys[lo].compareTo(k) != 0) return null;
            return values[lo];
        }
        public void put(K k, V v) {
            putCount++;
            if (k == null) throw new IllegalArgumentException("不能在符号表中插入 null 键");
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
        public void delete(K k) {
            if (k == null) throw new IllegalArgumentException("符号表中没有 null 键");
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
        String path = "/Users/bot/Desktop/algs4-data/tale.txt";
        String[] words = new In(path).readAll().split("\\s+");
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        BinarySearchST<String, Integer> st1 = new BinarySearchST<String, Integer>();
        double t1 = 0, t2 = 0;
        StdDraw.setXscale(0, 100000);
        StdDraw.setYscale(0, 100);
        for (String w : words) {
            StdDraw.setPenColor(Color.red);
            Stopwatch timer = new Stopwatch();
            st.put(w, st.get(w) == null ? 1 : st.get(w) + 1);
            t1 += timer.elapsedTime();
            StdDraw.point(st.putCount + st.getCount, t1);
            
            StdDraw.setPenColor(Color.GREEN);
            Stopwatch timer1 = new Stopwatch();
            st1.put(w, st1.get(w) == null ? 1 : st1.get(w) + 1);
            t2 += timer1.elapsedTime();
            StdDraw.point(st1.putCount + st1.getCount, t2);
        }
    }
}

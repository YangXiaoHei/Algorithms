package Ch_3_1_Symbol_Tables;

import java.awt.Color;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_3_1_38 {
    static class SequentialSearchST<K, V> {
        private class Node {
            K k; V v; Node next;
            Node() {}
            Node(K kk, V vv, Node nextt) { k = kk; v = vv; next = nextt; }
            public String toString() { return String.format("{ %s %s }", k, v); }
        }
        private Node header = new Node();
        private int size;
        public int compares;
        public int totalCompares;
        public void put(K k, V v) {
            compares = 0;
            if (k == null) throw new IllegalArgumentException();
            if (v == null) {
                delete(k);
                totalCompares += compares;
                return;
            }
            for (Node x = header.next; x != null; x = x.next) {
                if (isEqual(x.k, k)) {
                    x.v = v;
                    totalCompares += compares;
                    return;
                }
            }
            ++size;
            header.next = new Node(k, v, header.next);
            totalCompares += compares;
        }
        public V get(K k) {
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
            compares++;
            return a.equals(b);
        }
    }
    static class BinarySearchST<K extends Comparable<K>, V> {
        @SuppressWarnings("unchecked")
        private K[] keys = (K[])new Comparable[1];
        @SuppressWarnings("unchecked")
        private V[] values = (V[])new Object[1];
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
        private int access;
        private int totalAccess;
        public int rank(K k) {
            if (k == null) throw new IllegalArgumentException("符号表中没有 null 键");
            int lo = 0, hi = size - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int cmp = k.compareTo(keys[mid]);
                access++;
                if      (cmp > 0) lo = mid + 1;
                else if (cmp < 0) hi = mid - 1;
                else    return mid;
            }
            return lo;
        }
        public V get(K k) {
            if (k == null) throw new IllegalArgumentException("符号表中没有 null 键");
            int lo = rank(k);
            access++;
            if (lo >= size || keys[lo].compareTo(k) != 0) return null;
            access++;
            return values[lo];
        }
        public void put(K k, V v) {
            access = 0;
            if (k == null) throw new IllegalArgumentException("不能在符号表中插入 null 键");
            if (v == null) {
                delete(k);
                totalAccess += access;
                return;
            }
            int lo = rank(k);
            access++;
            if (lo < size && keys[lo].compareTo(k) == 0) {
                values[lo] = v;
                access++;
                totalAccess += access;
                return;
            }
            if (size == keys.length)
                resize(size << 1);
            for (int i = size; i > lo; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
                access += 4;
            }
            ++size;
            keys[lo] = k;
            values[lo] = v;
            access += 2;
            totalAccess += access;
        }
        public void delete(K k) {
            if (k == null) throw new IllegalArgumentException("符号表中没有 null 键");
            int lo = rank(k);
            access++;
            if (lo >= size || keys[lo].compareTo(k) != 0) return;
            for (int i = lo + 1; i < size; i++) {
                keys[i - 1] = keys[i];
                values[i - 1] = values[i];
                access += 4;
            }
            --size;
            keys[size] = null;
            values[size] = null;
            access += 2;
            if (size > 0 && size == keys.length >> 2)
                resize(keys.length >> 1);
        }
    }
    /*
     * 操作成本是数组访问次数
     */
    public static void binarySearchSTTest () {
        String path = "/Users/bot/Desktop/algs4-data/tale.txt";
        String[] words = new In(path).readAll().split("\\s+");
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        StdDraw.setXscale(0, 30000);
        StdDraw.setYscale(0, 5000);
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() < 8) continue;
            st.put(words[i], st.get(words[i]) == null ? 1 : st.get(words[i]) + 1);
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.point(i, st.access);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(i, st.totalAccess / i);
        }
    }
    /*
     * 操作成本是比较次数
     */
    public static void SequentialSearchSTTest() {
        String path = "/Users/bot/Desktop/algs4-data/tale.txt";
        String[] words = new In(path).readAll().split("\\s+");
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        StdDraw.setXscale(0, 30000);
        StdDraw.setYscale(0, 2300);
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() < 8) continue;
            st.put(words[i], st.get(words[i]) == null ? 1 : st.get(words[i]) + 1);
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.point(i, st.compares);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(i, st.totalCompares / i);
        }
    }
    public static void main(String[] args) {
        SequentialSearchSTTest();
    }
}

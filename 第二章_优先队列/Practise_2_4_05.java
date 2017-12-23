package 第二章_优先队列;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_2_4_05 {
    static class MaxPQ<Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[2];
        private int size;
        public boolean isEmpty() { return size == 0; }
        @SuppressWarnings("unchecked")
        public void resize(int newSize) {
            Key[] newKeys = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= size; i++)
                newKeys[i] = keys[i];
            keys = newKeys;
        }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            keys[++size] = key;
            swim(size);
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key max = keys[1];
            exch(1, size--);
            sink(1);
            assert keys[size + 1] == max;
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1) >> 2)
                resize((keys.length - 1) >> 1);
            return max;
        }
        private void swim(int k) {
            while (k > 1 && less(k >>> 1, k)) {
                exch(k >>> 1, k);
                k >>>= 1;
            }
        }
        private void sink(int k) {
            while ((k << 1) <= size) {
                int j = k << 1;
                if (j < size && less(j, j + 1)) j++;
                if (greaEqual(k, j)) break;
                exch(k, j);
                k = j;
            }
        }
        private boolean less(int i, int j) {
            return keys[i].compareTo(keys[j]) < 0;
        }
        private boolean greaEqual(int i, int j) {
            return keys[i].compareTo(keys[j]) >= 0;
        }
        private void exch(int i, int j) {
            Key t = keys[i]; keys[i] = keys[j]; keys[j] = t;
        }
    }
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        pq.insert("E");
        pq.insert("A");
        pq.insert("S");
        pq.insert("Y");
        pq.insert("Q");
        pq.insert("U");
        pq.insert("E");
        pq.insert("S");
        pq.insert("T");
        pq.insert("I");
        pq.insert("O");
        pq.insert("N");
        while (!pq.isEmpty())
            StdOut.print(pq.delMax() + " ");
    }
    // output
    /*
     * Y U T S S Q O N I E E A 
     */
}

package Ch_2_4_Priority_Queues;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class __MinPQ<Key extends Comparable<Key>> {
    private Key[] keys;
    private int size;
    @SuppressWarnings("unchecked")
    public __MinPQ(int N) {
        keys = (Key[])new Comparable[N + 1];
    }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == keys.length - 1; }
    public void insert(Key key) {
        if (isFull())
            throw new IllegalArgumentException("priority queue overflow");
        keys[++size] = key;
        swim(size);
    }
    public Key delMin() {
        if (isEmpty())
            throw new NoSuchElementException("priority queue underflow");
        Key min = keys[1];
        exch(1, size--);
        sink(1);
        assert keys[size + 1] == min;
        keys[size + 1] = null;
        return min;
    }
    private void sink(int k) {
        while ((k << 1) <= size) {
            int j = k << 1;
            if (j < size && grea(j, j + 1)) j++;
            if (!grea(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    private void swim(int k) {
        while (k > 1 && grea(k >>> 1, k)) {
            exch(k >>> 1, k);
            k >>>= 1;
        }
    }
    private void exch(int i, int j) {
        Key t = keys[i]; keys[i] = keys[j]; keys[j] = t;
    }
    private boolean grea(int i, int j) {
        return keys[i].compareTo(keys[j]) > 0;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= size; i++)
            sb.append(keys[i] + " ");
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        __MinPQ<Integer> pq = new __MinPQ<Integer>(10);
        for (int i = 0; i < 10; i++)
            pq.insert(StdRandom.uniform(100));
        while (!pq.isEmpty())
            StdOut.println(pq.delMin());
    }
    // output
    /*
     *  0
        6
        17
        19
        47
        48
        67
        78
        86
        95
     */
}

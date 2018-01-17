package Ch_2_4_Priority_Queues;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class __MaxPQ <T extends Comparable<T>> {
    private T[] keys;
    private int size;
    @SuppressWarnings("unchecked")
    public __MaxPQ(int N) {
        keys = (T[])new Comparable[N + 1];
    }
    public boolean isFull() { return size == keys.length - 1; }
    public boolean isEmpty() { return size == 0; }
    public void insert(T key) {
        if (isFull())
            throw new IllegalArgumentException("priority queue overflow");
        keys[++size] = key;
        swim(size);
    }
    public T delMax() {
        if (isEmpty())
            throw new NoSuchElementException("priority queue underflow");
        T max = keys[1];
        exch(1, size--);
        sink(1);
        assert keys[size + 1] == max;
        keys[size + 1] = null;
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
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    private void exch(int i, int j) {
        T t = keys[i]; keys[i] = keys[j]; keys[j] = t;
    }
    private boolean less(int i, int j) {
        return keys[i].compareTo(keys[j]) < 0;
    }
    public static void main(String[] args) {
        __MaxPQ<Integer> pq = new __MaxPQ<Integer>(10);
        for (int i = 0; i < 10; i++)
            pq.insert(StdRandom.uniform(100));
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
}
    //output
    /*
     *  97
        94
        77
        76
        73
        68
        66
        48
        32
        3

     */


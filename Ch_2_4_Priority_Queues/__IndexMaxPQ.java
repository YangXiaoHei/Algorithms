package Ch_2_4_Priority_Queues;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class __IndexMaxPQ <Key extends Comparable<Key>> implements Iterable<Key> {
    private int[] pq;
    private int[] qp;
    private Key[] keys;
    private int size;
    @SuppressWarnings("unchecked")
    public __IndexMaxPQ(int N) {
        pq = new int[N + 1];
        qp = new int[N + 1];
        keys = (Key[])new Comparable[N];
        for (int i = 0; i < N + 1; i++) {
            pq[i] = -1; qp[i] = -1;
        }
    }
    public boolean isFull() { return size == keys.length; }
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
    public void change(int k, Key key) {
        if (k >= size)
            throw new IllegalArgumentException("index out of array's bounds");
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }
    public void delete(int k) {
        int index = qp[k];
        exch(index, size--);
        swim(index);
        sink(index);
        assert pq[size + 1] == k;
        keys[k] = null;
        pq[size + 1] = -1;
        qp[k] = -1;
    }
    public void insert(Key key) {
        ++size;
        keys[size - 1] = key;
        pq[size] = size - 1;
        qp[pq[size]] = size;
        swim(size);
    }
    public int delMax() {
        if (isEmpty())
            throw new NoSuchElementException("priority queue underflow");
        int maxIndex = pq[1];
        exch(1, size--);
        sink(1);
        assert pq[size + 1] == maxIndex;
        pq[size + 1] = -1;
        qp[maxIndex] = -1;
        keys[maxIndex] = null;
        return maxIndex;
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
    private boolean greaEqual(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) >= 0;
    }
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }
    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++)
            sb.append(keys[i] + " ");
        sb.append("\n");
        sb.append("pq : ");
        for (int i = 0; i < pq.length; i++)
            sb.append(pq[i] + " ");
        sb.append("\n");
        sb.append("qp : ");
        for (int i = 0; i < qp.length; i++)
            sb.append(qp[i] + " ");
        sb.append("\n");
        return sb.toString();
    }
    public Iterator<Key> iterator() {
        return new Iterator<Key>() {
            private __IndexMaxPQ<Key> copy = new __IndexMaxPQ<Key>(keys.length);
            {
                for (int i = 0; i < size; i++)
                    copy.insert(keys[pq[i + 1]]);
            }
            public boolean hasNext() { return !copy.isEmpty(); }
            public Key next() {
                Key max = copy.keys[copy.pq[1]];
                copy.delMax();
                return max;
            }
        };
    }
    public static void main(String[] args) {
        String[] s = { "F", "E", "I", "C", "G", "B", "H", "J", "D", "A" };
        __IndexMaxPQ<String> pq = new __IndexMaxPQ<String>(s.length);
        for (String ss : s)
            pq.insert(ss);
        pq.delete(0);
        pq.delete(1);
        pq.delete(2);
        pq.delete(3);
        pq.change(4, "Y");
        pq.change(5, "Z");
        for (String ss : pq)
            StdOut.println(ss);
    }
    // output
    /*
     *  Z
        Y
        J
        H
        D
        A

     */
}

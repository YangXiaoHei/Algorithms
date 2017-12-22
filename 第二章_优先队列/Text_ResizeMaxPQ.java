package 第二章_优先队列;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Text_ResizeMaxPQ <Key extends Comparable<Key>> {
    @SuppressWarnings("unchecked")
    private Key keys[] = (Key[])new Comparable[2];
    private int size;
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Key[] items = (Key[])new Comparable[newSize + 1];
        for (int i = 1; i <= size; i++)
            items[i] = keys[i];
        keys = items;
    }
    public boolean isEmpty() { return size == 0; }
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
        if (size > 0 && size == (keys.length - 1) >>> 2)
            resize((keys.length - 1) >>> 1);
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
    private boolean less(int i, int j) {
        return keys[i].compareTo(keys[j]) < 0;
    }
    private void exch(int i, int j) {
        Key t = keys[i]; keys[i] = keys[j]; keys[j] = t;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("【size = %d】", size));
        for (int i = 0; i < keys.length; i++)
            sb.append(String.format("%5s", keys[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        Text_ResizeMaxPQ<Integer> pq = new Text_ResizeMaxPQ<Integer>();
        for (int i = 0; i < 30; i++)
            pq.insert(StdRandom.uniform(100));
        while (!pq.isEmpty())
           StdOut.println(pq.delMax());
    }
    // output
    /*
     * 
        98
        94
        91
        82
        76
        74
        74
        64
        61
        59
        59
        56
        53
        52
        50
        50
        43
        39
        36
        34
        33
        33
        31
        23
        23
        22
        18
        16
        16
        15

     */
}

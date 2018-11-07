package Review;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MinPQ<T extends Comparable<T>>  {
    @SuppressWarnings("unchecked")
    private T[] pq = (T[])new Comparable[2];
    private int n;
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newArr = (T[])new Comparable[newSize];
        for (int i = 1; i <= n; i++)
            newArr[i] = pq[i];
        pq = newArr;
    }
    public boolean isEmpty() { return n == 0; }
    public void insert(T t) {
        if (n == pq.length - 1)
            resize((n << 2) + 1);
        pq[++n] = t;
        swim(n);
    }
    public T delMin() {
        if (n == 0)
            throw new NoSuchElementException();
        T min = pq[1];
        pq[1] = pq[n--];
        sink(1);
        if (n > 1 && n == (pq.length - 1) >> 2)
            resize(((pq.length - 1) >> 1) + 1);
        return min;
    }
    private void sink(int k) {
        T toSink = pq[k];
        while ((k << 1) <= n) {
            int j = k << 1;
            if (pq[j].compareTo(pq[j + 1]) > 0) j++; /* 哨兵避免 j < size 判断 */
            if (pq[j].compareTo(toSink) >= 0) break;
            pq[k] = pq[j];
            k = j;
        }
        pq[k] = toSink;
    }
    private void swim(int k) {
        T toSwim = pq[k];
        while (k > 1 && toSwim.compareTo(pq[k >> 1]) < 0) {
            pq[k] = pq[k >> 1];
            k >>= 1;
        }
        pq[k] = toSwim;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pq.length; i++)
            sb.append(pq[i] + " ");
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<>();
        int k = 100;
        while (k-- > 0) 
            pq.insert(StdRandom.uniform(100));
        while (!pq.isEmpty()) 
            StdOut.println(pq.delMin());
    }
}

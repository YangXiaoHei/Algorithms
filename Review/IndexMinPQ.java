package Review;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class IndexMinPQ<T extends Comparable<T>> {
    private T[] keys;
    private int[] pq;
    private int[] qp;
    private int n;
    @SuppressWarnings("unchecked")
    public IndexMinPQ(int v) {
        keys = (T[])new Comparable[v];
        pq = new int[v + 1];
        qp = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            pq[i] = -1;
            qp[i] = -1;
        }
    }
    private void swim(int i) {
        int toSwimIndex = pq[i];
        while (i > 1 && keys[toSwimIndex].compareTo(keys[pq[i >> 1]]) < 0) {
            pq[i] = pq[i >> 1];
            qp[pq[i]] = i;
            i >>= 1;
        }
        pq[i] = toSwimIndex;
        qp[pq[i]] = i;
    }
    private void sink(int i) {
        int toSinkIndex = pq[i];
        while ((i << 1) <= n) {
            int j = i << 1;
            if (j < n && keys[pq[j]].compareTo(keys[pq[j + 1]]) > 0) j++;
            if (keys[toSinkIndex].compareTo(keys[pq[j]]) <= 0) break;
            pq[i] = pq[j];
            qp[pq[i]] = i;
            i = j;
        }
        pq[i] = toSinkIndex;
        qp[pq[i]] = i;
    }
    public boolean isEmpty() { return n == 0; }
    public boolean isFull() { return n == keys.length; }
    public int size() { return n; }
    public int delMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        int minIndex = pq[1];
        pq[1] = pq[n--];
        qp[pq[1]] = 1;
        sink(1);
        keys[minIndex] = null;
        qp[minIndex] = -1;
        pq[n + 1] = -1;
//        StdOut.println(this);
        return minIndex;
    }
    public void insert(int i, T key) {
        if (isFull())
            throw new RuntimeException("priority queue overflow!");
        if (i < 0 || i >= keys.length || contains(i))
            throw new IllegalArgumentException("is already exist or index invalid!");
        keys[i] = key;
        n++;
        pq[n] = i;
        qp[pq[n]] = n;
        swim(n); 
        StdOut.println(this);
    }
    public T minKey() {
        if (isEmpty())
            throw new NoSuchElementException("priority queue is empty!");
        return keys[pq[1]];
    }
    @SuppressWarnings("unchecked")
    public T[] allKeys() {
        T[] dup = (T[])new Integer[keys.length];
        for (int i = 0; i < keys.length; i++)
            dup[i] = keys[i];
        return dup;
    }
    public void changeKey(int i, T key) {
        if (isEmpty())
            throw new NoSuchElementException("priority queue is empty!");
        if (i < 0 || i >= keys.length || !contains(i))
            throw new IllegalArgumentException("not exist!");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
        StdOut.println(this);
    }
    public void delete(int i) {
        if (isEmpty())
            throw new NoSuchElementException("priority queue is empty!");
        if (i < 0 || i >= keys.length || !contains(i))
            throw new IllegalArgumentException("not exist!");
        int index = qp[i];
        pq[index] = pq[n--];
        qp[pq[index]] = index;
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
        pq[n + 1] = -1;
        StdOut.println(this);
    }
    public void decreaseKey(int i, T key) {
        if (isEmpty())
            throw new NoSuchElementException("priority queue is empty!");
        if (i < 0 || i >= keys.length || !contains(i))
            throw new IllegalArgumentException("not exist!");
        keys[i] = key;
        int index = qp[i];
        swim(index);
        StdOut.println(this);
    }
    public void increaseKey(int i, T key) {
        if (isEmpty())
            throw new NoSuchElementException("priority queue is empty!");
        if (i < 0 || i >= keys.length || !contains(i))
            throw new IllegalArgumentException("not exist!");
        keys[i] = key;
        int index = qp[i];
        sink(index);
        StdOut.println(this);
    }
    public boolean contains(int i) {
        return keys[i] != null;
    }
    public T keyOf(int i) {
        if (!contains(i))
            throw new NoSuchElementException("priority queue is empty!");
        return keys[i];
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-6c", ' '));
        for (int i = 0; i < pq.length; i++) 
            sb.append(String.format("%-6d", i));
        sb.append("\n");
        sb.append(String.format("%-6s", "keys :"));
        for (int i = 0; i < keys.length; i++)
            sb.append(String.format("%-6d", keys[i]));
        sb.append("\n");
        sb.append(String.format("%-6s", "pq : "));
        for (int i = 0; i < pq.length; i++)
            sb.append(String.format("%-6d", pq[i]));
        sb.append("\n");
        sb.append(String.format("%-6s", "qp : "));
        for (int i = 0; i < qp.length; i++)
            sb.append(String.format("%-6d", qp[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(10);
        pq.insert(3, 8);
        pq.insert(1, 5);
        pq.insert(0, 1);
        pq.insert(7, 10);
        pq.insert(5, 20);
        pq.insert(2, 13);
        pq.insert(6, 7);
        pq.insert(8, 9);
        
        pq.delete(3);
        pq.delete(8);
        pq.delete(0);
        pq.delete(5);
        
        pq.changeKey(7, 20);
        pq.changeKey(2, 99);
        pq.changeKey(1, 37);
        
        Integer[] arr = pq.allKeys();
        
        while (!pq.isEmpty())
            StdOut.println(arr[pq.delMin()]);
    }
}

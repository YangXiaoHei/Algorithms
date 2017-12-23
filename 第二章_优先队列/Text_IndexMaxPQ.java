package 第二章_优先队列;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Text_IndexMaxPQ <Key extends Comparable<Key>> {
    private int[] pq;
    private int[] qp;
    private Key[] keys;
    private int size;
    public Text_IndexMaxPQ(int N) {
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
        for (int i = 0; i < size; i++)
            sb.append(keys[i] + " ");
        sb.append("\n");
        sb.append("pq : ");
        for (int i = 0; i <= size; i++)
            sb.append(pq[i] + " ");
        sb.append("\n");
        sb.append("qp : ");
        for (int i = 0; i <= size; i++)
            sb.append(qp[i] + " ");
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] s = { "F", "E", "I", "C", "G", "B", "H", "J", "D", "A" };
        Text_IndexMaxPQ<String> pq = new Text_IndexMaxPQ<String>(s.length);
        for (String ss : s)
            pq.insert(ss);
        while (!pq.isEmpty())
            StdOut.println(s[pq.delMax()]);
    }
}

package Ch_4_3_Minimum_Spanning_Trees;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class IndexMinPQ <T extends Comparable<T>> {
    private int pq[];
    private int qp[];
    private T[] keys;
    private int size;
    public IndexMinPQ(int n) {
        pq = new int[n + 1];
        qp = new int[n + 1];
        keys = (T[])new Comparable[n];
        for (int i = 0; i < n; i++)
            pq[i] = qp[i] = -1;
    }
    public T minKey() { return keys[pq[1]]; }
    public int minIndex() { return pq[1]; }
    public void insert(T item) {
        if (size == keys.length)
            throw new RuntimeException("overflow");
        keys[size++] = item;
        pq[size] = size - 1;
        qp[pq[size]] = size;    
        swim(size);
    }
    
    public T delMin() {
        T minKey = keys[pq[1]];
        pq[1] = pq[size--];
        qp[pq[1]] = 1;
        sink(1);
        return minKey;
    }
    public boolean isEmpty() { return size == 0; }
    private void swim(int k) {
        T toSwim = keys[pq[k]];
        int toSwimIndex = pq[k];
        while (k > 1 && toSwim.compareTo(keys[pq[k >> 1]]) < 0) {
            pq[k] = pq[k >> 1];
            qp[pq[k]] = k;
            k >>= 1;
        }
        pq[k] = toSwimIndex;
        qp[pq[k]] = k;
    }
    private void sink(int k) {
        T toSink = keys[pq[k]];
        int toSinkIndex = pq[k];
        while ((k << 1) <= size) {
            int j = k << 1;
            if (j < size && keys[pq[j]].compareTo(keys[pq[j + 1]]) > 0) j++;
            if (toSink.compareTo(keys[pq[j]]) <= 0) break;
            pq[k] = pq[j];
            qp[pq[k]] = k;
            k = j;
        }
        pq[k] = toSinkIndex;
        qp[pq[k]] = k;
    }
    public static void main(String[] args) {
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(10);
        int k = 10;
        while (k-- > 0)
            pq.insert(StdRandom.uniform(100));
        while (!pq.isEmpty())
            StdOut.println(pq.delMin());
    }
}

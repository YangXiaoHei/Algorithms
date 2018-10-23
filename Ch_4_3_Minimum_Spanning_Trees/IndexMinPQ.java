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
    public int size() { return size; }
    public T delMin() {
        T minKey = keys[pq[1]];
        pq[1] = pq[size--];
        qp[pq[1]] = 1;
        sink(1);
        return minKey;
    }
    // 不改变元素的数组
    public void delete(int i) {
        int k = qp[i];  // 得到该元素索引的下标
        pq[k] = pq[size--]; 
        qp[pq[k]] = k;
        swim(k);
        sink(k);
    }
    public void change(int i, T item) {
        keys[i] = item;
        swim(qp[i]);
        sink(qp[i]);
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
            sb.append(keys[pq[qp[i]]] + "  ");
        sb.append("\n");
        return sb.toString();
    }
    /*
     *  6  3  1  2  1  3  4  5  

        6  3  1  2  1  3  4  5  
        
        删除第 0 个元素
        把第 0 个元素改成 18
        删除最小值为 : 1
        18  3  1  2  1  3  
        
        删除第 0 个元素
        把第 1 个元素改成 13
        删除最小值为 : 1
        18  13  1  2  
        
        删除第 3 个元素
        把第 1 个元素改成 17
        删除最小值为 : 3
        18  17  
        
        删除第 0 个元素
        把第 0 个元素改成 13
        删除最小值为 : 4
     */
    public static void main(String[] args) {
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(8);
        int k = 8;
        while (k-- > 0) 
            pq.insert(StdRandom.uniform(1, 10));
        StdOut.println(pq);
        while (!pq.isEmpty()) {
            StdOut.println(pq);
            int toDelete = StdRandom.uniform(0, pq.size());
            pq.delete(toDelete);
            StdOut.printf("删除第 %d 个元素\n", toDelete);
            int toChange = StdRandom.uniform(0, pq.size());
            int value = StdRandom.uniform(10, 20);
            StdOut.printf("把第 %d 个元素改成 %d\n", toChange, value);
            pq.change(toChange, value);
            int min = pq.delMin();
            StdOut.printf("删除最小值为 : %d\n", min);
        }
    }
}

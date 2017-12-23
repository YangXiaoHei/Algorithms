package 第二章_优先队列;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Text_IndexMinPQ <Key extends Comparable<Key>> implements Iterable<Key> {
    private int[] pq;
    private int[] qp;
    private Key[] keys;
    private int size;
    @SuppressWarnings("unchecked")
    public Text_IndexMinPQ(int N) {
        pq = new int[N + 1];
        qp = new int[N + 1];
        keys = (Key[])new Comparable[N + 1];
        for (int i = 0; i <= N; i++) {
            qp[i] = -1; pq[i] = -1;
        }
    }
    public boolean contains(int i) { return keys[i] != null; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull()  { return size == keys.length - 1; }
    public Key keyOf(int i) { 
        if (!contains(i)) 
            throw new NoSuchElementException("index is not in the prioriy queue");
        return keys[i];
    }
    public void change(int i, Key key) {
        if (i > size)
            throw new IllegalArgumentException("array out of bounds");
        if (!contains(i))
            throw new IllegalArgumentException("key is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }
    public Key minKey() {
        if (isEmpty()) 
            throw new NoSuchElementException("prioriy queue is empty");
        return keys[pq[1]];
    }
    public int minIndex() {
        if (isEmpty()) 
            throw new NoSuchElementException("prioriy queue is empty");
        return pq[1];   
    }
    public void delete(int k) {
        if (!contains(k))
            throw new NoSuchElementException("index is not in the prioriy queue");
        int index = qp[k];
        exch(index, size--);
        swim(index);
        sink(index);
        assert pq[size + 1] == k;
        keys[pq[size + 1]] = null;
        pq[size + 1] = -1;
        qp[k] = -1;
    }
    public int delMin() {
        int minIndex = pq[1];
        exch(1, size--);
        sink(1);
        assert pq[size + 1] == minIndex;
        pq[size + 1] = -1;
        qp[minIndex] = -1;
        keys[minIndex] = null;
        return minIndex;
    }
    public void insert(Key key) {
        if (isFull())
            throw new IllegalArgumentException("priority queue overflow");
        ++size;
        pq[size] = size - 1;
        qp[pq[size]] = size;
        keys[size - 1] = key;
        swim(size);
    }
    private void swim(int k) {
        // 如果父节点更大，那么就交换
        while (k > 1 && grea(k >>> 1, k)) {
            exch(k >>> 1, k);
            k >>>= 1;
        }
    }
    private void sink(int k) {
        while ((k << 1) <= size) {
            int j = k << 1;
            if (j < size && grea(j, j + 1)) j++; // 选两个分叉中最小的
            if (lessEqual(k, j)) break; // 比最小的都小，那就不用下沉了
            exch(k, j); // 只交换索引
            k = j;
        }
    }
    private boolean lessEqual(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) <= 0;
    }
    private boolean grea(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }
    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    public Iterator<Key> iterator() {
        return new Iterator<Key>() {
            private Text_IndexMinPQ<Key> copy = new Text_IndexMinPQ<Key>(keys.length);
            {
                for (int i = 0; i <= size; i++) {
                    copy.pq[i] = pq[i];
                    copy.qp[i]= qp[i];
                    copy.keys[i] = keys[i];
                    copy.size = size;
                }
            }
            public boolean hasNext() { return !copy.isEmpty(); }
            public Key next() { return keys[copy.delMin()];  }
        };
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Key k : keys) sb.append(k + " ");
        sb.append("\n");
        sb.append("pq : ");
        for (int i : pq) sb.append(i + " ");
        sb.append("\n");
        sb.append("qp : ");
        for (int i : qp) sb.append(i + " ");
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] s = { "F", "E", "I", "C", "G", "B", "H", "J", "D", "A" };
        Text_IndexMinPQ<String> pq = new Text_IndexMinPQ<String>(s.length);
        for (String ss : s)
            pq.insert(ss);
        for (String i : pq) 
            StdOut.println(i);
        StdOut.println("\n");
        pq.delete(0);
        pq.delete(9);
        pq.change(8, "Z");
        pq.change(5, "H");
        for (String i : pq) 
            StdOut.println(i);
    }
    // output
    /*
     *  A
        B
        C
        D
        E
        F
        G
        H
        I
        J
        
        
        C
        E
        G
        H
        H
        I
        J
        Z

     */
    
}

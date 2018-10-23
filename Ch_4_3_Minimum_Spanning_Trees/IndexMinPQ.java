package Ch_4_3_Minimum_Spanning_Trees;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;

public class IndexMinPQ <T extends Comparable<T>> {
    private int pq[];
    private int qp[];
    private T[] keys;
    private int size;
    public IndexMinPQ(int n) {
        pq = new int[n + 1];
        qp = new int[n + 1];
        keys = (T[])new Comparable[n];
        for (int i = 0; i < n + 1; i++)
            pq[i] = qp[i] = -1;
    }
    public T[] allKeys() {
        T[] k = (T[])new Comparable[keys.length];
        for (int i = 0; i < keys.length; i++)
            k[i] = keys[i];
        return k;
    }
    public IndexMinPQ<T> dupIndexMinPQ() {
        IndexMinPQ<T> pqI = new IndexMinPQ<>(keys.length);
        for (int i = 0; i < keys.length; i++)
            pqI.keys[i] = keys[i];
        for (int j = 0; j < keys.length + 1; j++) {
            pqI.pq[j] = pq[j];
            pqI.qp[j] = qp[j];
        }
        pqI.size = size;
        return pqI;
    }
    public T minKey() { return keys[pq[1]]; }
    public int minIndex() { return pq[1]; }
    public void insert(int k, T item) {
        if (size == keys.length)
            throw new RuntimeException("overflow");
        if (contains(k))
            throw new RuntimeException("already exist!");
        pq[++size] = k;
        qp[k] = size;    
        keys[k] = item;
        swim(size);
    }
    public boolean contains(int i) { return keys[i] != null; }
    public int size() { return size; }
    public int delMin() {
        if (isEmpty())
            throw new NoSuchElementException("underflow!");
        /* 取出最小值的索引 */
        int min = pq[1];
        /* 交换位置 */
        pq[1] = pq[size];
        /* 更新索引位置信息 */
        qp[pq[1]] = 1;
        size--;
        sink(1);
        /* 把这两个货搞成 -1 免得混淆视听 */
        pq[size + 1] = -1;
        qp[min] = -1;
        /* 有助于垃圾回收 */
        keys[min] = null;
        return min;
    }
    public void delete(int i) {
        if (!contains(i))
            throw new NoSuchElementException("delete a nonexist element");
        int k = qp[i];  
        pq[k] = pq[size]; 
        qp[pq[k]] = k;
        size--;
        swim(k);
        sink(k);
        pq[size + 1] = -1;
        qp[i] = -1;
        keys[i] = null;
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
        sb.append(String.format("%-6c", ' '));
        for (int i = 0; i < pq.length; i++) 
            sb.append(String.format("%-6d", i));
        sb.append("\n");
        sb.append(String.format("%-6s", "pq : "));
        for (int i = 0; i < pq.length; i++)
            sb.append(String.format("%-6d", pq[i]));
        sb.append("\n");
        sb.append(String.format("%-6s", "qp : "));
        for (int i = 0; i < qp.length; i++)
            sb.append(String.format("%-6d", qp[i]));
        sb.append("\n");
        sb.append(String.format("%-7s", "keys : "));
        for (int i = 0; i < keys.length; i++)
            sb.append(String.format("%-6d", keys[i]));
        sb.append("\n");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(10);
        pq.insert(3, 33);
        pq.insert(8, 22);
        pq.insert(2, 65);
        pq.insert(4, 12);
        pq.insert(6, 98);
        pq.insert(5, 73);
        pq.insert(1, 71);
        pq.insert(0, 68);
        pq.insert(9, 56);
        pq.insert(7, 11);
        StdOut.println(pq);
        
        Comparable[] keys = pq.allKeys();
        IndexMinPQ<Integer> dup = pq.dupIndexMinPQ();
        
        while (!pq.isEmpty())
            StdOut.println(keys[pq.delMin()]);
        
        dup.delete(3);
        StdOut.println(dup);
        
        dup.delete(8);
        StdOut.println(dup);
        
        dup.delete(2);
        StdOut.println(dup);
        
        dup.delete(7);
        StdOut.println(dup);
        
        dup.delete(1);
        StdOut.println(dup);
        
        dup.delete(0);
        StdOut.println(dup);
        
        dup.delete(4);
        StdOut.println(dup);
        
        dup.delete(5);
        StdOut.println(dup);
        
        dup.delete(6);
        StdOut.println(dup);
        
        dup.delete(9);
        StdOut.println(dup);
    }
    // output
    /*
     *     0     1     2     3     4     5     6     7     8     9     10    
    pq :   -1    7     4     2     3     8     5     1     0     9     6     
    qp :   8     7     3     4     2     6     10    1     5     9     -1    
    keys : 68    71    65    33    12    73    98    11    22    56    
    
    11
    12
    22
    33
    56
    65
    68
    71
    73
    98
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    7     4     2     9     8     5     1     0     6     -1    
    qp :  8     7     3     -1    2     6     9     1     5     4     -1    
    keys : 68    71    65    null  12    73    98    11    22    56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    7     4     2     9     6     5     1     0     -1    -1    
    qp :  8     7     3     -1    2     6     5     1     -1    4     -1    
    keys : 68    71    65    null  12    73    98    11    null  56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    7     4     0     9     6     5     1     -1    -1    -1    
    qp :  3     7     -1    -1    2     6     5     1     -1    4     -1    
    keys : 68    71    null  null  12    73    98    11    null  56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    4     9     0     1     6     5     -1    -1    -1    -1    
    qp :  3     4     -1    -1    1     6     5     -1    -1    2     -1    
    keys : 68    71    null  null  12    73    98    null  null  56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    4     9     0     5     6     -1    -1    -1    -1    -1    
    qp :  3     -1    -1    -1    1     4     5     -1    -1    2     -1    
    keys : 68    null  null  null  12    73    98    null  null  56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    4     9     6     5     -1    -1    -1    -1    -1    -1    
    qp :  -1    -1    -1    -1    1     4     3     -1    -1    2     -1    
    keys : null  null  null  null  12    73    98    null  null  56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    9     5     6     -1    -1    -1    -1    -1    -1    -1    
    qp :  -1    -1    -1    -1    -1    2     3     -1    -1    1     -1    
    keys : null  null  null  null  null  73    98    null  null  56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    9     6     -1    -1    -1    -1    -1    -1    -1    -1    
    qp :  -1    -1    -1    -1    -1    -1    2     -1    -1    1     -1    
    keys : null  null  null  null  null  null  98    null  null  56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    9     -1    -1    -1    -1    -1    -1    -1    -1    -1    
    qp :  -1    -1    -1    -1    -1    -1    -1    -1    -1    1     -1    
    keys : null  null  null  null  null  null  null  null  null  56    
    
          0     1     2     3     4     5     6     7     8     9     10    
    pq :  -1    -1    -1    -1    -1    -1    -1    -1    -1    -1    -1    
    qp :  -1    -1    -1    -1    -1    -1    -1    -1    -1    -1    -1    
    keys : null  null  null  null  null  null  null  null  null  null  
     */
}

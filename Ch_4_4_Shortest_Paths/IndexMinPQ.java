package Ch_4_4_Shortest_Paths;

import edu.princeton.cs.algs4.StdOut;

public class IndexMinPQ <Key extends Comparable<Key>> {
    private Key[] keys;
    private int[] pq;
    private int[] qp;
    private int n;
    public IndexMinPQ(int n) {
        keys = (Key[])new Comparable[n];
        pq = new int[n + 1];
        qp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            pq[i] = -1;
            qp[i] = -1;
        }
    }
    public boolean contains(int i) {
        return keys[i] != null;
    }
    public void insert(int i, Key key) {
        if (contains(i))
            throw new RuntimeException("already exist!");
        keys[i] = key;
        ++n;
        pq[n] = i;
        qp[pq[n]] = n;
        swim(n);
    }
    public boolean isEmpty() { return n == 0; }
    public int delMin() {
        if (isEmpty())
            throw new RuntimeException("underflow!");
        int min = pq[1];
        pq[1] = pq[n--];
        qp[pq[1]] = 1;
        sink(1);
        qp[min] = -1;
        pq[n + 1] = -1;
        keys[min] = null;
        return min;
    }
    public void change(int i, Key k) {
        if (!contains(i))
            throw new RuntimeException("no such elem");
        keys[i] = k;
        swim(qp[i]);
        sink(qp[i]);
    }
    public void delete(int i) {
        if (!contains(i))
            throw new RuntimeException("No such elem");
        int index = qp[i];
        pq[index] = pq[n--];
        qp[pq[index]] = index;
        swim(index);
        sink(index);
        qp[i] = -1;
        pq[n + 1] = -1;
        keys[i] = null;
    }
    private void swim(int k) {
        int bottom = pq[k];
        while (k > 1 && keys[bottom].compareTo(keys[pq[k >> 1]]) < 0) {
            pq[k] = pq[k >> 1];
            qp[pq[k]] = k;
            k >>= 1;
        }
        pq[k] = bottom;
        qp[pq[k]] = k;
    }
    private void sink(int k) {
        int top = pq[k];
        while ((k << 1) <= n) {
            int j = k << 1;
            if (j < n && keys[pq[j]].compareTo(keys[pq[j + 1]]) > 0) j++;
            if (keys[top].compareTo(keys[pq[j]]) <= 0) break;
            pq[k] = pq[j];
            qp[pq[k]] = k;
            k = j;
        }
        pq[k] = top;
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
    public Key[] allKeys() {
        Key[] k = (Key[])new Comparable[keys.length];
        for (int i = 0; i < keys.length; i++)
            k[i] = keys[i];
        return k;
    }
    public IndexMinPQ<Key> dupIndexMinPQ() {
        IndexMinPQ<Key> pqI = new IndexMinPQ<>(keys.length);
        for (int i = 0; i < keys.length; i++)
            pqI.keys[i] = keys[i];
        for (int j = 0; j < keys.length + 1; j++) {
            pqI.pq[j] = pq[j];
            pqI.qp[j] = qp[j];
        }
        pqI.n = n;
        return pqI;
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
     *       0     1     2     3     4     5     6     7     8     9     10    
        pq :  -1    7     4     2     3     8     5     1     0     9     6     
        qp :  8     7     3     4     2     6     10    1     5     9     -1    
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

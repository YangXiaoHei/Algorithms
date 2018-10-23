package Ch_4_3_Minimum_Spanning_Trees;

public class IndexMinPQ <T extends Comparable<T>> implements Iterable<T> {
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
        
    }
    
    public T delMin() {
        
    }
    private void swim(int k) {
        T toSwim = keys[pq[k]];
        int toSwimIndex = pq[k];
        while (k > 1 && toSwim.compareTo(keys[pq[k >> 1]]) < 0) {
            pq[k] = pq[k >> 1];
            // pq[k] = x;
            // qp[pq[k]] = k;
            // 
            // pq[k] = y;
            // qp[y] = k;
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
            if (j < size && keys[pq[k]].compareTo(keys[pq[k + 1]]) > 0) j++;
            if (toSink.compareTo(keys[pq[j]]) <= 0) break;
            pq[k] = pq[j];
            qp[pq[k]] = k;
            k = j;
        }
        pq[k] = toSinkIndex;
        qp[pq[k]] = k;
    }
    public static void main(String[] args) {
        
    }
}

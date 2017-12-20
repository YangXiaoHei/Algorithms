package 第二章_优先队列;

import edu.princeton.cs.algs4.*;

public class Text_MaxPQ <T extends Comparable<T>> {
    private T[] pq;
    private int size = 0;
    @SuppressWarnings("unchecked")
    public Text_MaxPQ(int N) {
        pq = (T[])new Comparable[N + 1];
    }
    public boolean isEmpty() { return size == 0; }
    public void insert(T v) {
        pq[++size] = v;
        swim(size); // 上浮
    }
    public T delMax() {
        T max = pq[1]; // 根结点是最大的值
        exch(1, size--); // 交换根结点和叶子节点
        pq[size + 1] = null; // 删除叶子节点
        sink(1); // 下沉
        return max;
    }
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
    private void exch(int i, int j) {
        T t = pq[i]; pq[i] = pq[j]; pq[j] = t;
    }
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++)
            sb.append(pq[i] + " ");
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        Text_MaxPQ<Integer> pq = new Text_MaxPQ<Integer>(100);
        for (int i = 0; i < 30; i++)
            pq.insert(StdRandom.uniform(1000));
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
    //output
    /*
     *  981
        962
        936
        933
        890
        870
        850
        827
        817
        806
        792
        683
        609
        524
        507
        496
        493
        420
        404
        400
        359
        346
        337
        305
        246
        211
        168
        77
        62
        44
     */
}

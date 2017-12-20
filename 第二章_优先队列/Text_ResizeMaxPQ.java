package 第二章_优先队列;

import edu.princeton.cs.algs4.*;

public class Text_ResizeMaxPQ <T extends Comparable<T>> {
    @SuppressWarnings("unchecked")
    private T[] pq = (T[])new Comparable[2];
    private int size = 0;
    boolean isEmpty() { return size == 0; }
    void insert(T item) {
        if (size == pq.length - 1)
            resize(size << 1);
        pq[++size] = item;
        swim(size);
        StdOut.println(this);
    }
    T delMax() {
        T max = pq[1];
        T t = pq[1]; pq[1] = pq[size]; pq[size] = t;
        pq[size--] = null;
        sink(1);
        if (size > 0 && size == (pq.length - 1) >> 2)
            resize((pq.length - 1) >> 1);
        StdOut.println(this);
        return max;
    }
    void resize(int newSize) {
        @SuppressWarnings("unchecked")
        T[] newItems = (T[])new Comparable[newSize + 1];
        for (int i = 1; i <= size; i++)
            newItems[i] = pq[i];
        pq = newItems;
    }
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && pq[j].compareTo(pq[j + 1]) < 0) j++;
            if (pq[k].compareTo(pq[j]) >= 0) break;
            T t = pq[k]; pq[k] = pq[j]; pq[j] = t;
            k = j;
        }
    }
    private void swim(int k) {
        while (k > 1 && pq[k].compareTo(pq[k / 2]) >= 0) {
            T t = pq[k]; pq[k] = pq[k / 2]; pq[k / 2] = t;
            k /= 2;
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pq.length; i++)
            sb.append(String.format("%5s", pq[i]));
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        Text_ResizeMaxPQ<Integer> pq = new Text_ResizeMaxPQ<Integer>();
        StdOut.println("\n=========== 开始插入元素 ===============\n");
        for (int i = 0; i < 30; i++)
            pq.insert(i);
        StdOut.println("\n=========== 开始删除最大元素 ===============\n");
        while (!pq.isEmpty())
            pq.delMax();
    }
    // output
    /*
     * 
        =========== 开始插入元素 ===============
        
         null    0
        
         null    1    0
        
         null    2    0    1 null
        
         null    3    2    1    0
        
         null    4    3    1    0    2 null null null
        
         null    5    3    4    0    2    1 null null
        
         null    6    3    5    0    2    1    4 null
        
         null    7    6    5    3    2    1    4    0
        
         null    8    7    5    6    2    1    4    0    3 null null null null null null null
        
         null    9    8    5    6    7    1    4    0    3    2 null null null null null null
        
         null   10    9    5    6    8    1    4    0    3    2    7 null null null null null
        
         null   11    9   10    6    8    5    4    0    3    2    7    1 null null null null
        
         null   12    9   11    6    8   10    4    0    3    2    7    1    5 null null null
        
         null   13    9   12    6    8   10   11    0    3    2    7    1    5    4 null null
        
         null   14    9   13    6    8   10   12    0    3    2    7    1    5    4   11 null
        
         null   15   14   13    9    8   10   12    6    3    2    7    1    5    4   11    0
        
         null   16   15   13   14    8   10   12    9    3    2    7    1    5    4   11    0    6 null null null null null null null null null null null null null null null
        
         null   17   16   13   15    8   10   12    9   14    2    7    1    5    4   11    0    6    3 null null null null null null null null null null null null null null
        
         null   18   17   13   16    8   10   12    9   15    2    7    1    5    4   11    0    6    3   14 null null null null null null null null null null null null null
        
         null   19   18   13   16   17   10   12    9   15    8    7    1    5    4   11    0    6    3   14    2 null null null null null null null null null null null null
        
         null   20   19   13   16   18   10   12    9   15   17    7    1    5    4   11    0    6    3   14    2    8 null null null null null null null null null null null
        
         null   21   20   13   16   19   10   12    9   15   17   18    1    5    4   11    0    6    3   14    2    8    7 null null null null null null null null null null
        
         null   22   21   13   16   20   10   12    9   15   17   19    1    5    4   11    0    6    3   14    2    8    7   18 null null null null null null null null null
        
         null   23   21   22   16   20   13   12    9   15   17   19   10    5    4   11    0    6    3   14    2    8    7   18    1 null null null null null null null null
        
         null   24   21   23   16   20   22   12    9   15   17   19   13    5    4   11    0    6    3   14    2    8    7   18    1   10 null null null null null null null
        
         null   25   21   24   16   20   23   12    9   15   17   19   13   22    4   11    0    6    3   14    2    8    7   18    1   10    5 null null null null null null
        
         null   26   21   25   16   20   24   12    9   15   17   19   13   23    4   11    0    6    3   14    2    8    7   18    1   10    5   22 null null null null null
        
         null   27   21   26   16   20   24   25    9   15   17   19   13   23   12   11    0    6    3   14    2    8    7   18    1   10    5   22    4 null null null null
        
         null   28   21   27   16   20   24   26    9   15   17   19   13   23   25   11    0    6    3   14    2    8    7   18    1   10    5   22    4   12 null null null
        
         null   29   21   28   16   20   24   27    9   15   17   19   13   23   25   26    0    6    3   14    2    8    7   18    1   10    5   22    4   12   11 null null
        
        
        =========== 开始删除最大元素 ===============
        
         null   28   21   27   16   20   24   26    9   15   17   19   13   23   25   11    0    6    3   14    2    8    7   18    1   10    5   22    4   12 null null null
        
         null   27   21   26   16   20   24   25    9   15   17   19   13   23   12   11    0    6    3   14    2    8    7   18    1   10    5   22    4 null null null null
        
         null   26   21   25   16   20   24   12    9   15   17   19   13   23    4   11    0    6    3   14    2    8    7   18    1   10    5   22 null null null null null
        
         null   25   21   24   16   20   23   12    9   15   17   19   13   22    4   11    0    6    3   14    2    8    7   18    1   10    5 null null null null null null
        
         null   24   21   23   16   20   22   12    9   15   17   19   13    5    4   11    0    6    3   14    2    8    7   18    1   10 null null null null null null null
        
         null   23   21   22   16   20   13   12    9   15   17   19   10    5    4   11    0    6    3   14    2    8    7   18    1 null null null null null null null null
        
         null   22   21   13   16   20   10   12    9   15   17   19    1    5    4   11    0    6    3   14    2    8    7   18 null null null null null null null null null
        
         null   21   20   13   16   19   10   12    9   15   17   18    1    5    4   11    0    6    3   14    2    8    7 null null null null null null null null null null
        
         null   20   19   13   16   18   10   12    9   15   17    7    1    5    4   11    0    6    3   14    2    8 null null null null null null null null null null null
        
         null   19   18   13   16   17   10   12    9   15    8    7    1    5    4   11    0    6    3   14    2 null null null null null null null null null null null null
        
         null   18   17   13   16    8   10   12    9   15    2    7    1    5    4   11    0    6    3   14 null null null null null null null null null null null null null
        
         null   17   16   13   15    8   10   12    9   14    2    7    1    5    4   11    0    6    3 null null null null null null null null null null null null null null
        
         null   16   15   13   14    8   10   12    9    3    2    7    1    5    4   11    0    6 null null null null null null null null null null null null null null null
        
         null   15   14   13    9    8   10   12    6    3    2    7    1    5    4   11    0 null null null null null null null null null null null null null null null null
        
         null   14    9   13    6    8   10   12    0    3    2    7    1    5    4   11 null null null null null null null null null null null null null null null null null
        
         null   13    9   12    6    8   10   11    0    3    2    7    1    5    4 null null null null null null null null null null null null null null null null null null
        
         null   12    9   11    6    8   10    4    0    3    2    7    1    5 null null null null null null null null null null null null null null null null null null null
        
         null   11    9   10    6    8    5    4    0    3    2    7    1 null null null null null null null null null null null null null null null null null null null null
        
         null   10    9    5    6    8    1    4    0    3    2    7 null null null null null null null null null null null null null null null null null null null null null
        
         null    9    8    5    6    7    1    4    0    3    2 null null null null null null null null null null null null null null null null null null null null null null
        
         null    8    7    5    6    2    1    4    0    3 null null null null null null null null null null null null null null null null null null null null null null null
        
         null    7    6    5    3    2    1    4    0 null null null null null null null null
        
         null    6    3    5    0    2    1    4 null null null null null null null null null
        
         null    5    3    4    0    2    1 null null null null null null null null null null
        
         null    4    3    1    0    2 null null null null null null null null null null null
        
         null    3    2    1    0 null null null null
        
         null    2    0    1 null null null null null
        
         null    1    0 null null
        
         null    0 null
        
         null null null

     */
}

package 第二章_优先队列;

import static 第二章_初级排序算法.Text_Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_16 {
    static class MaxPQ<Key extends Comparable<Key>> {
        private int size;
        private Key[] keys;
        @SuppressWarnings("unchecked")
        public MaxPQ(int N) {
            keys = (Key[])new Comparable[N + 1];
        }
        public void insert(Key key) {
            if (size == keys.length - 1)
                throw new IllegalArgumentException("priority queue overflow!");
            keys[++size] = key;
            int k = size;
            while (k > 1 && less(k >> 1, k)) {
                Key t = keys[k >> 1]; keys[k >> 1] = keys[k]; keys[k] = t;
                k >>= 1;
            }
        }
        private int compares;
        public boolean less(int i, int j) {
            compares++;
            return keys[i].compareTo(keys[j]) < 0;
        }
        public Key delMax() {
            Key max = keys[1];
            keys[1] = keys[size--];
            int k = size;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (less(j, j + 1)) j++;
                if (keys[k].compareTo(keys[j]) >= 0) break;
                Key t = keys[j]; keys[j] = keys[k]; keys[k] = t;
                k = j;
            }
            return max;
        }
    }
    public static void main(String[] args) {
       /*
        * 按照升序插入使得比较次数达到最大，因为每次插入的新结点，都会重新交换至根结点
        */
        MaxPQ<Integer> pq = new MaxPQ<Integer>(32);
        for (Integer i : ascendIntegers(0, 31))
            pq.insert(i);
        StdOut.printf("最大比较次数 : %d\n", pq.compares);
        /*
         * 按照降序插入会使得比较次数达到最小，因为一次交换都不会发生，所以比较次数为 N - 1
         */
        MaxPQ<Integer> pq1 = new MaxPQ<Integer>(32);
        for (Integer i : descendIntegers(31, 0))
            pq1.insert(i);
        StdOut.printf("最小比较次数 : %d\n", pq1.compares);
    }
    // output
    /*
     *  最大比较次数 : 103
        最小比较次数 : 31

     */
}

package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_14 {
    static class MaxPQ<Key extends Comparable<Key>> {
        private Key[] keys;
        private int size;
        @SuppressWarnings("unchecked")
        public MaxPQ(int N) {
            keys = (Key[])new Comparable[N + 1];
        }
        public boolean isFull() { return size == keys.length - 1; }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (isFull())
                throw new IllegalArgumentException("priority queue overflow!");
            keys[++size] = key;
            int k = size;
            while (k > 1 && keys[k >> 1].compareTo(keys[k]) < 0) {
                Key t = keys[k >> 1]; keys[k >> 1] = keys[k]; keys[k] = t;
                k >>= 1;
            }
        }
        private int exchanges;
        public Key delMax() {
            if (isEmpty())
                throw new IllegalArgumentException("priority queue underflow!");
            Key max = keys[1];
            keys[1] = keys[size--];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j].compareTo(keys[j + 1]) < 0) j++;
                if (keys[k].compareTo(keys[j]) >= 0) break;
                exchanges++;
                Key t = keys[k]; keys[k] = keys[j]; keys[j] = t;
                k = j;
            }
            keys[size + 1] = null;
            return max;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= size; i++)
                sb.append(keys[i] + " ");
            sb.append("\n");
            return sb.toString();
        }
    }
    public static void construct2ExchangesHeap() {
        int N = 15;
        while (true) {
            MaxPQ<Integer> pq = new MaxPQ<Integer>(N);
            for (Integer i : Integers(0, N - 1)) 
                pq.insert(i);
            Integer[] copy = new Integer[N + 1];
            System.arraycopy(pq.keys, 0, copy, 0, N + 1);
            Integer t = pq.delMax();
            if (pq.exchanges < 3) {
                StdOut.printf("删掉 %d 的交换次数 : %d\n", t, pq.exchanges);
                StdOut.println(Arrays.toString(copy));
                break;
            }
        }
            /*
             * 
             * 删掉 14 的交换次数 : 2
                [null, 
                14, 
                10, 13, 
                9, 6, 11, 12, 
                3, 4, 2, 5, 0, 8, 1, 7]

                [null,
                 14,
                 13, 10, 
                 11, 12, 8, 7, 
                 2, 9, 0, 1, 3, 5, 6, 4]
             */
    }
    public static void construct3ExchangesHeap() {
        int N = 15;
        while (true) {
            MaxPQ<Integer> pq = new MaxPQ<Integer>(N);
            for (Integer i : Integers(0, N - 1)) 
                pq.insert(i);
            Integer[] copy = new Integer[N + 1];
            System.arraycopy(pq.keys, 0, copy, 0, N + 1);
            Integer t = pq.delMax();    // 连续删 2 次
            Integer t1 = pq.delMax();
            if (pq.exchanges < 3) {
                StdOut.printf("删掉 %d %d 的交换次数 : %d\n",t, t1, pq.exchanges);
                StdOut.println(Arrays.toString(copy));
                break;
            }
        }
            /*
             * 
             * 删掉 14 13 的交换次数 : 3
               [null, 
               14, 
               12, 13,
               8, 5, 7, 11, 
               2, 1, 3, 0, 4, 6, 9, 10]

             */
    }
    public static void construct5ExchangesHeap() {
        int N = 15;
        while (true) {
            MaxPQ<Integer> pq = new MaxPQ<Integer>(N);
            for (Integer i : Integers(0, N - 1)) 
                pq.insert(i);
            Integer[] copy = new Integer[N + 1];
            System.arraycopy(pq.keys, 0, copy, 0, N + 1);
            Integer t = pq.delMax(); // 连续删 3 次
            Integer t1 = pq.delMax();
            Integer t2 = pq.delMax();
            if (pq.exchanges < 6) {
                StdOut.printf("删掉 %d %d %d 的交换次数 : %d\n",t, t1, t2, pq.exchanges);
                StdOut.println(Arrays.toString(copy));
                break;
            }
        }
        /*
         * 删掉 14 13 12 的交换次数 : 5
            [null, 
            14,
            12, 13, 
            8, 5, 7, 11, 
            2, 6, 0, 3, 1, 4, 9, 10]

         */
    }
    public static void main(String[] args) {
    }
}

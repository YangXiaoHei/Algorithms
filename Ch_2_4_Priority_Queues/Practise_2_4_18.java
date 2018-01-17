package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_18 {
    static class MaxPQ {
        private int size;
        private int keys[];
        public MaxPQ(int N) {
            keys = new int[N + 1];
        }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull() { return size == keys.length - 1; }
        public void insert(int key) {
            if (isFull())
                throw new IllegalArgumentException("priority queue overflow!");
            keys[++size] = key;
            int k = size;
            while (k > 1 && keys[k >> 1] < keys[k]) {
                int t = keys[k >> 1]; keys[k >> 1] = keys[k]; keys[k] = t;
                k >>= 1;
            }
        }
        public int delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow!");
            int max = keys[1];
            keys[1] = keys[size--];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j] < keys[j + 1]) j++;
                if (keys[k] >= keys[j]) break;
                int t = keys[k]; keys[k] = keys[j]; keys[j] = t;
                k = j;
            }
            keys[size + 1] = 0;
            return max;
        }
    }
    public static boolean equal(int[] pq1, int[] pq2) {
         if (pq1.length != pq2.length) return false;
         for (int i = 0; i < pq1.length; i++)
             if (pq1[i] != pq2[i]) return false;
         return true;
    }
    public static void main(String[] args) {
        /*
         * 插入一个比堆中所有元素都大的元素，然后再删除它
         * 
         * 插入前的堆和删除后的堆，是完全相等的 
         */
        while (true) {
            int N = 15;
            int[] a = intsNoDupli(N - 2, 1, 1000);
            MaxPQ pq = new MaxPQ(N);
            for (int i : a)
                pq.insert(i);
            
            int[] copy = intsCopy(pq.keys);
            pq.insert(2000);
            int[] copy1 = intsCopy(pq.keys);
            int max = pq.delMax();
            int[] copy2 = intsCopy(pq.keys);
            
            if (!equal(copy, copy2)) {
                StdOut.println("\n插入之前");
                print(copy);
                StdOut.printf("\n插入了 %d 后\n", 1000);
                print(copy1);
                StdOut.printf("\n删除了最大元素 %d 后\n", max);
                print(copy2);
                break;
            }
        }
        StdOut.println("-------------");
    }
}

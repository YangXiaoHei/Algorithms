package 第二章_优先队列;

import java.util.*;
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;

import static 第二章_初级排序算法.Text_Array.*;

public class Practise_2_4_36 {
    static class MaxPQ <Key extends Comparable<Key>> {
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
                throw new IllegalArgumentException("priority queue overflow");
            keys[++size] = key;
            swim(size);
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key max = keys[1];
            keys[1] = keys[size--];
            sink(1);
            keys[size + 1] = null;
            return max;
        }
        private void sink(int k) {
            Key key = keys[k];
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j].compareTo(keys[j + 1]) < 0) j++;
                if (key.compareTo(keys[j]) >= 0) break;
                keys[k] = keys[j];
                k = j;
            }
            keys[k] = key;
        }
        private void swim(int k) {
            Key key = keys[k];
            while (k > 1 && key.compareTo(keys[k >> 1]) > 0) {
                keys[k] = keys[k >> 1];
                k >>= 1;
            }
            keys[k] = key;
        }
    }
    public static void main(String[] args) {
        int N = 100000;
        MaxPQ<Integer> pq = new MaxPQ<Integer>(N);
        int[] a = ints(0, N - 1);
        for (int i : a) pq.insert(i);
        for (int i = 0; i < 30; i++) {
            Stopwatch timer = new Stopwatch();
            int del = N / 2;
            while (del-- > 0)
                pq.delMax();
            for (int j : ints(0, N / 2 - 1))
                pq.insert(j);
            StdOut.printf("耗时 : %.3f\n", timer.elapsedTime());
        }
    }
    // output
    /*
     *  耗时 : 0.038
        耗时 : 0.025
        耗时 : 0.017
        耗时 : 0.018
        耗时 : 0.018
        耗时 : 0.019
        耗时 : 0.024
        耗时 : 0.023
        耗时 : 0.022
        耗时 : 0.030
        耗时 : 0.019
        耗时 : 0.021
        耗时 : 0.023
        耗时 : 0.021
        耗时 : 0.019
        耗时 : 0.024
        耗时 : 0.024
        耗时 : 0.033
        耗时 : 0.031
        耗时 : 0.027
        耗时 : 0.029
        耗时 : 0.028
        耗时 : 0.021
        耗时 : 0.028
        耗时 : 0.017
        耗时 : 0.019
        耗时 : 0.022
        耗时 : 0.020
        耗时 : 0.020
        耗时 : 0.016
     */
}

package Ch_2_4_Priority_Queues;

import static Tool.ArrayGenerator.*;
import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_29 {
    static class MaxMinPQ<Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] maxKeys = (Key[])new Comparable[2];
        @SuppressWarnings("unchecked")
        private Key[] minKeys = (Key[])new Comparable[2];
        private Key min, max;
        private int maxSize;
        private int minSize;
        private int totalSize;
        private int delCount;
        @SuppressWarnings("unchecked")
        public void maxResize(int newSize) {
            Key[] max = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= maxSize; i++)
                max[i] = maxKeys[i];
            maxKeys = max;
        }
        @SuppressWarnings("unchecked")
        public void minResize(int newSize) {
            Key[] min = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= minSize; i++)
                min[i] = minKeys[i];
            minKeys = min;
        }
        public boolean isEmpty() { return delCount == totalSize; }
        public Key max() { return max; }
        public Key min() { return min; }
        public void insert(Key key) {
            ++totalSize;
            if      (max == null) max = key;
            else if (max.compareTo(key) < 0) max = key;
            if      (min == null) min = key;
            else if (min.compareTo(key) > 0) min = key;
            insertToMaxHeap(key);
            insertToMinHeap(key);
        }
        public void insertToMaxHeap(Key key) {
            if (maxSize == maxKeys.length - 1)
                maxResize(maxSize << 1);
            int k = ++maxSize;
            while (k > 1 && key.compareTo(maxKeys[k >> 1]) > 0) {
                maxKeys[k] = maxKeys[k >> 1];
                k >>= 1;
            }
            maxKeys[k] = key;
        }
        public void insertToMinHeap(Key key) {
            if (minSize == minKeys.length - 1)
                minResize(minSize << 1);
            int k = ++minSize;
            while (k > 1 && key.compareTo(minKeys[k >> 1]) < 0) {
                minKeys[k] = minKeys[k >> 1];
                k >>= 1;
            }
            minKeys[k] = key;
        }
        public Key delMin() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            ++delCount;
            Key min = minKeys[1];
            minKeys[1] = minKeys[minSize--];
            Key top = minKeys[1];
            int k = 1;
            while ((k << 1) <= minSize) {
                int j = k << 1;
                if (minKeys[j].compareTo(minKeys[j + 1]) > 0) j++;
                if (top.compareTo(minKeys[j]) <= 0) break;
                minKeys[k] = minKeys[j];
                k = j;
            }
            minKeys[k] = top;
            minKeys[minSize + 1] = null;
            this.min = minKeys[1];
            if (minSize > 0 && minSize == (minKeys.length - 1) >> 2)
                minResize((minKeys.length - 1) >> 1);
            return min;
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            ++delCount;
            Key max = maxKeys[1];
            maxKeys[1] = maxKeys[maxSize--];
            Key top = maxKeys[1];
            int k = 1;
            while ((k << 1) <= maxSize) {
                int j = k << 1;
                if (maxKeys[j].compareTo(maxKeys[j + 1]) < 0) j++;
                if (top.compareTo(maxKeys[j]) >= 0) break;
                maxKeys[k] = maxKeys[j];
                k = j;
            }
            maxKeys[k] = top;
            maxKeys[maxSize + 1] = null;
            this.max = maxKeys[1];
            if (maxSize > 0 && maxSize == (maxKeys.length - 1) >> 2)
                maxResize((maxKeys.length - 1) >> 1);
            return max;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("最小堆 : ");
            for (int i = 0; i < minKeys.length; i++)
                sb.append(minKeys[i] + " ");
            sb.append("\n");
            sb.append("最大堆 : ");
            for (int i = 0; i < maxKeys.length; i++)
                sb.append(maxKeys[i] + " ");
            sb.append("\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        MaxMinPQ<Integer> pq = new MaxMinPQ<Integer>();
        Integer[] a = Integers(0, 20);
        for (Integer i : a) pq.insert(i);
        
        for (int i = 0; i < 3; i++)
            StdOut.println(pq.delMax());
        StdOut.println("====");
        
        for (int i = 0; i < 5; i++)
            StdOut.println(pq.delMin());
        StdOut.println("====");
        
        for (int i = 0; i < 7; i++)
            StdOut.println(pq.delMax());
        StdOut.println("====");
        
        while (!pq.isEmpty())
            StdOut.println(pq.delMin());
        StdOut.println("====");
    }
    // output
    /*
     *  20
        19
        18
        ====
        0
        1
        2
        3
        4
        ====
        17
        16
        15
        14
        13
        12
        11
        ====
        5
        6
        7
        8
        9
        10
        ====

     */
}

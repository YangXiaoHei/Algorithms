package 第二章_优先队列;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_2_4_22 {
    static class MaxPQ<Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[2];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Key[] newKeys = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= size; i++)
                newKeys[i] = keys[i];
            keys = newKeys;
        }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            keys[++size] = key;
            int k = size;
            while (k > 1 && keys[k >> 1].compareTo(keys[k]) < 0) {
                Key t = keys[k >> 1]; keys[k >> 1] = keys[k]; keys[k] = t;
                k >>= 1;
            }
        }
        public Key delMax() {
            if (isEmpty()) 
                throw new NoSuchElementException("priority queue underflow!");
            Key max = keys[1];
            keys[1] = keys[size--];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j].compareTo(keys[j + 1]) < 0) j++;
                if (keys[k].compareTo(keys[j]) >= 0) break;
                Key t = keys[k]; keys[k] = keys[j]; keys[j] = t;
                k = j;
            }
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1) >> 2)
                resize((keys.length - 1) >> 1);
            return max;
        }
    }
    public static void main(String[] args) {
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        for (int i = 0; i < 100; i++)
            pq.insert(StdRandom.uniform(100));
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
    // output
    /*
     *  99
        99
        99
        97
        97
        93
        93
        93
        91
        91
        89
        89
        88
        85
        84
        83
        83
        82
        82
        82
        81
        81
        79
        77
        77
        76
        75
        73
        73
        72
        70
        70
        69
        67
        66
        66
        65
        65
        65
        64
        62
        62
        60
        60
        59
        58
        57
        56
        56
        56
        54
        54
        52
        52
        52
        48
        48
        47
        46
        45
        45
        44
        42
        39
        39
        38
        37
        34
        34
        33
        33
        33
        33
        30
        30
        30
        26
        25
        25
        24
        22
        22
        21
        18
        16
        16
        15
        15
        10
        8
        8
        7
        7
        4
        3
        2
        2
        2
        2
        1
     */
}

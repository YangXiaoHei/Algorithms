package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_26 {
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
            int k = ++size;
            while (k > 1 && keys[k >> 1].compareTo(key) < 0) {
                keys[k] = keys[k >> 1];
                k >>= 1;
            }
            keys[k] = key;
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key max = keys[1];
            keys[1] = keys[size--];
            Key last = keys[1];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j].compareTo(keys[j + 1]) < 0) j++;
                if (last.compareTo(keys[j]) >= 0) break;
                keys[k] = keys[j];
                k = j;
            }
            keys[k] = last;
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1) >> 2)
                resize((keys.length - 1) >> 1);
            return max;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < keys.length; i++)
                sb.append(keys[i] + " ");
            sb.append("\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        Integer[] a = Integers(0, 100);
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        for (int i = 0; i < a.length; i++)
            pq.insert(a[i]);
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
    // output
    /*
     *  100
        99
        98
        97
        96
        95
        94
        93
        92
        91
        90
        89
        88
        87
        86
        85
        84
        83
        82
        81
        80
        79
        78
        77
        76
        75
        74
        73
        72
        71
        70
        69
        68
        67
        66
        65
        64
        63
        62
        61
        60
        59
        58
        57
        56
        55
        54
        53
        52
        51
        50
        49
        48
        47
        46
        45
        44
        43
        42
        41
        40
        39
        38
        37
        36
        35
        34
        33
        32
        31
        30
        29
        28
        27
        26
        25
        24
        23
        22
        21
        20
        19
        18
        17
        16
        15
        14
        13
        12
        11
        10
        9
        8
        7
        6
        5
        4
        3
        2
        1
        0
     */
}

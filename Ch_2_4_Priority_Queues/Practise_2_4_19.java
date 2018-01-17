package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_19 {
    static class MaxPQ <Key extends Comparable<Key>> {
        private Key[] keys;
        private int size;
        @SuppressWarnings("unchecked")
        public MaxPQ(int N) { keys = (Key[])new Comparable[N + 1]; }
        @SuppressWarnings("unchecked")
        public MaxPQ(Key[] keys) {
            int N = keys.length; size = N;
            this.keys = (Key[])new Comparable[N + 1];
            System.arraycopy(keys, 0, this.keys, 1, N);
            for (int i = N >> 1; i > 0; i--) {
                int k = i;
                while ((k << 1) <= size) {
                    int j = k << 1;
                    if (j < N && this.keys[j].compareTo(this.keys[j + 1]) < 0) j++;
                    if (this.keys[k].compareTo(this.keys[j]) >= 0) break;
                    Key t = this.keys[k]; 
                    this.keys[k] = this.keys[j]; 
                    this.keys[j] = t;
                    k = j;
                }
            }
        }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (size == keys.length - 1)
                throw new IllegalArgumentException();
            keys[++size] = key;
            int k = size;
            while (k > 1 && keys[k >> 1].compareTo(keys[k]) < 0) {
                Key t = keys[k >> 1];
                keys[k >> 1] = keys[k];
                keys[k] = t;
                k >>= 1;
            }
        }
        public Key delMax() {
            Key max = keys[1];
            keys[1] = keys[size--];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j].compareTo(keys[j + 1]) < 0) j++;
                if (keys[k].compareTo(keys[j]) >= 0) break;
                Key t = keys[k];
                keys[k] = keys[j];
                keys[j] = t;
                k = j;
            }
            return max;
        }
    }
    public static void main(String[] args) {
        Integer[] a = Integers(0, 99);
        MaxPQ<Integer> pq = new MaxPQ<Integer>(a);
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
    // output
    /*
     *  99
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

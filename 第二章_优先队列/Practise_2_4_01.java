package 第二章_优先队列;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_01 {
    static class MaxPQ<Key extends Comparable<Key>> {
        private int size;
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[2];
        @SuppressWarnings("unchecked")
        public void resize(int newSize) {
            Key[] newKeys = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= size; i++)
                newKeys[i] = keys[i];
            keys = newKeys;
        }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            keys[++size] = key;
            swim(size);
        }
        public Key delMax() {
             Key max = keys[1];
             exch(1, size--);
             sink(1);
             assert keys[size + 1] == max;
             keys[size + 1] = null;
             if (size > 0 && size == (keys.length - 1) >> 2)
                 resize((keys.length - 1) >> 1);
             return max;
        }
        private void sink(int k) {
            while ((k << 1) <= size) {
                int j = k << 1;
                if (j < size && keys[j].compareTo(keys[j + 1]) < 0) j++;
                if (keys[k].compareTo(keys[j]) >= 0) break;
                exch(k, j);
                k = j;
            }
        }
        private void swim(int k) {
            while (k > 1 && keys[k >>> 1].compareTo(keys[k]) < 0) {
                exch(k >>> 1, k);
                k >>>= 1;
            }
        }
        public void exch(int i, int j) {
            Key t = keys[i]; keys[i] = keys[j]; keys[j] = t;
        }
    }
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        pq.insert("P");
        pq.insert("R");
        pq.insert("I");
        pq.insert("O");
        StdOut.println(pq.delMax());
        pq.insert("R");
        StdOut.println(pq.delMax());
        StdOut.println(pq.delMax());
        pq.insert("I");
        StdOut.println(pq.delMax());
        pq.insert("T");
        StdOut.println(pq.delMax());
        pq.insert("Y");
        StdOut.println(pq.delMax());
        StdOut.println(pq.delMax());
        StdOut.println(pq.delMax());
        pq.insert("Q");
        pq.insert("U");
        pq.insert("E");
        StdOut.println(pq.delMax());
        StdOut.println(pq.delMax());
        StdOut.println(pq.delMax());
        pq.insert("U");
        StdOut.println(pq.delMax());
        pq.insert("E");
    }
    // output
    /*
     *  R
        R
        P
        O
        T
        Y
        I
        I
        U
        Q
        E
        U
     */
}

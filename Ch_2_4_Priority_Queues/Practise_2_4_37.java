package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class Practise_2_4_37 {
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
        int N = 10000000;
        int count = 0;
        MaxPQ<Integer> pq = new MaxPQ<Integer>(N);
        for (int i : ints(0, N - 1)) pq.insert(i);
        
        for (int i = 0; i < 20; i++) {
            Stopwatch timer = new Stopwatch();
            while (timer.elapsedTime() < 1) {
                count++;
                pq.delMax();
            }
            StdOut.printf("%d \n", count);
            int[] d = ints(0, count - 1);
            for (int j : d) pq.insert(j);
            count = 0;
        } 
    }
    // output
    /*
     *  699535 
        686903 
        765378 
        682454 
        727454 
        812187 
        673607 
        712185 
        802836 
        696743 
        635399 
        699192 
        700439 
        803350 
        729895 
        789049 
        756818 
        724726 
        772998 
        916975 

     */
}   

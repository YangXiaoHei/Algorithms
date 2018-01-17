package Ch_2_4_Priority_Queues;

import static Tool.ArrayGenerator.*;
import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_27 {
    static class MaxPQ<Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[2];
        private int size;
        private Key min;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Key[] newKeys = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= size; i++) 
                newKeys[i] = keys[i];
            keys = newKeys;
        }
        public boolean isEmpty() { return size == 0; }
        public Key min() { return min; }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            int k = ++size;
            while (k > 1 && keys[k >> 1].compareTo(key) < 0) {
                keys[k] = keys[k >> 1];
                k >>= 1;
            }
            keys[k] = key;
            if      (min == null) min = key;
            else if (min.compareTo(key) > 0) min = key;
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
            if (size == 0) min = null;
            return max;
        }
    }
    public static void main(String[] args) {
        Integer[] a = Integers(10, 40);
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        for (Integer i : a) pq.insert(i);
        while (!pq.isEmpty()) 
            StdOut.printf("%s  最小值是 %s\n",pq.delMax(), pq.min());  
    }
    // output
    /*
     *  40  最小值是 10
        39  最小值是 10
        38  最小值是 10
        37  最小值是 10
        36  最小值是 10
        35  最小值是 10
        34  最小值是 10
        33  最小值是 10
        32  最小值是 10
        31  最小值是 10
        30  最小值是 10
        29  最小值是 10
        28  最小值是 10
        27  最小值是 10
        26  最小值是 10
        25  最小值是 10
        24  最小值是 10
        23  最小值是 10
        22  最小值是 10
        21  最小值是 10
        20  最小值是 10
        19  最小值是 10
        18  最小值是 10
        17  最小值是 10
        16  最小值是 10
        15  最小值是 10
        14  最小值是 10
        13  最小值是 10
        12  最小值是 10
        11  最小值是 10
        10  最小值是 null
     */
}

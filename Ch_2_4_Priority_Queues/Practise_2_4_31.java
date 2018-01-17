package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_31 {
    public static int[] reverse(int[] a) {
        int i = 0, j = a.length - 1;
        while (i < j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
            ++i; --j;
        }
        return a;
    }
    static class MinPQ<Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[2];
        private int size;
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Key[] keys = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= size; i++)
                keys[i] = this.keys[i];
            this.keys = keys;
        }
        public boolean isEmpty() { return size == 0; }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            keys[++size] = key;
            int k = size;
            ArrayList<Integer> indexs = new ArrayList<Integer>();
            indexs.add(k);
            while ((k >>= 1) >= 1)  indexs.add(k);
            int[] in = reverse(IntegerToInt(indexs.toArray()));
            int lo = 0, hi = in.length - 2;
            while (lo < hi) {
                int mid = (lo + hi) >> 1;
                if (keys[in[mid]].compareTo(key) <= 0)
                    lo = mid + 1;
                else
                    hi = mid;
            }
            if (keys[in[lo]].compareTo(key) <= 0) return;
            for (int i = in.length - 1; i > lo; i--)
                keys[in[i]] = keys[in[i - 1]];
            keys[in[lo]] = key;
        }
        public Key delMin() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key min = keys[1];
            keys[1] = keys[size--];
            Key top = keys[1];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[j].compareTo(keys[j + 1]) > 0) j++;
                if (top.compareTo(keys[j]) <= 0) break;
                keys[k] = keys[j];
                k = j;
            }
            keys[k] = top;
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1) >> 2)
                resize((keys.length - 1) >> 1);
            return min;
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
        int[] a = ints(20, 0, 100);
        print(a);
        MinPQ<Integer> pq = new MinPQ<Integer>();
        for (int i : a) pq.insert(i);
        StdOut.println(pq);
        while (!pq.isEmpty())
            StdOut.println(pq.delMin());
    }
    // output
    /*
     * 
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        58  39  70  5   48  78  86  33  70  13  57  90  15  82  12  51  25  92  100 0   
        null 0 5 12 25 13 70 15 39 70 33 57 90 78 86 82 58 51 92 100 48 null null null null null null null null null null null null 
        
        0
        5
        12
        13
        15
        25
        33
        39
        48
        51
        57
        58
        70
        70
        78
        82
        86
        90
        92
        100
     */
}  

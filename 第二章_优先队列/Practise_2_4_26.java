package 第二章_优先队列;

import java.util.*;
import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

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
            StdOut.printf("k = %d\n", k);
            keys[k] = key;
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key max = keys[1];
            keys[1] = keys[size];
            Key last = keys[size--];
            int k = size;
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
            for (int i = 1; i <= size; i++)
                sb.append(keys[i] + " ");
            sb.append("\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        Integer[] a = Integers(0, 10);
        print(a);
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        for (int i = 0; i < 10; i++)
            pq.insert(StdRandom.uniform(10));
        StdOut.println(pq);
//        while (!pq.isEmpty())
//            StdOut.println(pq.delMax());
    }
}

package 第二章_优先队列;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

import static 第二章_初级排序算法.Text_Array.*;

public class Practise_2_4_33 {
    static class IndexMinPQ<Key extends Comparable<Key>> {
        private Key[] keys;
        private int[] pq;
        private int[] qp;
        private int size;
        @SuppressWarnings("unchecked")
        public IndexMinPQ(int N) {
            keys = (Key[])new Comparable[N];
            pq = new int[N + 1];
            qp = new int[N + 1];
            Arrays.fill(qp, -1);
        }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull() { return size == keys.length; }
        public void insert(Key key) {
            if (size == keys.length)
                throw new IllegalArgumentException("priority queue overflow");
            keys[size++] = key;
            int k = size;
            while (k > 1 && keys[pq[k >> 1]].compareTo(key) > 0) {
                pq[k] = pq[k >> 1];
                qp[pq[k]] = k;
                k >>= 1;
            }
            pq[k] = size - 1;
            qp[pq[k]] = k;
        }
        public Key min() { return keys[pq[1]]; }
        public boolean contains(int k) { return qp[k] != -1; }
        public Key delMin() {
            if (size == 0)
                throw new NoSuchElementException("priority queue underflow");
            Key min = keys[pq[1]];
            int minIndex = pq[1];
            pq[1] = pq[size--];
            int top = pq[1];
            int k = 1;
            while ((k << 1) <= size) {
                int j = k << 1;
                if (keys[pq[j]].compareTo(keys[pq[j + 1]]) > 0) j++;
                if (keys[top].compareTo(keys[pq[j]]) <= 0) break;
                pq[k] = pq[j];
                qp[pq[k]] = k;
                k = j;
            }
            pq[k] = top;
            qp[pq[k]] = k;
            qp[minIndex] = -1;
            return min;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("元素 : ");
            for (int i = 0; i < keys.length; i++)
                sb.append(keys[i] + " ");
            sb.append("\n");
            sb.append("pq : ");
            for (int i = 0; i < pq.length; i++)
                sb.append(pq[i] + " ");
            sb.append("\n");
            sb.append("qp : ");
            for (int i = 0; i < qp.length; i++)
                sb.append(qp[i] + " ");
            sb.append("\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        int N = 30;
        String[] a =  Text_Alphabet.random(N);
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
        for (String i : a) pq.insert(i);
        StdOut.println(pq);
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMin());
        }
    }
    // output
    /*
     *  元素 : U H S Q Y R A Y E J P K H W I O K Y L V E H O J P Y F K N U 
        pq : 0 6 8 26 16 20 1 14 15 18 9 21 23 12 27 5 7 3 17 0 4 19 10 22 2 24 25 11 13 28 29 
        qp : 19 6 24 17 20 15 1 16 2 10 22 27 13 28 7 8 4 18 9 21 5 11 23 12 25 26 3 14 29 30 -1 
        
  
        A
        E
        E
        F
        H
        H
        H
        I
        J
        J
        K
        K
        K
        L
        N
        O
        O
        P
        P
        Q
        R
        S
        U
        U
        V
        W
        Y
        Y
        Y
        Y
     */
}

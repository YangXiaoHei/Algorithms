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
        StdOut.println("\n");
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMin());
        }
    }
    // output
    /*
     *  插入 : 1
        元素 : 1 null null null null null null null null null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 23
        元素 : 1 23 null null null null null null null null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 2 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 14
        元素 : 1 23 14 null null null null null null null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 2 3 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 25
        元素 : 1 23 14 25 null null null null null null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 1 2 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 2 3 4 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 10
        元素 : 1 23 14 25 10 null null null null null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 4 2 3 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 5 3 4 2 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 7
        元素 : 1 23 14 25 10 7 null null null null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 4 5 3 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 5 6 4 2 3 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 11
        元素 : 1 23 14 25 10 7 11 null null null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 4 5 3 1 2 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 5 6 4 2 3 7 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 28
        元素 : 1 23 14 25 10 7 11 28 null null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 4 5 3 1 2 6 7 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 5 6 4 2 3 7 8 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 29
        元素 : 1 23 14 25 10 7 11 28 29 null null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 4 5 3 1 2 6 7 8 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 5 6 4 2 3 7 8 9 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 8
        元素 : 1 23 14 25 10 7 11 28 29 8 null null null null null null null null null null null null null null null null null null null null 
        pq : 0 0 9 5 3 4 2 6 7 8 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 1 10 6 4 5 3 7 8 9 2 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 0
        元素 : 1 23 14 25 10 7 11 28 29 8 0 null null null null null null null null null null null null null null null null null null null 
        pq : 0 10 0 5 3 9 2 6 7 8 1 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 6 4 11 3 7 8 9 5 1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 19
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 null null null null null null null null null null null null null null null null null null 
        pq : 0 10 0 5 3 9 2 6 7 8 1 4 11 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 6 4 11 3 7 8 9 5 1 12 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 6
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 null null null null null null null null null null null null null null null null null 
        pq : 0 10 0 12 3 9 5 6 7 8 1 4 11 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 13 4 11 6 7 8 9 5 1 12 3 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 3
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 null null null null null null null null null null null null null null null null 
        pq : 0 10 0 13 3 9 5 12 7 8 1 4 11 2 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 13 4 11 6 14 8 9 5 1 12 7 3 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 20
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 null null null null null null null null null null null null null null null 
        pq : 0 10 0 13 3 9 5 12 7 8 1 4 11 2 6 14 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 13 4 11 6 14 8 9 5 1 12 7 3 15 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 2
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 null null null null null null null null null null null null null null 
        pq : 0 10 0 13 15 9 5 12 3 8 1 4 11 2 6 14 7 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 13 8 11 6 14 16 9 5 1 12 7 3 15 4 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 15
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 null null null null null null null null null null null null null 
        pq : 0 10 0 13 15 9 5 12 16 8 1 4 11 2 6 14 7 3 0 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 13 17 11 6 14 16 9 5 1 12 7 3 15 4 8 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 16
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 null null null null null null null null null null null null 
        pq : 0 10 0 13 15 9 5 12 16 17 1 4 11 2 6 14 7 3 8 0 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 13 17 11 6 14 16 18 5 1 12 7 3 15 4 8 9 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 22
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 null null null null null null null null null null null 
        pq : 0 10 0 13 15 9 5 12 16 17 1 4 11 2 6 14 7 3 8 18 0 0 0 0 0 0 0 0 0 0 0 
        qp : 2 10 13 17 11 6 14 16 18 5 1 12 7 3 15 4 8 9 19 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 17
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 null null null null null null null null null null 
        pq : 0 10 0 13 15 9 5 12 16 17 19 4 11 2 6 14 7 3 8 18 1 0 0 0 0 0 0 0 0 0 0 
        qp : 2 20 13 17 11 6 14 16 18 5 1 12 7 3 15 4 8 9 19 10 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 18
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 null null null null null null null null null 
        pq : 0 10 0 13 15 9 5 12 16 17 19 4 11 2 6 14 7 3 8 18 1 20 0 0 0 0 0 0 0 0 0 
        qp : 2 20 13 17 11 6 14 16 18 5 1 12 7 3 15 4 8 9 19 10 21 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 4
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 null null null null null null null null 
        pq : 0 10 0 13 15 21 5 12 16 17 19 9 11 2 6 14 7 3 8 18 1 20 4 0 0 0 0 0 0 0 0 
        qp : 2 20 13 17 22 6 14 16 18 11 1 12 7 3 15 4 8 9 19 10 21 5 -1 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 24
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 null null null null null null null 
        pq : 0 10 0 13 15 21 5 12 16 17 19 9 11 2 6 14 7 3 8 18 1 20 4 22 0 0 0 0 0 0 0 
        qp : 2 20 13 17 22 6 14 16 18 11 1 12 7 3 15 4 8 9 19 10 21 5 23 -1 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 9
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 9 null null null null null null 
        pq : 0 10 0 13 15 21 5 12 16 17 19 9 23 2 6 14 7 3 8 18 1 20 4 22 11 0 0 0 0 0 0 
        qp : 2 20 13 17 22 6 14 16 18 11 1 24 7 3 15 4 8 9 19 10 21 5 23 12 -1 -1 -1 -1 -1 -1 -1 
        
        插入 : 5
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 9 5 null null null null null 
        pq : 0 10 0 13 15 21 24 12 16 17 19 9 5 2 6 14 7 3 8 18 1 20 4 22 11 23 0 0 0 0 0 
        qp : 2 20 13 17 22 12 14 16 18 11 1 24 7 3 15 4 8 9 19 10 21 5 23 25 6 -1 -1 -1 -1 -1 -1 
        
        插入 : 26
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 9 5 26 null null null null 
        pq : 0 10 0 13 15 21 24 12 16 17 19 9 5 2 6 14 7 3 8 18 1 20 4 22 11 23 25 0 0 0 0 
        qp : 2 20 13 17 22 12 14 16 18 11 1 24 7 3 15 4 8 9 19 10 21 5 23 25 6 26 -1 -1 -1 -1 -1 
        
        插入 : 13
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 9 5 26 13 null null null 
        pq : 0 10 0 13 15 21 24 12 16 17 19 9 5 26 6 14 7 3 8 18 1 20 4 22 11 23 25 2 0 0 0 
        qp : 2 20 27 17 22 12 14 16 18 11 1 24 7 3 15 4 8 9 19 10 21 5 23 25 6 26 13 -1 -1 -1 -1 
        
        插入 : 21
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 9 5 26 13 21 null null 
        pq : 0 10 0 13 15 21 24 12 16 17 19 9 5 26 6 14 7 3 8 18 1 20 4 22 11 23 25 2 27 0 0 
        qp : 2 20 27 17 22 12 14 16 18 11 1 24 7 3 15 4 8 9 19 10 21 5 23 25 6 26 13 28 -1 -1 -1 
        
        插入 : 27
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 9 5 26 13 21 27 null 
        pq : 0 10 0 13 15 21 24 12 16 17 19 9 5 26 6 14 7 3 8 18 1 20 4 22 11 23 25 2 27 28 0 
        qp : 2 20 27 17 22 12 14 16 18 11 1 24 7 3 15 4 8 9 19 10 21 5 23 25 6 26 13 28 29 -1 -1 
        
        插入 : 12
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 9 5 26 13 21 27 12 
        pq : 0 10 0 13 15 21 24 12 16 17 19 9 5 26 6 29 7 3 8 18 1 20 4 22 11 23 25 2 27 28 14 
        qp : 2 20 27 17 22 12 14 16 18 11 1 24 7 3 30 4 8 9 19 10 21 5 23 25 6 26 13 28 29 15 -1 
        
        元素 : 1 23 14 25 10 7 11 28 29 8 0 19 6 3 20 2 15 16 22 17 18 4 24 9 5 26 13 21 27 12 
        pq : 0 10 0 13 15 21 24 12 16 17 19 9 5 26 6 29 7 3 8 18 1 20 4 22 11 23 25 2 27 28 14 
        qp : 2 20 27 17 22 12 14 16 18 11 1 24 7 3 30 4 8 9 19 10 21 5 23 25 6 26 13 28 29 15 -1 
        
        0
        1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        11
        12
        13
        14
        15
        16
        17
        18
        19
        20
        21
        22
        23
        24
        25
        26
        27
        28
        29
     */
}

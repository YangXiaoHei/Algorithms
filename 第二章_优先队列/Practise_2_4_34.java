package 第二章_优先队列;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import static 第二章_初级排序算法.Text_Array.*;

public class Practise_2_4_34 {
    static class IndexMinPQ<Key extends Comparable<Key>> {
        private Key[] keys;
        private int[] pq;
        private int[] qp;
        private int size;
        public boolean isFull() { return size == keys.length; }
        public boolean isEmpty() { return size == 0; }
        @SuppressWarnings("unchecked")
        public IndexMinPQ (int N) {
            keys = (Key[])new Comparable[N];
            pq = new int[N + 1];
            qp = new int[N + 1];
        }
        public int minIndex() { 
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            return pq[1];
        }
        public boolean contains(int k) { return qp[k] != -1; }
        public void delete(int i) {
            if (!contains(i))
                throw new IllegalArgumentException("index is not in the priority queue");
            // 用末尾元素位置覆盖 i 位置
            pq[qp[i]] = pq[size--];
            
            // 取出 i 在 pq 中的位置
            int k = qp[i];
            
            // 取出 i 的索引
            int exchanged = pq[k];
            
            // 取出 i
            Key key = keys[pq[k]];
            while (k > 1 && key.compareTo(keys[pq[k >> 1]]) < 0) {
                pq[k] = pq[k >> 1];
                qp[pq[k]] = k;
                k >>= 1;
            }
            pq[k] = exchanged;
            qp[pq[k]] = k;
            
            // 取出此时 i 在 pq 中的位置
            k = qp[i];
            
            // 取出 i 的索引
            exchanged = pq[k];
            
            while ((k << 1) <= size) {
                int j = k << 1;
                if (j < size && keys[pq[j]].compareTo(keys[pq[j + 1]]) > 0) j++;
                if (key.compareTo(keys[j]) <= 0) break;
                pq[k] = pq[j];
                qp[pq[k]] = k;
                k = j;
            }
            pq[k] = exchanged;
            qp[pq[k]] = k;
            
            keys[i] = null;
            qp[i] = -1;
        }
        public void change(int i, Key key) {
            if (!contains(i))
                throw new IllegalArgumentException("index is not in the priority queue");
            keys[i] = key;
            int k = qp[i];
            while (k > 1 && key.compareTo(keys[pq[k >> 1]]) < 0) {
                pq[k] = pq[k >> 1];
                qp[pq[k]] = k;
                k >>= 1;
            }
            pq[k] = i;
            qp[pq[k]] = k;
            k = qp[i];
            while ((k << 1) <= size) {
                int j = k << 1;
                if (j < size && keys[pq[j]].compareTo(keys[pq[j + 1]]) > 0) j++;
                if (key.compareTo(keys[pq[j]]) <= 0) break;
                pq[k] = pq[j];
                qp[pq[k]] = k;
                k = j;
            }
            pq[k] = i;
            qp[pq[k]] = k;
        }
        public void insert(Key key) {
            if (isFull())
                throw new IllegalArgumentException("priority queue overflow");
            keys[size++] = key;
            int k = size;
            while (k > 1 && key.compareTo(keys[pq[k >> 1]]) < 0) {
                pq[k] = pq[k >> 1];
                qp[pq[k]] = k;
                k >>= 1;
            }
            pq[k] = size - 1;
            qp[pq[k]] = k;
        }
        public Key delMin() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key min = keys[pq[1]];
            int minIndex = pq[1];
            pq[1] = pq[size--];
            qp[pq[1]] = 1;
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
            
            sb.append("size = " + size);
            sb.append("\n");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        int N = 10;
        String[] s = { "K", "N", "D", "R", "P", "N", "T", "Y", "C", "J" };
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
        for (String i : s) pq.insert(i);
        pq.change(8, "Z");
        pq.change(0, "A");
        pq.change(5, "M");
        pq.delete(4);
        pq.delete(2);
        pq.delete(7);
        StdOut.println(pq);
        while (!pq.isEmpty()) 
            StdOut.println(pq.delMin());
    }
    // output
    /*
     *  元素 : A N null R null M T null Z J 
        pq : 0 0 9 3 1 8 5 6 7 3 8 
        qp : 1 4 -1 3 -1 6 7 -1 5 2 0 size = 7
        
        A
        J
        M
        N
        R
        T
        Z

     */
}

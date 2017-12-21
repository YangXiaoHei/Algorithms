package 第二章_优先队列;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Text_IndexMaxPQ<Key extends Comparable<Key>> {
       private int[] pq;
       private int[] qp;
       private Key[] keys;
       private int size;
       @SuppressWarnings("unchecked")
       Text_IndexMaxPQ(int maxN) {
           pq = new int[maxN + 1];
           qp = new int[maxN + 1];
           keys = (Key[])new Comparable[maxN];
           while (maxN >= 0) qp[maxN--] = -1;
       }
       int size() { return size; }
       boolean isEmpty() { return size == 0; }
       boolean contains(int i) { return keys[i] != null; }
       Key keyOf(int i) {
           if (!contains(i))
               throw new NoSuchElementException("index is not in the priority queue");
           return keys[i]; 
       }
       Key maxKey() { 
           if (isEmpty()) throw new NoSuchElementException("priority queue is empty");
           return keys[pq[1]]; 
       }
       int maxIndex() {
           if (isEmpty()) throw new NoSuchElementException("priority queue is empty");
           return pq[1];
       }
       void insert(int i, Key key) {
           if (contains(i)) throw new IllegalArgumentException("key is already in the priority queue");
           keys[i] = key;
           size++;
           pq[size] = i;
           qp[i] = size;
           swim(size);
       }
       int delMax() {
           int max = pq[1];
           exch(1, size--);
           sink(1);
           assert pq[size + 1] == max;
           pq[size + 1] = -1;
           qp[max] = -1;
           keys[max] = null;
           return max;
       }
       void delete(int k) {
           if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");
           int index = qp[k];
           int tmp = pq[index];
           exch(index, size--);
           swim(index);
           sink(index);
           assert pq[size + 1] == tmp;
           keys[k] = null;
           qp[k] = -1;
           pq[size + 1] = -1; 
       }
       private void swim(int k) {
           while (k > 1 && less(k / 2, k)) {
               exch(k / 2, k);
               k /= 2;
           }
       }
       private void sink(int k) {
           while (2 * k <= size) {
               int j = 2 * k;
               if (j < size && less(j, j + 1)) j++;
               if (!less(k, j)) break;
               exch(k, j);
               k = j;
           }
       }
       private void exch(int i, int j) {
           int t = pq[i];
           pq[i] = pq[j];
           pq[j] = t;
           qp[pq[i]] = i;
           qp[pq[j]] = j;
       }
       private boolean less(int i, int j) {
           return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
       }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Key k : keys)
            sb.append(k + " ");
        sb.append("\n");
        sb.append("pq : ");
        for (int i : pq)
            sb.append(i + " ");
        sb.append("\n");
        sb.append("qp : ");
        for (int i : qp)
            sb.append(i + " ");
        sb.append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] strs = { "F", "E", "I", "C", "G", "B", "H", "J", "D", "A" };
        Text_IndexMaxPQ<String> pq = new Text_IndexMaxPQ<String>(strs.length);
        for (int i = 0; i < strs.length; i++)
            pq.insert(i, strs[i]);
        pq.delete(7);
        pq.delete(9);
        while (!pq.isEmpty())
            StdOut.println(strs[pq.delMax()]);
    }
    // output
    /*
     *  I
        H
        G
        F
        E
        D
        C
        B
     */
}

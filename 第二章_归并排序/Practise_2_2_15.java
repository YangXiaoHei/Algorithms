package 第二章_归并排序;

import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;

public class Practise_2_2_15 {
    static class Queue<T extends Comparable<T>> {
        class Node {
            T item;
            Node next, prev;
            Node() {}
            Node(T item, Node prev, Node next) { 
                this.item = item; 
                this.next = next; 
                this.prev = prev;
           }
           Node insertBefore(T item) {
               Node node = new Node(item, prev, this);
               if (prev != null)
                   prev.next = node;
               prev = node;
               return node;
           }
           Node insertAfter(T item) {
               Node node = new Node(item, this, next);
               if (next != null)
                   next.prev = node;
               next = node;
               return node;
           }
           T delete() {
               T del = item;
               if (prev != null)
                   prev.next = next;
               if (next != null)
                   next.prev = prev;
               return del;
           }
        }
        private Node header = new Node();
        private Node tailer = new Node();
        private int size;
        {
            header.next = tailer;
            tailer.prev = header;
            header.prev = tailer.next = null;
        }
        void enqueue(T item) {
            ++size;
            tailer.insertBefore(item);
        }
        boolean isEmpty() { return size == 0; }
        T peek() {
            if (isEmpty()) return null;
            return header.next.item;
        }
        T dequeue() {
            if (isEmpty())
                throw new RuntimeException("dequeue from a empty queue!");
            --size;
            return header.next.delete();
        }
        void print() {
            Node cur = header.next;
            StdOut.print(cur.item + " ");
            while ((cur = cur.next) != tailer)
                StdOut.print(cur.item + " ");
            StdOut.println();
        }
        void sort() {
            Node cur = null, prev = null;
            while ((cur = inversion()) != null) {
                prev = cur;
                while ((prev = prev.prev) != header &&
                        prev.item.compareTo(cur.item) > 0);
                prev.insertAfter(cur.delete());
            }
        }
        private Node inversion() {
            if (isEmpty()) 
                throw new RuntimeException("empty queue not mean sorted!");
            if (size == 1) return null;
            Node cur = header.next, prev = header.next;
            while ((cur = cur.next) != tailer)
                if (cur.item.compareTo(prev.item) < 0) return cur;
                else                                   prev = cur;
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public static Queue<Integer>[] queues(int N) {
        Queue<Integer>[] qs = (Queue<Integer>[])new Queue[N];
        for (int i = 0; i < N; i++) {
            Queue<Integer> q = new Queue<Integer>();
            q.enqueue(StdRandom.uniform(10));
            qs[i] = q;
        }
        return qs;    
    }
    public static <T extends Comparable<T>> Queue<T> merge(Queue<T> q1, Queue<T> q2) {
        Queue<T> q = new Queue<T>();
        while (q1.isEmpty() || q2.isEmpty()) 
            if      (q1.isEmpty())                       q.enqueue(q2.dequeue());
            else if (q2.isEmpty())                       q.enqueue(q1.dequeue());
            else if (q1.peek().compareTo(q2.peek()) < 0) q.enqueue(q1.dequeue());
            else                                         q.enqueue(q2.dequeue());
        return q;
    }
    public static void main(String[] args) {
       
    }
}

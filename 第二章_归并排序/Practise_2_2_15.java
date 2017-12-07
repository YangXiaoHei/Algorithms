package 第二章_归并排序;

import edu.princeton.cs.algs4.*;

public class Practise_2_2_15 {
    // 这里不能写成 <T extends Comparable<T>>，因为要用这个 Queue 装 Queue
    // 总感觉这样很别扭，下面使用到 sort 时直接强转成 Comparable 类型了...
    static class Queue<T> {
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
        int size() { return size; }
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
            if (isEmpty()) StdOut.println("empty");
            Node cur = header.next;
            StdOut.print(cur.item + " ");
            while ((cur = cur.next) != tailer)
                StdOut.print(cur.item + " ");
            StdOut.println();
        }
        @SuppressWarnings("unchecked")
        void sort() {
            Node cur = null, prev = null;
            while ((cur = inversion()) != null) {
                prev = cur;
                while ((prev = prev.prev) != header &&
                        ((Comparable<T>)prev.item).compareTo(cur.item) > 0);
                prev.insertAfter(cur.delete());
            }
        }
        @SuppressWarnings("unchecked")
        private Node inversion() {
            if (isEmpty()) 
                throw new RuntimeException("empty queue not mean sorted!");
            if (size == 1) return null;
            Node cur = header.next, prev = header.next;
            while ((cur = cur.next) != tailer)
                if (((Comparable<T>)cur.item).compareTo(prev.item) < 0) 
                    return cur;
                else                                   
                    prev = cur;
            return null;
        }
    }
    public static Queue<Queue<Integer>> queues(int N) { 
        Queue<Queue<Integer>> queue = new Queue<Queue<Integer>>();
        while (N-- > 0) {
            Queue<Integer> q = new Queue<Integer>();
            q.enqueue(StdRandom.uniform(100));
            queue.enqueue(q);
        }
        return queue;
    }
    public static <T extends Comparable<T>> Queue<T> merge(Queue<T> q1, Queue<T> q2) {
        Queue<T> q = new Queue<T>();
        while (!q1.isEmpty() || !q2.isEmpty()) 
            if      (q1.isEmpty())                       q.enqueue(q2.dequeue());
            else if (q2.isEmpty())                       q.enqueue(q1.dequeue());
            else if (q1.peek().compareTo(q2.peek()) < 0) q.enqueue(q1.dequeue());
            else                                         q.enqueue(q2.dequeue());
        return q;
    }
    public static <T extends Comparable<T>> void mergeAll(Queue<Queue<T>> q) {
        while (q.size() > 1)
            q.enqueue(merge(q.dequeue(), q.dequeue()));
    }
    public static void main(String[] args) {
       Queue<Queue<Integer>> q = queues(30);
       mergeAll(q);
       q.dequeue().print();
    }
    // output
    /*
     * 0 8 12 17 21 24 31 33 41 45 45 47 47 47 51 61 66 70 73 74 75 75 75 79 81 81 82 82 85 94 
     */
}

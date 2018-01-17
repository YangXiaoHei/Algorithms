package Ch_2_2_Mergesort;

import edu.princeton.cs.algs4.StdOut;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.util.concurrent.TimeUnit;

public class Practise_2_2_14 {
    static class Queue {
        private static int counter = 0;
        private final int id = counter++;
        private int[] items = new int[1];
        private int head, tail, size;
        void resize(int newSize) {
            int[] newItems = new int[newSize];
            int cur = head, k = 0;
            do {
                newItems[k++] = items[cur];
                cur = (cur + 1) % items.length;
            } while (cur != tail);
            head = 0;
            tail = size;
            items = newItems;
        }
        int peek() { 
            if (isEmpty()) return 0;
            return items[head];
        }
        boolean isEmpty() { return size == 0; }
        void enqueue(int item) {
            if (size == items.length)
                resize(size * 2);
            ++size;
            items[tail] = item;
            tail = (tail + 1) % items.length;
        }
        int dequeue() {
            if (isEmpty())
                throw new RuntimeException("dequeue from a empty queue!");
            int del = items[head];
            items[head] = 0;
            head = (head + 1) % items.length;
            --size;
            if (size > 0 && size == items.length / 4)
                resize(items.length / 2);
            return del;
        }
        /*
         * 出队排列
         */
        private int[] topTwo() {
            int top = items[head];
            int topNext = items[(head + 1) % items.length];
            return new int[] { top, topNext };
        }
        private void makeTopEnqueueAgain() { enqueue(dequeue()); }
        private void exchangeTopTwo() {
            int i = head, j = (head + 1) % items.length;
            int t = items[i];
            items[i] = items[j];
            items[j] = t;
        }
        private boolean isSorted() {
            if (isEmpty()) return false;
            if (size < 2)  return true;
            int cur = head, next = 0;
            while ((next = (cur + 1) % items.length) != tail)
                if (items[cur] > items[next]) return false;
                else                          cur = next;
            return true;
        } 
        void dequeueSort() {
            while (!isSorted()) {
               int k = size;
                while (--k > 0) {
                    if (topTwo()[0] > topTwo()[1])
                        exchangeTopTwo();
                    makeTopEnqueueAgain();
                }
                makeTopEnqueueAgain();
            }
        }
        void print() {
            if (isEmpty()) StdOut.printf("[Queue %d]  Empty", id);
            int cur = head;
            StdOut.printf("[Queue %d]  ", id);
            StdOut.print(items[cur] + " ");
            while ((cur = (cur + 1) % items.length) != tail)
                StdOut.print(items[cur] + " ");
            StdOut.println();
        }
        public String toString() {
            if (isEmpty()) return "Empty";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < items.length; i++)
                sb.append(String.format("%4s", items[i] == 0 ? "-" : items[i] + ""));
            sb.append(String.format("              head = %d tail = %d size = %d", head, tail, size));
            return sb.toString();
        }
    }
    public static Queue mergeTwoSortedQueue(Queue q1, Queue q2) {
        if (!q1.isSorted() || !q2.isSorted())
            throw new IllegalArgumentException("q1 or q2 is not in order!");
        Queue newQueue = new Queue();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if      (q1.isEmpty())          newQueue.enqueue(q2.dequeue());
            else if (q2.isEmpty())          newQueue.enqueue(q1.dequeue());
            else if (q1.peek() < q2.peek()) newQueue.enqueue(q1.dequeue());
            else                            newQueue.enqueue(q2.dequeue());
        }
        return newQueue;
    }
    public static Queue randomQueue(int lo, int hi) {
        Queue queue = new Queue();
        for (int i : ints(lo, hi))
            queue.enqueue(i);  
        return queue;
    }
    public static void main(String[] args) {
        Queue q1 = randomQueue(3, 8);
        Queue q2 = randomQueue(10, 20);
        q1.print();
        q2.print();
        q1.dequeueSort();
        q2.dequeueSort();
        StdOut.println("\n======== 归并 q1 和 q2 ==========");
        Queue mergedQueue = mergeTwoSortedQueue(q1, q2);
        mergedQueue.print();
    }
    // output
    /*
     *  [Queue 0]  6 8 5 7 3 4 
        [Queue 1]  18 14 15 20 17 16 19 12 10 11 13 
        
        ======== 归并 q1 和 q2 ==========
        [Queue 2]  3 4 5 6 7 8 10 11 12 13 14 15 16 17 18 19 20 

     */
}

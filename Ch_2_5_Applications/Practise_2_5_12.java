package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_12 {
    static class Task {
        private static int count = 0;
        private final int id = count++;
        private double time;
        public Task() {}
        public Task(double time) { this.time = time; }
        public String toString() { return String.format("(Task-%d  %.2f)", id, time); }
        public static Task[] random(int N) {
            Task[] t = new Task[N]; int i = 0;
            while (N-- > 0) t[i++] = new Task(StdRandom.uniform(10, 10000));
            return t;
        }
        public static Comparator<Task> accordingTime() {
            return new Comparator<Task>() {
                public int compare(Task a, Task b) {
                    return a.time < b.time ? -1 : a.time > b.time ? 1 : 0;
                }
            };
        }
    }
    static class MinPQ <Key> {
        private Comparator<Key> c;
        private int size;
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Object[2];
        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            Key[] keys = (Key[])new Object[newSize + 1];
            for (int i = 1; i <= size; i++)
                keys[i] = this.keys[i];
            this.keys = keys;
        }
        public boolean isEmpty() { return size == 0; }
        public MinPQ(Comparator<Key> c) {
            this.c = c;
        }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size << 1);
            keys[++size] = key;
            swim(size);
        }
        public Key delMin() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Key min = keys[1];
            keys[1] = keys[size--];
            sink(1);
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1) >> 2)
                resize((keys.length - 1) >> 1);
            return min;
        }
        private void swim(int k) {
            Key key = keys[k];
            while (k > 1 && c.compare(key, keys[k >> 1]) < 0) {
                keys[k] = keys[k >> 1];
                k >>= 1;
            }
            keys[k] = key;
        }
        private void sink(int k) {
            Key key = keys[k]; int j;
            while ((j = k << 1) <= size) {
                if (c.compare(keys[j], keys[j + 1]) > 0) j++;
                if (c.compare(key, keys[j]) <= 0) break;
                keys[k] = keys[j];
                k = j;
            }
            keys[k] = key;
        }
    }
    public static void correctTest() {
        int[] i = ints(0, 100);
        MinPQ<Integer> pq = new MinPQ<Integer>(Comparator.naturalOrder());
        for (Integer d : i) pq.insert(d);
        while (!pq.isEmpty())
            StdOut.println(pq.delMin());
    }
    public static void main(String[] args) {
        int M = 20;
        MinPQ<Task> pq = new MinPQ<Task>(Task.accordingTime());
        for (Task k : Task.random(1000)) 
            pq.insert(k);
        while (M-- > 0) // 从 N 个任务中调度耗时最短的前 M 个优先执行
            StdOut.println(pq.delMin());
    }
    // output
    /*
     *  (Task-42  39.00)
        (Task-716  66.00)
        (Task-545  81.00)
        (Task-712  84.00)
        (Task-277  86.00)
        (Task-638  107.00)
        (Task-603  110.00)
        (Task-173  120.00)
        (Task-619  124.00)
        (Task-132  125.00)
        (Task-465  133.00)
        (Task-322  135.00)
        (Task-688  147.00)
        (Task-754  153.00)
        (Task-590  170.00)
        (Task-982  195.00)
        (Task-898  203.00)
        (Task-390  208.00)
        (Task-350  209.00)
        (Task-344  219.00)
     */
}

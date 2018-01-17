package Ch_2_5_Applications;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_2_5_13 {
    static class Processor {
        private static int count = 0;
        private final int id = count++;
        private double totalTime;
        public void addTask(Task t) { totalTime += t.getTime(); }
        public String toString() { return String.format("(Processor-%d  %.2f)", id, totalTime); }
        public static Comparator<Processor> ascendOrder() {
            return new Comparator<Processor>() {
                public int compare(Processor a, Processor b) {
                    return a.totalTime < b.totalTime ? -1 : a.totalTime > b.totalTime ? 1 : 0;
                }
            };
        }
    }
    static class Task {
        private static int count = 0;
        private final int id = count++;
        private double time;
        public Task() {}
        public Task(double time) { this.time = time; }
        public String toString() { return String.format("(Task-%d  %.2f)", id, time); }
        public double getTime() { return time; }
        public static Task[] random(int N, double lo, double hi) {
            Task[] t = new Task[N]; int i = 0;
            while (N-- > 0) t[i++] = new Task(StdRandom.uniform(lo, hi));
            return t;
        }
        public static Comparator<Task> ascendOrder() {
            return new Comparator<Task>() {
                public int compare(Task a, Task b) {
                    return a.time < b.time ? -1 : a.time > b.time ? 1 : 0;
                }
            };
        }
        public static Comparator<Task> descendOrder() {
            return new Comparator<Task>() {
                public int compare(Task a, Task b) {
                    return a.time > b.time ? -1 : a.time < b.time ? 1 : 0;
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
    public static void quick(Object[] a, Comparator c) {
        quick(a, c, 0, a.length - 1);
    }
    private static void quick(Object[] a, Comparator c, int lo, int hi) {
        if (hi - lo + 1 < 8) {
            for (int i = lo; i <= hi; i++) {
                Object t = a[i]; int j;
                for (j = i - 1; j >= lo && c.compare(t, a[j]) < 0; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >> 1;
        if (c.compare(a[mid], a[lo]) < 0) exch(a, lo, mid);
        if (c.compare(a[hi], a[lo]) < 0) exch(a, hi, lo);
        if (c.compare(a[mid], a[hi]) < 0) exch(a, mid, hi);
        Object pivot = a[hi]; int i = lo - 1, j = hi, p = lo - 1, q = hi;
        while (true) {
            while (c.compare(a[++i], pivot) < 0);
            while (c.compare(a[--j], pivot) > 0);
            if (i >= j) break;
            exch(a, i, j);
            if (c.compare(a[i], pivot) == 0) exch(a, i, ++p);
            if (c.compare(a[j], pivot) == 0) exch(a, j, --q);
        }
        exch(a, i, hi);
        int lt = i - 1, gt = i + 1, m = lo, n = hi - 1;
        while (m <= p) exch(a, m++, lt--);
        while (n >= q) exch(a, n--, gt++);
        quick(a, c, lo, lt);
        quick(a, c, gt, hi);
    }
    private static void exch(Object[] a, int i, int j) {
        Object t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        
        // 创建 N 个任务，并按耗时降序排列
        int N = 30;
        Task[] t = Task.random(N, 10, 500);
        quick(t, Task.descendOrder());
        
        // 创建由堆负责调度的 M 台处理器
        int M = 5;
        MinPQ<Processor> pq = new MinPQ<Processor>(Processor.ascendOrder());
        while (M-- > 0)
            pq.insert(new Processor());
        
        // 开始执行任务
        int i = 0;
        while (N-- > 0) {
            Processor min = pq.delMin(); // 把当前最耗时的任务分派给最闲的那台控制器
            min.addTask(t[i++]);
            pq.insert(min); // 重新安排调度顺序
            StdOut.printf("让 %s 执行任务 %s\n", min, t[i - 1]);
            try {
                TimeUnit.SECONDS.sleep(1);  
            } catch (Exception e) {};
        }
        StdOut.println("任务执行完毕!");
    }
    // output
    /*
     *  让 (Processor-0  495.00) 执行任务 (Task-6  495.00)
        让 (Processor-4  492.41) 执行任务 (Task-26  492.41)
        让 (Processor-1  476.81) 执行任务 (Task-2  476.81)
        让 (Processor-3  463.71) 执行任务 (Task-17  463.71)
        让 (Processor-2  416.54) 执行任务 (Task-22  416.54)
        让 (Processor-2  831.66) 执行任务 (Task-21  415.12)
        让 (Processor-3  873.22) 执行任务 (Task-18  409.51)
        让 (Processor-1  884.52) 执行任务 (Task-19  407.70)
        让 (Processor-4  876.33) 执行任务 (Task-3  383.92)
        让 (Processor-0  869.41) 执行任务 (Task-1  374.41)
        让 (Processor-2  1201.83) 执行任务 (Task-29  370.16)
        让 (Processor-0  1233.75) 执行任务 (Task-11  364.34)
        让 (Processor-3  1228.47) 执行任务 (Task-4  355.25)
        让 (Processor-4  1198.99) 执行任务 (Task-8  322.66)
        让 (Processor-1  1205.97) 执行任务 (Task-13  321.46)
        让 (Processor-4  1480.43) 执行任务 (Task-23  281.44)
        让 (Processor-2  1481.62) 执行任务 (Task-14  279.79)
        让 (Processor-1  1480.17) 执行任务 (Task-28  274.20)
        让 (Processor-3  1501.81) 执行任务 (Task-7  273.34)
        让 (Processor-0  1483.01) 执行任务 (Task-0  249.26)
        让 (Processor-1  1726.16) 执行任务 (Task-9  245.99)
        让 (Processor-4  1713.12) 执行任务 (Task-15  232.69)
        让 (Processor-2  1705.79) 执行任务 (Task-5  224.17)
        让 (Processor-0  1673.26) 执行任务 (Task-12  190.25)
        让 (Processor-3  1664.86) 执行任务 (Task-16  163.04)
        让 (Processor-3  1819.15) 执行任务 (Task-25  154.30)
        让 (Processor-0  1790.03) 执行任务 (Task-20  116.77)
        让 (Processor-2  1815.47) 执行任务 (Task-27  109.68)
        让 (Processor-4  1763.93) 执行任务 (Task-10  50.81)
        让 (Processor-1  1771.08) 执行任务 (Task-24  44.92)
        任务执行完毕!
     */
}  

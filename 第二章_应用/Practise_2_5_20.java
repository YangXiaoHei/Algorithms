package 第二章_应用;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_20 {
    static class Task {
        private static int count = 0;
        private final int id = count++;
        private final String start;
        private final String end;
        private final long startIntervals;
        private final long endIntervals;
        private final long intervals;
        public Task(String s, String e) {
            if (!s.matches("\\d+\\:\\d+") || !e.matches("\\d+\\:\\d+"))
                throw new IllegalArgumentException("非法的时间格式");
            start = s;
            end = e;
            String[] sc = s.split("\\:");
            String[] ec = e.split("\\:");
            int sch = Integer.parseInt(sc[0]); // 起始时
            int scm = Integer.parseInt(sc[1]); // 起始分
            int ech = Integer.parseInt(ec[0]); // 结束时
            int ecm = Integer.parseInt(ec[1]); // 结束分
            if (sch < 0 || sch >= 24 || // 0 ~ 23
                scm < 0 || scm >= 60 || // 0 ~ 59
                ech < 0 || ech >= 24 || // 0 ~ 23
                ecm < 0 || ecm >= 60)   // 0 ~ 59
                throw new IllegalArgumentException("时间格式错误，长点心啊大兄弟");
            startIntervals = sch * 60 + scm;
            endIntervals = ech * 60 + ecm;
            intervals = endIntervals - startIntervals;
            if (intervals <= 0)
                throw new IllegalArgumentException("起始时间不能大于或等于结束时间");
        }
        public String toString() { return String.format("Task-%4d \t%5s ~ %5s", id, start, end); }
        /*
         * 主键比较器
         */
        public static Comparator<Task> mainKey() {
            return new Comparator<Task>() {
                public int compare(Task t1, Task t2) {
                    return t1.startIntervals < t2.startIntervals ? -1 :
                           t1.startIntervals > t2.startIntervals ? 1 : 0;
                }
            };
        }
        /*
         * 次主键比较器
         */
        public static Comparator<Task> secondaryKey() {
            return new Comparator<Task>() {
                public int compare(Task t1, Task t2) {
                    return t1.intervals < t2.intervals ? -1 :
                           t1.intervals > t2.intervals ? 1 : 0;
                }
            };
        }
    }
    public static void quick(Task[] a) { quick(a, 0, a.length - 1); }
    public static void quick(Task[] a, int lo, int hi) {
        if (hi - lo + 1 < 8) {
            for (int i = lo; i <= hi; i++) {
                Task t = a[i]; int j;
                for (j = i - 1; j >= lo && less(t, a[j]); j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >> 1;
        if (less(a, mid, lo)) exch(a, mid, lo);
        if (less(a, hi, lo))  exch(a, lo, hi);
        if (less(a, mid, hi)) exch(a, mid, hi);
        Task t = a[hi]; int i = lo - 1, j = hi, p = lo - 1, q = hi;
        while (true) {
            while (less(a[++i], t));
            while (less(t, a[--j]));
            if (i >= j) break;
            exch(a, i, j);
            if (equal(a[i], t)) exch(a, i, ++p);
            if (equal(a[j], t)) exch(a, j, --q);
        }
        exch(a, i, hi);
        int lt = i - 1, gt = i + 1, m = lo, n = hi - 1;
        while (m <= p) exch(a, m++, lt--);
        while (n >= q) exch(a, n--, gt++);
        quick(a, lo, lt);
        quick(a, gt, hi);
    }
    public static boolean less(Task[] a, int i, int j) { return compare(a[i], a[j]) < 0; }
    public static boolean less(Task a, Task b) { return compare(a, b) < 0; }
    public static boolean equal(Task a, Task b) { return compare(a, b) == 0; }
    public static void exch(Task[] a, int i, int j) { Task t = a[i]; a[i] = a[j]; a[j] = t; }
    /*
     * 保证稳定性
     */
    public static int compare(Task a, Task b) {
        if      (Task.mainKey().compare(a, b) < 0) return -1; // 主键
        else if (Task.mainKey().compare(a, b) > 0) return 1;
        else if (Task.secondaryKey().compare(a, b) < 0) return -1; // 次主键
        else if (Task.secondaryKey().compare(a, b) > 0) return 1;
        else    return 0;
    }
    public static void correctTest() {
        Task[] ts = {
                new Task("11:30", "11:55"),
                new Task("11:00", "13:00"),
                new Task("12:05", "12:50"),
                new Task("12:30", "14:20"),
                new Task("12:12", "13:50"),
                new Task("8:20", "9:40"),
                new Task("8:10", "8:30"),
                new Task("9:00", "9:27"),
                new Task("9:00", "9:26"),
                new Task("9:10", "9:20"),
                new Task("8:10", "8:20"),
                new Task("8:20", "8:25"),
                new Task("8:20", "9:10"),
                new Task("8:40", "10:00"),
                new Task("8:30", "9:45"),
                new Task("9:00", "9:29"),
                new Task("9:00", "9:28"),
                new Task("10:10", "13:40"),
        };
        quick(ts);
        for (Task t : ts) StdOut.println(t);
    }
    public static void findMaxIdleAndMaxBusy(Task[] ts) {
        quick(ts);
        for (Task t : ts) StdOut.println(t);
        long maxBusy = ts[0].intervals;
        Task pre = ts[0], cur;
        /*
         * 并行运行任务模拟图
         *                          任务
         *      ------------------------------------------
         *      👆                                       👆
         *      startIntervals                 endIntervals
         *                        intervals
         * 
         * 开始运行....
         * 
         * ---
         *   ----
         *    ---------
         *     ----
         *       ----
         *               -----
         *               -------
         *               ---------
         *               -----
         *                  ----
         *                     --------------
         *                              ----
         *                              --
         *                               ------
         */                                 
        for (int i = 1; i < ts.length; i++) {
            cur = ts[i];
            if (cur.startIntervals <= pre.endIntervals) {
                if (cur.endIntervals - pre.startIntervals > maxBusy)
                    maxBusy = cur.endIntervals - pre.startIntervals;
            } else {
                maxBusy = cur.intervals;
                pre = cur;
            }
        }
        long maxIdle = 0, tmp = ts[0].endIntervals;
        for (int i = 1; i < ts.length; i++) {
            cur = ts[i];
            if (cur.startIntervals - tmp > maxIdle)
                maxIdle = cur.startIntervals - tmp;
            if (cur.endIntervals > tmp)
                tmp = cur.endIntervals;
        }
        StdOut.printf("最长繁忙时间为 : %d 分钟\n最长空闲时间为 : %d 分钟\n", maxBusy, maxIdle);
    }
    public static void main(String[] args) {
        findMaxIdleAndMaxBusy(new Task[] {
                new Task("11:30", "11:55"),
                new Task("11:00", "13:00"),
                new Task("12:05", "12:50"),
                new Task("12:30", "14:20"),
                new Task("12:12", "13:50"),
                new Task("8:20", "9:40"),
                new Task("8:10", "8:30"),
                new Task("9:00", "9:27"),
                new Task("9:00", "9:26"),
                new Task("9:10", "9:20"),
                new Task("8:10", "8:20"),
                new Task("8:20", "8:25"),
                new Task("8:20", "9:10"),
                new Task("8:40", "10:00"),
                new Task("8:30", "9:45"),
                new Task("9:00", "9:29"),
                new Task("9:00", "9:28"),
                new Task("10:10", "13:40"),
        });
    }
    // output
    /*
     *  Task-  10    8:10 ~  8:20
        Task-   6    8:10 ~  8:30
        Task-  11    8:20 ~  8:25
        Task-  12    8:20 ~  9:10
        Task-   5    8:20 ~  9:40
        Task-  14    8:30 ~  9:45
        Task-  13    8:40 ~ 10:00
        Task-   8    9:00 ~  9:26
        Task-   7    9:00 ~  9:27
        Task-  16    9:00 ~  9:28
        Task-  15    9:00 ~  9:29
        Task-   9    9:10 ~  9:20
        Task-  17   10:10 ~ 13:40
        Task-   1   11:00 ~ 13:00
        Task-   0   11:30 ~ 11:55
        Task-   2   12:05 ~ 12:50
        Task-   4   12:12 ~ 13:50
        Task-   3   12:30 ~ 14:20
        最长繁忙时间为 : 250 分钟
        最长空闲时间为 : 10 分钟

     */
}

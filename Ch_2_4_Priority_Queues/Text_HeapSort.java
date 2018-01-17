package Ch_2_4_Priority_Queues;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Text_HeapSort {
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 20) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >>> 1;
        if (a[mid] < a[lo]) { int t = a[mid]; a[mid] = a[lo]; a[lo] = t; }
        if (a[hi] < a[lo])  { int t = a[hi];  a[hi] = a[lo];  a[lo] = t; }
        if (a[mid] < a[hi]) { int t = a[mid]; a[mid] = a[hi]; a[hi] = t; }
        int p = lo - 1, i = lo - 1, q = hi, j = hi, v = a[hi];
        while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            { int t = a[i]; a[i] = a[j]; a[j] = t; }
            if (a[i] == v) { ++p; int t = a[i]; a[i] = a[p]; a[p] = t; }
            if (a[j] == v) { --q; int t = a[j]; a[j] = a[q]; a[q] = t; }
        }
        { int t = a[hi]; a[hi] = a[i]; a[i] = t; }
        int lt = i - 1, gt = i + 1, k = lo, m = hi - 1;
        while (k <= p) { int t = a[k]; a[k] = a[lt]; a[lt] = t; --lt; ++k; }
        while (m >= q) { int t = a[m]; a[m] = a[gt]; a[gt] = t; ++gt; --m; }
        quick(a, lo, lt);
        quick(a, gt, hi);
    }
    public static double heap(int[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for (int i = N >>> 1; i > 0; i--) 
            sink(a, i, N);
        while (N > 1) {
            int t = a[0];
            a[0] = a[N - 1];
            a[N - 1] = t;
            sink(a, 1, --N);
        }
        return timer.elapsedTime();
    }
    private static final void sink(int[] a, int k, int n) {
        while ((k << 1) <= n) {
            int j = k << 1;
            if (j < n && a[j - 1] < a[j]) j++;
            if (a[k - 1] >= a[j - 1]) break;
            int t = a[j - 1];
            a[j - 1] = a[k - 1];
            a[k - 1] = t;
            k = j;
        }
    }
    public static void main(String[] args) {
        int[] a = ints(0, 4000000);
        int[] copy = intsCopy(a);
        StdOut.printf("堆排序 : %.3f\n",heap(a));
        StdOut.printf("快速排序 : %.3f\n",quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    // output
    /*
     *  堆排序 : 2.608
        快速排序 : 1.042
     */
}

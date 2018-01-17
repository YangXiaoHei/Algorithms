package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_16 {
    public static int[] best(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = i;
        best(a, 0, N - 1);
        return a;
    }
    private static void best(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >> 1;
        best(a, lo, mid - 1);
        best(a, mid + 1, hi);
        exch(a, lo, mid);
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    public static void main(String[] args) {
        int N = 10000000;
        int[] average = ints(0, N - 1);
        int[] best = best(N);
        StdOut.printf("平均情况 : %.3f\n", quick(average));
        StdOut.printf("最佳情况 : %.3f\n", quick(best));
    }
    // output
    /*
     *  平均情况 : 2.321
        最佳情况 : 0.846
     */
}

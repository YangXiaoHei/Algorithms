package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_3_17 {
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        int max = a[0], index = 0, N = a.length;
        for (int i = 0; i < N; i++)
            if (a[i] > max) { max = a[i]; index = i; }
        exch(a, N - 1, index);
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double quickNormal(int[] a) {
        Stopwatch timer = new Stopwatch();
        quickNormal(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quickNormal(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        quickNormal(a, lo, j - 1);
        quickNormal(a, j + 1, hi);
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = ints(0, 10000000);
        int[] copy = intsCopy(a);
        StdOut.printf("去掉边界判断 ： %.3f\n", quick(a));
        StdOut.printf("教材的代码 ： %.3f\n", quickNormal(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    // output
    /*
     *  去掉边界判断 ： 2.450
        教材的代码 ： 2.925
        
        去掉边界判断 ： 2.339
        教材的代码 ： 2.273
        
        去掉边界判断 ： 2.271
        教材的代码 ： 2.302

     */
}

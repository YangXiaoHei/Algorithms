package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_07 {
    private static int size0;
    private static int size1;
    private static int size2;
    public static void quick(int[] a) {
        size0 = size1 = size2 = 0;
        quick(a, 0, a.length - 1);
    }
    private static void quick(int[] a, int lo, int hi) {
        int size = hi - lo + 1;
        if (size == 0) size0++;
        if (size == 1) size1++;
        if (size == 2) size2++;
        if (size <= 1) return;
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static int parition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (j > lo && a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int T = 1000, i = T;
        int s0 = 0, s1 = 0, s2 = 0;
        int N = 10000;
        while (i-- > 0) {
            int[] a = ints(0, N - 1);
            quick(a);
            s0 += size0;
            s1 += size1;
            s2 += size2;
        }
        
        StdOut.printf("尺寸为 0 的子数组有 %d 个, 占数组长度的 %.1f%%\n", s0 /= T, (s0 * 100.0) / N);
        StdOut.printf("尺寸为 1 的子数组有 %d 个, 占数组长度的 %.1f%%\n", s1 /= T, (s1 * 100.0) / N);
        StdOut.printf("尺寸为 2 的子数组有 %d 个, 占数组长度的 %.1f%%\n", s2 /= T, (s2 * 100.0) / N);
    }
    // output
    /*
     *  尺寸为 0 的子数组有 3332 个, 占数组长度的 33.3%
        尺寸为 1 的子数组有 3334 个, 占数组长度的 33.3%
        尺寸为 2 的子数组有 1667 个, 占数组长度的 16.7%

     */
}

package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_06 {
    public static int compares;
    public static int quick(int[] a) {
        compares = 0;
        quick(a, 0, a.length - 1);
        return compares;
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static int parition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && compare(a[++i], v) < 0);
            while (j > lo && compare(a[--j], v) > 0);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }
    private static int compare(int i, int j) {
        compares++;
        return i < j ? -1 : i > j ? 1 : 0;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = ints(0, 100);
        quick(a);
        StdOut.printf("精确值 : %d  理论值 : %.0f\n", compares, 2 * 100 * Math.log(100));
        int[] b = ints(0, 1000);
        quick(b);
        StdOut.printf("精确值 : %d  理论值 : %.0f\n", compares, 2 * 1000 * Math.log(1000));
        int[] c = ints(0, 10000);
        quick(c);
        StdOut.printf("精确值 : %d  理论值 : %.0f\n", compares, 2 * 10000 * Math.log(10000));
    }
    // output
    /*
     *  精确值 : 794  理论值 : 921
        精确值 : 12078  理论值 : 13816
        精确值 : 163405  理论值 : 184207
     */
}

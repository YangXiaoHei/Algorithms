package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_04 {
    private static int compares;
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
            while (i < hi && less(a[++i], v));
            while (j > lo && less(v, a[--j]));
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }
    private static boolean less(int i, int j) {
        compares++;
        return i < j;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = ascendInts(0, 9);
        int[] b = ascendInts(10, 19);   // 对于上述切分方法，升序序列是笔记次数最多的，
        int[] c = ascendInts(20, 29);   // 全部相等的序列，以及逆序序列都不需要那么多的比较次数
        int[] d = ascendInts(30, 39);
        int[] e = ascendInts(40, 49);
        int[] f = ascendInts(50, 59);
        StdOut.printf("%s \t%d\n", Arrays.toString(a), quick(a));
        StdOut.printf("%s \t%d\n", Arrays.toString(b), quick(b));
        StdOut.printf("%s \t%d\n", Arrays.toString(c), quick(c));
        StdOut.printf("%s \t%d\n", Arrays.toString(d), quick(d));
        StdOut.printf("%s \t%d\n", Arrays.toString(e), quick(e));
        StdOut.printf("%s \t%d\n", Arrays.toString(f), quick(f));
    }
    // output
    /*
     *  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]   63
        [10, 11, 12, 13, 14, 15, 16, 17, 18, 19]    63
        [20, 21, 22, 23, 24, 25, 26, 27, 28, 29]    63
        [30, 31, 32, 33, 34, 35, 36, 37, 38, 39]    63
        [40, 41, 42, 43, 44, 45, 46, 47, 48, 49]    63
        [50, 51, 52, 53, 54, 55, 56, 57, 58, 59]    63

     */
}

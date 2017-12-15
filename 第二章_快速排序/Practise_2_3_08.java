package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_3_08 {
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
    private static boolean less(int i, int j) { compares++; return i < j; }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = allSameInts(5, 1);
        quick(a);
        StdOut.printf("规模 : %d 比较次数 : %d\n", 5, compares);
        
    }
}

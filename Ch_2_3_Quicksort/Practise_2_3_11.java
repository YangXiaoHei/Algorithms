package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_11 {
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static int parition(int[] a, int lo, int hi) {
        int v = a[lo], i = lo, j = hi + 1;
        while (true) {
            while (i < hi && a[++i] <= v); // modify slightly to fit the quadratic condition
            while (j > lo && a[--j] >= v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void doublingTest() {
        double pre = 0, cur = 0; // if j == 7 then stack overflow exception be thrown
        for (int i = 1000, j = 0; j < 6; j++, i += i) {
            int[] a = intsVrg(i, 1, 2, 3);
            StdOut.printf("规模 : %d 耗时 %.3f 倍率 : %.2f\n", 
                    i, (cur = quick(a)), cur / pre);
            assert isSorted(a);
            pre = cur;
        }
    }
    public static void main(String[] args) {
        doublingTest();
    }
    // output
    /*
     *  规模 : 1000 耗时 0.006 倍率 : Infinity
        规模 : 2000 耗时 0.006 倍率 : 1.00
        规模 : 4000 耗时 0.013 倍率 : 2.17
        规模 : 8000 耗时 0.015 倍率 : 1.15
        规模 : 16000 耗时 0.050 倍率 : 3.33
        规模 : 32000 耗时 0.244 倍率 : 4.88

     */
}

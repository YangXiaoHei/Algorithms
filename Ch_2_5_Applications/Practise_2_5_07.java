package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_2_5_07 {
    public static int select(int[] a, int k) {
        if (k >= a.length) 
            throw new IllegalArgumentException("k is greater than array's size");
        compares = 0;
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int r = StdRandom.uniform(lo, hi + 1);
            int t = a[lo]; a[lo] = a[r]; a[r] = t;
            int pivot = a[lo], i = lo, j = hi + 1;
            while (true) {
                while (i < hi && less(a[++i], pivot));
                while (grea(a[--j], pivot));
                if (i >= j) break;
                t = a[i]; a[i] = a[j]; a[j] = t;
            }
            t = a[lo]; a[lo] = a[j]; a[j] = t;
            if      (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
            else    return a[j];
        }
        throw new RuntimeException("fatal error");
    }
    public static int compares;
    private static boolean less(int i, int j) {
        compares++;
        return i < j;
    }
    private static boolean grea(int i, int j) {
        compares++;
        return i > j;
    }
    public static void main(String[] args) {
        int avrg = 0;
        for (int i = 0; i < 100; i++) {
            int[] a = ints(100, 5, 1000);
            select(a, 0);
            avrg += compares;
        }
        StdOut.printf("平均比较次数为 : %d\n", avrg / 100);
    }
    // output
    /*
     * 平均比较次数为 : 204
       平均比较次数为 : 205

     */
}

package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_2_5_06 {
    /*
     * 非递归实现
     */
    public static int select(int[] a, int k) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int r = StdRandom.uniform(lo, hi + 1);
            int t = a[lo]; a[lo] = a[r]; a[r] = t;
            int pivot = a[lo], i = lo, j = hi + 1;
            while (true) {
                while (i < hi && a[++i] < pivot);
                while (a[--j] > pivot);
                if (i >= j) break;
                t = a[i]; a[i] = a[j]; a[j] = t;
            }
            t = a[j]; a[j] = a[lo]; a[lo] = t;
            if      (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
            else    return a[j];
        }
        return -1;
    }
    /*
     * 递归实现
     */
    public static int selectRe(int[] a, int k) {
        return selectRe(a, k, 0, a.length - 1);
    }
    public static int selectRe(int[] a, int k, int lo, int hi) {
        if (lo > hi) return -1;
        int r = StdRandom.uniform(lo, hi + 1);
        int t = a[lo]; a[lo] = a[r]; a[r] = t;
        int pivot = a[lo], i = lo, j = hi + 1;
        while (true) {
            while (i < hi && a[++i] < pivot);
            while (a[--j] > pivot);
            if (i >= j) break;
            t = a[i]; a[i] = a[j]; a[j] = t;
        }
        t = a[j]; a[j] = a[lo]; a[lo] = t;
        if      (j > k) return selectRe(a, k, lo, j - 1);
        else if (j < k) return selectRe(a, k, j + 1, hi);
        else    return a[j];
    }
    public static void main(String[] args) {
        int[] a = ints(30, 0, 100);
        int[] copy = intsCopy(a);
        print(a);
        int k = 9;
        StdOut.printf("第 %d 大的数是 %d\n", k, select(a, k));
        StdOut.printf("第 %d 大的数是 %d\n", k, selectRe(copy, k));
    }
    // output
    /*
     * 
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  
        98  65  72  49  46  77  39  30  73  86  28  26  100 66  50  54  5   10  65  54  40  32  98  1   100 97  91  51  54  100 
        第 9 大的数是 46
        第 9 大的数是 46

     */
}

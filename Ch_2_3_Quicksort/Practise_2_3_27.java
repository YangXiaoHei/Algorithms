package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_3_27 {
    public static double quick(int[] a, int M) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1, M);
        for (int i = 0; i < a.length; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi, int M) {
        if (hi - lo + 1 < M) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[lo]; a[lo] = a[j]; a[j] = t;
        quick(a, lo, j - 1, M);
        quick(a, j + 1, hi, M);
    }
    public static double _quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        _quick(a, 0, a.length - 1);
        return timer.elapsedTime();      
    }
    private static void _quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[lo]; a[lo] = a[j]; a[j] = t;
        _quick(a, lo, j - 1);
        _quick(a, j + 1, hi);
    }
    public static void main(String[] args) {
        int[] a = ints(0, 1000000);
        int[] copy = intsCopy(a);
        StdOut.printf("忽略小数组 : %.3f\n", quick(a, 20 /* 20 的小数组直接忽略掉 */));
        StdOut.printf("普通快排 : %.3f\n", _quick(copy));
        assert isSorted(copy);
        assert isSorted(a);
    }
    // output
    /*
     *  忽略小数组 : 0.110
        普通快排 : 0.123

        忽略小数组 : 0.108
        普通快排 : 0.123
        
        忽略小数组 : 0.110
        普通快排 : 0.116
        
        忽略小数组 : 0.101
        普通快排 : 0.113


     */
}

package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_22 {
    /*
     * 额外的操作用于交换和枢轴相等的元素
     */
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int v = a[lo], p = lo, q = hi + 1, i = lo, j = hi + 1;
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            { int t = a[i]; a[i] = a[j]; a[j] = t; }
            if (a[i] == v) { ++p; int t = a[p]; a[p] = a[i]; a[i] = t; }
            if (a[j] == v) { --q; int t = a[q]; a[q] = a[j]; a[j] = t; }
        }
        { int t = a[j]; a[j] = a[lo]; a[lo] = t; }
        int lt = j - 1, gt = j + 1, k = lo + 1, m = hi;
        while (k <= p) { int t = a[k]; a[k] = a[lt]; a[lt] = t; ++k; --lt; }
        while (m >= q) { int t = a[m]; a[m] = a[gt]; a[gt] = t; --m; ++gt; }
        quick(a, lo, lt);
        quick(a, gt, hi);
    }
    /*
     * 额外的操作用于交换和枢轴不等的元素
     */
    public static double _quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        _quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void _quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int v = a[lo], lt = lo, i = lo + 1, gt = hi;
        while (i <= gt) {
            if      (a[i] > v) { int t = a[i]; a[i] = a[gt]; a[gt] = t; --gt; }
            else if (a[i] < v) { int t = a[lt]; a[lt] = a[i]; a[i] = t; ++i; ++lt; }
            else    i++;
        }
        _quick(a, lo, lt - 1);
        _quick(a, gt + 1, hi);
    }
    public static double __quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        __quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void __quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t; 
        }
        int t = a[j]; a[j] = a[lo]; a[lo] = t; 
        __quick(a, lo, j - 1);
        __quick(a, j + 1, hi);
    }
    public static void main(String[] args) {
//        int[] a = ints(0, 10000000);
//        int[] a = allSameInts(10000000, 0);
//        int[] a = intsVrg(10000000, 1, 2, 3, 4);
        int[] a = ints(0, 4000000);
        int[] copy = copy(a);
        int[] copy1 = copy(a);
        StdOut.printf("普通快排 : %.3f\n", __quick(copy1));
        StdOut.printf("快速三向切分 : %.3f\n", quick(a));
        StdOut.printf("普通三向切分 : %.3f\n", _quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
        assert isSorted(copy1);
    }
    // output
    /*
     *  大量重复元素
     *  
     *  普通快排 : 1.310
        快速三向切分 : 0.480
        普通三向切分 : 0.218
        
        全部元素相同
        
        普通快排 : 0.540
        快速三向切分 : 0.095
        普通三向切分 : 0.020

        无重复元素
        
        普通快排 : 0.909
        快速三向切分 : 0.968
        普通三向切分 : 1.213

     */
    
}

package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_3_23 {
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
    public static double __quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        __quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void __quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 9) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[lo]; a[lo] = a[j]; a[j] = t;
        __quick(a, lo, j - 1);
        __quick(a, j + 1, hi);
    }
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 9) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int interval = (hi - lo + 1) >>> 3;
        int e5 = (lo + hi) >>> 1;
        int e1 = lo, 
            e9 = hi;
        int e2 = e1 + interval, 
            e3 = e2 + interval, 
            e4 = e3 + interval;
        int e8 = e9 - interval, 
            e7 = e8 - interval, 
            e6 = e7 - interval;
        
        int m1 = Math.min(Math.max(a[e1], a[e2]), a[e3]);
        m1 = m1 == a[e1] ? e1 : m1 == a[e2] ? e2 : e3;
        int m2 = Math.min(Math.max(a[e4], a[e5]), a[e6]);
        m2 = m2 == a[e4] ? e4 : m2 == a[e5] ? e5 : e6;
        int m3 = Math.min(Math.max(a[e7], a[e8]), a[e9]);
        m3 = m3 == a[e7] ? e7 : m3 == a[e8] ? e8 : e9;
        int median = Math.min(Math.max(a[m1], a[m2]), a[m3]);
        median = median == a[m1] ? m1 : median == a[m2] ? m2 : m3;
        
        { int t = a[median]; a[median] = a[lo]; a[lo] = t; }
        
        int v = a[lo], i = lo, j = hi + 1;
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[lo]; a[lo] = a[j]; a[j] = t;
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    public static void main(String[] args) {
        int[] a = ints(0, 4000000);
//        int[] a = allSameInts(4000000, 0);
//        int[] a = ascendInts(0, 10000000);
//        int[] a = descendInts(1000000, 0);
//        int[] a = intsVrg(10000000, 1, 2, 3, 4);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        StdOut.printf("Tukey's ninther : %.3f\n", quick(a));
        StdOut.printf("对照 : %.3f\n", __quick(copy1));
        StdOut.printf("普通快排 : %.3f\n", _quick(copy));
        assert isSorted(a);
        assert isSorted(copy1);
        assert isSorted(copy);
    }
    // output
    /*
     * 
     *  升序序列 
     *  
     *  栈溢出
     *  栈溢出
     *  Tukey's ninther : 0.561

     *  
     *  降序序列
     *  
     *  栈溢出
     *  栈溢出
     *  Tukey's ninther : 0.222

        全部元素相同
        
        Tukey's ninther : 0.238
        对照 : 0.376
        普通快排 : 0.354
        
        乱序无重复元素
        
        Tukey's ninther : 0.913
        对照 : 0.989
        普通快排 : 1.154

        大量重复元素
        
        对照 : 0.962
        普通快排 : 1.432
        Tukey's ninther : 1.096
     *  
     */
}

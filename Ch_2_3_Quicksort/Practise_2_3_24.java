package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_24 {
    public static double __quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        __quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void __quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 47) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int mid = (lo + hi) >>> 1;
        if (a[mid] < a[lo]) exch(a, mid, lo);
        if (a[hi] < a[lo])  exch(a, lo, hi);
        if (a[mid] < a[hi]) exch(a, mid, hi);
        
        int i = lo - 1, j = hi, p = lo - 1, q = hi, v = a[hi];
        while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
            if (a[i] == v) exch(a, ++p, i);
            if (a[j] == v) exch(a, --q, j);
        }
        exch(a, i, hi);
        int lt = i - 1, gt = i + 1, k = lo, m = hi - 1;
        while (k <= p) exch(a, k++, lt--);
        while (m >= q) exch(a, m--, gt++);
        __quick(a, lo, lt);
        __quick(a, gt, hi);
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
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
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        int k = 0, pivot = 0, h = 1;
        // 计算出所有的取样点
        while (pivot < a.length) {
            pivot = (h <<= 1) - 1;
            k++;
        }
        // 分配一个数组来装所有的取样点
        int[] pivots = new int[--k];
        for (int i = k - 1; i >= 0; i--)
            pivots[i] = (h >>= 1) - 1;
        // 2的幂次方增长很快，因此这里用插入排序，将枢轴的相对位置排好
        for (int i = 0; i < pivots.length; i++) {
            int t = a[pivots[i]], j;
            for (j = i - 1; j >= 0 && t < a[pivots[j]]; j--)
                a[pivots[j + 1]] = a[pivots[j]];
            a[pivots[j + 1]] = t;
        }
       quick(a, 0, a.length - 1, pivots);     
       return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi, int[] pivots) {
        if (lo >= hi) return;
        int li = 0, ri = pivots.length - 1;
        while (li <= ri && pivots[li] < lo) li++;
        while (ri >= 0 && pivots[ri] > hi) ri--;
        if (li <= ri) {
            int mid = (li + ri) >>> 1;
            int t = a[pivots[mid]]; a[pivots[mid]] = a[lo]; a[lo] = t;
        }
        int v = a[lo], i = lo, j = hi + 1;
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[j]; a[j] = a[lo]; a[lo] = t;
        quick(a, lo, j - 1, pivots);
        quick(a, j + 1, hi, pivots);  
    }
    public static void main(String[] args) {
//        int[] a = ints(0, 4000000);
//        int[] a = allSameInts(4000000, 1);
        int[] a = intsVrg(4000000, 1, 2, 3, 4);
//        int[] a = ascendInts(0, 4000000);
        int[] copy = copy(a);
        int[] copy1 = copy(a);
        StdOut.printf("取样排序 : %.3f\n", quick(a));
        StdOut.printf("普通快排 : %.3f\n", _quick(copy));
        StdOut.printf("优化快排 : %.3f\n", __quick(copy1));
        assert isSorted(a);
        assert isSorted(copy);
        assert isSorted(copy1);
    }
    // output
    /*
     *
     * 乱序无重复
     * 
     * 取样排序 : 0.980
       普通快排 : 1.052
       优化快排 : 0.827
       
       全部都相同
       
       取样排序 : 0.390
       普通快排 : 0.328
       优化快排 : 0.115
       
       大量重复元素
       
       取样排序 : 0.594
       普通快排 : 0.552
       优化快排 : 0.259
       
       升序
       栈溢出
       栈溢出
       优化快排 : 0.145


     */
}

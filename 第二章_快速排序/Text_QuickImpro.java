package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Text_QuickImpro {
    /*
     * 通常的快速排序
     */
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 小数组切换插入排序的快速排序
     */
    public static double quick_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick_A(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 三向切分快速排序
     */
    public static double quick_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick_B(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 三取样切分加小数组切换的插入排序
     */
    public static double quick_C(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick_C(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick_C(int[] a, int lo, int hi) {
        if (hi - lo <= 2) {
            insertion(a, lo, hi);
            return;
        }
        int median = median(a, lo, hi);
        int j = parition_C(a, lo, hi, median);
        quick_C(a, lo, j - 1);
        quick_C(a, j + 1, hi);
    }
    private static int median(int[] a, int lo, int hi) {
        int mid = (lo + hi) / 2;
        if (a[mid] < a[lo]) exch(a, mid, lo);
        if (a[hi] < a[lo]) exch(a, hi, lo);
        if (a[hi] < a[mid]) exch(a, hi, mid);
        exch(a, hi, hi - 1);
        return hi - 1;
    }
    private static int parition_C(int[] a, int lo, int hi, int pivot) {
        int i = lo, j = hi - 1;
        while (true) {
            while (i < hi && a[++i] < pivot);
            while (j > lo && a[--j] > pivot);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, i, hi - 1);
        return i;
    }
    
    private static void quick_B(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        int v = a[lo];
        while (i <= gt) {
            if      (a[i] < v) exch(a, lt++, i++);
            else if (a[i] > v) exch(a, i, gt--);
            else    i++;
        }
        quick_B(a, lo, lt - 1);
        quick_B(a, gt + 1, hi);
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static void quick_A(int[] a, int lo, int hi) {
        if (hi - lo <= 5) {
            insertion(a, lo, hi);
           return;
        }
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    private static int parition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (i < hi && a[++i] < a[lo]);
            while (j > lo && a[--j] > a[lo]);
            if (i >= j) break;
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        int t = a[j];
        a[j] = a[lo];
        a[lo] = t;
        return j;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = ints(0, 5);
        print(a);
        StdOut.printf("耗时 : %.3f\n", quick_C(a));
        print(a);
    }
}

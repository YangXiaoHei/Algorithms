package 第二章_快速排序;

import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;

public class Text_Review {
    /*
     * 切换插入排序
     */
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        shuffle(a);
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        if (hi - lo < 7) {
            insertion(a, lo, hi);
            return;
        }
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static int parition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (i < hi && a[++i] < a[lo]);
            while (j > lo && a[--j] > a[lo]);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }
    /*
     * 三取样切分 & 切换插入排序
     */
    public static double quick_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        shuffle(a);
        quick_A(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick_A(int[] a, int lo, int hi) {
        if (hi - lo < 7) {
            insertion(a, lo, hi);
            return;
        }
        int privot = privot(a, lo, hi);
        int i = parition(a, lo, hi, privot);
        quick_A(a, lo, i - 1);
        quick_A(a, i + 1, hi);
    }
    /*
     * 三向切分 & 切换插入排序
     */
    public static double quick_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        shuffle(a);
        quick_B(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick_B(int[] a, int lo, int hi) {
        if (hi - lo < 7) {
            insertion(a, lo, hi);
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        int v = a[lo];
        while (i <= gt) {
            if      (a[i] > v) exch(a, i, gt--);
            else if (a[i] < v) exch(a, lt++, i++);
            else    i++;
        }
        quick_B(a, lo, lt - 1);
        quick_B(a, gt + 1, hi);
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    private static int parition(int[] a, int lo, int hi, int privot) {
        int i = lo, j = hi - 1;
        while (true) {
            while (a[++i] < privot);
            while (a[--j] > privot);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, i, hi - 1);
        return i;
    }
    private static int privot(int[] a, int lo, int hi) {
        int mid = (lo + hi) >> 1;
        if (a[mid] < a[lo]) exch(a, mid, lo);
        if (a[hi] < a[lo])  exch(a, hi, lo);
        if (a[hi] < a[mid]) exch(a, hi, mid);
        exch(a, mid, hi - 1);
        return a[hi - 1];
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = intsVrg(50000000, 1, 2, 3, 4);
//        int[] a = descendInts(10000, 0);
//        int[] a = ints(10000000);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        StdOut.printf("耗时 : %.3f\n", quick(a));
        StdOut.printf("耗时 : %.3f\n", quick_A(copy));
        StdOut.printf("耗时 : %.3f\n", quick_B(copy1));
    }
}

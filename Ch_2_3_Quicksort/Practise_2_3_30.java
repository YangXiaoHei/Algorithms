package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.Practise_2_1_36.*;
import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_30 {
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int ran = StdRandom.uniform(lo, hi + 1);
        int r = a[ran]; a[ran] = a[lo]; a[lo] = r;
        int i = lo, j = hi + 1;
        double v = a[lo];
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
    public static double _quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < a.length; i++) {
            int r = StdRandom.uniform(i, a.length);
            int t = a[r];
            a[r] = a[i];
            a[i] = t;
        }
        _quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void _quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1;
        double v = a[lo];
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
    public static double quick(double[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(double[] a, int lo, int hi) {
        if (lo >= hi) return;
        int ran = StdRandom.uniform(lo, hi + 1);
        double r = a[ran]; a[ran] = a[lo]; a[lo] = r;
        int i = lo, j = hi + 1;
        double v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            double t = a[i]; a[i] = a[j]; a[j] = t;
        }
        double t = a[lo]; a[lo] = a[j]; a[j] = t;
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    public static double _quick(double[] a) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < a.length; i++) {
            int r = StdRandom.uniform(i, a.length);
            double t = a[r];
            a[r] = a[i];
            a[i] = t;
        }
        _quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void _quick(double[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1;
        double v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            double t = a[i]; a[i] = a[j]; a[j] = t;
        }
        double t = a[lo]; a[lo] = a[j]; a[j] = t;
        _quick(a, lo, j - 1);
        _quick(a, j + 1, hi);
    }
    public static void gaussianTest() {
        double[] a = gaussian(1000000, 10000);
        double[] copy = copy(a);
        StdOut.printf("高斯分布  :  随机化 : %.3f  随机切分 : %.3f\n", _quick(a), quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    public static void possionTest() {
        double[] a = possion(1000000, 100);
        double[] copy = copy(a);
        StdOut.printf("泊松分布  :  随机化 : %.3f  随机切分 : %.3f\n", _quick(a), quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    public static void geometricTest() {
        double[] a = geometric(1000000, 0.8);
        double[] copy = copy(a);
        StdOut.printf("几何分布  :  随机化 : %.3f  随机切分 : %.3f\n", _quick(a), quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    public static void discreteTets() { 
                                       /*  0.078 */  /*  0.1 */  /* 0.022 */   /* 0.4 */
        double[] a = discrete(1000000, 0.032, 0.046, 0.01, 0.09,    0.022,     0.3, 0.1, 0.2, 0.055, 0.045, 0.1);
        double[] copy = copy(a);
        StdOut.printf("离散分布  :  随机化 : %.3f  随机切分 : %.3f\n", _quick(a), quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    public static void halfZeroHalfOneTest() {
        int[] a = halfZeroHalfOne(1000000);
        int[] copy = copy(a);
        StdOut.printf("一半0一半1  :  随机化 : %.3f  随机切分 : %.3f\n", _quick(a), quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    public static void halfZeroQuarterOneQuarterTwoTest() {
        int[] a = halfZeroQuarterOneQuarterTwo(1000000);
        int[] copy = copy(a);
        StdOut.printf("一半01/411/42  :  随机化 : %.3f  随机切分 : %.3f\n", _quick(a), quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    public static void halfZeroHalfRandomTest() {
        int[] a = halfZeroHalfRandom(1000000);
        int[] copy = copy(a);
        StdOut.printf("一半0一半随机  :  随机化 : %.3f  随机切分 : %.3f\n", _quick(a), quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    public static void main(String[] args) {
        gaussianTest();
        possionTest();
        geometricTest();
        discreteTets();
        halfZeroHalfOneTest();
        halfZeroQuarterOneQuarterTwoTest();
        halfZeroHalfRandomTest();
    }
    // output
    /*
     *  高斯分布  :  随机化 : 0.203  随机切分 : 0.152
        泊松分布  :  随机化 : 0.093  随机切分 : 0.077
        几何分布  :  随机化 : 0.090  随机切分 : 0.052
        离散分布  :  随机化 : 0.110  随机切分 : 0.072
        一半0一半1  :  随机化 : 0.108  随机切分 : 0.083
        一半01/411/42  :  随机化 : 0.106  随机切分 : 0.050
        一半0一半随机  :  随机化 : 0.137  随机切分 : 0.108
     */
}

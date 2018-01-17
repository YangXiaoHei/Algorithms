package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class __QuickImpro {
    /*
     * 普通
     */
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 切换插入排序
     */
    public static double quick_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick_A(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 三向切分 & 切换插入排序
     */
    public static double quick_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick_B(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 三取样切分 & 切换插入排序
     */
    public static double quick_C(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick_C(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick_C(int[] a, int lo, int hi) {
        if (hi - lo < 3) {
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
        exch(a, mid, hi - 1);
        return a[hi - 1];
    }
    private static int parition_C(int[] a, int lo, int hi, int pivot) {
        int i = lo, j = hi - 1;
        while (true) {
            while (a[++i] < pivot);
            while (a[--j] > pivot);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, i, hi - 1);
        return i;
    }
    private static void quick_B(int[] a, int lo, int hi) {
        if (hi - lo < 3) {
            insertion(a, lo, hi);
            return;
        }
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
            for (j = i - 1; j >= lo && t < a[j]; j--)
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
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    /*
     * 
     *  规模 : 204800 耗时 : 0.029 倍率 : 2.1
        规模 : 409600 耗时 : 0.063 倍率 : 2.2
        规模 : 819200 耗时 : 0.119 倍率 : 1.9
        规模 : 1638400 耗时 : 0.212 倍率 : 1.8
        规模 : 3276800 耗时 : 0.401 倍率 : 1.9
        规模 : 6553600 耗时 : 0.831 倍率 : 2.1
        规模 : 13107200 耗时 : 1.801 倍率 : 2.2
        规模 : 26214400 耗时 : 3.794 倍率 : 2.1
        规模 : 52428800 耗时 : 8.028 倍率 : 2.1
     * 
     */
    public static void sampleOfThreeDoublingRatioTest() {
        double pre = 0, cur = 0;
        for (int i = 100, j = 0; j < 20; j++, i += i) {
            int[] ints = ints(i);
            StdOut.printf("规模 : %d 耗时 : %.3f 倍率 : %.1f\n",
                    i, (cur = quick_C(ints)), cur / pre);
            pre = cur;
        }
    }
    /*
     *  规模 : 12800 三取样切分 / 普通 = 0.500
        规模 : 25600 三取样切分 / 普通 = 0.667
        规模 : 51200 三取样切分 / 普通 = 0.800
        规模 : 102400 三取样切分 / 普通 = 0.824
        规模 : 204800 三取样切分 / 普通 = 0.929
        规模 : 409600 三取样切分 / 普通 = 0.955
        规模 : 819200 三取样切分 / 普通 = 1.121
        规模 : 1638400 三取样切分 / 普通 = 0.870
        规模 : 3276800 三取样切分 / 普通 = 0.822
        规模 : 6553600 三取样切分 / 普通 = 0.873
        规模 : 13107200 三取样切分 / 普通 = 0.872
        规模 : 26214400 三取样切分 / 普通 = 0.870
        规模 : 52428800 三取样切分 / 普通 = 0.899
     * 
     * 
     */
    public static void sampleOfThreeVSNormalQuick() {
        for (int i = 100, j = 0; j < 20; j++, i += i) {
            int[] a = ints(i);
            int[] copy = copy(a);
            StdOut.printf("规模 : %d 三取样切分 / 普通 = %.3f\n",
                    i, quick_C(copy) / quick(a));
        }
    }
    /*
     *  规模 : 12800 三取样切分 / 三向切分 = 0.333
        规模 : 25600 三取样切分 / 三向切分 = 0.500
        规模 : 51200 三取样切分 / 三向切分 = 0.600
        规模 : 102400 三取样切分 / 三向切分 = 0.625
        规模 : 204800 三取样切分 / 三向切分 = 0.763
        规模 : 409600 三取样切分 / 三向切分 = 0.909
        规模 : 819200 三取样切分 / 三向切分 = 0.715
        规模 : 1638400 三取样切分 / 三向切分 = 0.664
        规模 : 3276800 三取样切分 / 三向切分 = 0.647
        规模 : 6553600 三取样切分 / 三向切分 = 0.549
        规模 : 13107200 三取样切分 / 三向切分 = 0.585
        规模 : 26214400 三取样切分 / 三向切分 = 0.615
        规模 : 52428800 三取样切分 / 三向切分 = 0.682
     * 
     * 
     */
    public static void sampleOfThreeVSQuick3Way() {
        for (int i = 100, j = 0; j < 20; j++, i += i) {
            int[] a = ints(i);
            int[] copy = copy(a);
            StdOut.printf("规模 : %d 三取样切分 / 三向切分 = %.3f\n",
                    i, quick_C(copy) / quick_B(a));
        }
    }
    /*
     *  规模 : 51200 三取样切分 / 三向切分 = 2.000
        规模 : 102400 三取样切分 / 三向切分 = 4.000
        规模 : 204800 三取样切分 / 三向切分 = 10.000
        规模 : 409600 三取样切分 / 三向切分 = 13.000
        规模 : 819200 三取样切分 / 三向切分 = 28.000
        规模 : 1638400 三取样切分 / 三向切分 = 62.000
        规模 : 3276800 三取样切分 / 三向切分 = 37.333
        规模 : 6553600 三取样切分 / 三向切分 = 52.750
        规模 : 13107200 三取样切分 / 三向切分 = 55.625
        规模 : 26214400 三取样切分 / 三向切分 = 39.435
        规模 : 52428800 三取样切分 / 三向切分 = 62.871
     * 
     */
    public static void theWorstCaseComparasion() {
        for (int i = 100, j = 0; j < 20; j++, i += i) {
            int[] a = allSameInts(i, 0);
            int[] copy = copy(a);
            StdOut.printf("规模 : %d 三取样切分 / 三向切分 = %.3f\n",
                    i, quick_C(copy) / quick_B(a));
        }
    }
    public static void main(String[] args) {
        int[] arr = ints(0, 10);
        print(arr);
    }
}

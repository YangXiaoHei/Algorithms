package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class __AllQuickVersionComparasion {
    /*
     * 三向切分 & 切换插入排序
     */
    public static double quick_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        shuffle(a);
        quick_A(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick_A(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 5) {
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
        quick_A(a, lo, lt - 1);
        quick_A(a, gt + 1, hi);
    }
    /*
     * 最终版
     */
    public static double quick_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick_B(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick_B(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 5) {
            insertion(a, lo, hi);
            return;
        }
        int mid = (lo + hi) >> 1; // 三取样切分
        if (a[mid] < a[lo]) exch(a, mid, lo);
        if (a[hi] < a[lo]) exch(a, hi, lo);
        if (a[mid] < a[hi]) exch(a, mid, hi);
        
        int i = lo - 1, j = hi, p = lo - 1, q = hi;
        int v = a[hi];
        while (true) {
            while (a[++i] < v);  // 大小关系 a < b < c  --> a  ....  c  .... b
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
            if (a[i] == v) exch(a, i, ++p); // 把相等元素移到数组的两端
            if (a[j] == v) exch(a, j, --q);
        }
        exch(a, i, hi);
        
        int lt = i - 1, gt = i + 1, k = lo, m = hi - 1; // 把相等元素移到数组中间
        while (k <= p) exch(a, k++, lt--);
        while (m >= q) exch(a, m--, gt++);
        
        quick_B(a, lo, lt); // 从相等元素的左右两端开始切分
        quick_B(a, gt, hi);
    }
    public static double quick_C(int[] a) {
        Stopwatch timer = new Stopwatch();
        shuffle(a);
        quick_C(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick_C(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 5) {
            insertion(a, lo, hi);
            return;
        }
        int mid = (lo + hi) >> 1;
        if (a[mid] < a[lo]) exch(a, mid, lo);
        if (a[hi] < a[lo])  exch(a, hi, lo);
        if (a[mid] < a[hi]) exch(a, mid, hi);
        exch(a, mid, hi - 1);
        
        int privot = a[hi - 1];
        int i = lo, j = hi - 1;
        while (true) {
            while (a[++i] < privot);
            while (a[--j] > privot);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, hi - 1);
        
        quick_C(a, lo, j - 1);
        quick_C(a, j + 1, hi);
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1]) return false;
        return true;
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    public static void correctTest() {
        while (true) {
            int[] a = intsVrgWithEachAmount(3, 1, 2, 3, 4, 5);
            int[] copy = intsCopy(a);
            quick_B(a);
            if (!isSorted(a)) {
                print(copy);
                assert isSorted(a);
                break;
            }
        }
    }
    public static void tooManyDupliRandomOrderSequence() {
        StdOut.println("=================  大量重复元素 ===================");
        int[] a = intsVrgWithEachAmount(10000000, 1, 2, 3, 4, 5);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        StdOut.printf("最终版 : %.3f\n", quick_B(a));
        StdOut.printf("三向切分 : %.3f\n", quick_A(copy));
        StdOut.printf("三取样切分 : %.3f\n", quick_C(copy1));
    }
    public static void randomOrderNoDupliSequence() {
        StdOut.println("=================  无重复元素序列 ===================");
        int[] a = ints(0, 10000000);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        StdOut.printf("最终版 : %.3f\n", quick_B(a));
        StdOut.printf("三向切分 : %.3f\n", quick_A(copy));
        StdOut.printf("三取样切分 : %.3f\n", quick_C(copy1));
    }
    public static void sortedSequence() {
        StdOut.println("=================  已排序序列 ===================");
        int[] a = ascendInts(0, 10000000);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        StdOut.printf("最终版 : %.3f\n", quick_B(a));
        StdOut.printf("三向切分 : %.3f\n", quick_A(copy));
        StdOut.printf("三取样切分 : %.3f\n", quick_C(copy1));
    }
    public static void reverseSequence() {
        StdOut.println("=================  逆序序列 ===================");
        int[] a = descendInts(10000000, 0);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        StdOut.printf("最终版 : %.3f\n", quick_B(a));
        StdOut.printf("三向切分 : %.3f\n", quick_A(copy));
        StdOut.printf("三取样切分 : %.3f\n", quick_C(copy1));
    }
    public static void allSameSequence() {
        StdOut.println("=================  全部元素相同 ===================");
        int[] a = allSameInts(10000000, 0);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        StdOut.printf("最终版 : %.3f\n", quick_B(a));
        StdOut.printf("三向切分 : %.3f\n", quick_A(copy));
        StdOut.printf("三取样切分 : %.3f\n", quick_C(copy1));
    }
    public static void main(String[] args) {
        tooManyDupliRandomOrderSequence();
        randomOrderNoDupliSequence();
        sortedSequence();
        reverseSequence();
        allSameSequence();
    }
    // output
    /*
     *  =================  大量重复元素 ===================
        最终版 : 1.239
        三向切分 : 5.740
        三取样切分 : 9.371
        =================  无重复元素序列 ===================
        最终版 : 2.042
        三向切分 : 3.266
        三取样切分 : 3.187
        =================  已排序序列 ===================
        最终版 : 0.428
        三向切分 : 3.280
        三取样切分 : 3.141
        =================  逆序序列 ===================
        最终版 : 0.483
        三向切分 : 3.568
        三取样切分 : 2.995
        =================  全部元素相同 ===================
        最终版 : 0.068
        三向切分 : 0.870
        三取样切分 : 1.550
     */
}

package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_3_18 {
    public static double quickNormal(int[] a) {
        Stopwatch timer = new Stopwatch();
        quickNormal(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quickNormal(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        quickNormal(a, lo, j - 1);
        quickNormal(a, j + 1, hi);
    }
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        int size = hi - lo + 1;  // 在这里如果切换插入排序，即解决了小数组三取样的 bug，又避免了过多上下文切换的开销，可谓一举两得
        if (size <= 1) return; // 单元素自然有序
        if (size == 2) {
            if (a[lo] > a[hi]) exch(a, lo, hi); // 双元素直接判断是否交换位置
            return;
        }
        int mid = (lo + hi) >> 1;  // 三取样切分，排好了 lo mid hi 的相对位置
        if (a[mid] < a[lo]) exch(a, mid, lo);
        if (a[hi] < a[lo])  exch(a, hi, lo);
        if (a[hi] < a[mid]) exch(a, mid, hi);
        
        if (size == 3) return;  // 如果现在是三元素，那么该子数组已然有序
        
        exch(a, mid, hi - 1); 
        int i = lo - 1, j = hi - 1, pivot = a[hi - 1];
        while (true) {
            while (a[++i] < pivot);
            while (a[--j] > pivot);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, hi - 1, i);
        
        quick(a, lo, i - 1);
        quick(a, i + 1, hi);
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = ints(0, 10000000);
        int[] copy = intsCopy(a);
        StdOut.printf("三取样切分 ：%.3f\n", quick(a));
        StdOut.printf("教材代码 ：%.3f\n", quickNormal(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    // output
    /*
     *  三取样切分 ：2.007
        教材代码 ：2.338
        
        三取样切分 ：2.070
        教材代码 ：2.499


     */
}

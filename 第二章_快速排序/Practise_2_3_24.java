package 第二章_快速排序;

import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;

public class Practise_2_3_24 {
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
        int[] a = ints(0, 10000000);
        int[] copy = intsCopy(a);
        StdOut.printf("取样排序 : %.3f\n", quick(a));
        StdOut.printf("普通快排 : %.3f\n", _quick(copy));
        assert isSorted(a);
        assert isSorted(copy);
    }
    // output
    /*
     *  取样排序 : 2.691
        普通快排 : 2.500
        
        取样排序 : 2.635
        普通快排 : 2.495
        
        取样排序 : 2.787
        普通快排 : 2.278

     */
}

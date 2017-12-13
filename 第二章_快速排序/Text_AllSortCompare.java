package 第二章_快速排序;

import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;

public class Text_AllSortCompare {
    public static double insertion(int[] a) {
        Stopwatch timer = new Stopwatch();
        insertion(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double shell(int[] a) {
        Stopwatch timer = new Stopwatch();
        shell(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double merge(int[] a) {
        Stopwatch timer = new Stopwatch();
        int[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 7) {
            insertion(a, lo, hi);
            return;
        }
        int mid = (lo + hi) >> 1;
        if (a[mid] < a[lo]) exch(a, lo, mid);
        if (a[hi] < a[lo]) exch(a, lo, hi);
        if (a[mid] < a[hi]) exch(a, mid, hi);
        
        int i = lo - 1, p = lo - 1, j = hi, q = hi, v = a[hi];
        while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
            if (a[i] == v) exch(a, i, ++p);
            if (a[j] == v) exch(a, j, --q);
        }
        exch(a, hi, i);
        
        int lt = i - 1, gt = i + 1, k = lo, m = hi - 1;
        while (k <= p) exch(a, k++, lt--);
        while (m >= q) exch(a, m--, gt++);
        
        quick(a, lo, lt);
        quick(a, gt, hi);
    }
    private static void merge(int[] src, int[] dst, int lo, int hi) {
        if (hi - lo + 1 < 7) {
            insertion(dst, lo, hi);
            return;
        }
        int mid = (lo + hi) >> 1;
        merge(dst, src, lo, mid);
        merge(dst, src, mid + 1, hi);
        if (src[mid] < src[mid + 1]) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, lo, mid, hi);
    }
    private static void mergeSort(int[] src, int[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           dst[k] = src[j++];
            else if (j > hi)            dst[k] = src[i++];
            else if (src[j] < src[i])   dst[k] = src[j++];
            else                        dst[k] = src[i++];
    }
    private static void shell(int[] a, int lo, int hi) {
        int h = 1, N = hi - lo + 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= lo && t < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= lo && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    
    public static void main(String[] args) {
        int[] a = ints(0, 10000000);
//        int[] a = allSameInts(10000000, 0);
//        int[] a = descendInts(10000000, 0);
//        int[] a = ascendInts(0, 10000000);
//        int[] a = intsVrg(10000000, 1, 2, 3, 4, 5, 6, 7, 8);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        StdOut.printf("希尔排序 ：%.3f\n", shell(a));
        StdOut.printf("归并排序 ：%.3f\n", merge(copy));
        StdOut.printf("快速排序 ：%.3f\n", quick(copy1));
    }
    // output
    /*
     *  随机不重复元素
     *  
     *  希尔排序 ：6.694
        归并排序 ：3.287
        快速排序 ：2.131
        
        全部元素都相同
         
        希尔排序 ：0.261
        归并排序 ：1.348
        快速排序 ：0.087
        
        逆序数组
        
        希尔排序 ：0.881
        归并排序 ：1.231
        快速排序 ：0.615

        已排序的数组
        
        希尔排序 ：0.132
        归并排序 ：0.520
        快速排序 ：0.565
        
        大量重复元素的数组
        
        希尔排序 ：1.157
        归并排序 ：1.774
        快速排序 ：0.413


     */
}

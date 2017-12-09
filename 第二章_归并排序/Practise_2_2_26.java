package 第二章_归并排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_2_26 {
    private static int[] aux;
    public static double merge_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new int[a.length];
        merge_A(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double merge_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        merge_B(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void merge_A(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge_A(a, lo, mid);
        merge_A(a, mid + 1, hi);
        mergeSort_A(a, lo, mid, hi);
    }
    private static void merge_B(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge_B(a, lo, mid);
        merge_B(a, mid + 1, hi);
        mergeSort_B(a, lo, mid, hi);
    }
    private static void mergeSort_A(int[] a, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
    }
    private static void mergeSort_B(int[] a, int lo, int mid, int hi) {
        int[] aux = new int[hi - lo + 1];
        for (int k = lo; k <= hi; k++)
            aux[k - lo] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)                       a[k] = aux[j++ - lo];
            else if (j > hi)                        a[k] = aux[i++ - lo];
            else if (aux[j - lo] < aux[i - lo])     a[k] = aux[j++ - lo];
            else                                    a[k] = aux[i++ - lo];
    }
    public static void main(String[] args) {
        int[] arr = ints(20000000);
        int[] copy = intsCopy(arr);
        StdOut.printf("一开始就创建好 : %.3f\n", merge_A(arr));
        StdOut.printf("每次都创建新数组 : %.3f\n", merge_B(copy));
    }
    // output
    /*
     *  一开始就创建好 : 3.522
        每次都创建新数组 : 4.608
     */
}

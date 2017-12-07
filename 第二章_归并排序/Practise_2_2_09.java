package 第二章_归并排序;

import static 第二章_初级排序算法.Text_Array.*;
import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class Practise_2_2_09 {
    private static int[] aux;
    public static double merge_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new int[a.length];
        merge(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double merge_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        int[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void merge(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge(a, lo, mid);
        merge(a, mid + 1, hi);
        mergeSort(a, lo, mid, hi);
    }
    private static void merge(int[] src, int[] dst, int lo, int hi) {
        if (hi - lo <= 7) {
            insertion(dst, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        merge(dst, src, lo, mid);
        merge(dst, src, mid + 1, hi);
        if (src[mid] < src[mid + 1]) {
            System.arraycopy(dst, lo, src, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, lo, mid, hi);
    }
    private static void mergeSort(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
    }
    private static void mergeSort(int[] src, int[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           dst[k] = src[j++];
            else if (j > hi)            dst[k] = src[i++];
            else if (src[j] < src[i])   dst[k] = src[j++];
            else                        dst[k] = src[i++];
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    public static void draw() {
        StdDraw.setXscale(0, 10000000);
        StdDraw.setYscale(-2, 4);
        for (int i = 1000; i < 10000000; i += 40000) {
            int[] arr = ints(i);
            int[] copy = intsCopy(arr);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(i, merge_A(arr));
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, merge_B(copy));
        }
    }
    public static void main(String[] args) {
        draw();
    }
}

package 第二章_归并排序;

import static 第二章_初级排序算法.Text_Array.*;
import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class Text_Merge {
    private static Comparable[] aux;
    /*
     * 归并排序
     */
    public static double merge(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 归并排序改进 A
     */
    public static double merge_A(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new Comparable[a.length];
        sort_A(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 归并排序改进 B
     */
    public static double merge_B(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new Comparable[a.length];
        sort_B(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        mergeSort(a, lo, mid, hi);
    }
    private static void sort_A(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        sort_A(a, lo, mid);
        sort_A(a, mid + 1, hi);
        if (a[mid].compareTo(a[mid + 1]) < 0) return;
        mergeSort(a, lo, mid, hi);
    }
    public static void sort_B(Comparable[] a, int lo, int hi) {
        if (hi - lo <= 7) {
            insertion(a, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        sort_B(a, lo, mid);
        sort_B(a, mid + 1, hi);
        if (a[mid].compareTo(a[mid + 1]) < 0) return;
        mergeSort(a, lo, mid, hi);
    }
    private static void mergeSort(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if      (i > mid)                      a[k] = aux[j++];
            else if (j > hi)                       a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else    a[k] = aux[i++]; 
    }
    /*
     * 希尔排序
     */
    public static double shell(Double[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                Double t = a[i];
                int j;
                for (j = i - h; j >= 0 && t.compareTo(a[j]) < 0; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    /*
     * 插入排序
     */
    public static double insertion(Comparable[] a, int lo, int hi) {
        Stopwatch timer = new Stopwatch();
        for (int i = lo + 1; i <= hi; i++) {
            Comparable t = a[i];
            int j;
            for (j = i - 1; j >= lo && t.compareTo(a[j]) < 0; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
        return timer.elapsedTime();
    }
    /*
     * 希尔排序和归并排序的性能比较
     */
    public static void draw(int limit) {
        if (limit <= 1000 * 10) 
            throw new IllegalArgumentException();
        StdDraw.setXscale(-1, limit);
        StdDraw.setYscale(-1, 2);
        StdDraw.setPenRadius(.003);
        for (int i = 1000; i < limit; i += 4000) {
            Double[] d = DoubleRandom_size(i);
            Double[] copy = DoubleCopy_arr(d);
            Double[] copy2 = DoubleCopy_arr(d);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, merge_A(copy2));
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.point(i, merge_B(copy)); // 蓝线是归并_A
        }
    }
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i - 1]) < 0) return false;
        return true;
    }
    public static void main(String[] args) {
//        draw(1000000);
        Double[] d = DoubleRandom_size(8000000);
        Double[] copy = DoubleCopy_arr(d);
        Double[] copy2 = DoubleCopy_arr(d);
        StdOut.printf("归并排序 A : %.3f\n", merge_A(copy));
        StdOut.printf("归并排序 : %.3f\n", merge(d));
        
    }
}

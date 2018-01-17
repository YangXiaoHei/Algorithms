package Ch_2_2_Mergesort;

import static Tool.ArrayGenerator.*;
import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class __Merge {
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
     * 归并排序 迭代版
     */
    public static double merge_iterativly(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) 
            for (int lo = 0; lo < N - sz; lo += 2 * sz) 
                mergeSort(a, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1,  N - 1));
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
    /*
     * 归并排序改进 C
     */
    public static double merge_C(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        Comparable[] aux = a.clone();
        sort_C(aux, a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static boolean isShallowCopy(Comparable[] a, Comparable[] b) {
        for (Comparable aa : a)
            for (Comparable bb : b)
                if (aa.equals(bb) && aa != bb) return false;
        return true;
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
    public static void sort_C(Comparable[] src, Comparable[] dest, int lo, int hi) {
        if (hi - lo <= 7) {
            insertion(dest, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        sort_C(dest, src, lo, mid);
        sort_C(dest, src, mid + 1, hi);
        if (src[mid].compareTo(src[mid + 1]) < 0) {
            System.arraycopy(src, lo, dest, lo , hi - lo + 1);
            return;
        }
        mergeSort_C(src, dest, lo, mid, hi);
    }
    private static void mergeSort_C(Comparable[] src, Comparable[] dest, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) 
            if      (i > mid)                      dest[k] = src[j++];
            else if (j > hi)                       dest[k] = src[i++];
            else if (src[j].compareTo(src[i]) < 0) dest[k] = src[j++];
            else                                   dest[k] = src[i++];
    }
    private static void mergeSort(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if      (i > mid)                      a[k] = aux[j++];
            else if (j > hi)                       a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else                                   a[k] = aux[i++]; 
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
            Double[] d = Doubles(i);
            Double[] copy = DoublesCopy(d);
            Double[] copy2 = DoublesCopy(d);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, merge_A(copy2));
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.point(i, merge_B(copy)); // 蓝线是归并_A
        }
    }
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i - 1]) < 0)  {
                StdOut.printf("a[%d] = %d a[%d] = %d\n", i - 1, a[i - 1], i, a[i]);
                return false;
            }
        return true;
    }
    public static void main(String[] args) {
        int N = 51200000;
        Double[] d = Doubles(N);
        Double[] copy = DoublesCopy(d);
        Double[] copy1 = DoublesCopy(d);
        Double[] copy2 = DoublesCopy(d);
        Double[] copy3 = DoublesCopy(d);
        Double[] copy4 = DoublesCopy(d);
        StdOut.printf("========== 规模 : %d ==========\n", N);
        StdOut.printf("希尔排序 : %.3f\n", shell(copy4));
        StdOut.printf("归并排序 C : %.3f\n", merge_C(d));
        StdOut.printf("归并排序 B : %.3f\n", merge_B(copy1));
        StdOut.printf("归并排序 A : %.3f\n", merge_A(copy2));
        StdOut.printf("归并排序 : %.3f\n", merge(copy));
        StdOut.printf("归并排序 迭代 : %.3f\n", merge_iterativly(copy3));
        assert isSorted(d);
        assert isSorted(copy1);
        assert isSorted(copy2);
        assert isSorted(copy3);
        assert isSorted(copy4);
        assert isSorted(d);
    }
    // output
    /*
     *  ========== 规模 : 5000000 ==========
        希尔排序 : 7.301
        归并排序 C : 2.366
        归并排序 B : 3.050
        归并排序 A : 2.598
        归并排序 : 2.552
        归并排序 迭代 : 2.885
        
        ========== 规模 : 6000000 ==========
        希尔排序 : 9.452
        归并排序 C : 2.954
        归并排序 B : 3.335
        归并排序 A : 9.256
        归并排序 : 3.208
        归并排序 迭代 : 3.544
        
        ========== 规模 : 8000000 ==========
        希尔排序 : 13.570
        归并排序 C : 4.001
        归并排序 B : 11.296 // 猜测是垃圾回收导致 ?
        归并排序 A : 4.459
        归并排序 : 4.404
        归并排序 迭代 : 4.867
     */
}

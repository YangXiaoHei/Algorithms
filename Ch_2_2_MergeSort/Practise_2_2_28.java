package Ch_2_2_MergeSort;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.awt.Color;

import edu.princeton.cs.algs4.*;

public class Practise_2_2_28 {
    private static int[] aux;
    /*
     * 自顶向下
     */
    public static double merge_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new int[a.length];
        merge(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    /*
     * 自底向上
     */
    public static double merge_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new int[a.length];
        int N = a.length;
        for (int sz = 1; sz < N; sz += sz)
            for (int lo = 0; lo < N - sz; lo += 2 * sz) 
                mergeSort(a, lo, lo + sz - 1, Math.min(N - 1, lo + 2 * sz - 1));
        return timer.elapsedTime();
    }
    private static void merge(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge(a, lo, mid);
        merge(a, mid + 1, hi);
        mergeSort(a, lo, mid, hi);
    }
    private static void mergeSort(int[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
    }
    public static void draw() {
        StdDraw.setXscale(0, 10000000);
        StdDraw.setYscale(0, 2);
        for (int i = 1000; i < 10000000; i += 16000) {
            int[] arr = ints(i);
            int[] copy = intsCopy(arr);
            StdDraw.setPenColor(Color.red);
            StdDraw.point(i, merge_A(arr));
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(i, merge_B(copy));
        }
    }
    public static void main(String[] args) {
        int N = 1000;
        int[] arr = ints(N);
        int[] copy = intsCopy(arr);
        StdOut.printf("规模 %d  自顶向下 : %.3f\n", N, merge_A(arr));
        StdOut.printf("规模 %d  自底向上 : %.3f\n\n", N, merge_B(copy));
        
         N = 10000;
        arr = ints(N);
        copy = intsCopy(arr);
        StdOut.printf("规模 %d  自顶向下 : %.3f\n", N, merge_A(arr));
        StdOut.printf("规模 %d  自底向上 : %.3f\n\n", N, merge_B(copy));
        
         N = 100000;
        arr = ints(N);
        copy = intsCopy(arr);
        StdOut.printf("规模 %d  自顶向下 : %.3f\n", N, merge_A(arr));
        StdOut.printf("规模 %d  自底向上 : %.3f\n\n", N, merge_B(copy));
        
        N = 1000000;
        arr = ints(N);
        copy = intsCopy(arr);
        StdOut.printf("规模 %d  自顶向下 : %.3f\n", N, merge_A(arr));
        StdOut.printf("规模 %d  自底向上 : %.3f\n\n", N, merge_B(copy));
        draw();
    }
    // output
    /*
     *  规模 1000  自顶向下 : 0.002
        规模 1000  自底向上 : 0.001
        
        规模 10000  自顶向下 : 0.003
        规模 10000  自底向上 : 0.002
        
        规模 100000  自顶向下 : 0.015
        规模 100000  自底向上 : 0.016
        
        规模 1000000  自顶向下 : 0.214
        规模 1000000  自底向上 : 0.182
     */
}

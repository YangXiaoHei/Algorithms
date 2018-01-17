package Ch_2_2_Mergesort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_2_23 {
    private static int[] aux;
    public static double merge_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new int[a.length];
        merge(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double merge_B(int[] a, int cutoff) {
        Stopwatch timer = new Stopwatch();
        int[] aux = a.clone();
        merge(aux, a, 0, a.length - 1, cutoff);
        return timer.elapsedTime();
    }
    private static void merge(int[] src,int[] dst, int lo, int hi, int cutoff) {
        if (hi - lo <= cutoff) {
            insertion(dst, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        merge(dst, src, lo, mid, cutoff);
        merge(dst, src, mid + 1, hi, cutoff);
        if (src[mid] < src[mid + 1]) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, lo, mid, hi);
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= lo && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    private static void merge(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge(a, lo, mid);
        merge(a, mid + 1, hi);
        mergeSort(a, lo, mid, hi);
    }
    private static void mergeSort(int[] a, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
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
    public static void comparasion_A() {
        int[] arr = ints(20000000);
        int[] copy = intsCopy(arr);
        StdOut.printf("普通 : %.3f\n", merge_A(arr));
        StdOut.printf("改进 : %.3f\n", merge_B(copy, 8)); 
        StdOut.println("\n");
    }
    public static void comparasion_B() {
        int[] arr = ints(20000000);
        int[] copy = intsCopy(arr);
        int[] copy1 = intsCopy(arr);
        int[] copy2 = intsCopy(arr);
        int[] copy3 = intsCopy(arr);
        int[] copy4 = intsCopy(arr);
        StdOut.printf("改进 : %.3f\n", merge_B(arr, 4)); 
        StdOut.printf("改进 : %.3f\n", merge_B(copy, 5)); 
        StdOut.printf("改进 : %.3f\n", merge_B(copy1, 6));
        StdOut.printf("改进 : %.3f\n", merge_B(copy2, 7)); 
        StdOut.printf("改进 : %.3f\n", merge_B(copy3, 8));
        StdOut.printf("改进 : %.3f\n", merge_B(copy4, 9)); 
    }
    public static void main(String[] args) {
        comparasion_A();
        comparasion_B();
    }
    // output
    /*
     *  普通 : 3.421
        改进 : 3.253
        
        改进 : 3.187
        改进 : 3.253
        改进 : 3.298
        改进 : 3.174
        改进 : 3.349
        改进 : 3.138


     */
}

package Ch_2_2_Mergesort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_2_27 {
    private static int subArrLength;
    private static int[] aux;
    public static void merge(int[] a) {
        aux = new int[a.length];
        merge(a, 0, a.length - 1);
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
            if      (i > mid)         { a[k] = aux[j++]; subArrLength++; }
            else if (j > hi)          { a[k] = aux[i++]; subArrLength++; }
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
    }
    public static void main(String[] args) {
        int N = 8123053;
        int[] a = ints(N);
        merge(a);
        StdOut.printf("子数组平均长度 : %.3f\n", subArrLength * 1.0 / N);
    }
    // output
    /*
     * 子数组平均长度 : 1.277

     */
}   

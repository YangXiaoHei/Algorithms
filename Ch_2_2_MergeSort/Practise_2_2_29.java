package Ch_2_2_Mergesort;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_2_29 {
    public static int merge(int[] a) {
        int lo = 0, mid = 0, hi = 0, N = a.length, mergeCount = 0;
        int[] aux = new int[a.length];
        while ((mid = inversion(a, 0)) >= 0) {
            hi = inversion(a, mid + 1);
            hi = hi < 0 ? N - 1 : hi;
            mergeSort(a, aux, lo, mid, hi);
            mergeCount++;
        }
        return mergeCount;
    }
    private static void mergeSort(int[] a, int[] aux, int lo, int mid, int hi) {
        StdOut.printf("lo = %d mid = %d hi = %d\n", lo, mid + 1, hi);
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
    }
    private static int inversion(int[] a, int lo) {
        while (lo < a.length - 1 && a[lo + 1] >= a[lo]) lo++;
        return lo == a.length - 1 ? -1 : lo;
    }
    public static void main(String[] args) {
        int[] a = ints(10);
        print(a);
        StdOut.println(merge(a));
    }
    // output
    /*
     *  0       1       2       3       4       5       6       7       8       9       
        8       0       5       -1      4       -8      7       8       -6      2       
        lo = 0 mid = 1 hi = 2
        lo = 0 mid = 3 hi = 4
        lo = 0 mid = 5 hi = 7
        lo = 0 mid = 8 hi = 9
        4
        
        10^3 需要约 500 次归并
        10^6 需要约 500000 次归并
        10^9 需要约 500000000 次归并
        
     */
}

package Ch_2_2_Mergesort;

import static Ch_2_1_Elementary_Sorts.__Array.*;

public class Practise_2_2_16 {
    private static int[] aux;
    public static void merge(int[] a) {
        int N = a.length;
        aux = new int[N];
        for (int sz = 1; sz < N; sz += sz) 
            for (int lo = 0; lo < N - sz; lo += 2 * sz) 
                merge(a, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, N - 1));
    }
    private static void merge(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
    }
    public static void mergeNatural(int[] a) {
        if (a.length == 1) return;
        int lo = 0, mid = 0, hi = 0, N = a.length;
        aux = new int[N];
        while (true) {
            mid = 0;
            while (mid < N - 1 && a[mid] < a[mid + 1]) mid++;
            hi = mid + 1;
            while (hi < N - 1 && a[hi] < a[hi + 1]) hi++;
            if (hi == N) return;
            merge(a, lo, mid, hi);
        } 
    }
    public static void main(String[] args) {
        int[] arr = ints(10, 30);
        print(arr);
        mergeNatural(arr);
        print(arr);
    }
    // output
    /*
     * 
        0       1       2       3       4       5       6       7       8       9       10      11      12      13      14      15      16      17      18      19      20      
        10      16      17      14      11      23      19      12      13      22      21      26      27      18      24      25      30      28      15      29      20      
        
        0       1       2       3       4       5       6       7       8       9       10      11      12      13      14      15      16      17      18      19      20      
        10      11      12      13      14      15      16      17      18      19      20      21      22      23      24      25      26      27      28      29      30      

     */
}

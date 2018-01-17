package Ch_2_2_Mergesort;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_2_11 {
    public static double merge(int[] a) {
        Stopwatch timer = new Stopwatch();
        int[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void merge(int[] src, int[] dst, int lo, int hi) {
        if (hi - lo <= 7) {
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
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    private static void mergeSort(int[] src, int[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           dst[k] = src[j++];
            else if (j > hi)            dst[k] = src[i++];
            else if (src[j] < src[i])   dst[k] = src[j++];
            else                        dst[k] = src[i++];
    }
    public static void main(String[] args) {
        int[] arr = ints(10);
        merge(arr);
        print(arr);
    }
    // output
    /*
     * 
     *  0       1       2       3       4       5       6       7       8       9       
        -9      -9      -6      -2      3       3       6       6       8       8       

     */
}

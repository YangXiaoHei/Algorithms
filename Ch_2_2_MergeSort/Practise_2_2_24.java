package Ch_2_2_Mergesort;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_2_24 {
    private static int success;
    public static void merge(int[] a) {
        int[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
    }
    private static void merge(int[] src, int[] dst, int lo, int hi) {
        if (hi - lo <= 7) {
            insertion(dst, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        merge(dst, src, lo, mid);
        merge(dst, src, mid + 1, hi);
        if (success(src[mid], src[mid + 1])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, lo, mid, hi);
    }
    private static boolean success(int a, int b) {
        if (a < b) success++;
        return a < b;
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= lo && t < a[j]; j--)
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
    public static void draw() {
        StdDraw.setXscale(0, 10000000);
        StdDraw.setYscale(0, 5000);
        for (int i = 4000; i < 10000000; i += 4000) {
            success = 0;
            int[] arr = ints(i);
            merge(arr);
            StdDraw.point(i, success);
        }
    }
    public static void main(String[] args) {
        draw();
    }
    // output
    /*
     * 比较成功的次数 不依赖于 N, 而是依赖于输入
     */
}

package 第二章_归并排序;

import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;

public class Text {
    private static int[] aux;
    public static double merge(int[] a) {
        Stopwatch timer = new Stopwatch();
        int[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void merge(int[] src, int[] dest, int lo, int hi) {
        if (hi - lo <= 7) {
            insertion(dest, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        merge(dest, src, lo, mid);
        merge(dest, src, mid + 1, hi);
        if (src[mid] < src[mid + 1]) {
            System.arraycopy(src, lo, dest, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dest, lo, mid, hi);
    }
    public static double insertion(int[] a, int lo, int hi) {
        Stopwatch timer = new Stopwatch();
        for (int i = lo + 1; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= lo && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
        return timer.elapsedTime();
    }
    private static void mergeSort(int[] src, int[] dest, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) 
            if      (i > mid)           dest[k] = src[j++];
            else if (j > hi)            dest[k] = src[i++];
            else if (src[j] < src[i])   dest[k] = src[j++];
            else                        dest[k] = src[i++];
    }
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1]) {
                StdOut.printf("a[%d] = %d a[%d] = %d\n", i - 1, a[i - 1], i, a[i]);
                return false;
            }
        return true;
    }
    public static void main(String[] args) {
        int[] arr = intRandom_size(100000);
        StdOut.printf("规模 : %d 用时 : %.3f\n", arr.length, merge(arr));
        assert isSorted(arr);
    }
}

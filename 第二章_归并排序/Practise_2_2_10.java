package 第二章_归并排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_2_10 {
    private static int[] aux;
    public static double merge(int[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new int[a.length];
        merge(a, 0, a.length - 1);
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
        for (int k = lo; k <= mid; k++)
            aux[k] = a[k];
        for (int k = mid + 1; k <= hi; k++)
            aux[k] = a[hi - k + 1 + mid];
        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++)
            if (aux[j] < aux[i])    a[k] = aux[j--];
            else                    a[k] = aux[i++];
    }
    public static void main(String[] args) {
        int[] arr = ints(20);
        merge(arr);
        print(arr);
    }
    // output
    /*
     * 
        0       1       2       3       4       5       6       7       8       9       
        -8      -5      -3      -1      0       5       5       6       7       8       

     */
}

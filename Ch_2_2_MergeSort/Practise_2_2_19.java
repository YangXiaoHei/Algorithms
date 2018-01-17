package Ch_2_2_Mergesort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_2_19 {
    private static int[] aux;
    public static int merge(int[] a) {
        a = copy(a);
        aux = new int[a.length];
        return merge(a, 0, a.length - 1);
    }
    private static int merge(int[] a, int lo, int hi) {
        int inversions = 0;
        if (lo >= hi) return 0;
        int mid = (lo + hi) / 2;
        inversions += merge(a, lo, mid);
        inversions += merge(a, mid + 1, hi);
        inversions += mergeSort(a, lo, mid, hi);
        return inversions;
    }
    private static int mergeSort(int[] a, int lo, int mid, int hi) {
        int inversions = 0;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i]) { a[k] = aux[j++]; inversions += (mid - i + 1); }
            else                        a[k] = aux[i++];
        return inversions;
    }
    public static int brute(int[] a) {
        a = copy(a);
        int inversions = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++)
                if (a[j] < a[i]) inversions++;
        return inversions;
    }
    public static void main(String[] args) {
        int[] arr = ints(0, 20);
        StdOut.printf("线性对数级别的统计 : %d\n", merge(arr));
        StdOut.printf("暴力解法的统计 : %d\n", brute(arr));
    }
    // output
    /*
     *  线性对数级别的统计 : 137
        暴力解法的统计 : 137

     */
}

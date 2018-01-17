package Ch_2_2_MergeSort;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_2_20 {
    private static int[] aux;
    public static int[] merge(int[] a) {
        int[] perm = new int[a.length];
        aux = new int[a.length];
        for (int i = 0; i < a.length; i++)
            perm[i] = i;
        merge(a, perm, 0, a.length - 1);
        return perm;
    }
    private static void merge(int[] a, int[] perm, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge(a, perm, lo, mid);
        merge(a, perm, mid + 1, hi);
        mergeSort(a, perm, lo, mid, hi);
    }
    private static void mergeSort(int[] a, int[] perm, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = perm[k];
        for (int k = lo; k <= hi; k++)
            if      (i > mid)                   perm[k] = aux[j++];
            else if (j > hi)                    perm[k] = aux[i++];
            else if (a[aux[j]] < a[aux[i]])     perm[k] = aux[j++];
            else                                perm[k] = aux[i++];
    }
    public static void main(String[] args) {
        int[] arr = ints(10, 20);
        StdOut.println("============ 排序前原数组 ==============");
        print(arr);
        int[] perm = merge(arr);
        StdOut.println("\n\n============ 排序后 ==============");
        for (int i = 0; i < perm.length; i++)
            StdOut.print(arr[perm[i]] + "   ");
        StdOut.println("\n\n============ 排序后原数组 ==============");
        print(arr);
    }
    // output
    /*
     *  ============ 排序前原数组 ==============

        0       1       2       3       4       5       6       7       8       9       10      
        13      12      17      15      11      18      14      10      19      20      16      
        
        
        ============ 排序后 ==============
        10   11   12   13   14   15   16   17   18   19   20   
        
        ============ 排序后原数组 ==============
        
        0       1       2       3       4       5       6       7       8       9       10      
        13      12      17      15      11      18      14      10      19      20      16      

     */
}

package 第二章_归并排序;

import static 第二章_初级排序算法.Text_Array.*;

public class Practise_2_2_12 {
    public static void divideIntoMSizeBlockSort(int[] a, int M) {
        int N = a.length;
        if (N % M != 0)
            throw new IllegalArgumentException("M must be mutiple of array size!");
        int block = N / M;
        for (int k = 0, lo = k * M, hi = (k + 1) * M - 1; 
                k < block; 
                k++, lo = k * M, hi = (k + 1) * M - 1) 
            selection(a, lo, hi);
        for (int k = 0; k < block - 1; k++)
            mergeSort(a, 0, (k + 1) * M - 1, (k + 2) * M - 1);
    }
    private static void mergeSort(int[] a, int lo, int mid, int hi) {
        int[] aux = new int[hi - lo + 1];
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)       a[k] = aux[j++];
            else if (j > hi)        a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                    a[k] = aux[i++];
    }
    private static void selection(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int min = i;
            for (int j = i + 1; j <= hi; j++)
                if (a[j] < a[min]) min = j;
            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }
    public static void main(String[] args) {
        int[] a = ints(21);
        divideIntoMSizeBlockSort(a, 7);
        print(a);
    }
    // output
    /*
     * 
        0       1       2       3       4       5       6       7       8       9       10      11      12      13      14      15      16      17      18      19      20      
        -21     -20     -19     -17     -17     -16     -12     -9      -6      3       5       5       6       9       10      14      15      17      17      19      20      

     */
}

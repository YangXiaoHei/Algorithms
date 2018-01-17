package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_19 {
    public static long count(int[] a) {
        int[] b = a.clone();
        int[] aux = a.clone();
        return count(b, aux, 0, a.length - 1);
    }
    private static long count(int[] b, int[] aux, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = (lo + hi) >> 1;
        long inversions = 0;
        inversions += count(b, aux, lo, mid);
        inversions += count(b, aux, mid + 1, hi);
        inversions += mergeSort(b, aux, lo, mid, hi);
        return inversions;
    }
    private static int mergeSort(int[] b, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) 
            aux[k] = b[k];
        int i = lo, j = mid + 1, inversions = 0;;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           b[k] = aux[j++];
            else if (j > hi)            b[k] = aux[i++];
            else if (aux[j] < aux[i]) { b[k] = aux[j++]; inversions += (mid - i + 1); }
            else                        b[k] = aux[i++];
        return inversions;
    }
    /*
     * 遵守的约定是 a b 的长度一致，并且 a 中所有元素都能在 b 中找到，反之亦然
     * a 和 b 的序列是 0 ~ N - 1 的不同置换
     * 所以该函数不做参数的合法性校验
     */
    public static long KendallTau(int[] a, int[] b) {
        int[] aIndexs = new int[a.length];
        int[] aIndexsMappedByb = new int[a.length];
        for (int i = 0; i < a.length; i++)
            aIndexs[a[i]] = i;
        for (int i = 0; i < a.length; i++)
            aIndexsMappedByb[i] = aIndexs[b[i]];
        return count(aIndexsMappedByb);
    }
    public static void main(String[] args) {
        int[] a = ints(0, 5);
        int[] b = ints(0, 5);
        StdOut.print("a : ");
        print(a);
        StdOut.print("b : ");
        print(b);
        StdOut.printf("kendallTau distance : %d\n", KendallTau(a, b));
    }
    // output
    /*
     *  a : 
        0   1   2   3   4   5   
        1   3   4   2   5   0   
        b : 
        0   1   2   3   4   5   
        4   5   0   1   2   3   
        kendallTau distance : 9

     */
}

package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

public class Text_Review {
    public static void quick(int[] a) {
        quick(a, 0, a.length - 1);
    }
    private static void quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 7) {
            insertion(a, lo, hi);
            return;
        }
        int mid = (lo + hi) >> 1;
        if (a[mid] < a[lo]) exch(a, mid, lo);
        if (a[hi] < a[lo])  exch(a, hi, lo);
        if (a[mid] < a[hi]) exch(a, mid, hi);
        
        int i = lo - 1, p = lo - 1, j = hi, q = hi, v = a[hi];
        while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
            if (a[i] == v) exch(a, ++p, i);
            if (a[j] == v) exch(a, --q, j);
        }
        exch(a, i, hi);
        
        int lt = i - 1, gt = i + 1, k = lo, m = hi - 1;
        while (k <= p) exch(a, k++, lt--);
        while (m >= q) exch(a, m--, gt++);
        
        quick(a, lo, lt);
        quick(a, gt, hi);
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= lo && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1]) return false;
        return true;
    }
    public static void main(String[] args) {
//        int[] a = ints(0, 10);
//        quick(a);
//        print(a);
//        assert isSorted(a);
//        StdOut.println("排序成功!");
        int i, b = 0, k = 0;
        for (i = 1; i <= 5; i++) {
            b = i % 2;
            while (b-- >= 0) 
                k++;
        }
        StdOut.printf("%d %d", k, b);
    }
}

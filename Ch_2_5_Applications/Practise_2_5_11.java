package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_11 {
    /*
     * 间接的快速排序
     */
    public static int[] quick(int[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++) 
            p[i] = i;
        quick(a, p, 0, a.length - 1);
        return p;
    }
    private static void quick(int[] a, int[] p, int lo, int hi) {
        if (hi - lo + 1 < 7) {
            for (int i = lo; i <= hi; i++) {
                int t = a[p[i]], j;
                int index = p[i];
                for (j = i - 1; j >= lo && t < a[p[j]]; j--)
                    p[j + 1] = p[j];
                p[j + 1] = index;
            }
            return;
        }
        int mid = (lo + hi) >> 1;
        if (less(a, p, mid, lo)) exch(p, mid, lo);
        if (less(a, p, hi, lo)) exch(p, hi, lo);
        if (less(a, p, mid, hi)) exch(p, mid, hi);
        int v = hi, i = lo - 1, j = hi, P = lo - 1, Q = hi;
        while (true) {
            while (less(a, p, ++i, hi));
            while (less(a, p, hi, --j));
            if (i >= j) break;
            exch(p, i, j);
            if (equal(a, p, i, v)) exch(p, ++P, i);
            if (equal(a, p, j, v)) exch(p, --Q, j);
        }
        exch(p, i, hi);
        int lt = i - 1, gt = i + 1, m = lo, n = hi - 1;
        while (m <= P) exch(p, m++, lt--);
        while (n >= Q) exch(p, n--, gt++);
        quick(a, p, lo, lt);
        quick(a, p, gt, hi);
    }
    /*
     * 间接的插入排序
     */
    public static int[] insertion(int[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        for (int i = 0; i < a.length; i++) {
            int t = a[p[i]], j;
            int index = p[i];
            for (j = i - 1; j >= 0 && t < a[p[j]]; j--)
                p[j + 1] = p[j];
            p[j + 1] = index;
        }
        return p;
    }
    /*
     * 间接的选择排序
     */
    public static int[] selection(int[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        for (int i = 0; i < p.length; i++) {
            int min = a[p[i]], minIndex = i;
            for (int j = i + 1; j < p.length; j++) {
                if (a[p[j]] < min) {
                    min = a[p[j]];
                    minIndex = j;
                }
            }
            int t = p[i];
            p[i] = p[minIndex];
            p[minIndex] = t;
        }
        return p;
    }
    /*
     * 间接的希尔排序
     */
    public static int[] shell(int[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        int[] ints = { 4188161, 2354689, 1045055, 587521, 260609, 
                146305, 64769, 36289, 16001, 8929, 3905, 2161, 
                929, 505, 209, 109, 41, 19, 5, 1};
        for (int k = 0; k < ints.length; k++) {
            for (int h = ints[k], i = h; i < p.length; i++) {
                int t = a[p[i]], index = p[i], j;
                for (j = i - h; j >= 0 && t < a[p[j]]; j -= h) 
                    p[j + h] = p[j];
                p[j + h] = index;
            }
        }
        return p;
    }
    /*
     * 间接的归并排序
     */
    public static int[] merge(int[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        int[] aux = p.clone();
        merge(aux, p, a, 0, a.length - 1);
        return p;
    }
    private static void merge(int[] src, int[] dst, int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >> 1;
        merge(dst, src, a, lo, mid);
        merge(dst, src, a, mid + 1, hi);
        if (a[src[mid]] < a[src[mid + 1]]) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, a, lo, mid, hi);
    }
    private static void mergeSort(int[] src, int[] dst, int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)               dst[k] = src[j++];
            else if (j > hi)                dst[k] = src[i++];
            else if (a[src[j]] < a[src[i]]) dst[k] = src[j++];
            else                            dst[k] = src[i++];
    }
    /*
     * 间接的堆排序
     */
    public static int[] heap(int[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        int N = p.length;
        for (int i = N >> 1; i > 0; i--) {
            int k = i, j, index = p[i - 1];
            while ((j = k << 1) <= N) {
                if (j < N && a[p[j - 1]] < a[p[j]]) j++;
                if (a[index] >= a[p[j - 1]]) break;
                p[k - 1] = p[j - 1];
                k = j;
            }
            p[k - 1] = index;
        }
        // 使用 floyd 方法改进以减少比较次数
        while (N > 1) {
            int index = p[N - 1];
            p[N - 1] = p[0];
            --N;
            int k = 1, j;
            while ((j = k << 1) <= N) {
                if (j < N && a[p[j - 1]] < a[p[j]]) j++;
                p[k - 1] = p[j - 1];
                k = j;
            }
            p[k - 1] = index;
            while (k > 1 && a[index] > a[p[(k >> 1) - 1]]) {
                p[k - 1] = p[(k >> 1) - 1];
                k >>= 1;
            }
            p[k - 1] = index;
        }
        return p;
    }
    private static final void exch(int[] p, int i, int j) {
        int t = p[i]; p[i] = p[j]; p[j] = t;
    }
    private static final boolean equal(int[] a, int[] p, int i, int j) {
        return a[p[i]] == a[p[j]];
    }
    private static final boolean less(int[] a, int[] p, int i, int j) {
        return a[p[i]] < a[p[j]];
    }
    /*
     * 正确性测试
     */
    public static void correctTest() {
        int[] a = ints(0, 10);
        int[] p = heap(a);
        for (int i = 0; i < p.length; i++)
            StdOut.print(a[p[i]] + " ");
    }
    public static void main(String[] args) {
        int[] forQuick = allSameInts(7, 1);
        int[] forInsertion = intsCopy(forQuick);
        int[] forSelection = intsCopy(forQuick);
        int[] forShell = intsCopy(forQuick);
        int[] forMerge = intsCopy(forQuick);
        int[] forHeap = intsCopy(forQuick);
        
        int[] quickP = quick(forQuick);
        int[] insertionP = insertion(forInsertion);
        int[] selectionP = selection(forSelection);
        int[] shellP = shell(forShell);
        int[] mergeP = merge(forMerge);
        int[] heapP = heap(forHeap);
        StdOut.println("快速排序 : ");
        print(quickP);
        StdOut.println("\n插入排序 : ");
        print(insertionP);
        StdOut.println("\n选择排序 : ");
        print(selectionP);
        StdOut.println("\n希尔排序 : ");
        print(shellP);
        StdOut.println("\n归并排序 : ");
        print(mergeP);
        StdOut.println("\n堆排序 : ");
        print(heapP);
    }
    // output
    /*
     *  快速排序 : 

        0   1   2   3   4   5   6   
        5   4   3   2   1   0   6   
        
        插入排序 : 
        
        0   1   2   3   4   5   6   
        0   1   2   3   4   5   6   
        
        选择排序 : 
        
        0   1   2   3   4   5   6   
        0   1   2   3   4   5   6   
        
        希尔排序 : 
        
        0   1   2   3   4   5   6   
        0   1   2   3   4   5   6   
        
        归并排序 : 
        
        0   1   2   3   4   5   6   
        0   1   2   3   4   5   6   
        
        堆排序 : 
        
        0   1   2   3   4   5   6   
        2   4   5   6   3   1   0   
     */
}

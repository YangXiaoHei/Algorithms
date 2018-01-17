package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_17 {
    public static int[] quick(Comparable[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++) 
            p[i] = i;
        quick(a, p, 0, a.length - 1);
        return p;
    }
    private static void quick(Comparable[] a, int[] p, int lo, int hi) {
        if (hi - lo + 1 < 7) {
            for (int i = lo; i <= hi; i++) {
                Comparable t = a[p[i]];
                int index = p[i], j;
                for (j = i - 1; j >= lo && t.compareTo(a[p[j]]) < 0; j--)
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
    private static final void exch(int[] p, int i, int j) {
        int t = p[i]; p[i] = p[j]; p[j] = t;
    }
    private static final boolean equal(Comparable[] a, int[] p, int i, int j) {
        return a[p[i]].compareTo(a[p[j]]) == 0;
    }
    private static final boolean less(Comparable[] a, int[] p, int i, int j) {
        return a[p[i]].compareTo(a[p[j]]) < 0;
    }
    public static int[] insertion(Comparable[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        for (int i = 0; i < a.length; i++) {
            Comparable t = a[p[i]]; int j;
            int index = p[i];
            for (j = i - 1; j >= 0 && t.compareTo(a[p[j]]) < 0; j--)
                p[j + 1] = p[j];
            p[j + 1] = index;
        }
        return p;
    }
    public static int[] selection(Comparable[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        for (int i = 0; i < p.length; i++) {
            Comparable min = a[p[i]]; int minIndex = i;
            for (int j = i + 1; j < p.length; j++) {
                if (a[p[j]].compareTo(min) < 0) {
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
    public static int[] shell(Comparable[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        int[] ints = { 4188161, 2354689, 1045055, 587521, 260609, 
                146305, 64769, 36289, 16001, 8929, 3905, 2161, 
                929, 505, 209, 109, 41, 19, 5, 1};
        for (int k = 0; k < ints.length; k++) {
            for (int h = ints[k], i = h; i < p.length; i++) {
                Comparable t = a[p[i]]; int index = p[i], j;
                for (j = i - h; j >= 0 && t.compareTo(a[p[j]]) < 0; j -= h) 
                    p[j + h] = p[j];
                p[j + h] = index;
            }
        }
        return p;
    }
    public static int[] merge(Comparable[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        int[] aux = p.clone();
        merge(aux, p, a, 0, a.length - 1);
        return p;
    }
    private static void merge(int[] src, int[] dst, Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >> 1;
        merge(dst, src, a, lo, mid);
        merge(dst, src, a, mid + 1, hi);
        if (a[src[mid]].compareTo(a[src[mid + 1]]) < 0) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, a, lo, mid, hi);
    }
    private static void mergeSort(int[] src, int[] dst, Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) 
            if      (i > mid)                               dst[k] = src[j++];
            else if (j > hi)                                dst[k] = src[i++];
            else if (a[src[j]].compareTo(a[src[i]]) < 0)    dst[k] = src[j++];
            else                                            dst[k] = src[i++];
    }
    public static int[] heap(Comparable[] a) {
        int[] p = new int[a.length];
        for (int i = 0; i < a.length; i++)
            p[i] = i;
        int N = p.length;
        for (int i = N >> 1; i > 0; i--) {
            int k = i, index = p[i - 1], j;
            while ((j = k << 1) <= N) {
                if (j < N && a[p[j - 1]].compareTo(a[p[j]]) < 0) j++;
                if (a[index].compareTo(a[p[j - 1]]) >= 0) break;
                p[k - 1] = p[j - 1];
                k = j;
            }
            p[k - 1] = index;
        }
        while (N > 1) {
            int t = p[N - 1];
            p[N - 1] = p[0];
            p[0] = t;
            --N;
            int k = 1, j, index = p[0];
            while ((j = k << 1) <= N) {
                if (j < N && a[p[j - 1]].compareTo(a[p[j]]) < 0) j++;
                if (a[index].compareTo(a[p[j - 1]]) >= 0) break;
                p[k - 1] = p[j - 1];
                k = j;
            }
            p[k - 1] = index;
        }
        return p;
    }
    public static boolean stable(int[] p, Comparable[] b) {
        for (int i = 1; i < p.length; i++) {
            if (b[p[i]].compareTo(b[p[i - 1]]) == 0)
                if (p[i] < p[i - 1]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Integer[] a = intToInteger(intsVrg(10, 1, 2, 3, 4));
        int[] heapP = heap(a);
        int[] mergeP = merge(a);
        int[] quickP = quick(a);
        int[] selectionP = selection(a);
        int[] shellP = shell(a);
        int[] insertionP = insertion(a);
        StdOut.printf("堆排序是否稳定 : %s\n", stable(heapP, a));
        StdOut.printf("归并排序是否稳定 : %s\n", stable(mergeP, a));
        StdOut.printf("快速排序是否稳定 : %s\n", stable(quickP, a));
        StdOut.printf("归并排序是否稳定 : %s\n", stable(selectionP, a));
        StdOut.printf("希尔排序是否稳定 : %s\n", stable(shellP, a));
        StdOut.printf("插入排序是否稳定 : %s\n", stable(insertionP, a));
    }
    // output
    /*
     *  堆排序是否稳定 : false
        归并排序是否稳定 : true
        快速排序是否稳定 : false
        归并排序是否稳定 : false
        希尔排序是否稳定 : false
        插入排序是否稳定 : true

     */
}

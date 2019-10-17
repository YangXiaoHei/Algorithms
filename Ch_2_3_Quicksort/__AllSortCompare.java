package Ch_2_3_Quicksort;

import edu.princeton.cs.algs4.*;
import static Tool.ArrayGenerator.*;
import java.util.Arrays;

public class __AllSortCompare {
    public static double JDK(int[] a) {
        Stopwatch timer = new Stopwatch();
        Arrays.sort(a);
        return timer.elapsedTime();
    }
    public static double pigeonHole(int[] a) {
        Stopwatch timer = new Stopwatch();
        int max = a[0], min = a[0];
        for (int i = 1; i < a.length; i++)  // 同时找到最大最小值，以便减小空间复杂度
            if      (a[i] > max) max = a[i];
            else if (a[i] < min) min = a[i];
        int k = 0, N = max - min + 1; 
        int[] count = new int[N];
        for (int i = 0; i < a.length; i++)
            count[a[i] - min]++;
        for (int i = 0; i < N; i++)
            for (int j = 1; j <= count[i]; j++)
                a[k++] = i + min;
        return timer.elapsedTime();
    }
    public static double insertion(int[] a) {
        Stopwatch timer = new Stopwatch();
        insertion(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double shell(int[] a) {
        Stopwatch timer = new Stopwatch();
        shell(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double merge(int[] a) {
        Stopwatch timer = new Stopwatch();
        int[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void exch(int[] a, int j, int i) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    private static void quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 7) {
            insertion(a, lo, hi);
            return;
        }
        
//        1 9 5 -> 
        int mid = (lo + hi) >> 1;
        // 保证 lo 不是最大
        if (a[mid] < a[lo]) exch (a, mid, lo); 
        // 保证 lo 是最小
        if (a[hi] < a[lo])   exch (a, hi, lo);
        // 保证 mid 是中间
        if (a[mid] < a[hi])  exch (a, mid, hi); 
        
        // pivot = 4
        //
        /*
         * 
         * 
         * 
         * 3   2   3   1   1   4   1   0   2   4   4   2   0   4   2
         * 
         */
        
        int i = lo - 1, p = lo - 1, j = hi, q = hi, v = a[hi];
        while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
             exch(a, i, j); 
             
            if (a[i] == v)  exch(a, i, ++p); 
            if (a[j] == v)  exch(a, j, --q);  
        }
         exch(a, hi, i); 
        
        int lt = i - 1, gt = i + 1, k = lo, m = hi - 1;
        while (k <= p)  
            exch(a, k++, lt--); 
        while (m >= q)  
            exch(a, m--, gt++); 
        
        quick(a, lo, lt);
        quick(a, gt, hi);
    }
    private static void merge(int[] src, int[] dst, int lo, int hi) {
        if (hi - lo + 1 < 7) {
            insertion(dst, lo, hi);
            return;
        }
        int mid = (lo + hi) >> 1;
        merge(dst, src, lo, mid);
        merge(dst, src, mid + 1, hi);
        if (src[mid] < src[mid + 1]) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        mergeSort(src, dst, lo, mid, hi);
    }
    private static void mergeSort(int[] src, int[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)           dst[k] = src[j++];
            else if (j > hi)            dst[k] = src[i++];
            else if (src[j] < src[i])   dst[k] = src[j++];
            else                        dst[k] = src[i++];
    }
    private static void shell(int[] a, int lo, int hi) {
        int[] ints  = { 4188161, 2354689, 1045055, 587521, 260609, 
                        146305, 64769, 36289, 16001, 8929, 3905, 2161, 
                        929, 505, 209, 109, 41, 19, 5, 1};
        for (int k = 0; k < ints.length; k++)
            for (int h = ints[k], i = h; i < a.length; i++) {
                int t = a[i], j;
                for (j = i - h; j >= lo && t < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= lo && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
//    private static void exch(int[] a, int i, int j) {
//        int t = a[i]; a[i] = a[j]; a[j] = t;
//    }
    
    public static void main(String[] args) {
//        int[] a = ints(0, 10000000);
//        int[] a = allSameInts(10000000, 0);
//        int[] a = descendInts(10000000, 0);
//        int[] a = ascendInts(0, 10000000);
        int[] a = intsVrg(10000000, 1, 2, 3, 4, 5, 6, 7, 8);
        int[] copy = copy(a);
        int[] copy1 = copy(a);
        int[] copy2 = copy(a);
        int[] copy3 = copy(a);
        StdOut.printf("希尔排序 ：\t%.3f\n", shell(a));
        StdOut.printf("归并排序 ：\t%.3f\n", merge(copy));
        StdOut.printf("快速排序 ：\t%.3f\n", quick(copy1));
        StdOut.printf("鸽巢排序 ：\t%.3f\n", pigeonHole(copy3));
        StdOut.printf("JDK ：%.3f\n", JDK(copy2));
    }
    // output
    /*
     *  随机不重复元素
     *  
     *  希尔排序 ：  3.358
        归并排序 ：  2.909
        快速排序 ：  2.847
        鸽巢排序 ：  0.484
        JDK ：1.992

        全部元素都相同
         
        希尔排序 ：  0.195
        归并排序 ：  1.104
        快速排序 ：  0.086
        鸽巢排序 ：  0.125
        JDK ：0.020

        逆序数组
        
        希尔排序 ：  1.051
        归并排序 ：  1.093
        快速排序 ：  0.692
        鸽巢排序 ：  0.125
        JDK ：0.054

        已排序的数组
        
        希尔排序 ：  0.251
        归并排序 ：  0.439
        快速排序 ：  0.789
        鸽巢排序 ：  0.170
        JDK ：0.112


        大量重复元素的数组
        
        希尔排序 ：  1.462
        归并排序 ：  1.812
        快速排序 ：  0.369
        鸽巢排序 ：  0.111
        JDK ：0.367


     */
}

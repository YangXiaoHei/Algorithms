package Ch_2_2_Mergesort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_2_22 {
    private static int[] aux;
    public static double merge_3(int[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new int[a.length];
        merge_3(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void merge_3(int[] a, int lo, int hi) {
        if (lo >= hi) return;   // 递归基和分割点的选取很重要，下面三句代码卡了我好久...
        int mid_left = lo + (hi - lo) / 3;
        int mid_right = hi - (hi - lo) / 3;
        merge_3(a, lo, mid_left);
        merge_3(a, mid_left + 1, mid_right);
        merge_3(a, mid_right + 1, hi);
        mergeSort_3(a, lo, mid_left, mid_right, hi);
    }
    private static int minIndex(int[] a, int i, int j, int k) {
        if      (Math.min(Math.min(a[i], a[j]), a[k]) == a[k]) return k;
        else if (Math.min(a[i], a[j]) == a[i]) return i;
        else    return j;
    }
    private static void mergeSort_3(int[] a, int lo, int mid_left, int mid_right, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid_left + 1, k = mid_right + 1;
        for (int m = lo; m <= hi; m++) {
            if  (i > mid_left) {    // 左侧取完
                // 转化为二路归并
                if      (j > mid_right)     a[m] = aux[k++];
                else if (k > hi)            a[m] = aux[j++];
                else if (aux[j] < aux[k])   a[m] = aux[j++];
                else                        a[m] = aux[k++];
            } else if (j > mid_right) { // 中间取完
                // 转化为二路归并
                if      (i > mid_left)      a[m] = aux[k++];
                else if (k > hi)            a[m] = aux[i++];
                else if (aux[i] < aux[k])   a[m] = aux[i++];
                else                        a[m] = aux[k++];
            } else if (k > hi) { // 右侧取完
                // 转化为二路归并
                if      (j > mid_right)     a[m] = aux[i++];
                else if (i > mid_left)      a[m] = aux[j++];
                else if (aux[j] < aux[i])   a[m] = aux[j++];
                else                        a[m] = aux[i++];
            } else if (minIndex(aux, i, j, k) == i) {
                a[m] = aux[i++];
            } else if (minIndex(aux, i, j, k) == j) {
                a[m] = aux[j++];
            } else if (minIndex(aux, i, j, k) == k) {
                a[m] = aux[k++];
            }
        }
    }
    /*
     * 接近线性级别的增长
     */
    public static void doublingRatioTest() {
        double pre = 0, cur = 0;
        for (int i = 100, j = 0; j < 24; j++, i += i) {
            int[] a = ints(i);
            StdOut.printf(
                    "规模 : %d "
                    + "耗时 : %.3f "
                    + "倍率 : %.1f\n", 
                    i, 
                    (cur = merge_3(a)), 
                    cur / pre);
            pre = cur;
        }
    }
    public static void main(String[] args) {
        doublingRatioTest();
    }
    // output
    /*
     *  规模 : 100 耗时 : 0.001 倍率 : Infinity
        规模 : 200 耗时 : 0.000 倍率 : 0.0
        规模 : 400 耗时 : 0.000 倍率 : NaN
        规模 : 800 耗时 : 0.001 倍率 : Infinity
        规模 : 1600 耗时 : 0.000 倍率 : 0.0
        规模 : 3200 耗时 : 0.001 倍率 : Infinity
        规模 : 6400 耗时 : 0.002 倍率 : 2.0
        规模 : 12800 耗时 : 0.004 倍率 : 2.0
        规模 : 25600 耗时 : 0.008 倍率 : 2.0
        规模 : 51200 耗时 : 0.007 倍率 : 0.9
        规模 : 102400 耗时 : 0.015 倍率 : 2.1
        规模 : 204800 耗时 : 0.025 倍率 : 1.7
        规模 : 409600 耗时 : 0.078 倍率 : 3.1
        规模 : 819200 耗时 : 0.164 倍率 : 2.1
        规模 : 1638400 耗时 : 0.312 倍率 : 1.9
        规模 : 3276800 耗时 : 0.514 倍率 : 1.6
        规模 : 6553600 耗时 : 0.995 倍率 : 1.9
        规模 : 13107200 耗时 : 2.304 倍率 : 2.3
        规模 : 26214400 耗时 : 4.842 倍率 : 2.1
        规模 : 52428800 耗时 : 9.954 倍率 : 2.1
        
        Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
        at java.lang.Integer.valueOf(Integer.java:832)
        at 第二章_初级排序算法.Text_Array.Integers(Text_Array.java:238)
        at 第二章_初级排序算法.Text_Array.ints(Text_Array.java:446)
        at 第二章_归并排序.Practise_2_2_22.doublingRatioTest(Practise_2_2_22.java:63)
        at 第二章_归并排序.Practise_2_2_22.main(Practise_2_2_22.java:70)
     */
}

package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_3_13 {
    private static int depth;
    public static int quick(int[] a) {
        depth = 0;
        quick(a, 0, a.length - 1);
        return --depth;
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) 
            return; 
        depth++;
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static int parition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t; 
    }
    public static void main(String[] args) {
        /*
         * 最坏情况是 N - 1
         * 
         * 最佳情况是 logN  现在这种测法不能用
         */
        int[] a = ascendInts(0, 1000);
        StdOut.printf("最坏情况 递归最大深度 : %d\n", quick(a));
        
        a = descendInts(1000, 0);
        StdOut.printf("最坏情况 递归最大深度 : %d\n", quick(a));
    }
    // output
    /*
     *  最坏情况 递归最大深度 : 999
        最坏情况 递归最大深度 : 999

     */
}

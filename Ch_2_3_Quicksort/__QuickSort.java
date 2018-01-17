package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class __QuickSort {
    public static void quick(int[] a) {
        quick(a, 0, a.length - 1);
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (i < hi && less(a[++i], a[lo]));
            while (j > lo && less(a[lo], a[--j]));
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    private static int compares = 0;
    private static boolean less(int a, int b) {
        compares++;
        return a < b;
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static void normalCase() {
        compares = 0;
        int N = 100;
        int[] a = ints(0, N - 1);
        quick(a);
        print(a);
        StdOut.printf("比较次数 : %d 理论值 : %d\n", compares, (int)(2 * N * Math.log(N)));
    }
    public static void worstCase1() {
        compares = 0;
        int N = 100;
        
        int[] a = ascendInts(0, N - 1);
        quick(a);
        print(a);
        
        StdOut.printf("比较次数 : %d 理论值 : %d\n", compares, (N + 1) * N / 2);
    }
    public static void worstCase2() {
        compares = 0;
        int N = 100;
        
        int[] a = descendInts(N - 1, 0);
        quick(a);
        print(a);
        
        StdOut.printf("比较次数 : %d 理论值 : %d\n", compares, (N + 1) * N / 2);
    }
    public static void main(String[] args) {
        normalCase();
    }
    // output
    /*
     * 
        0       1       2       3       4       5       6       7       8       9       10      
        2       5       0       6       3       9       4       1       7       8       10      
        
        0       1       2       3       4       5       6       7       8       9       10      
        2       5       0       6       3       9       4       1       7       8       10      
        lo = 0 切分结果 : i = 1 j = 7
        
        0       1       2       3       4       5       6       7       8       9       10      
        2       1       0       6       3       9       4       5       7       8       10      
        切分结束 j = 2
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       9       4       5       7       8       10      
        //////////////////////////////
        将进入第一层 {lo = 0 hi = 1}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       9       4       5       7       8       10      
        切分结束 j = 0
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       9       4       5       7       8       10      
        //////////////////////////////
        将进入第一层 {lo = 0 hi = -1}
        lo = 0 hi = -1 ---返回---
        从 {lo = 0, hi = -1} 退出，将进入第二层 {lo = 1 hi = 1}
        lo = 1 hi = 1 ---返回---
        从 {lo = 0, hi = 1} 退出，将进入第二层 {lo = 3 hi = 10}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       9       4       5       7       8       10      
        lo = 3 切分结果 : i = 5 j = 7
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       6       3       5       4       9       7       8       10      
        切分结束 j = 6
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       4       3       5       6       9       7       8       10      
        //////////////////////////////
        将进入第一层 {lo = 3 hi = 5}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       4       3       5       6       9       7       8       10      
        切分结束 j = 4
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       9       7       8       10      
        //////////////////////////////
        将进入第一层 {lo = 3 hi = 3}
        lo = 3 hi = 3 ---返回---
        从 {lo = 3, hi = 3} 退出，将进入第二层 {lo = 5 hi = 5}
        lo = 5 hi = 5 ---返回---
        从 {lo = 3, hi = 5} 退出，将进入第二层 {lo = 7 hi = 10}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       9       7       8       10      
        切分结束 j = 9
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       8       7       9       10      
        //////////////////////////////
        将进入第一层 {lo = 7 hi = 8}
        
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       8       7       9       10      
        切分结束 j = 8
        //////////////////////////////
        0       1       2       3       4       5       6       7       8       9       10      
        0       1       2       3       4       5       6       7       8       9       10      
        //////////////////////////////
        将进入第一层 {lo = 7 hi = 7}
        lo = 7 hi = 7 ---返回---
        从 {lo = 7, hi = 7} 退出，将进入第二层 {lo = 9 hi = 8}
        lo = 9 hi = 8 ---返回---
        从 {lo = 7, hi = 8} 退出，将进入第二层 {lo = 10 hi = 10}
        lo = 10 hi = 10 ---返回---
     */
}

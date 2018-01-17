package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.StdOut;

/*
 * 选择排序是交换次数最少的排序
 * 快速排序次之
 * 接着是希尔排序
 * 最后是插入排序
 */
public class __ExchangesCompare {
    private static int exchanges;
    public static int selection(int[] a) {
        exchanges = 0;
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[j] < a[min]) min = j;
            exch(a, min, i);
        }
        return exchanges;
    }
    public static int insertion(int[] a) {
        exchanges = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i - 1; j >= 0 && a[j + 1] < a[j]; j--)
                exch(a, j + 1, j);
        }
        return exchanges;
    }
    public static int shell(int[] a) {
        exchanges = 0;
        int[] ints = { 4188161, 2354689, 1045055, 587521, 260609, 
                146305, 64769, 36289, 16001, 8929, 3905, 2161, 
                929, 505, 209, 109, 41, 19, 5, 1};
        for (int k = 0; k < ints.length; k++)
        for (int h = ints[k], i = h; i < a.length; i++) {
            for (int j = i - h; j >= 0 && a[j + h] < a[j]; j -= h) 
                exch(a, j + h, j);
        }
        return exchanges;
    }
    public static int quick(int[] a) {
        exchanges = 0;
        quick(a, 0, a.length - 1);
        return exchanges;
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    
    public static void exch(int[] a, int i, int j) {
        exchanges++;
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = ints(0, 100);
        int[] copy = intsCopy(a);
        int[] copy1 = intsCopy(a);
        int[] copy2 = intsCopy(a);
        StdOut.printf("选择 : %d\n", selection(a));
        StdOut.printf("插入 : %d\n", insertion(copy));
        StdOut.printf("希尔 : %d\n", shell(copy1));
        StdOut.printf("快速 : %d\n", quick(copy2));
        assert isSorted(a);
        assert isSorted(copy);
        assert isSorted(copy1);
        assert isSorted(copy2);
        StdOut.println("排序成功");
    }
    // output
    /*
     *  选择 : 101
        插入 : 2521
        希尔 : 465
        快速 : 162
        排序成功
     */
}

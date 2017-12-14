package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_3_03 {
    private static int exchanges;
    private static int max;
    public static void quick (int[] a) {
        exchanges = 0;
        max = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] > max) max = a[i];
        quick(a, 0, a.length - 1);
    }
    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = parition(a, lo, hi);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    private static int parition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (++i <= hi && a[i] < v);
            while (--j >= lo && a[j] > v);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }
    private static void exch(int[] a, int i, int j) {
        if (a[i] == max || a[j] == max) exchanges++;
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        /*
         * 假设现在有一个降序序列
         * 
         * 5 4 3 2 1 0
         * 
         * 将 5 选做枢轴，那么整个排序过程中，只会被交换一次，也就是最开始的那一次
         * 
         * 假设有一个升序序列
         * 
         * 0 1 2 3 4 5
         * 
         * 5 自始至终都没有参与交换
         * 
         * 假设是一个全部相等的序列
         * 
         * 5 5 5 5 5 5
         * 
         * 5 5 5   5  5 5     --> 3 次
         * 
         * 5   5  5  5  5 5   --> 2 次
         * 
         * 5   5  5  5  5  5  --> 1 次
         * 
         * 总共 6 次
         * 
         * 下面是随机无重复序列的实验
         */
        while (true) {
            int[] a = ints(0, 10);
            int[] copy = intsCopy(a);
            quick(a);
            assert isSorted(a);
            if (exchanges >= 5) {
                print(copy);
                StdOut.printf("最大元素 %d 的交换次数为 : %d\n", max, exchanges);
                break;
            }
        }
        /*
         * 我们可以发现，最大元素 10 的交换次数最多是 5 次
         * 
         * 
         * 
         * 
         */
    }
    // output
    /*
     * 
        0   1   2   3   4   5   6   7   8   9   10  
        2   9   10  4   1   6   3   8   5   7   0   
        最大元素 10 的交换次数为 : 5
        
        
        0   1   2   3   4   5   6   7   8   9   10  
        1   10  3   0   5   2   7   4   6   9   8   
        最大元素 10 的交换次数为 : 5


     */
}

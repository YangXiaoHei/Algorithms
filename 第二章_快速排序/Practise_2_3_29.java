package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_29 {
    public static double _quick(int[] a, int M) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < a.length; i++) {
            int r = i + StdRandom.uniform(0, a.length - i);
            int t = a[r];
            a[r] = a[i];
            a[i] = t;
        }
        _quick(a, 0, a.length - 1, M);
        return timer.elapsedTime();
    }
    private static void _quick(int[] a, int lo, int hi, int M) {
        if (hi - lo + 1 < M) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[lo]; a[lo] = a[j]; a[j] = t;
        _quick(a, lo, j - 1, M);
        _quick(a, j + 1, hi, M);
    }
    public static double quick(int[] a, int M) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1, M);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi, int M) {
        if (hi - lo + 1 < M) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            return;
        }
        int random = StdRandom.uniform(lo, hi + 1);
        int r = a[lo]; a[lo] = a[random]; a[random] = r;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        int t = a[j]; a[j] = a[lo]; a[lo] = t;
        quick(a, lo, j - 1, M);
        quick(a, j + 1, hi, M);
    }
    public static void main(String[] args) {
        int[] Ms = { 10, 20, 50 };
        int[] szs = { 1000, 10000, 100000, 1000000 };
        for (int j = 0, sz = szs[j]; j < szs.length; j++) {
            sz = szs[j];
            int[] a = ints(0, sz - 1);  // 0 ~ sz - 1 不重复元素
//            int[] a = ascendInts(0, sz - 1);  // 已排序元素
//            int[] a = allSameInts(sz - 1, 0); // 全部重复元素
//            int[] a = descendInts(sz - 1, 0); // 降序排列元素
            for (int i = 0, M = Ms[i]; i < Ms.length; i++) {
                M = Ms[i]; 
                int[] copy = intsCopy(a);
                int[] copy1 = intsCopy(a);
                StdOut.printf("规模 : %d M = %d 随机切分 : %.3f  随机化 : %.3f\n",
                        sz, M, quick(copy, M), _quick(copy1, M));
                assert isSorted(copy);
                assert isSorted(copy1);
            }
            StdOut.println();
        }
    }
    // output  随机切分比先随机化数组要好
    /*
     *  规模 : 1000 M = 10 随机切分 : 0.000  随机化 : 0.001
        规模 : 1000 M = 20 随机切分 : 0.000  随机化 : 0.000
        规模 : 1000 M = 50 随机切分 : 0.000  随机化 : 0.000
        
        规模 : 10000 M = 10 随机切分 : 0.002  随机化 : 0.002
        规模 : 10000 M = 20 随机切分 : 0.001  随机化 : 0.001
        规模 : 10000 M = 50 随机切分 : 0.001  随机化 : 0.003
        
        规模 : 100000 M = 10 随机切分 : 0.019  随机化 : 0.024
        规模 : 100000 M = 20 随机切分 : 0.019  随机化 : 0.031
        规模 : 100000 M = 50 随机切分 : 0.007  随机化 : 0.010
        
        规模 : 1000000 M = 10 随机切分 : 0.111  随机化 : 0.138
        规模 : 1000000 M = 20 随机切分 : 0.095  随机化 : 0.124
        规模 : 1000000 M = 50 随机切分 : 0.089  随机化 : 0.117
     */
}

package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_3_28 {
    private static int depth;
    public static int quick(int[] a, int M) {
        depth = 0;
        quick(a, 0, a.length - 1, M);
        return depth;
    }
    private static void quick(int[] a, int lo, int hi, int M) {
        depth++;
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
        int t = a[j]; a[j] = a[lo]; a[lo] = t;
        quick(a, lo, j - 1, M);
        quick(a, j + 1, hi, M);
    }
    public static void main(String[] args) {
        int[] Ms = { 10, 20, 50 };
        int[] szs = { 1000, 10000, 100000, 1000000 };
        for (int j = 0, sz = szs[j]; j < szs.length; j++) {
            sz = szs[j];
            int[] a = ints(0, sz - 1);
            for (int i = 0, M = Ms[i]; i < Ms.length; i++) {
                M = Ms[i]; 
                int[] copy = intsCopy(a);
                StdOut.printf("规模 : %d M = %d 递归平均深度 : %d\n",sz, M, quick(copy, M));
                assert isSorted(copy);
            }
        }
    }
    // output
    /*
     *  规模 : 1000 M = 10 递归平均深度 : 345
        规模 : 1000 M = 20 递归平均深度 : 175
        规模 : 1000 M = 50 递归平均深度 : 75
        规模 : 10000 M = 10 递归平均深度 : 3685
        规模 : 10000 M = 20 递归平均深度 : 1893
        规模 : 10000 M = 50 递归平均深度 : 793
        规模 : 100000 M = 10 递归平均深度 : 36271
        规模 : 100000 M = 20 递归平均深度 : 19075
        规模 : 100000 M = 50 递归平均深度 : 7871
        规模 : 1000000 M = 10 递归平均深度 : 363369
        规模 : 1000000 M = 20 递归平均深度 : 190003
        规模 : 1000000 M = 50 递归平均深度 : 78503
     */
}

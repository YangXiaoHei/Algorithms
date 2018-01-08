package 第三章_符号表;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_3_1_41 {
    public static double interpolation(int[] a, int key) {
        long start = System.nanoTime();
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            double scale = (key - a[lo]) / ((a[hi] - a[lo]) * 1.0);
            int mid = lo + (int)(scale * (hi - lo));
            if (mid > hi || mid < lo) break;
            if      (a[mid] > key) hi = mid - 1;
            else if (a[mid] < key) lo = mid + 1;
            else    { return System.nanoTime() - start; }
        }
        return System.nanoTime() - start;
    }
    public static double binary(int[] a, int key) {
        long start = System.nanoTime();
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if      (a[mid] > key)  hi = mid - 1;
            else if (a[mid] < key) lo = mid + 1;
            else    { return System.nanoTime() - start; }
        }
        return System.nanoTime() - start;
    }
    public static void main(String[] args) {
//        int N = 3000000; // 会让插值大概率比二分快 1 倍
        int N = 60000000; // 会让插值大概率比二分快 2 倍
        int[] a = ints(N, 0, N * 2);
        int key = StdRandom.uniform(N);
        double t1 = interpolation(a, key);
        double t2 = binary(a, key);
        StdOut.printf("二分/插值 = %.3f\n", t2 / t1);
    }
}

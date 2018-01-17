package Ch_2_3_Quicksort;

import static Tool.ArrayGenerator.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_3_25 {
    public static double quick(int[] a, int M) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1, M);
        return timer.elapsedTime();
    }
    private static void quick(int[] a, int lo, int hi, int M) {
        if (hi - lo + 1 < M) {
            insertion(a, lo, hi);
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
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    /*
     * 这个最佳的 M 很难说啊....感觉 5 是最好的，但 JDK 里面用的是 47
     */
    public static void bestM() {
        for (int i = 1000, j = 0; j < 4; j++, i *= 10) {
            int[] b = ints(0, i - 1);
            for (int M = 5; M <= 30; M++) {
                int[] a = intsCopy(b); 
                StdOut.printf("规模 : %d 耗时 : %.3f M = %d\n", i, quick(a, M), M);
                assert isSorted(a);
            }
            StdOut.println("\n\n");
        }
    }
    /*
     * 将 M 从 1 ~ 100 的每个值得到的平均运行时间绘成曲线
     */
    public static void draw() {
        int[] a = ints(0, 400000);
        StdDraw.setXscale(-5, 105);
        StdDraw.setYscale(0, 0.04);
        StdDraw.setPenRadius(.01);
        for (int M = 1; M <= 100; M++) {
            double avrg = 0;
            for (int T = 1; T <= 10; T++) {
                int[] b = intsCopy(a);
                avrg += quick(b, M);
            }
            avrg /= 20;
            StdDraw.point(M, avrg);
        }
    }
    public static void main(String[] args) {
        draw();
    }
}

package Review;

import static Tool.ArrayGenerator.*;
import java.util.*;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Practise_2_1_04 {
    /*
     * 插入排序
     */
    public static double insertion_A(int[] a) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
        }
        return timer.elapsedTime();
    }
    /*
     * 优化版插入排序
     */
    public static double insertion_B(int[] a) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < a.length; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
        return timer.elapsedTime();
    }
    /*
     * 加强优化版插入排序
     */
    public static double insertion_C(int[] a) {
        Stopwatch timer = new Stopwatch();
        int exchs = 0;
        for (int i = a.length - 1; i > 0; i--) {
            if (a[i] < a[i - 1]) {
                int t = a[i];
                a[i] = a[i - 1];
                a[i - 1] = t;
                ++exchs;
            }
        }
        if (exchs == 0) return timer.elapsedTime();
        for (int i = 2; i < a.length; i++) {
            int v = a[i];
            int j = i;
            while (v < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        int[] a = descendInts(80000, 0);
        int[] cp1 = copy(a);
        int[] cp2 = copy(a);
        StdOut.printf("A : %.3f\n", insertion_A(a));
        StdOut.printf("B : %.3f\n", insertion_B(cp1));
        StdOut.printf("C : %.3f\n", insertion_C(cp2));
    }
    // output
    /*
     *  A : 8.011
        B : 6.356
        C : 2.385
     */
}

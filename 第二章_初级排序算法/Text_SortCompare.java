package 第二章_初级排序算法;

import java.util.Arrays;

import edu.princeton.cs.algs4.*;

public class Text_SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Text_Insertion.sort(a);
        if (alg.equals("Selection")) Text_Selection.sort(a);
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        for (int t = 0; t < T; t++) {
            Double[] a = new Double[N];
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            Arrays.sort(a);
            total += time(alg, a);
        }
        return total / T * 1.0;
    }
    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        int N = 100000;
        int T = 1;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("%s / %s = %.1f t1 = %.1f t2 = %.1f\n", alg1, alg2, t1/t2, t1, t2);
    }
    // output
    /*
     * For 1500 random Doubles
         Insertion is  0.7 times faster than Selection

     */
}

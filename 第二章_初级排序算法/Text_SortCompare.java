package 第二章_初级排序算法;

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
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }
    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        int N = 1500;
        int T = 100;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random Doubles\n %s is", N, alg1);
        StdOut.printf("  %.1f times faster than %s\n", t2 / t1, alg2);
    }
    // output
    /*
     * For 1500 random Doubles
         Insertion is  0.7 times faster than Selection

     */
}

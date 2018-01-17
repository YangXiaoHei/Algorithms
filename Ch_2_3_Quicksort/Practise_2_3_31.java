package Ch_2_3_Quicksort;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.*;

public class Practise_2_3_31 {
    public static double quick(double[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void quick(double[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1;
        double v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            double t = a[i]; a[i] = a[j]; a[j] = t;
        }
        double t = a[lo]; a[lo] = a[j]; a[j] = t;
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }
    public static void draw(int N, int T) {
        double[] times = new double[T];
        for (int i = 0; i < T; i++) {
            double[] d = doubles(N);
            times[i] = quick(d);
        }
        double max = 0;
        for (double time : times)
            if (time > max) max = time;
        StdDraw.setCanvasSize(720, 640);
        StdDraw.setXscale(-1, T + 1);
        StdDraw.setYscale(-0.001, max);
        StdDraw.setPenRadius(0.1);
        double w = 1;
        for (int i = 1; i < T; i++) {
            double x = (i + 0.5) * w;
            double y = times[i] / 2;
            double rw = w * 0.5;
            double rh = times[i] / 2;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
    public static void main(String[] args) {
        // 你大概需要等待 3 分钟
        int  T = 1000, N = 1000000;
        StdOut.println(quick(doubles(N)));
        draw(N, T);
    }
}

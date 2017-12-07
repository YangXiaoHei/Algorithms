package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;
import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_33 {
    public static double shell(int N) {
        int[] a = ints(N, 1, N * 10);
        Stopwatch timer = new Stopwatch();
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int t = a[i], j;
                for (j = i - h; j >= 0 && t < a[j]; j -= h)
                    a[j + h] = a[j];
                a[j + h] = t;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(-1, 1);
        double sum = 0;
        for (int i = 1;; i++) {
            StdDraw.setPenRadius(.004);
            StdDraw.setPenColor(Color.GRAY);
            double time = shell(30000);
            StdDraw.point(i, time);
            sum += time;
            StdDraw.setPenRadius(.002);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, sum / i);
        }
    }
}

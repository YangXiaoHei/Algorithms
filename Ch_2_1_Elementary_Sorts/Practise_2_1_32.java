package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_32 {
    public static double insertion(int[] arr) {
        double avrg = 0;
        int[] a = null;
        for (int loop = 0; loop < 3; loop++) {
            a = intsCopy(arr);
            Stopwatch timer = new Stopwatch();
            int N = a.length;
            for (int i = 1; i < N; i++) {
                int t = a[i], j;
                for (j = i - 1; j >= 0 && t < a[j]; j--)
                    a[j + 1] = a[j];
                a[j + 1] = t;
            }
            avrg += timer.elapsedTime();
        }
        return avrg / 3;
    }
    public static double selection(int[] arr) {
        double avrg = 0;
        int[] a = null;
        for (int loop = 0; loop < 3; loop++) {
            a = intsCopy(arr);
            Stopwatch timer = new Stopwatch();
            int N = a.length;
            for (int i = 0; i < N; i++) {
                int min = i;
                for (int j = i + 1; j < N; j++)
                    if (a[j] < a[min]) min = j;
                int t = a[min];
                a[min] = a[i];
                a[i] = t;
            }
            avrg += timer.elapsedTime();
        }
        return avrg / 3;
    }
    public static double shell(int[] arr) {
        double avrg = 0;
        int[] a = null;
        for (int loop = 0; loop < 3; loop++) {
            a = intsCopy(arr);
            Stopwatch timer = new Stopwatch();
            int N = a.length, h = 1;
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
            avrg += timer.elapsedTime();
        }
        return avrg / 3;
    }
    public static void draw(int N) {
        StdDraw.setXscale(N - 10, 80000);
        StdDraw.setYscale(-1, 3);
        StdDraw.setPenRadius(.003);
        for (int i = N; i < 80000; i += 500) {
            int[] a = ints(i, 0, i * 10);
            for (int k = 0; k < 1; k++) {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.point(i, shell(a));
                StdDraw.setPenColor(Color.RED);
                StdDraw.point(i, insertion(a));
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.point(i, selection(a));
            }
        }
    }
    public static void main(String[] args) {
        draw(1000);
    }
}

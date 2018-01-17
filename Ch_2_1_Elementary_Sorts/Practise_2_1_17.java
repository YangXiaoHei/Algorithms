package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import java.awt.Color;
import java.util.concurrent.TimeUnit;
import edu.princeton.cs.algs4.*;

public class Practise_2_1_17 {
    public static Double[] maxComparesSequence = 
            parseDoublesString("48, 46, 54, 97, 83, 69, 76, 25, 10, 5, 87, 12, 21, 99, 61, 33, 30, 47, 57, 4, 36, 42, 98, 66, 100, 17, 94, 81, 11, 77, 24, 89, 73, 53, 38, 7, 29, 8, 27, 23, 56, 70, 60, 85, 39, 65, 9, 75, 15, 67, 64, 22, 51, 82, 43, 3, 37, 91, 45, 13, 34, 63, 74, 71, 95, 55, 80, 92, 2, 19, 62, 40, 84, 41, 50, 88, 86, 59, 28, 44, 72, 68, 14, 35, 93, 26, 18, 78, 31, 58, 96, 6, 1, 90, 49, 16, 52, 79, 32, 20");
    public static void draw(Double[] a,boolean mark, int markIndex1, int markIndex2) {
        Double max = Double.MIN_VALUE;
        for (Double d : a)
            if (d.compareTo(max) > 0)
                max = d;
        StdDraw.clear();
        StdDraw.setXscale(-1, a.length);
        StdDraw.setYscale(-0.5, max.doubleValue() + 0.5);
        for (int i = 0; i < a.length; i++) {
            double x = i;
            double y = a[i] / 2.0;
            double rw = (1 - 0.3) / 2;
            double rh = a[i] / 2.0;
            if (mark) {
                if (i == markIndex1 || i == markIndex2)
                    StdDraw.setPenColor(Color.RED);
                else
                    StdDraw.setPenColor(Color.BLACK);
            } else
                StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(x, y, rw, rh); 
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {}
    }
    public static void draw(Double[] a, boolean mark, int markIndex1, int markIndex2, int curmin) {
        Double max = Double.MIN_VALUE;
        for (Double d : a)
            if (d.compareTo(max) > 0)
                max = d;
        StdDraw.clear();
        StdDraw.setXscale(-1, a.length);
        StdDraw.setYscale(-0.5, max.doubleValue() + 0.5);
        for (int i = 0; i < a.length; i++) {
            double x = i;
            double y = a[i] / 2.0;
            double rw = (1 - 0.3) / 2;
            double rh = a[i] / 2.0;
            if (mark) {
                if (i == markIndex1 || i == markIndex2)
                    StdDraw.setPenColor(Color.RED);
                else if (i == curmin)
                    StdDraw.setPenColor(Color.BLUE);
                else
                    StdDraw.setPenColor(Color.BLACK);
            } else
                StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(x, y, rw, rh);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {}
    }
    public static void selection(Double[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++)  {
                draw(a, true, i, j, min);
                if (a[j] < a[min]) 
                    min = j;
            }
            draw(a, true, i, min);
            Double t = a[i];
            a[i] = a[min];
            a[min] = t;
            draw(a, true, i, min);
        }
        draw(a, false, 0, 0);
    }
    public static void insertion(Double[] a) {
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && a[j].compareTo(a[j - 1]) < 0; j--) {
                draw(a, true, j, j - 1);
                Double t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
                draw(a, true, j, j - 1);
            }
        draw(a, false, 0, 0);
    }
    public static void shell(Double[] a) {
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) 
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    draw(a, true, j, j - h);
                    Double t = a[j];
                    a[j] = a[j - h];
                    a[j - h] = t;
                    draw(a, true, j, j - h);
                }
            h /= 3;
        }
        draw(a, false, 0, 0);
    }
    public static void main(String[] args) {
        Double[] a = Doubles(50);
//        insertion(a);
        shell(a);
//        selection(a);
//        shell(maxComparesSequence);
    }
}

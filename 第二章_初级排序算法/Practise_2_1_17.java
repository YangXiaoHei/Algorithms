package 第二章_初级排序算法;

import static 第二章_初级排序算法.Text_Array.*;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.*;

public class Practise_2_1_17 {
    public static void draw(Double[] a, int markIndex1, int markIndex2) {
        StdDraw.clear();
        StdDraw.setXscale(-1, a.length);
        StdDraw.setYscale(-0.5, 1.5);
        for (int i = 0; i < a.length; i++) {
            double x = i;
            double y = a[i] / 2.0;
            double rw = (1 - 0.3) / 2;
            double rh = a[i] / 2.0;
            if (i == markIndex1 || i == markIndex2)
                StdDraw.setPenColor(Color.RED);
            else
                StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(x, y, rw, rh);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (Exception e) {}
    }
    public static void draw(Double[] a, int markIndex1, int markIndex2, int curmin) {
        StdDraw.clear();
        StdDraw.setXscale(-1, a.length);
        StdDraw.setYscale(-0.5, 1.5);
        for (int i = 0; i < a.length; i++) {
            double x = i;
            double y = a[i] / 2.0;
            double rw = (1 - 0.3) / 2;
            double rh = a[i] / 2.0;
            if (i == markIndex1 || i == markIndex2)
                StdDraw.setPenColor(Color.RED);
            else if (i == curmin)
                StdDraw.setPenColor(Color.BLUE);
            else
                StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(x, y, rw, rh);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (Exception e) {}
    }
    public static void selection(Double[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++)  {
                draw(a, i, j, min);
                if (a[j] < a[min]) 
                    min = j;
            }
            draw(a, i, min);
            Double t = a[i];
            a[i] = a[min];
            a[min] = t;
            draw(a, i, min);
        }
    }
    public static void insertion(Double[] a) {
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && a[j].compareTo(a[j - 1]) < 0; j--) {
                draw(a,j, j - 1);
                Double t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
                draw(a,j, j - 1);
            }
    }
    public static void shell(Double[] a) {
        int N = a.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) 
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    draw(a, j, j - h);
                    Double t = a[j];
                    a[j] = a[j - h];
                    a[j - h] = t;
                    draw(a, j, j - h);
                }
            h /= 3;
        }
    }
    public static void main(String[] args) {
        Double[] a = DoubleRandom_size(50);
//        insertion(a);
        shell(a);
//        selection(a);
    }
}

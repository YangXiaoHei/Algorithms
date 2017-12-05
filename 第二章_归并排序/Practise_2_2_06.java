package 第二章_归并排序;

import static 第二章_初级排序算法.Text_Array.*;

import java.awt.Color;

import edu.princeton.cs.algs4.*;

public class Practise_2_2_06 {
    /*
     * 未优化的归并排序
     */
    private static int accessTimes;
    private static int[] aux;
    private static boolean less_b(int a, int b) {
        accessTimes += 2;
        return a < b;
    }
    public static void merge(int[] a) {
        aux = new int[a.length];
        merge(a, 0, a.length - 1);
    }
    private static void merge(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge(a, lo, mid);
        merge(a, mid + 1, hi);
        mergeSort(a, lo, mid, hi);
    }
    private static void mergeSort(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            accessTimes += 2;
        }
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                 { a[k] = aux[j++]; accessTimes += 2;  }
            else if (j > hi)                  { a[k] = aux[i++]; accessTimes += 2;  } 
            else if (less_b(aux[j], aux[i]))  { a[k] = aux[j++]; accessTimes += 2;  }
            else                              { a[k] = aux[i++]; accessTimes += 2;  }
        }
    }
    public static void draw() {
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(-1000, 28000);
        for (int i = 1; i < 512; i++) {
            int[] arr = intRandom_size(i);
            merge(arr);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, 6 * i * Math.log(i) / Math.log(2)); // 红线理论值
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(i, accessTimes);  // 黑线实验值
            accessTimes = 0;
        }
    }
    public static void main(String[] args) {
        draw();
    }
    // output
    /*
     * execute to see draw...
     */
}

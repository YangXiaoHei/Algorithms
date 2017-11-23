package 第二章_初级排序算法;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Text_Selection {
    public static void sort(Comparable<?>[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) 
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
        }
    }
    @SuppressWarnings("unchecked")
    private static  boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }
    private static <T> boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }
   
    public static void main(String[] args) {
        Integer[] arr = sourceArr(10);
        sort(arr);
        show(arr);
    }
}

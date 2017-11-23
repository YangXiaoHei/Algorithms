package 第二章_初级排序算法;

import edu.princeton.cs.algs4.StdOut;

public class Text_Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        Integer[] arr = Text_RandomArray.sourceArr(100);
        sort(arr);
        for (int i = 0; i < arr.length; i++) 
            StdOut.print(arr[i] + " ");
        StdOut.println();
    }
}

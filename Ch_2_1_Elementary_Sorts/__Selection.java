package Ch_2_1_Elementary_Sorts;

import static Ch_2_1_Elementary_Sorts.__Array.*;

import edu.princeton.cs.algs4.StdOut;

/*
 * 选择排序 :
 * 
 * 假设 i = 0 是 1~N-1 中最小的元素，在 1~N-1 中依次与 i = 0 比较，找出一个最小者，并与 i = 0 交换位置
 * 假设 i = 1 是 2~N-1 中最小的元素，在 2~N-1 中依次与 i = 1 比较，找出一个最小者，并与 i = 1 交换位置
 * 假设 i = 2 是 3~N-1 中最小的元素，在 3~N-1 中依次与 i = 2 比较，找出一个最小者，并与 i = 2 交换位置
 * ...
 * 当 i = N-1 时，排序终止
 * 
 * 可以看出，选择排序的比较总次数为 N-1 + N-2 + N-3 + ... + 1 = N(N-1)/2 ~ N^2/2
 * 
 * 元素交换的次数为 N 次
 * 
 * 可以看出 选择排序是一个增长率为平方级别的算法
 * 
 */
public class __Selection {
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
        Integer[] arr = Integers(10);
        sort(arr);
        show(arr);
    }
}

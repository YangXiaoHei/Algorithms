package 第三章_符号表;

import edu.princeton.cs.algs4.*;
import static 第二章_初级排序算法.Text_Array.*;

public class Practise_3_1_40 {
    public static long binarySearch(int[] a, int key) {
        long start = System.nanoTime();
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if      (a[mid] > key) hi = mid - 1;
            else if (a[mid] < key) lo = mid + 1;
            else    return System.nanoTime() - start;
        }
        return System.nanoTime() - start;
    }
    public static long sequentialSearch(int[] a, int key) {
        long start = System.nanoTime();
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return System.nanoTime() - start;
        return System.nanoTime() - start;
    }
    public static void main(String[] args) {
        for (int i = 100000, j = 0; j < 10000; j++, i += 10000) {
            int[] a = ascendInts(0, i - 1);
            int key = i - 1;
            double t1 = binarySearch(a, key);
            double t2 = sequentialSearch(a, key);
            if (t2 / t1 > 1000) {
                StdOut.printf("二分查找比顺序查找快 1000 倍的 N = %d  t2/t1 = %.3f\n", i, t2 / t1);
            }
            if (t2 / t1 > 10000) {
                StdOut.printf("二分查找比顺序查找快 10000 倍的 N = %d\n", i);
                break;
            }
        }
    }
}

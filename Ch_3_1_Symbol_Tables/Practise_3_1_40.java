package Ch_3_1_Symbol_Tables;

import static Ch_2_1_Elementary_Sorts.Text_Array.*;

import edu.princeton.cs.algs4.*;

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
//            int N = 350000; 快1000倍
            int N = 60000000; // 暂时没有找到快 10000 倍的 N，在此之前已经 Java Heap space limit 异常了..
            int[] a = ascendInts(0, N - 1);
            int key = N - 1;
            double t1 = binarySearch(a, key);
            double t2 = sequentialSearch(a, key);
            StdOut.println(t2 / t1);
            if (t2 / t1 > 1000) {
                StdOut.printf("二分查找比顺序查找快 1000 倍的 N = %d  t2/t1 = %.3f\n", N, t2 / t1);
            }
            if (t2 / t1 > 10000) {
                StdOut.printf("二分查找比顺序查找快 10000 倍的 N = %d\n", N);
            }
    }
}

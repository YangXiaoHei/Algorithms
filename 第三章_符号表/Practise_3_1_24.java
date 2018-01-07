package 第三章_符号表;

import static 第二章_初级排序算法.Text_Array.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_3_1_24 {
    public static int interpolationSearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            double scale = (key - a[lo]) / ((a[hi] - a[lo]) * 1.0);
            int mid = lo +  (int)(scale * (hi - lo));
            if (mid > hi || mid < lo) break;
            int cmp = compare(a[mid], key);
            if (cmp > 0) hi = mid - 1;
            else if (cmp < 0) lo = mid + 1;
            else    return mid;
        }
        return -1;
    }
    public static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int cmp = compare(a[mid], key);
            if      (cmp > 0) hi = mid - 1;
            else if (cmp < 0) lo = mid + 1;
            else    return mid;
        }
        return -1;
    }
    public static int compares;
    public static int compare(int i, int j) {
        compares++;
        return i - j;
    }
    public static void main(String[] args) {
        int N = 40000000;
        int[] a = ints(0, N - 1);
        Arrays.sort(a);
        int k = StdRandom.uniform(0, N);
        
        Stopwatch timer = new Stopwatch();
        int rel1 = interpolationSearch(a, k);
        int cmp1 = compares;
        double t1 = timer.elapsedTime();
        
        compares = 0;
        
        timer = new Stopwatch();
        int rel2 = binarySearch(a, k);
        int cmp2 = compares;
        double t2 = timer.elapsedTime();
        StdOut.printf("%d 插值法查找结果为 : %d 耗时 : %.3f 比较次数 : %d\n", k, rel1, t1, cmp1);
        StdOut.printf("%d 二分法查找结果为 : %d 耗时 : %.3f 比较次数 : %d\n", k, rel2, t2, cmp2);
    }
    // output
    /*
     *  14025098 插值法查找结果为 : 14025098 耗时 : 0.000 比较次数 : 1
        14025098 二分法查找结果为 : 14025098 耗时 : 0.000 比较次数 : 23


     */
}
